
package com.companyz.app.controller;
import com.companyz.app.service.EmployeeService;
import com.companyz.app.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class SearchEmployeeController{
    private final EmployeeService service=new EmployeeService();
    public void show(Stage s){
        TextField nameField=new TextField(); nameField.setPromptText("Last Name");
        TextField ssnField=new TextField(); ssnField.setPromptText("SSN");
        TextField idField=new TextField(); idField.setPromptText("EmpID");
        Button searchBtn=new Button("Search");
        HBox top=new HBox(5,nameField,ssnField,idField,searchBtn); top.setPadding(new Insets(10));
        TableView<Employee> table=new TableView<>();
        TableColumn<Employee,Integer> idCol=new TableColumn<>("ID");
        idCol.setCellValueFactory(c-> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getEmpId()).asObject());
        TableColumn<Employee,String> nameCol=new TableColumn<>("Name");
        nameCol.setCellValueFactory(c-> new javafx.beans.property.SimpleStringProperty(c.getValue().getLastName()));
        table.getColumns().addAll(idCol,nameCol);
        searchBtn.setOnAction(e->{
            Integer id=null; try{id=Integer.parseInt(idField.getText());}catch(Exception ex){}
            var list=service.search(nameField.getText(), ssnField.getText(), id);
            ObservableList<Employee> data=FXCollections.observableArrayList(list);
            table.setItems(data);
        });
        table.setRowFactory(tv->{
            TableRow<Employee> row=new TableRow<>();
            row.setOnMouseClicked(ev->{
                if(ev.getClickCount()==2 && !row.isEmpty())
                    new EditEmployeeController(row.getItem()).show(new Stage());
            });
            return row;
        });
        BorderPane root=new BorderPane(table,top,null,null,null);
        s.setScene(new Scene(root,500,400)); s.setTitle("Search Employees"); s.show();
    }
}
