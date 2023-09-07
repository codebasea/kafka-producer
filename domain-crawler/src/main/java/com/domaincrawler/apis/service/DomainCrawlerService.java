package com.domaincrawler.apis.service;

import com.domaincrawler.apis.model.DomainList;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.domaincrawler.apis.constant.DomainCrawlerConstant.DOMAINS_SEARCH;
import static com.domaincrawler.apis.constant.DomainCrawlerConstant.KAFKA_TOPIC;
import static com.domaincrawler.apis.constant.DomainCrawlerConstant.ZONE;


@Service
public class DomainCrawlerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public DomainCrawlerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void crawl(String name) {

        Mono<DomainList> domainListMono = WebClient
                .create()
                .get()
                .uri(DOMAINS_SEARCH + name + ZONE)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DomainList.class);


        domainListMono.subscribe(domainList -> {
            domainList.getDomains().forEach(domain -> {
                kafkaTemplate.send(KAFKA_TOPIC, domain);
                System.out.println("Domain message" + domain.getDomain());
            });
        });

    }
}
