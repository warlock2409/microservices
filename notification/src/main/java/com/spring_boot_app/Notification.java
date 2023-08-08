package com.spring_boot_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {"com.spring_boot_app","com.spring_boot_app.amqp"}

)
public class Notification {
    public static void main(String[] args) {
        SpringApplication.run(Notification.class,args);

    }

//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMessageQueueProducer producer,NotificationConfig notificationConfig){
//        return args -> {
//            producer.publish(
//                    new Person("Rajaraman",26),
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey()
//            );
//        };
//    }
//
//    record Person(String name,int age){}

}