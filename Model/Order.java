package org.example.Model;

/**
 * Class used to create an object of type Order
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class Order {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;

    /**
     * Constructor used to create an order with given attributes
     * @param id of the order
     * @param clientId of the order
     * @param productId of the order
     * @param quantity of the order
     */
    public Order(int id, int clientId, int productId, int quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Constructor used to create an order with given attributes without id
     * @param clientId of the order
     * @param productId of the order
     * @param quantity of the order
     */
    public Order(int clientId, int productId, int quantity) {
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Order(){

    }

    /**
     * Method used to get the id of an order
     * @return the id of the order
     */
    public int getId() {
        return id;
    }

    /**
     * Method used to set the id of an order
     * @param id we want to set for the order
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method used to get the id of a client of an order
     * @return the id of the client of the order
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Method used to set the id for the client of an order
     * @param clientId we want to set for the client of the order
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Method used to get the id of a product of an order
     * @return the id of the product of the order
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Method used to set the id for the product of an order
     * @param productId we want to set for the product of the  order
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Method used to get the quantity of an order
     * @return the quantity of the order
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Method used to set the quantity of an order
     * @param quantity we want to set for the order
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Method used to create a string with the order attributes
     * @return a string describing the order
     */
    public String toString(){
        return "id: " + this.id + " client id: " + this.clientId + " product id: " +
                this.productId + " quantity " + this.quantity + "\n";
    }
}
