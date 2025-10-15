package org.example.Model;

/**
 * Class used to create an object of type Product
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    /**
     * Constructor used to create a product with given attributes
     * @param id of the product
     * @param name of the product
     * @param price of the product
     * @param stock of the product
     */
    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructor used to create a product with given attributes without id
     * @param name of the product
     * @param price of the product
     * @param stock of the product
     */
    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructor used to create an empty product object
     */
    public Product(){

    }

    /**
     * Method used to get the id of a product
     * @return the id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Method used to set the id of a product
     * @param id we want to set for the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method used to get the name of a product
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Method used to set the name of a product
     * @param name we want to set for the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method used to get the price of a product
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method used to set the price of a product
     * @param price we want to set for the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Method used to get the stock of a product
     * @return the stock of the product
     */
    public int getStock(){
        return this.stock;
    }

    /**
     * Method used to set the stock of a product
     * @param stock we want to set for the product
     */
    public void setStock(int stock){
        this.stock = stock;
    }

    /**
     * Method used to create a string with the product attributes
     * @return a string describing the product
     */
    public String toString(){
        return "id: " + this.id + " name: " + this.name + " price: " + this.price +
                " stock: " + this.stock;
    }
}
