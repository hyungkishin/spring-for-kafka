package com.practice.kafka;

import com.practice.kafka.producer.ClipProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(ClipProducer clipProducer) {
        return args -> {
            clipProducer.async("clip3", "Hello, Clip3-async");
            clipProducer.sync("clip3", "Hello, Clip3-sync");
            clipProducer.routingSend("clip3", "Hello, Clip3-routing");
            clipProducer.routingSendBytes("clip3-bytes", "Hello, Clip3-bytes".getBytes(StandardCharsets.UTF_8));
            clipProducer.replyingSend("clip3-request", "Ping Clip3");
        };
    }
}
