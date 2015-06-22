/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import UserInterface.SupplierWorkArea.SupplierWorkAreaContainerJPanel;
import javax.swing.JPanel;

/**
 *
 * @author Tushar
 */
public class SupplierRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount userAccount, Organization organization, Enterprise enterprise, EcoSystem ecoSystem) {
        return new SupplierWorkAreaContainerJPanel(userAccount, ecoSystem);
    }
    
    @Override
    public String toString() {
        return Role.RoleType.SupplierSpecialist.getValue();
    }
    
}
