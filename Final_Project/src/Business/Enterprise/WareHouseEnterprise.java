/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Enterprise;

import Business.Device;
import Business.EcoSystem;
import Business.Role.Role;
import Business.Role.WareHouseEnterpriseAdminRole;
import Business.ScheduleList;
import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public class WareHouseEnterprise extends Enterprise{

    private ArrayList<Device> devList;
    private ScheduleList scheduleList;
    
    public WareHouseEnterprise(String name, EnterpriseType enterpriseType) {
        super(name, enterpriseType);
        devList = new ArrayList<>();
        scheduleList = new ScheduleList();
    }

    public ScheduleList getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(ScheduleList scheduleList) {
        this.scheduleList = scheduleList;
    }

    public ArrayList<Device> getDevList() {
        return devList;
    }

    public void setDevList(ArrayList<Device> devList) {
        this.devList = devList;
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new WareHouseEnterpriseAdminRole());
        return roles;
    }
    
}
