/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Enterprise;

import Business.EcoSystem;
import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public abstract class Enterprise extends Organization{
    
    
    private OrganizationDirectory organizationDirectory;
    EnterpriseType enterpriseType;

    public Enterprise(String name, EnterpriseType enterpriseType){
        super(name);
        this.enterpriseType = enterpriseType;
        organizationDirectory = new OrganizationDirectory();
    }

    

    public enum EnterpriseType{
        Hospital("Hospital Enterprise"),
        WareHouse("WareHouse Enterprise"),
        Supplier("Supplier Enterprise");
        
        private String value;

        private EnterpriseType(String value) {
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
    
    public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }

    public void setOrganizationDirectory(OrganizationDirectory organizationDirectory) {
        this.organizationDirectory = organizationDirectory;
    }
    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
    }
}
