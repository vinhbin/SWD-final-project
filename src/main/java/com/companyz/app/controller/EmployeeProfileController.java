package com.companyz.app.controller;

import com.companyz.app.model.Employee;
import com.companyz.app.service.EmployeeService;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/** Read‑only profile screen for employees */
public class EmployeeProfileController {

    private final String loginName;
    public EmployeeProfileController(String loginName) { this.loginName = loginName; }

    public void show(Stage stage) {

        /* Naively match by first name == loginName; replace with your own lookup */
        EmployeeService svc = new EmployeeService();
        Employee emp = svc.search(null, null, null)
                          .stream()
                          .filter(e -> e.getFirstName().equalsIgnoreCase(loginName))
                          .findFirst()
                          .orElse(null);
        if (emp == null) {
            stage.setTitle("Profile not found");
            stage.setScene(new Scene(new Label("No profile found"), 200, 80));
            stage.show();
            return;
        }

        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10));
        gp.setVgap(6); gp.setHgap(8);

        gp.addRow(0, new Label("ID:"),      new Label(String.valueOf(emp.getEmpId())));
        gp.addRow(1, new Label("Name:"),    new Label(emp.getFirstName()+" "+emp.getLastName()));
        gp.addRow(2, new Label("SSN:"),     new Label(emp.getSsn()));
        gp.addRow(3, new Label("DOB:"),     new Label(emp.getDob().toString()));
        gp.addRow(4, new Label("Salary:"),  new Label(String.valueOf(emp.getSalary())));
        // add more rows if you pull Address, etc.

        stage.setScene(new Scene(gp, 340, 180));
        stage.setTitle("My Profile");
        stage.show();
    }
}
