package com.example.productservice.controller;

import com.example.productservice.repository.mapping.ProductGetMapping;
import com.example.productservice.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        return String.format("Product-Service Success : %s", request.getServerPort());
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductGetMapping>> getProductList() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/products/{productId}/stock")
    public ResponseEntity<Integer> getProductStock(@PathVariable String productId) {
        return ResponseEntity.ok(productService.getStock(productId));
    }
}