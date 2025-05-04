
package com.companyz.app.service;
import com.companyz.app.dao.EmployeeDAO;
import com.companyz.app.model.Employee;
import com.companyz.app.model.Address;
import java.util.*;
public class EmployeeService{
    private final EmployeeDAO dao=new EmployeeDAO();
    public List<Employee> search(String name,String ssn,Integer empId){return dao.search(name,ssn,empId);}
    public boolean add(Employee e, Address a){return dao.insert(e,a);}
    public boolean update(Employee e){return dao.update(e);}
    public boolean adjustSalary(double min,double max,double pct){return dao.adjustSalary(min,max,pct);}
}
