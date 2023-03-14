package br.combakery.api.dto;

import br.combakery.domain.entity.Status;
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
    private Status status;
    private List<ItemDoPedidoDTO> itens = new ArrayList<>();
}
