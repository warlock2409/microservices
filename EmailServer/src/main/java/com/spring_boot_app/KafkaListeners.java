package com.spring_boot_app;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class KafkaListeners {
    @Autowired
    EmailRepository emailRepository;
    private final Environment environment;

    @KafkaListener( topics = "petowner",groupId = "customer")
    void listener(String message){
//        System.out.println("Listener data"+data);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Owner owner = objectMapper.readValue(message, Owner.class);
            String serverPort = environment.getProperty("server.port");
            System.out.println(serverPort);
            assert serverPort != null;
            System.out.println(owner);
                EmailEntity serviceOwner = EmailEntity.builder()
                        .service(owner.getService())
                        .email(owner.getEmail())
                        .port(Integer.valueOf(serverPort))
                        .message(owner.getMessage())
                        .build();
            // Now you have the deserialized Owner object, you can process it.

            emailRepository.save(serviceOwner);
            Thread.sleep(5000);

        } catch (IOException e) {
            // Handle deserialization exception
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
