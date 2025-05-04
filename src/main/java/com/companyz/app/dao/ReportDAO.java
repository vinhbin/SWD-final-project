
package com.companyz.app.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ReportDAO{
    public List<String[]> totalByDivision(){
        List<String[]> rows=new ArrayList<>();
        String sql = """
    SELECT d.name, SUM(p.amount) total
    FROM division d
    JOIN employee_division ed ON ed.divisionID = d.divisionID
    JOIN payroll p           ON p.empid       = ed.empid
    GROUP BY d.name
    """;
        try(Connection c=DBConnectionManager.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                rows.add(new String[]{rs.getString(1), String.valueOf(rs.getDouble(2))});
            }
        }catch(Exception e){e.printStackTrace();}
        return rows;
    }
}
