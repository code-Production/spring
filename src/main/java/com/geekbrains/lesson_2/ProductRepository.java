package com.geekbrains.lesson_2;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>(Arrays.asList(
           new Product(1, "Bread", 10),
           new Product(2, "Milk", 5),
           new Product(3, "Eggs", 7),
           new Product(4, "Apples", 8),
           new Product(5, "Cheese", 5)
        ));
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductById(long id) {
        return productList.stream().filter(p -> p.getId() == id).findFirst().orElseThrow(RuntimeException::new);
    }
}
