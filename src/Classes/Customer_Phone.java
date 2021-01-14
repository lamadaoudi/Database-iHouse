package Classes;

public class Customer_Phone {
    private int customer_id;
    private String customer_name;
    private int phone_num;

    public Customer_Phone(int customer_id, String customer_name, int phone_num){
        this.customer_id=customer_id;
        this.customer_name=customer_name;
        this.phone_num = phone_num;


    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(int phone_num) {
        this.phone_num = phone_num;
    }


}
