package com.practice.kafka.configuration;

import com.practice.kafka.model.Animal;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class KafkaJsonListenerContainerConfiguration implements KafkaListenerConfigurer {

    private final LocalValidatorFactoryBean validator;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Animal>> kafkaJsonContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Animal> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(animalConsumerFactory());
        return factory;
    }

    private ConsumerFactory<String, Animal> animalConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                props(),
                new StringDeserializer(),
                new JsonDeserializer<>(Animal.class)
        );
    }

    private Map<String, Object> props() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
        registrar.setValidator(validator);
    }
}
