/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author omarq
 */
public class Receipt {
    int ReceiptNo;
    String details;
    String date;
    double amount;

    public Receipt(int ReceiptNo, String details, String date, double amount) {
        this.ReceiptNo = ReceiptNo;
        this.details = details;
        this.date = date;
        this.amount = amount;
    }

    public int getReceiptNo() {
        return ReceiptNo;
    }

    public void setReceiptNo(int ReceiptNo) {
        this.ReceiptNo = ReceiptNo;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}
