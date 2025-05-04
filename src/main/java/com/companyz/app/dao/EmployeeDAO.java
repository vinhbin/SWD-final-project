
package com.companyz.app.dao;
import com.companyz.app.model.Employee;
import com.companyz.app.model.Address;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
public class EmployeeDAO{
    public List<Employee> search(String name,String ssn,Integer empId){
        List<Employee> list=new ArrayList<>();
        StringBuilder sb=new StringBuilder("SELECT * FROM employees WHERE 1=1");
        if(name!=null && !name.isBlank()) sb.append(" AND lastName LIKE ?");
        if(ssn!=null && !ssn.isBlank()) sb.append(" AND ssn=?");
        if(empId!=null) sb.append(" AND empid=?");
        try(Connection c=DBConnectionManager.getConnection();
            PreparedStatement ps=c.prepareStatement(sb.toString())){
            int idx=1;
            if(name!=null && !name.isBlank()) ps.setString(idx++,"%" + name + "%");
            if(ssn!=null && !ssn.isBlank()) ps.setString(idx++, ssn);
            if(empId!=null) ps.setInt(idx++, empId);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(new Employee(
                    rs.getInt("empid"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("ssn"),
                    rs.getDate("dob").toLocalDate(),
                    rs.getDouble("salary")
                ));
            }
        }catch(Exception e){e.printStackTrace();}
        return list;
    }
    public boolean insert(Employee e, Address a){
        String empSql="INSERT INTO employees(empid,firstName,lastName,ssn,dob,salary) VALUES(?,?,?,?,?,?)";
        String addrSql="INSERT INTO address(empid,street,city,state,zip) VALUES(?,?,?,?,?)";
        try(Connection c=DBConnectionManager.getConnection()){
            c.setAutoCommit(false);
            try(PreparedStatement ps1=c.prepareStatement(empSql);
                PreparedStatement ps2=c.prepareStatement(addrSql)){
                ps1.setInt(1,e.getEmpId());
                ps1.setString(2,e.getFirstName());
                ps1.setString(3,e.getLastName());
                ps1.setString(4,e.getSsn());
                ps1.setDate(5,java.sql.Date.valueOf(e.getDob()));
                ps1.setDouble(6,e.getSalary());
                ps1.executeUpdate();
                ps2.setInt(1,a.getEmpId());
                ps2.setString(2,a.getStreet());
                ps2.setString(3,a.getCity());
                ps2.setString(4,a.getState());
                ps2.setString(5,a.getZip());
                ps2.executeUpdate();
                c.commit();
                return true;
            }catch(Exception ex){
                c.rollback();
                ex.printStackTrace();
            }
        }catch(Exception e1){e1.printStackTrace();}
        return false;
    }
    public boolean update(Employee e){
        String sql="UPDATE employees SET firstName=?, lastName=?, salary=? WHERE empid=?";
        try(Connection c=DBConnectionManager.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,e.getFirstName());
            ps.setString(2,e.getLastName());
            ps.setDouble(3,e.getSalary());
            ps.setInt(4,e.getEmpId());
            return ps.executeUpdate()==1;
        }catch(Exception ex){ex.printStackTrace();}
        return false;
    }
    public boolean adjustSalary(double min,double max,double pct){
        String sql="UPDATE employees SET salary = salary * (1+?/100) WHERE salary BETWEEN ? AND ?";
        try(Connection c=DBConnectionManager.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setDouble(1,pct);
            ps.setDouble(2,min);
            ps.setDouble(3,max);
            int rows=ps.executeUpdate();
            return rows>0;
        }catch(Exception ex){ex.printStackTrace();}
        return false;
    }
}
