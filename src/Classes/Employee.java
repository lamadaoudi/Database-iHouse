package Classes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Main
 */

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee  implements Cloneable{
    protected int id_num;
    protected String eName;
    protected Date DOB;
    protected String DOE;
    protected String Postion;
    protected double Salary;
    protected Date Emp_start_date;
    protected String e_mail;

    public Employee() {
        id_num = 0;
        eName = null;
        DOB = null;
        DOE = null;
        Salary = 0;
        Postion = null;
        Emp_start_date = null;
        e_mail = null;
    }

    public Employee(int id_num, String eName, Date dOB, String dOE, String postion, double salary, Date emp_start_date, String e_mail) {
        super();
        this.id_num = id_num;
        this.eName = eName;
        this.DOB = dOB;
        this.DOE = dOE;
        this.Postion = postion;
        this.Salary = salary;
        this.Emp_start_date = emp_start_date;
        this.e_mail = e_mail;
    }

    public int getId_num() {
        return id_num;
    }

    public void setId_num(int id_num) {
        this.id_num = id_num;
    }

    public String getName() {
        return eName;
    }

    public void seteName(String eNmae) {
        this.eName = eNmae;
    }

    public String getDOB() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(DOB); // formatted date
        return s;
    }

    public void setDOB(Date dOB) {
        DOB = dOB;
    }

    public String getDOE() {
        return DOE;
    }

    public void setDOE(String dOE) {
        DOE = dOE;
    }

    public String getPosition() {
        return Postion;
    }

    public void setPosition(String postion) {
        Postion = postion;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public String getEmp_start_date() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(Emp_start_date); // formatted date
        return s;
    }

    public void setEmp_start_date(Date emp_start_date) {
        Emp_start_date = emp_start_date;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    @Override
    public String toString() {
        return "Employee{" + "id_num=" + id_num + ", eName=" + eName + ", DOB=" + getDOB() + ", DOE=" + DOE + ", Postion=" + Postion + ", Salary=" + Salary + ", Emp_start_date=" + Emp_start_date + ", e_mail=" + e_mail + '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Employee newEmployee;
        newEmployee = (Employee) super.clone();
        return newEmployee;
    }
}
