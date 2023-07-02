package br.combakery.amqp;

import br.combakery.api.dto.PagamentoDTO;
import br.combakery.domain.entity.Pagamento;
import br.combakery.domain.repository.PagamentoRepository;
import br.combakery.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoListener {

    private final PagamentoRepository repository;
    private final ModelMapper mapper;

    @RabbitListener(queues = "pedidos-queue")
    public void recebeMensagem(PagamentoDTO pagamentoDTO) {

        if (!pagamentoDTO.getNumero().matches("^(?:\\d[.-]*){13,19}$")) {
            log.error("Número de cartão inválido");
            throw new BusinessException( "Número de cartão inválido!");
        }

        repository.save(mapper.map(pagamentoDTO, Pagamento.class));

        log.info("Pagamento realizado com sucesso!");
    }
}
