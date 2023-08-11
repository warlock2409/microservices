package com.spring_boot_app.Config;

import com.spring_boot_app.Owner;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-server}")
    private String bootStrapService;

    public Map<String,Object> consumerConfig(){
        Map<String,Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapService);
        return properties;
    }

    @Bean
    public ConsumerFactory<String, Owner> consumerFactory(){
        JsonDeserializer<Owner>  jsonDeserializer= new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("com.spring_boot_app");

        return new DefaultKafkaConsumerFactory<>(consumerConfig(),new StringDeserializer(),jsonDeserializer);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,Owner>> factory (ConsumerFactory<String, Owner> consumerFactory){

        ConcurrentKafkaListenerContainerFactory<String,Owner> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
