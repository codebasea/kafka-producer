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

    public void sendEventsToTopic(Customer customer) {
        try {
            publishMessages(customer);

        } catch (Exception ex) {

            log.error("Exception : getTopicName :{} ", ex.getMessage());
        }
    }

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(getTopicName(), message);
        future.whenComplete((result, ex) -> {
            if (Objects.isNull(ex)) {
                log.info("Sent message=[ {}] with offset=[ {} ]", result.toString(), result.getRecordMetadata().offset());
            } else {
                log.info("Unable to send message=[ {} ] due to : {} ", message, ex.getMessage());
            }
        });

    }


    public void sendEventsToTopicFromFiles(String urlString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Customer> customersList = objectMapper.readValue(new URL(urlString), new TypeReference<List<Customer>>() {
            });
            customersList.forEach(this::publishMessages);
        } catch (Exception ex) {
            log.error("Exception : sendEventsToTopicFromFiles :{} ", ex.getMessage());

        }
    }

    private void publishMessages(Customer customer) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(getTopicName(), customer);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[ {}] with offset=[ {} ]", customer.toString(), result.getRecordMetadata().offset());
            } else {
                log.info("Unable to send message=[ {} ] due to : {} ", customer.toString(), ex.getMessage());
            }
        });
    }

}