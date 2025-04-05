package org.example.project01.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.project01.model.SupplierModel;
import org.example.project01.tm.Supplier;

import java.util.ArrayList;

public class ProductController {
    public TextField nameField;
    public TextField descriptionField;
    public TextField priceField;
    public TableView tableView;
    public ComboBox supplierField;

    public void initialize() {
        loadSupplierIds();
    }


    public void add(ActionEvent actionEvent) {

        nameField.getText();
        descriptionField.getText();
        priceField.getText();
        supplierField.getValue();


    }

    public void update(ActionEvent actionEvent) {
    }

    public void delete(ActionEvent actionEvent) {
    }

    public void search(ActionEvent actionEvent) {

    }

    public  void loadSupplierIds(){

        ArrayList<Supplier> suppliers= SupplierModel.getAllSuppliers();

        ObservableList idsList = FXCollections.observableArrayList();

        suppliers.forEach(supplier -> {
            idsList.add(supplier.getId());
        });

        supplierField.setItems(idsList);

    }

}
