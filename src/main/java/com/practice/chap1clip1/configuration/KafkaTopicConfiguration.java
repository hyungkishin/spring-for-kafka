package com.practice.chap1clip1.configuration;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public AdminClient adminClient(KafkaAdmin kafkaAdmin) {
        return AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    @Bean
    public NewTopic clip() {
        return TopicBuilder
                .name("clip2")
                .build();
    }

    @Bean
    public KafkaAdmin.NewTopics clip2s() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("clip2-part1").build(),
                TopicBuilder.name("clip2-part2")
                        .partitions(3) // partition 의 갯수
                        .replicas(1) // Broker 가 local 환경에서 1대 이기때문에 1로 설정
                        .config(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(1000 * 60 * 60)) // 1 시간의 Retention Time 을 갖는 Topic
                        .build()
        );
    }

}
