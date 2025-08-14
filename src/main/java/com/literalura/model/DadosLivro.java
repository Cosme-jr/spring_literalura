package com.literalura.model;

// src/main/java/com/literalura/model/DadosLivro.java


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("id") Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DadosAutor> autores,
        @JsonAlias("translators") List<DadosAutor> tradutores, // Assumindo que tradutores têm a mesma estrutura de autor
        @JsonAlias("subjects") List<String> assuntos,
        @JsonAlias("bookshelves") List<String> estantes,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("copyright") boolean copyright,
        @JsonAlias("media_type") String tipoMidia,
        @JsonAlias("formats") DadosFormatos formatos,
        @JsonAlias("download_count") Integer numeroDeDownloads
) {}