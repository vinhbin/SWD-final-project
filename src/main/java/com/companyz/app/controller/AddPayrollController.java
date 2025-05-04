package com.companyz.app.controller;

import java.time.LocalDate;

import com.companyz.app.dao.PayrollDAO;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddPayrollController {

    private final PayrollDAO dao = new PayrollDAO();

    public void show(Stage s) {
        TextField empField = new TextField();
        DatePicker date    = new DatePicker(LocalDate.now());
        TextField amtField = new TextField();
        Button save = new Button("Save"); Label msg = new Label();

        save.setOnAction(e -> {
            try {
                int id   = Integer.parseInt(empField.getText());
                double a = Double.parseDouble(amtField.getText());
                if (dao.insert(id, date.getValue(), a))
                     msg.setText("Added");
                else msg.setText("FK error (empid?)");
            } catch (Exception ex) { msg.setText("Invalid input"); }
        });

        GridPane gp = new GridPane(); gp.setPadding(new Insets(10)); gp.setVgap(6); gp.setHgap(6);
        gp.addRow(0, new Label("EmpID"), empField);
        gp.addRow(1, new Label("Date"),   date);
        gp.addRow(2, new Label("Amount"), amtField);
        gp.addRow(3, save,  msg);

        s.setScene(new Scene(gp, 300, 180));
        s.setTitle("Add Payroll Entry");
        s.show();
    }
}
