package com.example.productservice.enumeration;

public enum Category {
    FOOD("식품"),
    CLOTHES("의류"),
    FURNITURE("가구"),
    COSMETIC("화장품");

    private String value;

    Category(String value) {
        this.value = value;
    }
}