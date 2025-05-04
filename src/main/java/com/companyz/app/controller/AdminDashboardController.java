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
    private final Label totalLbl  = new Label();
    private final Label payLbl    = new Label();
    private final Label addedLbl  = new Label();

    private void refreshMetrics() {
        totalLbl.setText("Total Employees:      " + metrics.totalEmployees());
        payLbl.setText  ("Payroll This Month: $" + metrics.payrollThisMonth());
        addedLbl.setText("New Hires This Month: " + metrics.employeesAddedThisMonth());
    }

    public void show(Stage stage) {

        /* ── metric box ───────────────────────────────────────────── */
        VBox metricsBox = new VBox(4, totalLbl, payLbl, addedLbl);
        metricsBox.setPadding(new Insets(10));
        Button refreshBtn = new Button("↻");
        refreshBtn.setOnAction(e -> refreshMetrics());
        HBox metricsRow = new HBox(10, metricsBox, refreshBtn);
        refreshMetrics();

        /* ── action buttons ───────────────────────────────────────── */
        Button search  = new Button("Search Employees");
        Button add     = new Button("Add Employee");
        Button adjust  = new Button("Adjust Salaries");
        Button reports = new Button("Reports");
        search .setOnAction(e -> new SearchEmployeeController   ().show(new Stage()));
        add    .setOnAction(e -> new AddEmployeeController      ().show(new Stage()));
        adjust .setOnAction(e -> new SalaryAdjustmentController ().show(new Stage()));
        reports.setOnAction(e -> new ReportController           ().show(new Stage()));

        VBox root = new VBox(15, metricsRow, search, add, adjust, reports);
        root.setPadding(new Insets(15));

        stage.setScene(new Scene(root, 380, 320));
        stage.setTitle("Admin Dashboard");
        stage.show();
    }
}
