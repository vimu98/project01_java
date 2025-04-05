package org.example.project01.model;

import org.example.project01.dto.SupplierDTO;
import org.example.project01.tm.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SupplierModel {

    public static int saveData(SupplierDTO supplierDTO) {

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

            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static ArrayList<Supplier> getAllSuppliers(){

        try {

            // 01 create a sql

            String SQL = "Select * FROM Supplier";

            // 02 run the driver s/w

            Class.forName("com.mysql.cj.jdbc.Driver");

            // 03 create a connection to the db

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_system","root","Vimu@2164");

            // 04 create a statement

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            // 05 execute the sql

            ResultSet result = preparedStatement.executeQuery();

            ArrayList<Supplier> suppliers = new ArrayList<>();

            while (result.next()){

                suppliers.add(new Supplier( result.getString("sid"),
                        result.getString("sname"),
                        result.getString("address"),
                        String.valueOf(result.getInt("tel"))
                ));

            }

            return suppliers;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return null;
    }


}
