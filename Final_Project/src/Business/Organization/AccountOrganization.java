/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.EcoSystem;
import Business.Role.AccountSpecialistRole;
import Business.Role.Role;
import Business.WorkQueue.WorkQueue;
import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public class AccountOrganization extends Organization{

    public AccountOrganization(){
        super(OrganizationType.Account.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new AccountSpecialistRole());
        return roles;
    }
    
}
