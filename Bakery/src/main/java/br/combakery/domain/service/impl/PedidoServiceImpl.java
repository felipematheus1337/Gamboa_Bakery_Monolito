package br.combakery.domain.service.impl;


import br.combakery.api.dto.PedidoDTO;
import br.combakery.api.dto.StatusDTO;
import br.combakery.domain.entity.Endereco;
import br.combakery.domain.entity.Pedido;
import br.combakery.domain.entity.Status;
import br.combakery.domain.entity.StatusPedido;
import br.combakery.domain.repository.EnderecoRepository;
import br.combakery.domain.repository.PedidoRepository;
import br.combakery.domain.service.PedidoService;
import br.combakery.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final ModelMapper modelMapper;
    private final EnderecoRepository enderecoRepository;

    @Override
    public List<PedidoDTO> obterTodos() {
        return repository.findAll().stream()
                .map(p -> modelMapper.map(p, PedidoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PedidoDTO obterPorId(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Id not found"));

        return modelMapper.map(pedido, PedidoDTO.class);
    }

    @Override
    public PedidoDTO criarPedido(PedidoDTO dto) {
        Pedido pedido = modelMapper.map(dto, Pedido.class);
        Endereco end = enderecoRepository.save(dto.getEndereco());
        pedido.setDataHora(LocalDateTime.now());
        pedido.setEndereco(end);
        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.getItens().forEach(item -> item.setPedido(pedido));
        Pedido salvo = repository.save(pedido);

        return modelMapper.map(salvo, PedidoDTO.class);
    }

    @Override
    public PedidoDTO atualizaStatus(Long id, StatusDTO dto) {
        Pedido pedido = repository.porIdComItens(id);

        if (pedido == null) {
            throw new EntityNotFoundException();
        }

        pedido.setStatus(dto.getStatus());
        repository.atualizaStatus(dto.getStatus(), pedido);
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    @Override
    public void aprovaPagamentoPedido(Long id) {
        Pedido pedido = repository.porIdComItens(id);

        if (pedido == null) {
            throw new EntityNotFoundException();
        }

        pedido.setStatus(StatusPedido.PAGO);
        repository.atualizaStatus(StatusPedido.PAGO, pedido);
    }
}
