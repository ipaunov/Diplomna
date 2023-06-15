package com.diploma.ivan.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.config.ConfigException;

import java.util.Properties;

public class PropertiesValidator {
    public static <T> KafkaProducer<String, T> checkProperties(Properties properties) {
        try {
            return new KafkaProducer<String, T>(properties);
        } catch (ConfigException exception) {
            //TODO custom exception
            throw new RuntimeException("Test");
        }
    }
}
