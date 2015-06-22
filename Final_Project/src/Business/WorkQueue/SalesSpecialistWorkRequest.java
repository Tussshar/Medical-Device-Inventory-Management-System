/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Order;
import Business.Person.Supplier;
import Business.UserAccount.UserAccount;

/**
 *
 * @author Tushar
 */
public class SalesSpecialistWorkRequest extends WorkRequest {

    private Order order;
    private Supplier supplier;
    private UserAccount InventorySpecialist;
    private UserAccount salesSpecialist;
    private UserAccount accountSpecialsit;
    private double invoiceAmount;
    private double paidAmount = 0;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public UserAccount getInventorySpecialist() {
        return InventorySpecialist;
    }

    public void setInventorySpecialist(UserAccount InventorySpecialist) {
        this.InventorySpecialist = InventorySpecialist;
    }

    public UserAccount getAccountSpecialsit() {
        return accountSpecialsit;
    }

    public void setAccountSpecialsit(UserAccount accountSpecialsit) {
        this.accountSpecialsit = accountSpecialsit;
    }

    public UserAccount getSalesSpecialist() {
        return salesSpecialist;
    }

    public void setSalesSpecialist(UserAccount salesSpecialist) {
        this.salesSpecialist = salesSpecialist;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
