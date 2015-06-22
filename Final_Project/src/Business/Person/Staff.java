/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Person;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Tushar
 */
public class Staff extends Person{
    private ArrayList<String> bookedDate;
    
    public Staff(){
        bookedDate = new ArrayList<>();
    }

    public ArrayList<String> getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(ArrayList<String> bookedDate) {
        this.bookedDate = bookedDate;
    }
}
