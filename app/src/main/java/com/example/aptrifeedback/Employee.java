package com.example.aptrifeedback;

public class Employee {

    String empname;

    String empid;

    public Employee()
    {

    }

    public Employee(String empid,String empname)
    {
        this.empid=empid;
        this.empname=empname;
    }

    public String getEmpname() {
        return empname;
    }

    public String getEmpid() {
        return empid;
    }
}
