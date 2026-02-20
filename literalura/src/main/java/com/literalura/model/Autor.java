package com.literalura.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String nome;
    
    @Column(name = "ano_nascimento")
    private Integer anoNascimento;
    
    @Column(name = "ano_falecimento")
    private Integer anoFalecimento;
    
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();
    
    // Construtores
    public Autor() {}
    
    public Autor(String nome, Integer anoNascimento, Integer anoFalecimento) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Integer getAnoNascimento() {
        return anoNascimento;
    }
    
    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
    
    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }
    
    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }
    
    public List<Livro> getLivros() {
        return livros;
    }
    
    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    
    // Método auxiliar para verificar se o autor estava vivo em determinado ano
    public boolean estaVivo(int ano) {
        if (anoNascimento == null) return false;
        if (anoFalecimento == null) return ano >= anoNascimento;
        return ano >= anoNascimento && ano <= anoFalecimento;
    }
    
    @Override
    public String toString() {
        return String.format("""
                
                ----- AUTOR -----
                Nome: %s
                Ano de Nascimento: %s
                Ano de Falecimento: %s
                Livros: %s
                ----------------
                """, 
                nome, 
                anoNascimento != null ? anoNascimento : "Desconhecido",
                anoFalecimento != null ? anoFalecimento : "Vivo ou Desconhecido",
                livros.stream().map(Livro::getTitulo).toList()
        );
    }
}
