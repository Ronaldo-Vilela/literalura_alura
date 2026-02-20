package com.literalura;

import com.literalura.service.LiteraluraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
    
    @Autowired
    private LiteraluraService service;
    
    private final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        exibirMenu();
    }
    
    private void exibirMenu() {
        int opcao = -1;
        
        while (opcao != 0) {
            System.out.println("\n");
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║         LITERALURA - CATÁLOGO              ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.println();
            System.out.println("1 - Buscar livro pelo título");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores registrados");
            System.out.println("4 - Listar autores vivos em determinado ano");
            System.out.println("5 - Listar livros por idioma");
            System.out.println("0 - Sair");
            System.out.println();
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1 -> buscarLivroPorTitulo();
                    case 2 -> service.listarLivrosRegistrados();
                    case 3 -> service.listarAutoresRegistrados();
                    case 4 -> listarAutoresVivosPorAno();
                    case 5 -> listarLivrosPorIdioma();
                    case 0 -> System.out.println("\nEncerrando aplicação... Até logo!");
                    default -> System.out.println("\nOpção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nPor favor, digite um número válido!");
                opcao = -1;
            } catch (Exception e) {
                System.out.println("\nErro: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    private void buscarLivroPorTitulo() {
        System.out.print("\nDigite o título do livro: ");
        String titulo = scanner.nextLine();
        
        if (titulo.isBlank()) {
            System.out.println("Título não pode estar vazio!");
            return;
        }
        
        service.buscarERegistrarLivro(titulo);
    }
    
    private void listarAutoresVivosPorAno() {
        System.out.print("\nDigite o ano: ");
        try {
            int ano = Integer.parseInt(scanner.nextLine());
            service.listarAutoresVivosNoAno(ano);
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido!");
        }
    }
    
    private void listarLivrosPorIdioma() {
        System.out.println("\nIdiomas disponíveis:");
        System.out.println("en - Inglês");
        System.out.println("pt - Português");
        System.out.println("es - Espanhol");
        System.out.println("fr - Francês");
        System.out.println("de - Alemão");
        System.out.println("it - Italiano");
        System.out.print("\nDigite o código do idioma: ");
        
        String idioma = scanner.nextLine().toLowerCase();
        
        if (idioma.isBlank()) {
            System.out.println("Idioma não pode estar vazio!");
            return;
        }
        
        service.exibirQuantidadeLivrosPorIdioma(idioma);
    }
}
