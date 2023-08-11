package com.spring_boot_app.Configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic createTopic(){

        return TopicBuilder.name("petowner")
                .partitions(2)
                .replicas(1)
                .build();
    }
}
