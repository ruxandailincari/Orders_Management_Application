package org.example.Connection;

import java.sql.*;
import java.util.Stack;

/**
 * Class used to establish a connection to the database
 * @author Ilincari Ruxanda
 * @since May 2025
 */

public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/ordersmanagement";
    private static final String USER = "root";
    private static final String PASS = "BazeDate2024!";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Private constructor used to load the JDBC driver class
     */
    private ConnectionFactory(){
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method used to create the connection
     * @return the created Connection
     */
    private Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    /**
     * Method used to provide a connection to the database using a singleton ConnectionFactory instance
     * @return A new connection to the database
     */
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    /**
     * Method used to close the connection
     * @param connection we want to close
     */
    public static void close(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method used to close a statement
     * @param statement we want to close
     */
    public static void close(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method used to close the result set
     * @param resultSet we want to close
     */
    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
