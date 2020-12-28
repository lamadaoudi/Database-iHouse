/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Main
 */
public class ReplacementParts {
    private String serialNo;
    private String partType;
    private int amountInStock;
    private String compatibility;
    private double price;

    public ReplacementParts(String serialNo, String partType, int amountInStock, String compatibility, double price) {
        this.serialNo = serialNo;
        this.partType = partType;
        this.amountInStock = amountInStock;
        this.compatibility = compatibility;
        this.price = price;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public String getPartType() {
        return partType;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ReplacementParts{" + "serialNo=" + serialNo + ", partType=" + partType + ", amountInStock=" + amountInStock + ", compatibility=" + compatibility + ", price=" + price + '}';
    }
    
    
    
    
    
}
