
package com.companyz.app.service;
import com.companyz.app.dao.AuthDAO;
public class AuthService{
    private final AuthDAO dao=new AuthDAO();
    public String login(String u,String p){return dao.findRole(u,p);}
}
