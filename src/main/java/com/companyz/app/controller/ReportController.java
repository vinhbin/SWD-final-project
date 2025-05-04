package com.companyz.app.controller;

import java.io.File;
import java.util.List;
import java.util.function.Supplier;

import com.companyz.app.service.ReportService;
import com.companyz.app.util.CSVUtil;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ReportController {

    private final ReportService service = new ReportService();

    public void show(Stage stage) {

        TabPane tabs = new TabPane();
        tabs.getTabs().add(makeTab("By Division", () -> service.totalByDivision()));
        // add more tabs here if you create service.byEmployee(), etc.

        BorderPane root = new BorderPane(tabs);
        root.setPadding(new Insets(10));
        stage.setScene(new Scene(root, 520, 420));
        stage.setTitle("Reports");
        stage.show();
    }

    /* ---------- helper ---------- */
    private Tab makeTab(String title, Supplier<List<String[]>> supplier) {

        TableView<String[]> table = new TableView<>();
        Label msg = new Label();
        Button loadBtn = new Button("Load");
        Button csvBtn  = new Button("Export CSV");
        csvBtn.setDisable(true);

        final List<String[]>[] rowsRef = new List[]{ List.of() };

        loadBtn.setOnAction(e -> {
            try {
                List<String[]> rows = supplier.get();
                rowsRef[0] = rows;
                csvBtn.setDisable(rows.isEmpty());
                msg.setText(rows.isEmpty() ? "No data" : "");

                if (!rows.isEmpty()) {
                    table.getColumns().clear();
                    for (int i = 0; i < rows.get(0).length; i++) {
                        final int idx = i;
                        TableColumn<String[], String> col = new TableColumn<>("Col " + (i + 1));
                        col.setCellValueFactory(r ->
                            new javafx.beans.property.SimpleStringProperty(r.getValue()[idx]));
                        table.getColumns().add(col);
                    }
                    table.setItems(FXCollections.observableArrayList(rows));
                } else {
                    table.getItems().clear();
                }
            } catch (Exception ex) { msg.setText("DB error"); ex.printStackTrace(); }
        });

        csvBtn.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.setInitialFileName(title.replace(" ", "_") + ".csv");
            File f = fc.showSaveDialog(table.getScene().getWindow());
            if (f != null) {
                try { CSVUtil.write(f.getAbsolutePath(), rowsRef[0]); }
                catch (Exception ex) { new Alert(Alert.AlertType.ERROR, "Write error").show(); }
            }
        });

        HBox controls = new HBox(6, loadBtn, csvBtn);
        VBox box = new VBox(8, controls, msg, table);
        box.setPadding(new Insets(10));

        Tab t = new Tab(title, box);
        t.setClosable(false);
        return t;
    }
}
