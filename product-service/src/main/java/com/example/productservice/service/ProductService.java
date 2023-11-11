package com.example.productservice.service;

import com.example.productservice.repository.ProductRepository;
import com.example.productservice.repository.mapping.ProductGetMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductGetMapping> getAllProduct() {
        List<ProductGetMapping> productList = productRepository.findAllBy();
        return productList;
    }
}
