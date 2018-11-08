package com.jeff.springboot.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);


    @KafkaListener(topics = {"misnew.misnew.mis_check_data_back"})
    public void listener(ConsumerRecord<?,?> record){
        logger.info("==============begin");
        logger.info("======{}",record.toString());
        logger.info("==============end");
    }
}
