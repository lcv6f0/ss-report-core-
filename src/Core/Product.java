/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 *
 * @author Christian
 */
public class Product {

    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;

    }

    public static void main(String[] args) {
        // Product test

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("----------Product----------\n");
//        sb.append("Product Name: ").append(name);
//        sb.append("\nPrice: ").append(price);
        return name;

//        return sb.toString();
    }
}
