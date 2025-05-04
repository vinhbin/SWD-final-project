
package com.companyz.app.controller;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class EmployeeDashboardController{
    private final String user;
    public EmployeeDashboardController(String user){this.user=user;}
    public void show(Stage s){
        VBox v=new VBox(10,new Label("Welcome "+user), new Label("Read-only access"));
        v.setPadding(new javafx.geometry.Insets(15));
        s.setScene(new Scene(v,200,120)); s.setTitle("Employee Dashboard"); s.show();
    }
}
