package com.literalura.service;

import com.literalura.dto.AutorDTO;
import com.literalura.dto.GutendexResponseDTO;
import com.literalura.dto.LivroDTO;
import com.literalura.model.Autor;
import com.literalura.model.Livro;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LiteraluraService {
    
    @Autowired
    private ConsumoAPI consumoAPI;
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    @Transactional
    public void buscarERegistrarLivro(String titulo) {
        try {
            GutendexResponseDTO response = consumoAPI.buscarLivrosPorTitulo(titulo);
            
            if (response.results() == null || response.results().isEmpty()) {
                System.out.println("\nNenhum livro encontrado com esse título.");
                return;
            }
            
            LivroDTO livroDTO = response.results().get(0);
            
            // Verificar se o livro já existe
            Optional<Livro> livroExistente = livroRepository.findByTitulo(livroDTO.title());
            if (livroExistente.isPresent()) {
                System.out.println("\nEste livro já está registrado no banco de dados!");
                System.out.println(livroExistente.get());
                return;
            }
            
            // Processar autor
            Autor autor = null;
            if (livroDTO.authors() != null && !livroDTO.authors().isEmpty()) {
                AutorDTO autorDTO = livroDTO.authors().get(0);
                
                // Verificar se o autor já existe
                Optional<Autor> autorExistente = autorRepository.findByNome(autorDTO.name());
                if (autorExistente.isPresent()) {
                    autor = autorExistente.get();
                } else {
                    autor = new Autor(
                            autorDTO.name(),
                            autorDTO.birthYear(),
                            autorDTO.deathYear()
                    );
                    autor = autorRepository.save(autor);
                }
            }
            
            // Criar e salvar o livro
            String idioma = livroDTO.languages() != null && !livroDTO.languages().isEmpty() 
                    ? livroDTO.languages().get(0) 
                    : "desconhecido";
            
            Livro livro = new Livro(
                    livroDTO.title(),
                    autor,
                    idioma,
                    livroDTO.downloadCount()
            );
            
            livroRepository.save(livro);
            
            System.out.println("\nLivro registrado com sucesso!");
            System.out.println(livro);
            
        } catch (DataIntegrityViolationException e) {
            System.out.println("\nErro: Este livro já está registrado no banco de dados!");
        } catch (Exception e) {
            System.out.println("\nErro ao buscar/registrar livro: " + e.getMessage());
        }
    }
    
    public void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAllByOrderByTituloAsc();
        
        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro registrado ainda.");
            return;
        }
        
        System.out.println("\n========== LIVROS REGISTRADOS ==========");
        livros.forEach(System.out::println);
    }
    
    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAllByOrderByNomeAsc();
        
        if (autores.isEmpty()) {
            System.out.println("\nNenhum autor registrado ainda.");
            return;
        }
        
        System.out.println("\n========== AUTORES REGISTRADOS ==========");
        autores.forEach(System.out::println);
    }
    
    public void listarAutoresVivosNoAno(int ano) {
        List<Autor> autores = autorRepository.findAutoresVivosNoAno(ano);
        
        if (autores.isEmpty()) {
            System.out.println("\nNenhum autor encontrado vivo no ano " + ano);
            return;
        }
        
        System.out.println("\n========== AUTORES VIVOS EM " + ano + " ==========");
        autores.forEach(System.out::println);
    }
    
    public void exibirQuantidadeLivrosPorIdioma(String idioma) {
        Long quantidade = livroRepository.countByIdioma(idioma);
        
        System.out.println("\n========== ESTATÍSTICAS ==========");
        System.out.println("Quantidade de livros em " + obterNomeIdioma(idioma) + ": " + quantidade);
        
        if (quantidade > 0) {
            System.out.println("\nLivros encontrados:");
            List<Livro> livros = livroRepository.findByIdioma(idioma);
            livros.forEach(l -> System.out.println("  - " + l.getTitulo()));
        }
    }
    
    private String obterNomeIdioma(String codigo) {
        return switch (codigo.toLowerCase()) {
            case "en" -> "Inglês";
            case "pt" -> "Português";
            case "es" -> "Espanhol";
            case "fr" -> "Francês";
            case "de" -> "Alemão";
            case "it" -> "Italiano";
            default -> codigo.toUpperCase();
        };
    }
}
