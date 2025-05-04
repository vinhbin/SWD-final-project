package com.companyz.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

    // full connection string with SSL flags
    private static final String JDBC_URL =
        "jdbc:mysql://localhost:3306/employeeData"
      + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASS = "password";

    public static Connection getConnection() throws SQLException {
        // ensure driver is loaded (some launchers still need this)
        try { Class.forName("com.mysql.cj.jdbc.Driver"); }
        catch (ClassNotFoundException e) { throw new SQLException("MySQL driver not found", e); }

        return DriverManager.getConnection(JDBC_URL, USER, PASS);
    }
}