package com.practice.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RequiredArgsConstructor
@Service
public class ClipProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void async(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new KafkaSendCallback<>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("success to send Message = " + result);
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                ProducerRecord<Object, Object> failedProducerRecord = ex.getFailedProducerRecord();
                System.out.println("Fail to send message. record=" + failedProducerRecord);
            }
        });

    }

}
