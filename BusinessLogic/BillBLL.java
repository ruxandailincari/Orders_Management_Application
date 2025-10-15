package org.example.BusinessLogic;

import org.example.DataAccess.LogDAO;
import org.example.Model.Bill;

import java.util.List;

/**
 * Class that contains the application logic for Bill
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class BillBLL {
    private LogDAO logDAO;

    /**
     * Constructor which initializes the DAO used for database operations
     */
    public BillBLL(){
        this.logDAO = new LogDAO();
    }

    /**
     * Method used to insert a new bill in the database
     * @param bill we want to insert
     */
    public void insertBill(Bill bill){
        logDAO.insertBill(bill);
    }

    /**
     * Method used to return a list of all bills from the database
     * @return list of bills
     */
    public List<Bill> findAllBills(){
        return logDAO.findAllBills();
    }
}
