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
public class RepPart {
    String PartName;
    int amount;
    int PricePerUnit;

    public RepPart() {
        amount=0;
        PricePerUnit=0;
        PartName="Non";
    }

    public RepPart(String PartName, int amount, int PricePerUnit) {
        this.PartName = PartName;
        this.amount = amount;
        this.PricePerUnit = PricePerUnit;
    }

    public String getPartName() {
        return PartName;
    }

    public void setPartName(String PartName) {
        this.PartName = PartName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPricePerUnit() {
        return PricePerUnit;
    }

    public void setPricePerUnit(int PricePerUnit) {
        this.PricePerUnit = PricePerUnit;
    }
   public int TotalPrice (int PricePerUnit,int amount){
       return PricePerUnit*amount;
   }
    @Override
    public String toString() {
        return  PartName+" *"+amount+".........................."+ PricePerUnit ;
    }
      
    
}
