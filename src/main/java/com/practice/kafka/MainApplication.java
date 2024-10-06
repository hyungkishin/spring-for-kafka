package com.practice.kafka;

import com.practice.kafka.service.ClipConsumer;
import com.practice.kafka.service.KafkaManager;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(KafkaManager kafkaManager,
                                    KafkaTemplate<String, String> kafkaTemplate,
                                    ClipConsumer clipConsumer) {
        return args -> {
            kafkaManager.describeTopicConfigs();
            kafkaManager.changeConfig();
            kafkaManager.findAllConsumerGroups();
            kafkaManager.findAllOffsets();

            kafkaTemplate.send("clip1-listener", "Hello, Listener.");
            clipConsumer.seek();
        };
    }
}
