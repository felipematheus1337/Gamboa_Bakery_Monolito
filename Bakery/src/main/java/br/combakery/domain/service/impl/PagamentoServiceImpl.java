package br.combakery.domain.service.impl;


import br.combakery.domain.repository.PagamentoRepository;
import br.combakery.domain.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoRepository repository;

    private final ModelMapper mapper;


}
