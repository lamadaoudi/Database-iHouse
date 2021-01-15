/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;

/**
 *
 * @author Main
 */
public class RepairJob {
    String repair_id,job_status,recieved_date ,closed_date,technician_id ,branch_no, customer_name ;

    public RepairJob(String repair_id, String customer_name, String job_status, String recieved_date, String closed_date, String technician_id, String branch_no) {
        this.repair_id = repair_id;
        this.job_status = job_status;
        this.recieved_date = recieved_date;
        this.closed_date = closed_date;
        this.technician_id = technician_id;
        this.branch_no = branch_no;
        this.customer_name = customer_name;
    }

    public String getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(String repair_id) {
        this.repair_id = repair_id;
    }

    public String getJob_status() {
        return job_status;
    }

    public void setJob_status(String job_status) {
        this.job_status = job_status;
    }

    public String getRecieved_date() {
        return recieved_date;
    }

    public void setRecieved_date(String recieved_date) {
        this.recieved_date = recieved_date;
    }

    public String getClosed_date() {
        return closed_date;
    }

    public void setClosed_date(String closed_date) {
        this.closed_date = closed_date;
    }

    public String getTechnician_id() {
        return technician_id;
    }

    public void setTechnician_id(String technician_id) {
        this.technician_id = technician_id;
    }

    public String getBranch_no() {
        return branch_no;
    }

    public void setBranch_no(String branch_no) {
        this.branch_no = branch_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    @Override
    public String toString() {
        return "RepairJob{" + "repair_id=" + repair_id + ", job_status=" + job_status + ", recieved_date=" + recieved_date + ", closed_date=" + closed_date + ", technician_id=" + technician_id + ", branch_no=" + branch_no + '}';
    }
    
}
