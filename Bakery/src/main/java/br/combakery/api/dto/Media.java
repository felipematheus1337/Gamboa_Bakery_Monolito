package br.combakery.api.dto;

import br.combakery.api.converter.MediaConverter;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media {
    private Long pedidoId;
    private Double media;
    private Long totalAvaliacoes;



    @Convert(converter = MediaConverter.class)
    public static class Converter {}
}