// src/main/java/com/literalura/model/DadosFormatos.java

package com.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosFormatos() {
    // Não precisamos de campos específicos aqui, pois usaremos o ObjectMapper
    // para ignorar as propriedades desconhecidas.
}