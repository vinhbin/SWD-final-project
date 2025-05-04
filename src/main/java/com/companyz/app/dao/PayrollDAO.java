package com.companyz.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * PayrollDAO  –  inserts pay‑statement rows and fetches the
 *                current user’s pay history.
 *
 * Assumptions
 * -----------
 * • Table `users` has columns (username, password_hash, role, empid)
 * • `users.empid` is a foreign‑key to `employees.empid`
 * • Table `payroll`    (empid, payDate, amount, …)
 */
public class PayrollDAO {

    /** Insert one payroll row */
    public boolean insert(int empId, LocalDate date, double amount) {
        String sql = "INSERT INTO payroll (empid, payDate, amount) VALUES (?,?,?)";
        try (Connection c = DBConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt   (1, empId);
            ps.setDate  (2, Date.valueOf(date));
            ps.setDouble(3, amount);
            return ps.executeUpdate() == 1;

        } catch (Exception ex) { ex.printStackTrace(); }
        return false;
    }

    /** Read back all pay statements for the given username */
    public List<String[]> payHistoryForLogin(String username) {

        List<String[]> rows = new ArrayList<>();

        String sql = """
            SELECT p.payDate, p.amount
            FROM   payroll p
            JOIN   users   u ON u.empid = p.empid   -- FK link to employees
            WHERE  u.username = ?
            ORDER  BY p.payDate DESC
            """;

        try (Connection c = DBConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                rows.add(new String[] {
                    rs.getDate(1).toString(),        // payDate
                    rs.getBigDecimal(2).toPlainString() // amount
                });

        } catch (SQLException ex) { ex.printStackTrace(); }
        return rows;
    }
}
