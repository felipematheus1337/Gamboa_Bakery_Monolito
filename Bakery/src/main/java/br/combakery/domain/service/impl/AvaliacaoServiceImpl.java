package br.combakery.domain.service.impl;

import br.combakery.domain.entity.Avaliacao;
import br.combakery.domain.repository.AvaliacaoRepository;
import br.combakery.domain.repository.PedidoRepository;
import br.combakery.domain.service.AvaliacaoService;
import br.combakery.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final AvaliacaoRepository repository;
    private final PedidoRepository pedidoRepository;



    @Override
    public Avaliacao criarAvaliacao(Avaliacao avaliacao) {

        if(pedidoRepository.findById(avaliacao.getPedido().getId()).isEmpty()) {
            throw new BusinessException("Pedido inexistente!");
        }
        return repository.save(avaliacao);
    }
}
