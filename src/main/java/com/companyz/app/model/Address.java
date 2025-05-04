
package com.companyz.app.model;
public class Address{
    private int empId;
    private String street;
    private String city;
    private String state;
    private String zip;
    public Address(int empId,String street,String city,String state,String zip){
        this.empId=empId;this.street=street;this.city=city;this.state=state;this.zip=zip;
    }
    public int getEmpId(){return empId;}
    public String getStreet(){return street;}
    public String getCity(){return city;}
    public String getState(){return state;}
    public String getZip(){return zip;}
}
