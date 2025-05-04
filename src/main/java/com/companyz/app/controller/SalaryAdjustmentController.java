
package com.companyz.app.controller;
import com.companyz.app.service.EmployeeService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class SalaryAdjustmentController{
    private final EmployeeService service=new EmployeeService();
    public void show(Stage s){
        TextField minField=new TextField();TextField maxField=new TextField();TextField pctField=new TextField();
        Button apply=new Button("Apply");Label msg=new Label();
        apply.setOnAction(e->{
            try{
                double min=Double.parseDouble(minField.getText());
                double max=Double.parseDouble(maxField.getText());
                double pct=Double.parseDouble(pctField.getText());
                if(service.adjustSalary(min,max,pct)) msg.setText("Adjustment done");
                else msg.setText("No rows updated");
            }catch(Exception ex){msg.setText("Invalid input");}
        });
        GridPane gp=new GridPane(); gp.setPadding(new Insets(10)); gp.setVgap(5); gp.setHgap(5);
        gp.addRow(0,new Label("Min"),minField,new Label("Max"),maxField,new Label("%"),pctField);
        gp.addRow(1,apply,msg);
        s.setScene(new Scene(gp,400,120)); s.setTitle("Salary Adjustment"); s.show();
    }
}
