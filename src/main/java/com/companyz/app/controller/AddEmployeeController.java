
package com.companyz.app.controller;
import com.companyz.app.model.Address;
import com.companyz.app.model.Employee;
import com.companyz.app.service.EmployeeService;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class AddEmployeeController{
    private final EmployeeService service=new EmployeeService();
    public void show(Stage s){
        TextField id=new TextField();TextField fn=new TextField();TextField ln=new TextField();TextField ssn=new TextField();
        DatePicker dob=new DatePicker();
        TextField salary=new TextField();
        TextField street=new TextField();TextField city=new TextField();TextField state=new TextField();TextField zip=new TextField();
        Button save=new Button("Save"); Label msg=new Label();
        save.setOnAction(e->{
            try{
                Employee emp=new Employee(Integer.parseInt(id.getText()),fn.getText(),ln.getText(),ssn.getText(),dob.getValue(),Double.parseDouble(salary.getText()));
                Address addr=new Address(emp.getEmpId(),street.getText(),city.getText(),state.getText(),zip.getText());
                if(service.add(emp,addr)){msg.setText("Added");}
                else msg.setText("Error");
            }catch(Exception ex){msg.setText("Invalid input");}
        });
        GridPane gp=new GridPane(); gp.setPadding(new Insets(10)); gp.setVgap(5); gp.setHgap(5);
        gp.addRow(0,new Label("ID"),id,new Label("First"),fn,new Label("Last"),ln);
        gp.addRow(1,new Label("SSN"),ssn,new Label("DOB"),dob,new Label("Salary"),salary);
        gp.addRow(2,new Label("Street"),street,new Label("City"),city);
        gp.addRow(3,new Label("State"),state,new Label("Zip"),zip);
        gp.addRow(4,save,msg);
        s.setScene(new Scene(gp,600,200)); s.setTitle("Add Employee"); s.show();
    }
}
