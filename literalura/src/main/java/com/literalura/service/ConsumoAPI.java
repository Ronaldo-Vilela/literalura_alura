package com.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.dto.GutendexResponseDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class ConsumoAPI {
    
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String BASE_URL = "https://gutendex.com/books/";
    
    public String obterDados(String endereco) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao buscar dados da API: " + e.getMessage());
        }
    }
    
    public GutendexResponseDTO buscarLivrosPorTitulo(String titulo) {
        String tituloEncoded = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        String url = BASE_URL + "?search=" + tituloEncoded;
        
        String json = obterDados(url);
        
        try {
            return mapper.readValue(json, GutendexResponseDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter JSON: " + e.getMessage());
        }
    }
}
