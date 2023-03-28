package br.combakery.domain.service;

import br.combakery.api.dto.Media;
import br.combakery.domain.entity.Avaliacao;

import java.util.List;

public interface AvaliacaoService {


    Avaliacao criarAvaliacao(Avaliacao avaliacao);

    List<Avaliacao> listarAvaliacoesDeUmPedido(Long pedidoId);

    Media getMedia(Long pedidoId);
}
