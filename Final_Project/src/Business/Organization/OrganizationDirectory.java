/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Organization;

import Business.EcoSystem;
import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public class OrganizationDirectory {
    
    private ArrayList<Organization> organizationList;
    
    public OrganizationDirectory(){
        organizationList = new ArrayList<>();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(ArrayList<Organization> organizationList) {
        this.organizationList = organizationList;
    }
    
    public void createOrganization(Organization.OrganizationType type){
        Organization organization = null;
        
        if(type.getValue().equals(Organization.OrganizationType.Account.getValue())){
            organization  = new AccountOrganization();
            organizationList.add(organization);
        }
        if(type.getValue().equals(Organization.OrganizationType.CareTeam.getValue())){
            organization  = new CareTeamOrganization();
            organizationList.add(organization);
        }
        if(type.getValue().equals(Organization.OrganizationType.Doctor.getValue())){
            organization  = new DoctorOrganization();
            organizationList.add(organization);
        }
        if(type.getValue().equals(Organization.OrganizationType.Inventory.getValue())){
            organization  = new InventoryOrganization();
            organizationList.add(organization);
        }
        if(type.getValue().equals(Organization.OrganizationType.Sales.getValue())){
            organization  = new SalesOrganization();
            organizationList.add(organization);
        }
        if(type.getValue().equals(Organization.OrganizationType.Supplier.getValue())){
            organization  = new SupplierOrganization();
            organizationList.add(organization);
        }
    }
}
