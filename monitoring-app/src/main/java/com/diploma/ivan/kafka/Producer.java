package com.diploma.ivan.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer<T> {
    private final KafkaProducer<String, T> producer;

    public Producer(KafkaProducer<String, T> producer) {
        this.producer = producer;
    }

    public void sendToKafka(T data, String topicName) {
        try {
            ProducerRecord<String, T> record = new ProducerRecord<String, T>(topicName, data);
            producer.send(record);
            producer.flush();
        } catch (Exception exception){
            //TODO customException
            throw new RuntimeException("Test");
        }
    }
}
