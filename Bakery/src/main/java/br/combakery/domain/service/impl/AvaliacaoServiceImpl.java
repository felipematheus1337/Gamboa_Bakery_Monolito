package br.combakery.domain.service.impl;

import br.combakery.api.dto.Media;
import br.combakery.domain.entity.Avaliacao;
import br.combakery.domain.repository.AvaliacaoRepository;
import br.combakery.domain.repository.PedidoRepository;
import br.combakery.domain.service.AvaliacaoService;
import br.combakery.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Override
    public List<Avaliacao> listarAvaliacoesDeUmPedido(Long pedidoId) {
        var avaliacoes = repository.findByPedidoId(pedidoId);

        if(avaliacoes.isEmpty()) {
            throw new BusinessException("Avaliações não encontradas!");
        }

        return avaliacoes.get();
    }

    @Override
    public Media getMedia(Long pedidoId) {
        if (repository.findByPedidoId(pedidoId).isEmpty()) {
            throw new BusinessException("Pedido não encontrado!");
        }
        return repository.getMedia(pedidoId);

    }
}
