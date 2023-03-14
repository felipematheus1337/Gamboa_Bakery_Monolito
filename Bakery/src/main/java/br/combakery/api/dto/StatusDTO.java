package br.combakery.api.dto;


import br.combakery.domain.entity.Status;
import br.combakery.domain.entity.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    private StatusPedido status;
}
