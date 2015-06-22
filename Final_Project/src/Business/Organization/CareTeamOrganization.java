/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.EcoSystem;
import Business.Role.CareTeamSpecialistRole;
import Business.Role.Role;
import Business.Role.StaffRole;
import Business.WorkQueue.WorkQueue;
import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public class CareTeamOrganization extends Organization{
    
    public CareTeamOrganization(){
        super(OrganizationType.CareTeam.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new CareTeamSpecialistRole());
        roles.add(new StaffRole());
        return roles;
    }
    
}
