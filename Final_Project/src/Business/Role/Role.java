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
import javax.swing.JPanel;

/**
 *
 * @author Tushar
 */
public abstract class Role {
    
    public enum RoleType{
        AccountSpecialist("Account Specialist Role"),
        CareTeamSpecialsit("Care Team Specialist Role"),
        Doctor("Doctor Role"),
        HospitalEnterpriseAdmin("Hospital Enterprise Admin Role"),
        InventorySpecialist("Inventory Specialist Role"),
        SalesSpecialist("Sales Specialist Role"),
        SupplierEnterpriseAdmin("Supplier Enterprise Admin Role"),
        SupplierSpecialist("Supplier Specialist Role"),
        Staff("Staff"),
        SystemAdmin("System Admin Role"),
        WareHouseEnterpriseAdmin("WareHouse Enterprise Admin Role");
        
        private String value;
        
        private RoleType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public abstract JPanel createWorkArea(
        JPanel userProcessContainer,
            UserAccount userAccount,
            Organization organization,
            Enterprise enterprise,
            EcoSystem ecoSystem
    );
    
    @Override
    public String toString() {
        return this.getClass().getName();
    }  
}
