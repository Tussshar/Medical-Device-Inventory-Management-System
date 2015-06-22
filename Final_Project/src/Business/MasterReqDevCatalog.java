/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public class MasterReqDevCatalog {
    
    private ArrayList<RequiredDeviceCatalog> masterReqDevCatalog;
    
    public MasterReqDevCatalog(){
        masterReqDevCatalog = new ArrayList<>();
    }

    public ArrayList<RequiredDeviceCatalog> getMasterReqDevCatalog() {
        return masterReqDevCatalog;
    }

    public void setMasterReqDevCatalog(ArrayList<RequiredDeviceCatalog> masterReqDevCatalog) {
        this.masterReqDevCatalog = masterReqDevCatalog;
    }
    
    public RequiredDeviceCatalog addRequiredDevice(RequiredDeviceCatalog reqDevCatalog){
        masterReqDevCatalog.add(reqDevCatalog);
        return reqDevCatalog;
    }
}
