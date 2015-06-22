/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import Business.Person.Supplier;
import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public class OrderItem {
    private Device device;
    private int quantity;
    private Supplier supplier;
    private ArrayList<String> uIDList;
    
    public OrderItem(){
        uIDList = new ArrayList<>();
    }
    
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ArrayList<String> getuIDList() {
        return uIDList;
    }

    public void setuIDList(ArrayList<String> uIDList) {
        this.uIDList = uIDList;
    }

    
    @Override
    public String toString() {
        return device.getDeviceName();
    }    
    
}
