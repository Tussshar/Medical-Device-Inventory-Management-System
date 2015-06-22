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
public class Order {

    private ArrayList<OrderItem> orderItem;
    private static int count;
    private int orderID;

    public Order() {
        count++;
        orderID = count;
        orderItem = new ArrayList<OrderItem>();
    }

    public void modifyCount() {
        count--;
        orderID = count;
    }

    public ArrayList<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(ArrayList<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public OrderItem addOrderItem() {
        OrderItem orderItem = new OrderItem();
        this.orderItem.add(orderItem);
        return orderItem;
    }
    public void addMocOrderItem(OrderItem orderItem){
        this.orderItem.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem) {
        this.orderItem.remove(orderItem);
    }

    @Override
    public String toString() {
        return String.valueOf(orderID);
    }
}
