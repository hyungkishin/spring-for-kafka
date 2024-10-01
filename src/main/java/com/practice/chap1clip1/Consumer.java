package com.practice.chap1clip1;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    // @KafkaListener 를 선언하면 해당 메서드가 Consumer 가 된다.
    @KafkaListener(id = "new-id", topics = "quickstart-events")
    public void listen(String message) {
        System.out.println("=======");
        System.out.println("message = " + message);
        System.out.println("=======");
    }
}
