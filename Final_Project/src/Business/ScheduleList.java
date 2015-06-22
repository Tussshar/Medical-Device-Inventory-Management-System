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
public class ScheduleList {
    
    private ArrayList<Schedule> scheduleCatalog;
    
    public ScheduleList(){
        scheduleCatalog = new ArrayList<>();
    }

    public ArrayList<Schedule> getScheduleCatalog() {
        return scheduleCatalog;
    }

    public void setScheduleCatalog(ArrayList<Schedule> scheduleCatalog) {
        this.scheduleCatalog = scheduleCatalog;
    }

}
