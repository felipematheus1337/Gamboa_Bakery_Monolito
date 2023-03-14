package br.combakery.domain.service;

import br.combakery.api.dto.PagamentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagamentoService {

    Page<PagamentoDTO> obterTodos(Pageable paginacao);

    PagamentoDTO obterPorId(Long id);

    PagamentoDTO criarPagamento(PagamentoDTO dto);

    PagamentoDTO atualizarPagamento(Long id, PagamentoDTO dto);

    void excluirPagamento(Long id);


    void alteraStatus(Long id);






}
