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
import UserInterface.WareHouseEnterpriseAdminWorkArea.WareHouseEnterpriseAdminWorkAreaContainerJPanel;
import javax.swing.JPanel;

/**
 *
 * @author Tushar
 */
public class WareHouseEnterpriseAdminRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount userAccount, Organization organization, Enterprise enterprise, EcoSystem ecoSystem) {
        return new WareHouseEnterpriseAdminWorkAreaContainerJPanel(userAccount, enterprise, ecoSystem);
    }
    
    @Override
    public String toString() {
        return Role.RoleType.WareHouseEnterpriseAdmin.getValue();
    }
}
