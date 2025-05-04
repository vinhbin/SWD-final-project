
package com.companyz.app.model;
import java.time.LocalDate;
public class Employee {
    private int empId;
    private String firstName;
    private String lastName;
    private String ssn;
    private LocalDate dob;
    private double salary;
    public Employee(int empId,String fn,String ln,String ssn,LocalDate dob,double salary){
        this.empId=empId;
        this.firstName=fn;
        this.lastName=ln;
        this.ssn=ssn;
        this.dob=dob;
        this.salary=salary;
    }
    public int getEmpId(){return empId;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getSsn(){return ssn;}
    public LocalDate getDob(){return dob;}
    public double getSalary(){return salary;}
    public void setSalary(double s){this.salary=s;}
}
