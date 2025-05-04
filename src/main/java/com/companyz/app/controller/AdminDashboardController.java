
package com.companyz.app.controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class AdminDashboardController{
    public void show(Stage s){
        Button search=new Button("Search Employees");
        Button add=new Button("Add Employee");
        Button salary=new Button("Adjust Salaries");
        Button report=new Button("Reports");
        search.setOnAction(e-> new SearchEmployeeController().show(new Stage()));
        add.setOnAction(e-> new AddEmployeeController().show(new Stage()));
        salary.setOnAction(e-> new SalaryAdjustmentController().show(new Stage()));
        report.setOnAction(e-> new ReportController().show(new Stage()));
        VBox root=new VBox(10,search,add,salary,report); root.setPadding(new Insets(15));
        s.setScene(new Scene(root,250,200)); s.setTitle("Admin Dashboard"); s.show();
    }
}
