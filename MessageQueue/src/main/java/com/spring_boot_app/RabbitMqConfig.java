package com.spring_boot_app;


import com.rabbitmq.client.AMQP;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMqConfig {

    // todo: establish connection to broker
    // ConnectionFactory is a component that provides connections to a RabbitMQ broker.
    private final ConnectionFactory connectionFactory;

    // todo: Publish message to a Queue
    // AmqpTemplate is a higher-level abstraction for sending and receiving messages to and from a RabbitMQ exchange and queue
    // SEND
    @Bean
    public AmqpTemplate amqpTemplate (){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    // todo: Receive message from consumer
    //SimpleMessageListenerContainer. This container is used to consume messages from RabbitMQ queues in a Spring-based application.
    // Receive
    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter());
        return factory;
    }

    // todo: Convert Message
    // MessageConverter is responsible for converting Java objects into messages and vice versa.
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
