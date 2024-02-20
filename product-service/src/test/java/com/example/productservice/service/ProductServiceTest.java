package com.example.productservice.service;

import com.example.productservice.domain.Product;
import com.example.productservice.dto.request.ProductCreateRequest;
import com.example.productservice.dto.response.ProductCreateResponse;
import com.example.productservice.enumeration.Category;
import com.example.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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

    @Test
    void 상품_생성_성공() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .productName("햄버거")
                .category(Category.FOOD)
                .stock(110)
                .unitPrice(9000)
                .build();

        // when
        ProductCreateResponse response = productService.createProduct(request);
        Optional<Product> product = productRepository.findById(response.getSavedId());

        // then
        assertEquals(request.getProductName(), product.get().getProductName());
        assertEquals(request.getCategory(), product.get().getCategory());
        assertEquals(request.getStock(), product.get().getStock());
        assertEquals(request.getUnitPrice(), product.get().getUnitPrice());
    }
}