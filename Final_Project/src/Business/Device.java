/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Tushar
 */
public class Device {

    private String deviceName;
    private String deviceType;
    private String deviceStatus;
    private String uniqueDeviceIdentifier;
    private String uniqueDeviceID;
    private String companyName;
    private double unitPrice;
    private String countryOfOrigin;
    private Date lastMaintainedDate;
    private Date nextMaintainedDate;
    private Date manufacuredDate;
    private String inStock;
    private int availability;
    private int count;// Used for ArrayList
    
    private EcoSystem ecoSystem = EcoSystem.getInstance();
    private boolean check;
    
    public Device() {

        label :
        do {
        check = true;
        count = 0;
            uniqueDeviceIdentifier = generateUniqueID();
            if(ecoSystem.getUDIList().isEmpty()){
                ecoSystem.getUDIList().add(uniqueDeviceIdentifier);
                break label;
            }
//            for (UniqueDeviceID uID : ecoSystem.getUDIList()) {
//                uniqueDeviceID = uID.getUniqueDeviceIdentifier();
//                if (uniqueDeviceID.equalsIgnoreCase(uniqueDeviceIdentifier)) {
//                    continue label;
//                } else {
//                    break label;
//                }
//            }
            for(String uID : ecoSystem.getUDIList()){
                uniqueDeviceID = uID;
                if(uniqueDeviceID.equalsIgnoreCase(uniqueDeviceIdentifier)){
                    count++;
                }
            }
            if(count == 0){
                ecoSystem.getUDIList().add(uniqueDeviceIdentifier);
                break label;
            }else{
                continue label;
            }
        } while (true);
    }

    public enum DeviceStatus{
        Usable("Usable"),
        Maintenance("Under Maintainance"),
        Nonusable("Non Usable");
        
        private String status;
        
        private DeviceStatus(String status){
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
    
    public enum TypeOfDevice{
        ClassI("Class I"),
        ClassII("Class II"),
        ClassIII("Class III");
        
        private String type;
        
        private TypeOfDevice(String type){
            this.type = type;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return type;
        }
    }
    
    private String generateUniqueID() {
        UUID uniqueID = UUID.randomUUID();
        return String.valueOf(uniqueID);
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getUniqueDeviceIdentifier() {
        return uniqueDeviceIdentifier;
    }

    public void setUniqueDeviceIdentifier(String uniqueDeviceIdentifier) {
        this.uniqueDeviceIdentifier = uniqueDeviceIdentifier;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Date getLastMaintainedDate() {
        return lastMaintainedDate;
    }

    public void setLastMaintainedDate(Date lastMaintainedDate) {
        this.lastMaintainedDate = lastMaintainedDate;
    }

    public Date getNextMaintainedDate() {
        return nextMaintainedDate;
    }

    public void setNextMaintainedDate(Date nextMaintainedDate) {
        this.nextMaintainedDate = nextMaintainedDate;
    }

    public Date getManufacuredDate() {
        return manufacuredDate;
    }

    public void setManufacuredDate(Date manufacuredDate) {
        this.manufacuredDate = manufacuredDate;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
        
    @Override
    public String toString() {
        return deviceName;
    }
}
