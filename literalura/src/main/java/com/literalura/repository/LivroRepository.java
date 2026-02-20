package com.literalura.repository;

import com.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    Optional<Livro> findByTitulo(String titulo);
    
    List<Livro> findAllByOrderByTituloAsc();
    
    @Query("SELECT COUNT(l) FROM Livro l WHERE l.idioma = :idioma")
    Long countByIdioma(@Param("idioma") String idioma);
    
    List<Livro> findByIdioma(String idioma);
}
