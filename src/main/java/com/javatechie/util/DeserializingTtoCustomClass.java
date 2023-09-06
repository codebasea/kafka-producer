package com.javatechie.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.javatechie.util.PredicateUtils.IS_VALID_CUSTOMERID;

public class DeserializingTtoCustomClass {

    public static <T> T get(URL url, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(url, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T mapToClassFromString(String url, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(url, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        String urlString = "https://microsoftedge.github.io/Demos/json-dummy-data/64KB.json";
        List<Customer> customers = castUrlToList(urlString);
        checkCustomerById(customers);
//        castListToMap(customers);
    }

    private static void castListToMap(List<Customer> customers) {
        Map<String, Customer> mapCust = customers.stream().collect(Collectors.toMap(Customer::getId, Function.identity()));
//        mapCust.entrySet().stream().forEach(System.out::println);
//        checkCustomerById(mapCust);
    }

    private static List<Customer> castUrlToList(String urlString) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Customer> customers = null;
        try {
            customers = objectMapper.readValue(new URL(urlString), new TypeReference<List<Customer>>() {
            });
//            customers.stream().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    private static void checkCustomerById(List<Customer> customers) {
        for (Customer customer : customers) {
            if (IS_VALID_CUSTOMERID.test(customer)) {
                System.out.println(customer);
                System.out.println(customer);
            }
        }

    }
}
