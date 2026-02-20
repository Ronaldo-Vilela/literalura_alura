package com.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(
        Long id,
        String title,
        
        List<AutorDTO> authors,
        
        List<String> languages,
        
        @JsonAlias("download_count")
        Integer downloadCount
) {}
