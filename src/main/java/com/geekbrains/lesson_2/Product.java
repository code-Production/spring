package com.geekbrains.lesson_2;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {

    private long id;
    private String title;
    private double price;

    @Override
    public String toString() {
        return "Product #" + id + ", title=" + title + ", price=" + price;
    }
}
