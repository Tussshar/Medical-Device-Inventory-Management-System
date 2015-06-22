/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Business.Enterprise.Enterprise;
import Business.Enterprise.HospitalEnterprise;
import Business.Person.Doctor;
import Business.Person.Staff;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Tushar
 */
public class Schedule {
    
    private Enterprise enterprise;
    private Device device;
    private String date;
    private ArrayList<Staff> staffMembers;
    private Doctor doctor;

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }
    
    

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }    

    public ArrayList<Staff> getStaffMembers() {
        return staffMembers;
    }

    public void setStaffMembers(ArrayList<Staff> staffMembers) {
        this.staffMembers = staffMembers;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
