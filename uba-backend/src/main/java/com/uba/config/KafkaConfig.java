package com.uba.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    
    @Bean
    public NewTopic userActivityTopic() {
        return TopicBuilder.name("user-activity")
                .partitions(3)
                .replicas(1)
                .build();
    }
} 