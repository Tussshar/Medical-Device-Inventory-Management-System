/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Enterprise;

import Business.EcoSystem;
import Business.Role.Role;
import Business.Role.SupplierEnterpriseAdminRole;
import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public class SupplierEnterprise extends Enterprise{

    
    public SupplierEnterprise(String name, EnterpriseType enterpriseType) {
        super(name, enterpriseType);
    }

    

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new SupplierEnterpriseAdminRole());
        return roles;
    }
    
}
