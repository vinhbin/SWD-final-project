
package com.companyz.app.controller;
import com.companyz.app.service.ReportService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
public class ReportController{
    private final ReportService service=new ReportService();
    public void show(Stage s){
        TableView<String[]> table=new TableView<>();
        TableColumn<String[],String> divCol=new TableColumn<>("Division");
        divCol.setCellValueFactory(c-> new javafx.beans.property.SimpleStringProperty(c.getValue()[0]));
        TableColumn<String[],String> totalCol=new TableColumn<>("Total Pay");
        totalCol.setCellValueFactory(c-> new javafx.beans.property.SimpleStringProperty(c.getValue()[1]));
        table.getColumns().addAll(divCol,totalCol);
        Button load=new Button("Load by Division");
        load.setOnAction(e->{
            var rows=service.totalByDivision();
            ObservableList<String[]> data=FXCollections.observableArrayList(rows);
            table.setItems(data);
        });
        VBox v=new VBox(10,load,table); v.setPadding(new Insets(10));
        s.setScene(new Scene(v,400,300)); s.setTitle("Reports"); s.show();
    }
}
