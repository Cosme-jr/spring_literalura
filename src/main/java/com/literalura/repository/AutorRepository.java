// src/main/java/com/literalura/repository/AutorRepository.java
package com.literalura.repository;

import com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional; // Importe a classe Optional

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome); // Novo método para buscar por nome

    @Query("SELECT a FROM Autor a WHERE a.anoDeFalecimento > :ano OR a.anoDeFalecimento IS NULL")
    List<Autor> findByAnoDeFalecimentoIsNullOrAnoDeFalecimentoGreaterThan(Integer ano);
}