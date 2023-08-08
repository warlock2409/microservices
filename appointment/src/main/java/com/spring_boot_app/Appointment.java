package com.spring_boot_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {"com.spring_boot_app","com.spring_boot_app.amqp"}

)
@EnableFeignClients(basePackages = "com.spring_boot_app.NotificationClient")
public class Appointment {

    public static void main(String[] args) {
        SpringApplication.run(Appointment.class,args);
    }


//
//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMessageQueueProducer producer){
//        return args -> {
//            producer.publish(
//                    new Person("Da",26),
//                    "internal.exchange",
//                    "internal.notification.routing-key"
//            );
//        };
//    }
//
//    record Person(String name,int age){}

}