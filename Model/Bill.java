package org.example.Model;

/**
 * @author Ilincari Ruxanda
 * @since May 2025
 * Immutable class used to create an object of type Bill
 * @param id of the bill
 * @param clientName that made the order
 * @param productName that the client ordered
 * @param quantity of the ordered product
 * @param price of a single product
 * @param totalPrice of all the ordered products
 */
public record Bill(
        int id,
        String clientName,
        String productName,
        int quantity,
        double price,
        double totalPrice){}