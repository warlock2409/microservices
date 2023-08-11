package com.spring_boot_app.Configuration;

import com.spring_boot_app.Dto.ResponseDto.Owner;
import com.spring_boot_app.Entity.PetOwnerEntity;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-server}")
    private String bootStrapService;

    public Map<String,Object> producerConfig(){
        Map<String,Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapService);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG,500000);
        return properties;
    }
//    allow us to create kafka Producer
    @Bean
    public ProducerFactory<String, Owner> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

//    send message
    @Bean
    public KafkaTemplate<String,Owner> kafkaTemplate(ProducerFactory<String,Owner> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }

}
