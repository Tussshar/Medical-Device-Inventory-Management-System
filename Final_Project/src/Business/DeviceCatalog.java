/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Tushar
 */
public class DeviceCatalog {
    
    private ArrayList<Device> deviceList;
        
    public DeviceCatalog(){
        deviceList = new ArrayList<>();
    }

    public ArrayList<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(ArrayList<Device> deviceList) {
        this.deviceList = deviceList;
    }
    
    public void removeProduct(Device device){
        int availability = device.getAvailability() - 1;
        deviceList.remove(device);
        for(Device dev : deviceList){
            if(dev.getDeviceName().equalsIgnoreCase(device.getDeviceName())){
                dev.setAvailability(availability);
            }
        }
    }
    
    public Device addDevice(String deviceName, String deviceType, String companyName, double unitPrice, String countryOfOrigin, Date nextMaintainedDate, int availability){
        
        Device device = new Device();
        device.setDeviceName(deviceName);
        device.setDeviceType(deviceType);
        device.setCompanyName(companyName);
        device.setUnitPrice(unitPrice);
        device.setCountryOfOrigin(countryOfOrigin);        
        device.setNextMaintainedDate(nextMaintainedDate);
        device.setDeviceStatus(String.valueOf(Device.DeviceStatus.Usable.getStatus()));
        Date sysDate = new Date();
        device.setManufacuredDate(sysDate);
        device.setLastMaintainedDate(sysDate);
        device.setAvailability(availability);
        device.setCount(0);
        device.setInStock("YES");
        deviceList.add(device);
        return device;
    }
    
        public Device searchProduct(String  deviceName) {
        
        for(Device device : deviceList) {
            if(device.getDeviceName().equalsIgnoreCase(deviceName)) {
                return device;
            }
        }
        return null;
    }
}
