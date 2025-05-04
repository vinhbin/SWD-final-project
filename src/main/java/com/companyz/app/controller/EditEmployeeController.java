
package com.companyz.app.controller;
import com.companyz.app.model.Employee;
import com.companyz.app.service.EmployeeService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class EditEmployeeController{
    private final EmployeeService service=new EmployeeService();
    private final Employee emp;
    public EditEmployeeController(Employee e){this.emp=e;}
    public void show(Stage s){
        TextField fn=new TextField(emp.getFirstName());
        TextField ln=new TextField(emp.getLastName());
        TextField salary=new TextField(String.valueOf(emp.getSalary()));
        Button save=new Button("Save"); Label msg=new Label();
        save.setOnAction(e->{
            emp.setSalary(Double.parseDouble(salary.getText()));
            if(service.update(emp)) msg.setText("Updated");
            else msg.setText("Error");
        });
        GridPane gp=new GridPane(); gp.setPadding(new Insets(10)); gp.setVgap(5); gp.setHgap(5);
        gp.addRow(0,new Label("First"),fn,new Label("Last"),ln);
        gp.addRow(1,new Label("Salary"),salary);
        gp.addRow(2,save,msg);
        s.setScene(new Scene(gp,400,150));
        s.setTitle("Edit Employee "+emp.getEmpId());
        s.show();
    }
}
