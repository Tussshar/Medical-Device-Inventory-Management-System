/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Role;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Person.Person;
import Business.UserAccount.UserAccount;
import UserInterface.HospitalEnterpriseAdminWorkArea.HospitalEnterpriseAdminWorkAreaContainerJPanel;
import UserInterface.HospitalEnterpriseAdminWorkArea.HospitalEnterpriseAdminWorkAreaJPanel;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Tushar
 */
public class HospitalEnterpriseAdminRole extends Role{

    private ArrayList<Person> personDirectory;
    private ArrayList<UserAccount> userAccountDirectory;
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount userAccount, Organization organization, Enterprise enterprise, EcoSystem ecoSystem) {
        personDirectory = enterprise.getPersonDirectory().getPersonList();
        userAccountDirectory = enterprise.getUserAccountDirectory().getUserAccountList();
        return new HospitalEnterpriseAdminWorkAreaContainerJPanel(userAccount, enterprise, ecoSystem);
    }    
    
    @Override
    public String toString() {
        return Role.RoleType.HospitalEnterpriseAdmin.getValue();
    }
}
