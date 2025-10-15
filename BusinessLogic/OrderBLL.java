package org.example.BusinessLogic;

import org.example.DataAccess.OrderDAO;
import org.example.Model.Order;

import java.util.List;

/**
 * Class that contains the application logic for Order
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class OrderBLL {
    private OrderDAO orderDAO;

    /**
     * Constructor which initializes the list of validators and the DAO used for
     * database operations
     */
    public OrderBLL(){
        orderDAO = new OrderDAO();
    }

    /**
     * Method used to validate and insert a new order in the database
     * @param order we want to insert
     * @return the inserted object
     */
    public Order insertOrder(Order order){
        return orderDAO.insert(order);
    }

    /**
     * Method used to return a list of all orders from the database
     * @return list of orders
     */
    public List<Order> findAllOrders(){
        return orderDAO.findAll();
    }
}
