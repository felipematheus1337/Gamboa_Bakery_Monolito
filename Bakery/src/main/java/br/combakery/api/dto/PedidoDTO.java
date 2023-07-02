package br.combakery.api.dto;

import br.combakery.domain.entity.Endereco;
import br.combakery.domain.entity.Status;
import br.combakery.domain.entity.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long id;
    private LocalDateTime dataHora;
    private StatusPedido status;
    private List<ItemDoPedidoDTO> itens = new ArrayList<>();
    private Endereco endereco;
}
