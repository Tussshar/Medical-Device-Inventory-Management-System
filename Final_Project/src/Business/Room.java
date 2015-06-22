/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Tushar
 */
public class Room {

    private int roomNumber;
    private int floorNumber;
    private ArrayList<String> bookedDate;

    public Room() {
        bookedDate = new ArrayList<>();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public ArrayList<String> getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(ArrayList<String> bookedDate) {
        this.bookedDate = bookedDate;
    }

    @Override
    public String toString() {
        return String.valueOf(roomNumber);
    }
    
    
}
