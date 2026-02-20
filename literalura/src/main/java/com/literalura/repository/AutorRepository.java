package com.literalura.repository;

import com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    
    Optional<Autor> findByNome(String nome);
    
    @Query("SELECT a FROM Autor a WHERE " +
           "(a.anoNascimento IS NOT NULL AND a.anoNascimento <= :ano) AND " +
           "(a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)")
    List<Autor> findAutoresVivosNoAno(@Param("ano") int ano);
    
    List<Autor> findAllByOrderByNomeAsc();
}
