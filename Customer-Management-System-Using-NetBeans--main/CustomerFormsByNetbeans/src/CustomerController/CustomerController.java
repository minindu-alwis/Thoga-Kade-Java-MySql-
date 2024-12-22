/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomerController;

import Customer.Customer;
import DBConnection.DBConnection;
import List.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerController {
    
    public static boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getInstance().getConnection();
    String SQL = "INSERT INTO customer (id, name, address, salary) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stm = connection.prepareStatement(SQL)) {
        stm.setObject(1, customer.getId());
        stm.setObject(2, customer.getName());
        stm.setObject(3, customer.getAddress());
        stm.setObject(4, customer.getSalary());
        return stm.executeUpdate() > 0;
    }
}

    
   public static String generateCustomerId() throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getInstance().getConnection();
    String lastId = null;

    String SQL = "SELECT id FROM customer WHERE id LIKE 'C%' ORDER BY id DESC LIMIT 1";
    try (PreparedStatement stm = connection.prepareStatement(SQL); 
         var resultSet = stm.executeQuery()) {
        if (resultSet.next()) {
            lastId = resultSet.getString("id");
        }
    }

    if (lastId == null) {
        return "C001";
    } else {
        String idPart = lastId.substring(1);
        int lastIdNumber = Integer.parseInt(idPart);
        return String.format("C%03d", lastIdNumber + 1);
    }
}   
}
