package com.javatechie.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Page {
    String title;
    String link;
    String description;
    String language;
    List<Item> items;
}
