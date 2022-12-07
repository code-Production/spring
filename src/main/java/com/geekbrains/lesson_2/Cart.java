package com.geekbrains.lesson_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private List<Product> orderList;
    private ProductRepository productRepository;

    public Cart() {
        orderList = new ArrayList<>();
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean addProduct(long id) {
        return orderList.add(productRepository.getProductById(id));
    }

    public boolean removeProduct(long id) {
        return orderList.remove(productRepository.getProductById(id));
    }

    public void showContent() {
        System.out.println("Cart content: ");
        if (orderList.size() == 0) {
            System.out.println("none here!");
            return;
        }
        orderList.forEach(System.out::println);
    }
}
