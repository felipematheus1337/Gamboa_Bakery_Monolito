package br.combakery.amqp;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {



    private static final String DEAD_LETTER_EXCHANGE_NAME = "deadletter-exchange";
    private static final String DEAD_LETTER_QUEUE_NAME = "pedidos-dlq";
    private static final String QUEUE_NAME = "pedidos-queue";
    private static final String EXCHANGE_NAME = "pagamentos-ex";

    private static final String QUEUE_AVALIACAO_NAME = "avaliacao-queue";

    private static final String DEAD_QUEUE_AVALIACAO_NAME = "avaliacao-dlq";



    @Bean
    public Queue avaliacaoFila() {
        return QueueBuilder
                .nonDurable(QUEUE_AVALIACAO_NAME)
                .deadLetterExchange(DEAD_LETTER_EXCHANGE_NAME)
                .build();
    }

    @Bean
    public Queue avaliacaoFilaDLQ() {
        return QueueBuilder
                .nonDurable(DEAD_QUEUE_AVALIACAO_NAME)
                .build();
    }

    @Bean
    public Binding avaliacaoDLQBinding() {
        return BindingBuilder
                .bind(avaliacaoFilaDLQ())
                .to(deadLetterExchange());
    }


    @Bean
    public Binding avaliacaoBinding() {
        return BindingBuilder
                .bind(avaliacaoFila())
                .to(defaultExchange());
    }


    @Bean
    public Queue pedidoFila() {
        return QueueBuilder
                .nonDurable(QUEUE_NAME)
                .deadLetterExchange(DEAD_LETTER_EXCHANGE_NAME)
                .build();
    }

    @Bean
    public FanoutExchange defaultExchange() {
        return ExchangeBuilder
                .fanoutExchange(EXCHANGE_NAME)
                .build();
    }

    @Bean
    public Binding bindingPedido() {
        return BindingBuilder
                .bind(pedidoFila())
                .to(defaultExchange());
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;

    }

    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    // Tratamento de falhas com Dead Letter Exchange e Dead Letter Queue

    @Bean
    public FanoutExchange deadLetterExchange() {
        return ExchangeBuilder.fanoutExchange(DEAD_LETTER_EXCHANGE_NAME)
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder
                .nonDurable(DEAD_LETTER_QUEUE_NAME).build();
    }

    @Bean
    public Binding deadBinding() {
        return BindingBuilder
                .bind(deadLetterQueue())
                .to(deadLetterExchange());
    }




}
