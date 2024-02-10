package com.example.productservice.repository;

import com.example.productservice.domain.Product;
import com.example.productservice.repository.mapping.ProductGetMapping;
import com.example.productservice.repository.mapping.ProductStockMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<ProductGetMapping> findAllBy();

    Product findByProductId(String productId);

    ProductStockMapping findStockByProductId(String productId);
}
