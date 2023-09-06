package com.javatechie.util;

import com.javatechie.model.Customer;

import java.util.function.Predicate;

public class PredicateUtils {
    public static final String CUSTOMER_ID = "ZI21GM8B08FVLMF0";
    public static final String CUSTOMER_ID_IZSO10C5ZHVYQ5O2 = "IZSO10C5ZHVYQ5O2";

    public static final Predicate<Customer> IS_CUSTOMERID = x -> CUSTOMER_ID.equalsIgnoreCase(x.getId());
    public static final Predicate<Customer> IS_CUSTOMERID_CUSTOMER_ID_IZSO10C5ZHVYQ5O2 = x -> CUSTOMER_ID_IZSO10C5ZHVYQ5O2.equalsIgnoreCase(x.getId());

    public static final Predicate<Customer> IS_VALID_CUSTOMERID = IS_CUSTOMERID.or(IS_CUSTOMERID_CUSTOMER_ID_IZSO10C5ZHVYQ5O2);
    public static Predicate<String> validateEqualValue(String value){
        Predicate<String> predicate = Predicate.isEqual(value);
       return predicate;
    }




}
