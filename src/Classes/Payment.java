/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author labibah
 */
public class Payment {
    String payment_no,amount,received_date,payment_method,customer_id,repair_id;

    public Payment(String payment_no, String amount, String received_date, String payment_method, String customer_id, String repair_id) {
        this.payment_no = payment_no;
        this.amount = amount;
        this.received_date = received_date;
        this.payment_method = payment_method;
        this.customer_id = customer_id;
        this.repair_id = repair_id;
    }

    public String getPayment_no() {
        return payment_no;
    }

    public void setPayment_no(String payment_no) {
        this.payment_no = payment_no;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReceived_date() {
        return received_date;
    }

    public void setReceived_date(String received_date) {
        this.received_date = received_date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(String repair_id) {
        this.repair_id = repair_id;
    }

    @Override
    public String toString() {
        return "Payment{" + "payment_no=" + payment_no + ", amount=" + amount + ", received_date=" + received_date + ", payment_method=" + payment_method + ", customer_id=" + customer_id + ", repair_id=" + repair_id + '}';
    }
    
}
