package com.example.productservice.service;

import com.example.productservice.domain.Product;
import com.example.productservice.enumeration.Category;
import com.example.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void 상품_재고_조회() throws Exception {
        // given
        Product product = Product.builder()
                .productId("F001")
                .productName("햄버거")
                .category(Category.FOOD)
                .stock(50)
                .unitPrice(10000)
                .build();

        productRepository.save(product);

        // when
        int stock = productService.getStock(product.getProductId());

        // then
        assertEquals(product.getStock(), stock);
    }
}