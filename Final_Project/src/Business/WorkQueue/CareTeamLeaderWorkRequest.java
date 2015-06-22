/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.WorkQueue;

import Business.Person.Doctor;
import Business.UserAccount.UserAccount;
import java.util.Date;

/**
 *
 * @author Tushar
 */
public class CareTeamLeaderWorkRequest extends WorkRequest{
    
    private String message;
    private Date fromOperationDate;
    private Date toOperationDate;
    private String operationDate;
    private int numberOfStaffMembers;
    private String patientName;
    private String operation;
    private int roomNumber;
    private UserAccount doctor;

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public UserAccount getDoctor() {
        return doctor;
    }

    public void setDoctor(UserAccount doctor) {
        this.doctor = doctor;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getFromOperationDate() {
        return fromOperationDate;
    }

    public void setFromOperationDate(Date fromOperationDate) {
        this.fromOperationDate = fromOperationDate;
    }

    public Date getToOperationDate() {
        return toOperationDate;
    }

    public void setToOperationDate(Date toOperationDate) {
        this.toOperationDate = toOperationDate;
    }

    public int getNumberOfStaffMembers() {
        return numberOfStaffMembers;
    }

    public void setNumberOfStaffMembers(int numberOfStaffMembers) {
        this.numberOfStaffMembers = numberOfStaffMembers;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    
}
