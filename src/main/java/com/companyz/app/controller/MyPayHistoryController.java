package com.companyz.app.controller;

import com.companyz.app.dao.PayrollDAO;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MyPayHistoryController {

    private final String loginName;
    public MyPayHistoryController(String loginName) { this.loginName = loginName; }

    public void show(Stage stage) {

        /* --- build table --- */
        TableView<String[]> table = new TableView<>();

        TableColumn<String[], String> dateCol = new TableColumn<>("Pay Date");
        dateCol.setCellValueFactory(c ->
            new javafx.beans.property.SimpleStringProperty(c.getValue()[0]));

        TableColumn<String[], String> amtCol  = new TableColumn<>("Amount");
        amtCol.setCellValueFactory(c ->
            new javafx.beans.property.SimpleStringProperty(c.getValue()[1]));

        table.getColumns().addAll(dateCol, amtCol);

        /* --- load data --- */
        var rows = new PayrollDAO().payHistoryForLogin(loginName);
        table.setItems(FXCollections.observableArrayList(rows));

        BorderPane root = new BorderPane(table);
        root.setPadding(new Insets(10));

        stage.setScene(new Scene(root, 320, 400));
        stage.setTitle("My Pay History");
        stage.show();
    }
}
