
package com.companyz.app.dao;
import java.sql.*;
public class AuthDAO{
    public String findRole(String username,String password){
        String sql="SELECT role FROM users WHERE username=? AND password_hash=SHA2(?,256)";
        try(Connection c=DBConnectionManager.getConnection();
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,username);ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) return rs.getString(1);
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
}
