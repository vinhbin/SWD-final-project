package com.companyz.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple DAO to fetch one employee’s pay‑statement history.
 * 
 * It expects employees.loginName to match the username passed in
 * (adjust the WHERE clause if you use a different link column).
 */
public class PayrollDAO {

    public List<String[]> payHistoryForLogin(String loginName) {
        List<String[]> rows = new ArrayList<>();
        String sql = """
            SELECT p.payDate, p.amount
            FROM payroll p
            JOIN employees e ON e.empid = p.empid
            WHERE e.loginName = ?
            ORDER BY p.payDate DESC
            """;
        try (Connection c = DBConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, loginName);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                rows.add(new String[] {
                    rs.getDate(1).toString(),
                    rs.getBigDecimal(2).toPlainString()
                });

        } catch (SQLException ex) { ex.printStackTrace(); }
        return rows;
    }
}
