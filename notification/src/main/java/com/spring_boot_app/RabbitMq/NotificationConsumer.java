package com.spring_boot_app.RabbitMq;

import com.spring_boot_app.B_Service.NotificationService;
import com.spring_boot_app.NotificationClient.NotificationRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationConsumer {

    @Autowired
    NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequestDto notificationRequestDto){
        log.info("consumer {} from Queue", notificationRequestDto);
        notificationService.addNotification(notificationRequestDto);
    }
}
