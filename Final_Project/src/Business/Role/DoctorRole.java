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
import UserInterface.DoctorWorkArea.DoctorWorkAreaContainerJPanel;
import javax.swing.JPanel;

/**
 *
 * @author Tushar
 */
public class DoctorRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount userAccount, Organization organization, Enterprise enterprise, EcoSystem ecoSystem) {
        return new DoctorWorkAreaContainerJPanel(userAccount, enterprise, ecoSystem);
    }
    @Override
    public String toString() {
        return Role.RoleType.Doctor.getValue();
    }
}
