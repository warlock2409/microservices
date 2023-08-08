package com.spring_boot_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.spring_boot_app.AppointmentClient")
public class PetOwner {

    public static void main(String[] args) {
        SpringApplication.run(PetOwner.class,args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(KafkaTemplate<String,String> kafkaTemplate){
//        return args -> {
//            for(int i=0;i<10;i++) {
//                kafkaTemplate.send("petowner", "Hello world"+i);
//            }
//        };
//    }
}