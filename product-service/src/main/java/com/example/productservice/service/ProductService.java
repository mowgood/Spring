package com.example.productservice.service;

import com.example.productservice.domain.Product;
import com.example.productservice.dto.request.ProductCreateRequest;
import com.example.productservice.dto.response.ProductCreateResponse;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.repository.mapping.ProductGetMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductGetMapping> getAllProduct() {
        List<ProductGetMapping> productList = productRepository.findAllBy();
        return productList;
    }

    @Transactional(readOnly = true)
    public int getStock(Long productId) {
        return productRepository.findStockById(productId).getStock();
    }

    @Transactional
    public ProductCreateResponse createProduct(ProductCreateRequest request) {
        Product product = Product.builder()
                .productName(request.getProductName())
                .category(request.getCategory())
                .stock(request.getStock())
                .unitPrice(request.getUnitPrice())
                .build();

        productRepository.save(product);

        return ProductCreateResponse.builder()
                .savedId(product.getId())
                .build();
    }
}