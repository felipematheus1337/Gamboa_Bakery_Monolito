package br.combakery.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvaliacaoDTO {

    public Long id;
    public int nota;
    public String descricao;
    public Long pedidoId;
}
