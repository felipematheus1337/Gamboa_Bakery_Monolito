package br.combakery.api.controller;


import br.combakery.api.dto.PedidoDTO;
import br.combakery.api.dto.StatusDTO;
import br.combakery.domain.service.impl.PedidoServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoServiceImpl service;


    @GetMapping()
    public List<PedidoDTO> listarTodos() {
        return service.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> listarPorId(@PathVariable @NotNull Long id) {
        PedidoDTO dto = service.obterPorId(id);

        return  ResponseEntity.ok(dto);
    }

    @GetMapping("/porta")
    public String retornaPorta(@Value("${local.server.port}") String porta){
        return String.format("Requisição respondida pela instância executando na porta %s", porta);
    }

    @PostMapping()
    public ResponseEntity<PedidoDTO> realizaPedido(@RequestBody @Valid PedidoDTO dto, UriComponentsBuilder uriBuilder) {
        PedidoDTO pedidoRealizado = service.criarPedido(dto);

        URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoRealizado.getId()).toUri();

        return ResponseEntity.created(endereco).body(pedidoRealizado);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoDTO> atualizaStatus(@PathVariable Long id, @RequestBody StatusDTO status){
        PedidoDTO dto = service.atualizaStatus(id, status);

        return ResponseEntity.ok(dto);
    }
    
}
