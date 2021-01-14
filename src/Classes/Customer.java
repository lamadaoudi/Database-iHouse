package Classes;



public class Customer {
    private int customer_id;
    private String customer_name;
    private String address;
    private int customer_phone;

    public Customer(int customer_id, int customer_phone) {
        this.customer_id = customer_id;
        this.customer_phone = customer_phone;
    }

    public Customer(int customer_id, String customer_name, String address) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        // this.customer_phone = customer_phone;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(int customer_phone) {
        this.customer_phone = customer_phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}