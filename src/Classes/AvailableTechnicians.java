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
public class AvailableTechnicians {
    private int technician_id;
    private String technician_Name;
    private int numberOfActiveJobs;

    public AvailableTechnicians(int technician_id, String technician_Name, int numberOfActiveJobs) {
        this.technician_id = technician_id;
        this.technician_Name = technician_Name;
        this.numberOfActiveJobs = numberOfActiveJobs;
    }

    public int getTechnician_id() {
        return technician_id;
    }

    public String getTechnician_Name() {
        return technician_Name;
    }

    public int getNumberOfActiveJobs() {
        return numberOfActiveJobs;
    }

    public void setTechnician_id(int technician_id) {
        this.technician_id = technician_id;
    }

    public void setTechnician_Name(String technician_Name) {
        this.technician_Name = technician_Name;
    }

    public void setNumberOfActiveJobs(int numberOfActiveJobs) {
        this.numberOfActiveJobs = numberOfActiveJobs;
    }

    @Override
    public String toString() {
        return "AvailableTechnicians{" + "technician_id=" + technician_id + ", technician_Name=" + technician_Name + ", numberOfActiveJobs=" + numberOfActiveJobs + '}';
    }
    
    
}
