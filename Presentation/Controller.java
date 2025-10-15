package org.example.Presentation;

import org.example.BusinessLogic.BillBLL;
import org.example.BusinessLogic.ClientBLL;
import org.example.BusinessLogic.OrderBLL;
import org.example.BusinessLogic.ProductBLL;
import org.example.Model.Bill;
import org.example.Model.Client;
import org.example.Model.Order;
import org.example.Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

/**
 * Class that manages user interactions from the gui
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class Controller implements ActionListener {
    private View view;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;
    private BillBLL billBLL;

    /**
     * Constructor used to initialize business logic objects and bind
     * the given GUI view to the controller
     * @param v the view to be controlled
     */
    public Controller(View v){
        this.view = v;
        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();
        billBLL = new BillBLL();
    }

    /**
     * Method used to access different methods based on the performed event
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(Objects.equals(command, "ADD CLIENT")){
            addClientAction();
        }
        if(Objects.equals(command, "UPDATE CLIENT")){
            updateClientAction();
        }
        if(Objects.equals(command, "ADD PRODUCT")){
            addProductAction();
        }
        if(Objects.equals(command, "UPDATE PRODUCT")){
            updateProductAction();
        }
        if(Objects.equals(command, "MAKE ORDER")){
            addOrderAction();
        }
    }

    /**
     * Method used to create a client and insert them into the database
     * based on attributes sent from the gui
     */
    public void addClientAction(){
        if(view.getClientMail().getText().isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(), "Insert a client mail!");
            return;
        }
        String clientMail = view.getClientMail().getText();
        if(view.getClientName().getText().isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(), "Insert a client name!");
            return;
        }
        String clientName = view.getClientName().getText();
        Client c = new Client(clientName, clientMail);
        try {
            clientBLL.insertClient(c);
            view.refreshClientTable();
            view.getClientMail().setText("");
            view.getClientName().setText("");
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }
    }

    /**
     * Method used to call the findAllClients method from the ClientBLL class
     * @return a list of the clients from the database
     */
    public List<Client> getClientList(){
        return clientBLL.findAllClients();
    }

    /**
     * Method used to call the deleteClient method from ClientBLL
     * @param id of the client we want to delete
     */
    public void deleteClient(int id){
        clientBLL.deleteClient(id);
        view.refreshClientTable();
    }

    /**
     * Method used to call the updateClient method from ClientBLL and give them
     * the new attributes taken from the gui
     */
    public void updateClientAction(){
        int clientId = Integer.parseInt(view.getUpdatedClientId().getText());
        String clientName = view.getUpdatedClientName().getText();
        String clientMail = view.getUpdatedClientMail().getText();
        Client c = new Client(clientId, clientName, clientMail);
        try {
            clientBLL.updateClient(view.getSelectedClient().getId(), c);
            view.refreshClientTable();
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }
    }

    /**
     * Method used to call the findAllProducts method from the ProductBLL class
     * @return a list of the products from the database
     */
    public List<Product> getProductList(){
        return productBLL.findAllProducts();
    }

    /**
     * Method used to create a product and insert them into the database
     * based on attributes sent from the gui
     */
    public void addProductAction(){
        if(validateProductFields() == -1){
            return;
        }
        String productName = view.getProductName().getText();
        double productPrice = Double.parseDouble(view.getProductPrice().getText());
        int productStock = Integer.parseInt(view.getProductStock().getText());
        Product p = new Product(productName, productPrice, productStock);
        try {
            productBLL.insertProduct(p);
            view.refreshProductTable();
            view.getProductName().setText("");
            view.getProductPrice().setText("");
            view.getProductStock().setText("");
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }
    }

    /**
     * Method used to validate the inputs from the gui for the product
     * @return -1 if the inputs are not valid and 0 otherwise
     */
    public int validateProductFields(){
        if(view.getProductName().getText().isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(), "Insert a product name!");
            return -1;
        }
        if(view.getProductPrice().getText().isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(), "Insert a product price!");
            return -1;
        }
        if(view.getProductStock().getText().isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(), "Insert a product stock!");
            return -1;
        }
        return 0;
    }

    /**
     * Method used to call the deleteProduct method from ProductBLL
     * @param id of the product we want to delete
     */
    public void deleteProduct(int id){
        productBLL.deleteProduct(id);
        view.refreshProductTable();
    }

    /**
     * Method used to call the updateProduct method from ProductBLL and give them
     * the new attributes taken from the gui
     */
    public void updateProductAction(){
        int productId = Integer.parseInt(view.getNewProductId().getText());
        String productName = view.getNewProductName().getText();
        double productPrice = Double.parseDouble(view.getNewProductPrice().getText());
        int productStock = Integer.parseInt(view.getNewProductStock().getText());
        Product product = new Product(productId, productName, productPrice, productStock);
        try {
            productBLL.updateProduct(view.getSelectedProduct().getId(), product);
            view.refreshProductTable();
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }
    }

    /**
     * Method used to create an order and insert them into the database
     * based on attributes sent from the gui
     */
    public void addOrderAction(){
        if(view.getProductQuantity().getText().isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(), "Insert a product quantity!");
            return;
        }
        Client c = (Client) view.getClientJComboBox().getSelectedItem();
        Product p = (Product) view.getProductJComboBox().getSelectedItem();
        int quantity = Integer.parseInt(view.getProductQuantity().getText());
        if(p.getStock() - quantity < 0){
            JOptionPane.showMessageDialog(new JFrame(), "Not enough products for this order, product stock: " + p.getStock());
            return;
        }
        Order o = new Order(c.getId(), p.getId(),quantity);
        orderBLL.insertOrder(o);
        p.setStock(p.getStock() - quantity);
        productBLL.updateProduct(p.getId(), p);
        view.getProductQuantity().setText("");
        view.refreshJComboBoxes();
        generateBill(quantity,c, p);
    }

    /**
     * Method used to call the findAllOrders method from the OrderBLL class
     * @return a list of the orders from the database
     */
    public List<Order> getOrderList(){
        return orderBLL.findAllOrders();
    }

    /**
     * Method used to create a bill and insert it into the database
     * @param quantity of the ordered product
     * @param c the client that made the order
     * @param p the product ordered by the client
     */
    public void generateBill(int quantity, Client c, Product p){
        String clientName = c.getName();
        String productName = p.getName();
        double price = p.getPrice();
        double totalPrice = p.getPrice() * quantity;
        Bill bill = new Bill(0, clientName,productName,quantity,price, totalPrice);
        billBLL.insertBill(bill);
    }

    /**
     * Method used to call the findAllBills method from the BillBLL class
     * @return a list of the bills from the database
     */
    public List<Bill> getBillList(){
        return billBLL.findAllBills();
    }
}
