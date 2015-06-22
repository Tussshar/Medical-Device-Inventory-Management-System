/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.EcoSystem;
import Business.Person.PersonDirectory;
import Business.Role.Role;
import Business.UserAccount.UserAccountDirectory;
import Business.WorkQueue.WorkQueue;
import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public abstract class Organization {
    private String organizationName;
    private PersonDirectory personDirectory;
    private UserAccountDirectory userAccountDirectory;
    private WorkQueue workQueue;
    private int organizationID;
    private static int counter = 0;
    
    public Organization(String organizationName){
        this.organizationName = organizationName;
        
        personDirectory = new PersonDirectory();
        userAccountDirectory = new UserAccountDirectory();
        workQueue = new WorkQueue();
    
        organizationID = ++counter;
    }
    
    public enum OrganizationType{
        Account("Account Organization"),
        CareTeam("Care Team Organization"),
        Doctor("Doctor Organization"),
        HospitalEnterpriseAdmin("Hospital Enterprise Admin Organization"),
        Inventory("Inventory Organization"),
        Sales("Sales Organization"),
        SupplierEnterpriseAdmin("Supplier Enterprise Admin Organization"),
        Supplier("Supplier Organization"),
        SystemAdmin("System Admin Organization"),
        WareHouseEnterpriseAdmin("WareHouse Enterprise Admin Organization");
        
        private String value;
        
        private OrganizationType(String value){
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
    
    public abstract ArrayList<Role> getSupportedRole();

    public int getOrganizationID() {
        return organizationID;
    }
    
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public PersonDirectory getPersonDirectory() {
        return personDirectory;
    }

    public void setPersonDirectory(PersonDirectory personDirectory) {
        this.personDirectory = personDirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public void setUserAccountDirectory(UserAccountDirectory userAccountDirectory) {
        this.userAccountDirectory = userAccountDirectory;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    @Override
    public String toString() {
        return organizationName;
    }
    
}
