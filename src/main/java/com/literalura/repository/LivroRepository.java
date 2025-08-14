package com.literalura.repository;

import com.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(String idioma);

    boolean existsByTitulo(String titulo);

    @Query("SELECT l FROM Livro l JOIN FETCH l.autor")
    List<Livro> findAllComAutor();
}