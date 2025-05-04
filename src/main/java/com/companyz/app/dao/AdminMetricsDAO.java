package com.companyz.app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/** SQL helpers for Admin dashboard metrics */
public class AdminMetricsDAO {

    public int totalEmployees() {
        String sql = "SELECT COUNT(*) FROM employees";
        try (Connection c = DBConnectionManager.getConnection();
             Statement  st = c.createStatement();
             ResultSet  rs = st.executeQuery(sql)) {
            rs.next();
            return rs.getInt(1);
        } catch (Exception ex) { ex.printStackTrace(); }
        return 0;
    }

    public double payrollThisMonth() {
        String sql = """
            SELECT IFNULL(SUM(amount),0)
            FROM payroll
            WHERE MONTH(payDate) = MONTH(CURDATE())
              AND YEAR(payDate)  = YEAR(CURDATE())
            """;
        try (Connection c = DBConnectionManager.getConnection();
             Statement  st = c.createStatement();
             ResultSet  rs = st.executeQuery(sql)) {
            rs.next();
            return rs.getDouble(1);
        } catch (Exception ex) { ex.printStackTrace(); }
        return 0.0;
    }

    public int employeesAddedThisMonth() {
        /* Using employees.dob as placeholder — replace with hireDate if you have it */
        String sql = """
            SELECT COUNT(*)
            FROM employees
            WHERE MONTH(dob)  = MONTH(CURDATE())
              AND YEAR(dob)   = YEAR(CURDATE())
            """;
        try (Connection c = DBConnectionManager.getConnection();
             Statement  st = c.createStatement();
             ResultSet  rs = st.executeQuery(sql)) {
            rs.next();
            return rs.getInt(1);
        } catch (Exception ex) { ex.printStackTrace(); }
        return 0;
    }
}
