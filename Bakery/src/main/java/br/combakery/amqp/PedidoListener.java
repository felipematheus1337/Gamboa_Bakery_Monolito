package br.combakery.amqp;

import br.combakery.api.dto.PagamentoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    @RabbitListener(queues = "pedidos-queue")
    public void recebeMensagem(PagamentoDTO pagamentoDTO) {

        if (pagamentoDTO.getNumero().equals("0000")) {
            throw new RuntimeException("Número de cartão inválido!");
        }

        System.out.println(pagamentoDTO.toString());
    }
}
