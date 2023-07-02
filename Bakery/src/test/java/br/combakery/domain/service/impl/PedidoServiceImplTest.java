package br.combakery.domain.service.impl;

import br.combakery.api.controller.PagamentoController;
import br.combakery.api.controller.PedidoController;
import br.combakery.domain.entity.ItemDoPedido;
import br.combakery.domain.entity.Pedido;
import br.combakery.domain.entity.Status;
import br.combakery.domain.entity.StatusPedido;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class PedidoServiceImplTest {


    @InjectMocks
    private PagamentoController pagamentoController;

    @Mock
    private PedidoServiceImpl service;

    @Mock
    private ModelMapper mapper;


    private static final Long ID = 1L;

    private static final LocalDateTime dataDoPedido = LocalDateTime.now();

    private static final StatusPedido statusDoPedidoAntesDeAprovadoOPagamento = StatusPedido.REALIZADO;


    private Pedido pedido;



    @BeforeEach
    void setUp() {
        this.carregarPedido();
    }


    @Test
    void aprovaPagamentoPedido() {
        Mockito.doNothing().when(service).aprovaPagamentoPedido(ID);

        pagamentoController.confirmarPagamento(ID);

        Mockito.verify(service, Mockito.times(1)).aprovaPagamentoPedido(ID);
        assertEquals(StatusPedido.PAGO, pedido.getStatus());
    }




    private void carregarPedido() {
        pedido = new Pedido(ID,dataDoPedido, statusDoPedidoAntesDeAprovadoOPagamento);

    }



}