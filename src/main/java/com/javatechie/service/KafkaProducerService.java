package com.javatechie.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.config.ConfigUtility;
import com.javatechie.model.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.javatechie.constant.KafkaConstant.KAFKA_TOPIC_NAME;


@Service
@AllArgsConstructor
@Slf4j
public class KafkaProducerService {
    private KafkaTemplate<String, Object> kafkaTemplate;
    private ConfigUtility configUtility;

    public void publishMessageToTopic(Object animal) {
        String TOPIC_NAME = configUtility.getProperty(KAFKA_TOPIC_NAME);
        kafkaTemplate.send(TOPIC_NAME, animal);
    }

    private String getTopicName() {
        return configUtility.getProperty(KAFKA_TOPIC_NAME);
    }




}