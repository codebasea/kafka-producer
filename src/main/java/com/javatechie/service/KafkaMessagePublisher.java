/*
package com.javatechie.service;

import com.javatechie.config.ConfigUtility;
import com.javatechie.model.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.javatechie.constant.KafkaConstant.KAFKA_TOPIC_NAME;

@Service
@Slf4j
public class KafkaMessagePublisher {


    @Autowired
    private ConfigUtility configUtility;
    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send( getTopicName(), message);
        future.whenComplete((result,ex)->{
            if ( Objects.isNull(ex) ){
                log.info("Sent message=[ {}] with offset=[ {} ]", result.toString(), result.getRecordMetadata().offset());
            } else {
                log.info("Unable to send message=[ {} ] due to : {} ", message, ex.getMessage());
            }
        });

    }

    private String getTopicName(){
        return configUtility.getProperty(KAFKA_TOPIC_NAME);
    }


    public void publishMessageToTopic(Customer customer) {
        try {
            String topicName = getTopicName();
            CompletableFuture<SendResult<String, Object>> future = template.send(topicName, customer);
            future.whenComplete((result, ex) -> {
                if ( Objects.isNull(ex) ){
                    log.info("Sent message=[ {}] with offset=[ {} ]", customer.toString(), result.getRecordMetadata().offset());
                } else {
                    log.info("Unable to send message=[ {} ] due to : {} ", customer.toString(), ex.getMessage());
                }
            });
        } catch (Exception ex) {
            log.error("publishMessageToTopic : {}", ex.getMessage());
        }
    }


}
*/
