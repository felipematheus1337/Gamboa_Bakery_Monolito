package br.combakery.domain.service;

import br.combakery.api.dto.PedidoDTO;
import br.combakery.api.dto.StatusDTO;

import java.util.List;

public interface PedidoService {

    List<PedidoDTO> obterTodos();

    PedidoDTO obterPorId(Long id);


    PedidoDTO criarPedido(PedidoDTO dto);

    PedidoDTO atualizaStatus(Long id, StatusDTO dto);

    void aprovaPagamentoPedido(Long id);
}
