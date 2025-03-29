package org.example.project01;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierController {

    public TextField txtId;
    public TextField txtTel;
    public TextField txtName;
    public TextField txtAddress;
    public TableView<Supplier> tableView;

    public void initialize() {
        tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("tel"));

        ArrayList<Supplier> List = getAllSuppliers();

        tableView.setItems(FXCollections.observableArrayList(List));

    }

    public void addBtn(ActionEvent actionEvent) {

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtTel.getText());

        try {

            // 01 create a sql

            String SQL = "INSERT INTO Supplier VALUES(?, ?, ?, ?)";

            // 02 run the driver s/w

            Class.forName("com.mysql.cj.jdbc.Driver");

            // 03 create a connection to the db

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_system","root","Vimu@2164");

            // 04 create a statement

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setInt(4, tel);

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

    public void updateBtn(ActionEvent actionEvent) {

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtTel.getText());

        try {

            // 01 create a sql

            String SQL = "UPDATE Supplier SET sname=?, address=?, tel=? WHERE sid=?";

            // 02 run the driver s/w

            Class.forName("com.mysql.cj.jdbc.Driver");

            // 03 create a connection to the db

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_system","root","Vimu@2164");

            // 04 create a statement

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, tel);
            preparedStatement.setString(4, id);

            // 05 execute the sql

            int result = preparedStatement.executeUpdate();

            if (result >=  0){
                System.out.println("Update successfully");
            }else {
                System.out.println("not updated successfully");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void deleteBtn(ActionEvent actionEvent) {

        String id = txtId.getText();

        try {

            // 01 create a sql

            String SQL = "Delete FROM Supplier WHERE sid=?";

            // 02 run the driver s/w

            Class.forName("com.mysql.cj.jdbc.Driver");

            // 03 create a connection to the db

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_system","root","Vimu@2164");

            // 04 create a statement

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, id);

            // 05 execute the sql

            int result = preparedStatement.executeUpdate();

            if (result >=  0){
                System.out.println("Delete successfully");
            }else {
                System.out.println("not delete successfully");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void searchBtn(ActionEvent actionEvent) {


        String id = txtId.getText();

        try {

            // 01 create a sql

            String SQL = "Select * FROM Supplier WHERE sid=?";

            // 02 run the driver s/w

            Class.forName("com.mysql.cj.jdbc.Driver");

            // 03 create a connection to the db

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_system","root","Vimu@2164");

            // 04 create a statement

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, id);

            // 05 execute the sql

            ResultSet result = preparedStatement.executeQuery();

            if(result.next()){

                txtName.setText(result.getString("sname"));
                txtAddress.setText(result.getString("address"));
                txtTel.setText(String.valueOf(result.getInt("tel")));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<Supplier> getAllSuppliers(){

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