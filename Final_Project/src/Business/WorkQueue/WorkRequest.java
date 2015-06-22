/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.WorkQueue;

import Business.Order;
import Business.UserAccount.UserAccount;
import java.util.Date;

/**
 *
 * @author Tushar
 */
public class WorkRequest {
    
    private String status;

    private Date requestDate;
    private Date resolveDate;
    private String testResults;
    private static int count;
    private int requestID;
    
    public String getStatus() {
        count++;
        requestID = count;
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Date resolveDate) {
        this.resolveDate = resolveDate;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }
    
    public enum TypeOfStatus{
        
        Pending("Pending"),
        InProgress("In Progress"),
        Completed("Completed");
        
        private String value;
        
        private TypeOfStatus(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public enum TypeOfResult{
        Approve("Approved"),
        Reject("Rejected");
        
        private String value;
        
        private TypeOfResult(String value){
            this.value = value;
        }
        
        public String getValue(){
            return value;
        }
        
        @Override
        public String toString() {
            return value;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(requestID);
    }
    
    
}
