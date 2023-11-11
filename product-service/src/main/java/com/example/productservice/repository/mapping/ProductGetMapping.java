package com.example.productservice.repository.mapping;

import com.example.productservice.enumeration.Category;

public interface ProductGetMapping {

    String getProductName();

    Category getCategory();

    int getUnitPrice();
}
