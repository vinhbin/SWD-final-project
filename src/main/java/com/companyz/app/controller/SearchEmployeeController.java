package com.companyz.app.controller;

import com.companyz.app.model.Employee;
import com.companyz.app.service.EmployeeService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/** Search + double–click‑to‑edit (no Delete button). */
public class SearchEmployeeController {

    private final EmployeeService service = new EmployeeService();

    public void show(Stage stage) {

        /* ---------- search fields ---------- */
        TextField nameField = new TextField(); nameField.setPromptText("Last Name");
        TextField ssnField  = new TextField(); ssnField .setPromptText("SSN");
        TextField idField   = new TextField(); idField  .setPromptText("EmpID");
        Button searchBtn    = new Button("Search");

        HBox top = new HBox(5, nameField, ssnField, idField, searchBtn);
        top.setPadding(new Insets(10));

        /* ---------- table ---------- */
        TableView<Employee> table = new TableView<>();

        TableColumn<Employee,Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(c ->
            new javafx.beans.property.SimpleIntegerProperty(c.getValue().getEmpId()).asObject());

        TableColumn<Employee,String> nameCol = new TableColumn<>("Last Name");
        nameCol.setCellValueFactory(c ->
            new javafx.beans.property.SimpleStringProperty(c.getValue().getLastName()));

        table.getColumns().addAll(idCol, nameCol);

        /* ---------- search action ---------- */
        searchBtn.setOnAction(e -> {
            Integer id = null;
            try { id = Integer.parseInt(idField.getText()); } catch (Exception ignored) {}
            var list  = service.search(nameField.getText(), ssnField.getText(), id);
            ObservableList<Employee> data = FXCollections.observableArrayList(list);
            table.setItems(data);
        });

        /* ---------- double‑click to edit ---------- */
        table.setRowFactory(tv -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(ev -> {
                if (ev.getClickCount() == 2 && !row.isEmpty())
                    new EditEmployeeController(row.getItem()).show(new Stage());
            });
            return row;
        });

        BorderPane root = new BorderPane(table, top, null, null, null);
        stage.setScene(new Scene(root, 520, 420));
        stage.setTitle("Search Employees");
        stage.show();
    }
}
