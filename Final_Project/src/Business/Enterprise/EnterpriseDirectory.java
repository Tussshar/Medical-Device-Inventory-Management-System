/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Enterprise;

import Business.EcoSystem;
import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public class EnterpriseDirectory {
    
    private ArrayList<Enterprise> enterpriseList;
    
    public EnterpriseDirectory(){
        enterpriseList = new ArrayList<>();
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }
    
    public Enterprise createAndAddEnterprise(String name, Enterprise.EnterpriseType enterpriseType){
        Enterprise enterprise = null;
        if (enterpriseType == Enterprise.EnterpriseType.Hospital){
            enterprise = new HospitalEnterprise(name, enterpriseType);
            enterpriseList.add(enterprise);
        }
        if (enterpriseType == Enterprise.EnterpriseType.WareHouse){
            enterprise = new WareHouseEnterprise(name, enterpriseType);
            enterpriseList.add(enterprise);
        }
        if (enterpriseType == Enterprise.EnterpriseType.Supplier){
            enterprise = new SupplierEnterprise(name, enterpriseType);
            enterpriseList.add(enterprise);
        }
        return enterprise;
    }
}
