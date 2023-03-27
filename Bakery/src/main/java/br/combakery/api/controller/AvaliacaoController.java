package br.combakery.api.controller;


import br.combakery.api.dto.AvaliacaoDTO;
import br.combakery.domain.entity.Avaliacao;
import br.combakery.domain.service.impl.AvaliacaoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/avaliacao")
@RequiredArgsConstructor
public class AvaliacaoController {


    private final RabbitTemplate rabbitTemplate;
    private final AvaliacaoServiceImpl service;

    private final ModelMapper mapper;


    @PostMapping
    public ResponseEntity<Object> avaliarPedido(@RequestBody Avaliacao avaliacao, UriComponentsBuilder uriBuilder) {
        AvaliacaoDTO avaliacaoDTO = mapper.map(service.criarAvaliacao(avaliacao), AvaliacaoDTO.class);

        URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(avaliacaoDTO.getPedidoId()).toUri();

        rabbitTemplate.convertAndSend("pagamentos-ex","",avaliacaoDTO);

        return ResponseEntity.created(endereco).body(avaliacaoDTO);
    }


}
