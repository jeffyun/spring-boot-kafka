package com.jeff.springboot.kafka.consumer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    private static final String SCHEMA_REGISTRY_URL_KEY = "schema.registry.url";

    private final String schemaRegistryUrl;

    private final KafkaProperties properties;

    public KafkaConsumerConfig(@Value("${schema.registry.url}") String schemaRegistryUrl, KafkaProperties properties) {
        this.schemaRegistryUrl = schemaRegistryUrl;
        this.properties = properties;
    }

    @Bean
    public ConsumerFactory<?, ?> kafkaConsumerFactory() {
        Map<String, Object> consumerProperties = properties.buildConsumerProperties();
        // Schema Registry
        consumerProperties.put(SCHEMA_REGISTRY_URL_KEY, schemaRegistryUrl);
        consumerProperties.entrySet().stream().forEach(entry->System.out.println("======>"+entry.getKey()+";"+entry.getValue()));
        return new DefaultKafkaConsumerFactory<>(consumerProperties);
    }
}
