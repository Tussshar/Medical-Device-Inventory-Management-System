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
public class RoomDirectory {
    
    private ArrayList<Room> roomList;
    
    public RoomDirectory(){
        roomList = new ArrayList<>();
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }
    
    public Room addRoom(int roomNumber, int floorNumber){
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setFloorNumber(floorNumber);
        roomList.add(room);
        return room;
    }
}
