package Classes;

import Classes.Employee;
import java.util.Date;

public class Technician extends Employee {

    protected String certification;
    protected String area_of_specialty;

    public Technician(int id_num, String eName, Date dOB, String dOE, String postion, double salary, Date emp_start_date, String e_mail, String certification, String area_of_specialty) {
        super(id_num, eName, dOB, dOE, postion, salary, emp_start_date, e_mail);
        this.certification = certification;
        this.area_of_specialty = area_of_specialty;
    }

    // ------------------------------------- Getters And Setters  ------------------------------------- //

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getArea_of_specialty() {
        return area_of_specialty;
    }

    public void setArea_of_specialty(String area_of_specialty) {
        this.area_of_specialty = area_of_specialty;
    }

    //------------------------------------- Over-Riding Methods ------------------------------------- //


    @Override
    public String toString() {
        return "Technician{" +
                "certification='" + certification + '\'' +
                ", area_of_specialty='" + area_of_specialty + '\'' +
                ", id_num=" + id_num +
                ", eName='" + eName + '\'' +
                ", DOB=" + DOB +
                ", DOE='" + DOE + '\'' +
                ", Postion='" + Postion + '\'' +
                ", Salary=" + Salary +
                ", Emp_start_date=" + Emp_start_date +
                ", e_mail='" + e_mail + '\'' +
                '}';
    }

    //
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}


