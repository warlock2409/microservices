package com.spring_boot_app;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RabbitMessageQueueProducer {

    private final AmqpTemplate amqpTemplate;

    public void publish(Object payload, String exchange, String routingKey){
        log.info("publishing to  using routingKey {} payload {}", routingKey,payload);
        amqpTemplate.convertAndSend(exchange,routingKey,payload);
        log.info("published to  using routingKey {} payload {}", routingKey,payload);

    }


}
