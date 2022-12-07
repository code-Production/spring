package com.geekbrains.lesson_2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class HomeWork {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.geekbrains.lesson_2");
        System.out.println("Welcome to simple cart manager!");
        System.out.println("Commands: new cart(), cart.add(id), cart.remove(id), cart.show(), cart.destroy(), exit");
        System.out.println("List of all products:");
        context.getBean(ProductRepository.class).getAllProducts().forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        Cart userCart = null;
        String command;
        while(true) {
            System.out.println("Enter you command:");
            command = scanner.nextLine();
            try {
                if (command.equals("new cart()")) {
                    userCart = context.getBean(Cart.class);
                    System.out.println("Cart created!");
                    continue;
                }
                if (userCart == null) {
                    System.out.println("Cart doesn't exist, create it first.");
                    continue;
                }
                if (command.startsWith("cart.add(") && command.endsWith(")")) {
                    command = command.replace("cart.add(", "").replace(")", "");
                    long id = Integer.parseInt(command);
                    System.out.println("Product #" + id + " was added to your cart: " + userCart.addProduct(id));
                } else if (command.startsWith("cart.remove(") && command.endsWith(")")) {
                    command = command.replace("cart.remove(", "").replace(")", "");
                    long id = Integer.parseInt(command);
                    System.out.println("Product #" + id + " was removed from your cart: " + userCart.removeProduct(id));
                } else if (command.equals("cart.show()")) {
                    userCart.showContent();
                } else if (command.equals("cart.destroy()")) {
                    userCart = null;
                    System.out.println("Cart was deleted!");
                } else if (command.equals("exit")) {
                    context.close();
                    System.out.println("App closed");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Product id was entered incorrectly.");
            }
        }
    }
}
