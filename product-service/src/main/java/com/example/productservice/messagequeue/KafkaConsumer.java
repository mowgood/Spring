package com.example.productservice.messagequeue;

import com.example.productservice.domain.Product;
import com.example.productservice.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final ProductRepository productRepository;

    @KafkaListener(topics = "product-stock-topic")
    @Transactional
    public void updateProductStock(String kafkaMessage) {
        log.info("Kafka Message ->" + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {
            });
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }

        Integer productId = (Integer) map.get("productId");

        Product product = productRepository.findById(productId.longValue())
                .orElseThrow(() -> new RuntimeException());

        product.updateStock((Integer) map.get("quantity"));
    }
}