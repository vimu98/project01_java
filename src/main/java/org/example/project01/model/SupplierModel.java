package org.example.project01.model;

import org.example.project01.dto.SupplierDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SupplierModel {

    public static void saveData(SupplierDTO supplierDTO) {

        try {

            // 01 create a sql

            String SQL = "INSERT INTO Supplier VALUES(?, ?, ?, ?)";

            // 02 run the driver s/w

            Class.forName("com.mysql.cj.jdbc.Driver");

            // 03 create a connection to the db

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_system","root","Vimu@2164");

            // 04 create a statement

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, supplierDTO.getId());
            preparedStatement.setString(2, supplierDTO.getName());
            preparedStatement.setString(3, supplierDTO.getAddress());
            preparedStatement.setInt(4, supplierDTO.getTel());

            // 05 execute the sql

            int result = preparedStatement.executeUpdate();

            if (result >=  0){
                System.out.println("Added successfully");
            }else {
                System.out.println("Not added successfully");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
