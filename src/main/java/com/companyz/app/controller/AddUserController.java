package com.companyz.app.controller;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import com.companyz.app.dao.DBConnectionManager;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;                          // ←  add
import javafx.scene.control.Button;                  // ←  add
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddUserController {

    public void show(Stage s) {

        TextField user   = new TextField();
        PasswordField pw = new PasswordField();
        TextField empId  = new TextField();
        ChoiceBox<String> role = new ChoiceBox<>(FXCollections.observableArrayList("EMPLOYEE", "ADMIN"));
        role.getSelectionModel().selectFirst();

        Label msg  = new Label();
        Button save = new Button("Save");

        save.setOnAction(e -> {
            try (Connection c = DBConnectionManager.getConnection();
                 PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO users (username,password_hash,role,empid) VALUES (?,?,?,?)"))
            {
                ps.setString(1, user.getText());
                ps.setString(2, sha256(pw.getText()));
                ps.setString(3, role.getValue());
                ps.setInt   (4, Integer.parseInt(empId.getText()));
                ps.executeUpdate();
                msg.setText("User added");
            }
            catch (SQLIntegrityConstraintViolationException ex) {
                msg.setText("Username or empid already exists");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                msg.setText("Error");
            }
        });

        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10));
        gp.setVgap(6); gp.setHgap(6);

        gp.addRow(0, new Label("Username"),  user);
        gp.addRow(1, new Label("Password"),  pw);
        gp.addRow(2, new Label("Role"),      role);
        gp.addRow(3, new Label("EmpID"),     empId);
        gp.addRow(4, save, msg);

        s.setScene(new Scene(gp, 320, 200));
        s.setTitle("Create Login");
        s.show();
    }

    /* simple SHA‑256 helper */
    private static String sha256(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(s.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder(hash.length * 2);
        for (byte b : hash) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
