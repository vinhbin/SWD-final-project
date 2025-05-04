package com.companyz.app.controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class EmployeeDashboardController {

    private final String loginName;
    public EmployeeDashboardController(String loginName) { this.loginName = loginName; }

    public void show(Stage stage) {

        Label welcome = new Label("Welcome " + loginName);
        Button profileBtn = new Button("My Profile");
        Button payBtn     = new Button("My Pay History");

        profileBtn.setOnAction(e -> new EmployeeProfileController(loginName).show(new Stage()));
        payBtn    .setOnAction(e -> new MyPayHistoryController   (loginName).show(new Stage()));

        VBox root = new VBox(12, welcome, profileBtn, payBtn);
        root.setPadding(new Insets(15));

        stage.setScene(new Scene(root, 260, 160));
        stage.setTitle("Employee Dashboard");
        stage.show();
    }
}
