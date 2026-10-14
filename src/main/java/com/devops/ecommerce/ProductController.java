package com.devops.ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @GetMapping("/")
    public String home() {
        return "E-Commerce DevOps Application Running!";
    }

    @GetMapping("/products")
    public List<Map<String, Object>> products() {
        return List.of(
            Map.of("id", 1, "name", "Laptop", "price", 50000),
            Map.of("id", 2, "name", "Mobile", "price", 20000),
            Map.of("id", 3, "name", "Headphones", "price", 2000)
        );
    }

    @GetMapping("/health")
    public String health() {
        return "UP";
    }
}
