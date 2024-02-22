package com.example.productservice.controller;

import com.example.productservice.dto.request.ProductCreateRequest;
import com.example.productservice.dto.response.ProductCreateResponse;
import com.example.productservice.repository.mapping.ProductGetMapping;
import com.example.productservice.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Integer> getProductStock(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getStock(productId));
    }

    @PostMapping("/products")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductCreateRequest request) {
        ProductCreateResponse response = productService.createProduct(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{productId}")
                .buildAndExpand(response.getSavedId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}