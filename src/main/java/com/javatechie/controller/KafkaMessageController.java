package com.javatechie.controller;

import com.javatechie.config.ConfigUtility;
import com.javatechie.model.Animal;
import com.javatechie.model.Customer;
import com.javatechie.model.KafkaRequestBody;
import com.javatechie.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@Slf4j
public class KafkaMessageController {
    private KafkaProducerService kafkaProducerService;
    private ConfigUtility configUtility;

    @PostMapping("/publish")
    public String publishMessageToTopic(@RequestBody Animal animal) {
        kafkaProducerService.publishMessageToTopic(animal);
        log.info("Successfully Published the Animal = '{}' to the AnimalTopic", animal);
        return "Successfully Published the Animal = '" + animal + "' to the AnimalTopic";
    }





    @PostMapping(value = "/urlpath", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity publishMessageFromFiles(@RequestBody KafkaRequestBody kafkaRequestBody) {
        try {
//                String urlString = configUtility.getProperty(REQUEST_ENDPOINT_JSON_DUMMY_DATA_64_KB);
            String urlPath = kafkaRequestBody.getUrlPath();
//            kafkaProducerService.sendEventsToTopicFromFiles(urlPath);

            return ResponseEntity.ok("message published successfully ..");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}