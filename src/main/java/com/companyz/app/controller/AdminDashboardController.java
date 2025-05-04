package com.companyz.app.controller;

import com.companyz.app.service.MetricsService;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboardController {

    private final MetricsService metrics = new MetricsService();
    private final Label totalLbl = new Label();
    private final Label payLbl   = new Label();
    private final Label addLbl   = new Label();

    private void refresh() {
        totalLbl.setText("Total Employees:      " + metrics.totalEmployees());
        payLbl .setText("Payroll This Month: $" + metrics.payrollThisMonth());
        addLbl .setText("New Hires This Month: " + metrics.employeesAddedThisMonth());
    }

    public void show(Stage stage) {

        /* metric tiles */
        VBox metricsBox = new VBox(4, totalLbl, payLbl, addLbl);
        metricsBox.setPadding(new Insets(10));
        Button refreshBtn = new Button("↻");
        refreshBtn.setOnAction(e -> refresh());
        HBox metricsRow = new HBox(10, metricsBox, refreshBtn);
        refresh();

        /* action buttons */
        Button search   = new Button("Search Employees");
        Button addEmp   = new Button("Add Employee");
        Button adjust   = new Button("Adjust Salaries");
        Button reports  = new Button("Reports");
        Button addPay   = new Button("New Payroll Entry");
        Button addLogin = new Button("Create Login");

        /* root must exist before we add children to it */
        VBox root = new VBox(15, metricsRow, search, addEmp, adjust, reports, addPay);
        root.setPadding(new Insets(15));

        /* wire actions AFTER root is created */
        search .setOnAction(e -> new SearchEmployeeController   ().show(new Stage()));
        addEmp .setOnAction(e -> new AddEmployeeController      ().show(new Stage()));
        adjust .setOnAction(e -> new SalaryAdjustmentController ().show(new Stage()));
        reports.setOnAction(e -> new ReportController           ().show(new Stage()));
        addPay .setOnAction(e -> new AddPayrollController       ().show(new Stage()));
        addLogin.setOnAction(e -> new AddUserController         ().show(new Stage()));

        root.getChildren().add(addLogin);   // add the button to the bottom

        stage.setScene(new Scene(root, 400, 380));
        stage.setTitle("Admin Dashboard");
        stage.show();
    }
}
