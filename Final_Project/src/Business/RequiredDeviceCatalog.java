/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public class RequiredDeviceCatalog {
    
    private ArrayList<Device> deviceList;
    
    public RequiredDeviceCatalog(){
        deviceList = new ArrayList<>();
    }

    public ArrayList<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(ArrayList<Device> deviceList) {
        this.deviceList = deviceList;
    }
    
    public void addDevice(Device device){
        this.deviceList.add(device);
    }
    
    public void removeDevice(Device device){
        this.deviceList.remove(device);
    }
}