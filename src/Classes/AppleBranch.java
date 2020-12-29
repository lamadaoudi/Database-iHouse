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
public class AppleBranch {
    private int branch_no;
    private String location;
    private String contactInfo;

    public AppleBranch(int branch_no, String location, String contactInfo) {
        this.branch_no = branch_no;
        this.location = location;
        this.contactInfo = contactInfo;
    }

    public void setBranch_no(int branch_no) {
        this.branch_no = branch_no;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getBranch_no() {
        return branch_no;
    }

    public String getLocation() {
        return location;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    @Override
    public String toString() {
        return "AppleBranch{" + "branch_no=" + branch_no + ", location=" + location + ", contactInfo=" + contactInfo + '}';
    }
    
}
