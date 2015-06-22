/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Person;

import Business.DeviceCatalog;
import Business.EcoSystem;

/**
 *
 * @author Tushar
 */
public class Supplier extends Person{
    
    private DeviceCatalog deviceCatalog;
    private String isEnrolled;
    private EcoSystem ecoSystem = EcoSystem.getInstance();
    
    public Supplier(){
        deviceCatalog = new DeviceCatalog();
        isEnrolled = "NO";
    }

    public DeviceCatalog getDeviceCatalog() {
        return deviceCatalog;
    }

    public void setDeviceCatalog(DeviceCatalog deviceCatalog) {
        this.deviceCatalog = deviceCatalog;
    }

    public String getIsEnrolled() {
        return isEnrolled;
    }

    public void setIsEnrolled(String isEnrolled) {
        this.isEnrolled = isEnrolled;
    }
    
    public enum EnrollmentStatus{
        YES("YES"),
        NO("NO");
        
        private String status;
        
        private EnrollmentStatus(String status){
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return status;
        }
        
        
    }
    
}
