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
public class RepInfo {
   public int repID;
   public String deviceT;
   public String model;
public RepInfo(){
    repID=-1;
    deviceT="non";
    model="non";
}
    public RepInfo(int repID, String deviceT, String model) {
        this.repID = repID;
        this.deviceT = deviceT;
        this.model = model;
    }
    public int getRepID() {
        return repID;
    }

    public void setRepID(int repID) {
        this.repID = repID;
    }

   
    public String toString() {
        return   "RepairID:"+repID+"  "+deviceT+ model ;
    }

    public String getDeviceT() {
        return deviceT;
    }

    public void setDeviceT(String deviceT) {
        this.deviceT = deviceT;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    
}
