package org.example.DataAccess;

import org.example.Connection.ConnectionFactory;
import org.example.Model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used for data access for Bill objects
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class LogDAO {

    /**
     * Method used to insert a given bill object in the database
     * @param bill we want to insert
     */
    public void insertBill(Bill bill){
        String s = "INSERT INTO log(clientName, productName, quantity, price, totalPrice) VALUES(?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement preparedStatement = null;
        try{
            con = ConnectionFactory.getConnection();
            preparedStatement = con.prepareStatement(s);
            preparedStatement.setString(1, bill.clientName());
            preparedStatement.setString(2, bill.productName());
            preparedStatement.setInt(3, bill.quantity());
            preparedStatement.setDouble(4, bill.price());
            preparedStatement.setDouble(5, bill.totalPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally{
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(con);
        }
    }



    /**
     * Method used to return a list of all the bills from the database
     * @return the list of bills
     */
    public List<Bill> findAllBills(){
        List<Bill> list = new ArrayList<>();
        String s = "SELECT * FROM log";
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            con = ConnectionFactory.getConnection();
            preparedStatement = con.prepareStatement(s);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Bill bill = new Bill(resultSet.getInt("id"), resultSet.getString("clientName"), resultSet.getString("productName"),
                        resultSet.getInt("quantity"), resultSet.getDouble("price"), resultSet.getDouble("totalPrice"));
                list.add(bill);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(con);
        }

        return list;
    }
}
