
package com.companyz.app.controller;
import com.companyz.app.service.AuthService;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class LoginController{
    private final AuthService auth=new AuthService();
    public void show(Stage s){
        TextField user=new TextField();
        PasswordField pass=new PasswordField();
        Label msg=new Label();
        Button btn=new Button("Login");
        btn.setOnAction(e->{
            String role=auth.login(user.getText(), pass.getText());
            if(role==null){msg.setText("Invalid");}
            else if(role.equalsIgnoreCase("ADMIN")){
                new AdminDashboardController().show(new Stage()); s.close();
            }else{
                new EmployeeDashboardController(user.getText()).show(new Stage()); s.close();
            }
            
        });
        GridPane gp=new GridPane(); gp.setPadding(new Insets(10)); gp.setVgap(5); gp.setHgap(5);
        gp.addRow(0,new Label("User:"),user);
        gp.addRow(1,new Label("Pass:"),pass);
        gp.add(btn,1,2); gp.add(msg,1,3);
        s.setScene(new Scene(gp,300,150));
        s.setTitle("EMS Login");s.show();
    }
    
}
