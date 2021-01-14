package Classes;

public class Employee_Phone {
    private int emp_id;
    private int phone_num;
    private String eName;
    private String e_mail;

    public Employee_Phone(int phone_num,int emp_id,String eName,String e_mail){
       this.emp_id=emp_id;
        this.phone_num = phone_num;
        this.eName=eName;
        this.e_mail = e_mail;
    }

    public int getPhone_num() {
        return phone_num;
    }

    public String getEName() {
        return eName;
    }

    public void setEName(String eName) {
        this.eName = eName;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public void setPhone_num(int phone_num) {
        this.phone_num = phone_num;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }
}
