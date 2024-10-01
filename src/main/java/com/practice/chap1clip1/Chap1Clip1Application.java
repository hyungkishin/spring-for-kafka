package com.practice.chap1clip1;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class Chap1Clip1Application {

    public static void main(String[] args) {
        SpringApplication.run(Chap1Clip1Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(AdminClient adminClient) {
        return args -> {
            Map<String, TopicListing> topics = adminClient.listTopics().namesToListings().get();
            for (String topicName : topics.keySet()) {
                TopicListing topicListing = topics.get(topicName); // TopicListing 객체는 토픽명과 internal 명 정도만 보인다.
                System.out.println("topics.get(topicName) = " + topicListing);

                Map<String, TopicDescription> description = adminClient.describeTopics(Collections.singleton(topicName)).all().get();

                System.out.println("description = " + description);

                if (!topicListing.isInternal()) {
                    adminClient.deleteTopics(Collections.singleton(topicName));
                }
            }
        };
    }
}
