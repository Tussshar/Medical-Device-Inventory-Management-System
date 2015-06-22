/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Enterprise;

import Business.EcoSystem;
import Business.Organization.OrganizationDirectory;
import Business.Role.HospitalEnterpriseAdminRole;
import Business.Role.Role;
import Business.RoomDirectory;
import java.util.ArrayList;

/**
 *
 * @author Tushar
 */

public class HospitalEnterprise extends Enterprise{

    private RoomDirectory roomDirectory;
    
    public HospitalEnterprise(String name, EnterpriseType enterpriseType) {
        super(name, enterpriseType);
        roomDirectory = new RoomDirectory();
    }

    public RoomDirectory getRoomDirectory() {
        return roomDirectory;
    }

    public void setRoomDirectory(RoomDirectory roomDirectory) {
        this.roomDirectory = roomDirectory;
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new HospitalEnterpriseAdminRole());
        return roles;
    }
}
