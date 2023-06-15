package com.diploma.ivan.configuration;

import com.diploma.ivan.kafka.*;
import com.diploma.ivan.model.CombinedConfiguration;
import com.diploma.ivan.repository.MonitoringConfigurationRepository;
import com.diploma.ivan.repository.UserRepository;
import com.diploma.ivan.service.*;
import com.diploma.ivan.tasks.MetricsPullerTask;
import com.diploma.ivan.tasks.ScheduledTask;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class BeanConfigurator {

    private static final String HOST1 = "PLAINTEXT://:9092";
    private static final String HOST = "http://localhost:9092";
    private static final String GROUP_ID = "metrics";

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("<<insert user>>");
        mailSender.setPassword("<<insert password>>");
        //wdmyfoceeqpoisfj
        //samo_levski1914

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MailService mailService(JavaMailSender javaMailSender) {
        return new MailService(javaMailSender);
    }

    @Bean
    public IConfigurationService configurationService(MonitoringConfigurationRepository monitoringConfigurationRepository) {
        return new ConfigurationService(monitoringConfigurationRepository);
    }

    @Bean
    public IUserService userService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return new UserService(userRepository, passwordEncoder);
    }

    @Bean
    public ScheduledTask metricsPullerTask(MonitoringConfigurationRepository monitoringConfigurationRepository) {
        return new MetricsPullerTask(monitoringConfigurationRepository, new Producer(getKafkaProducer()));
    }

    @Bean
    public IDeploymentService deploymentService() {
        return new DeploymentService();
    }

    @Bean
    public NewTopic metricsTopic() {
        return TopicBuilder //
                .name(Topics.METRICS) //
                .partitions(5) //
                .replicas(1) //
                .build();
    }

    @Bean("Test")
    public ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(getConsumerProperties());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> factory(@Qualifier("Test") ConsumerFactory<String, String> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    private static Map<String, Object> getConsumerProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    private static KafkaProducer<String, CombinedConfiguration> getKafkaProducer() {
        Properties properties = new Properties();
        TopicCreator topicCreator = new TopicCreator();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);


        topicCreator.createTopic(properties, Topics.METRICS);
        KafkaProducer<String, CombinedConfiguration> producer = PropertiesValidator.checkProperties(properties);

        return producer;
    }
}
