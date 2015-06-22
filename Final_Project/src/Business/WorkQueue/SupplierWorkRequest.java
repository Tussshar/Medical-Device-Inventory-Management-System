/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.WorkQueue;

import Business.UserAccount.UserAccount;

/**
 *
 * @author Tushar
 */
public class SupplierWorkRequest extends WorkRequest{
    
    private String Message;
    private UserAccount supplier;
    
    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public UserAccount getSupplier() {
        return supplier;
    }

    public void setSupplier(UserAccount supplier) {
        this.supplier = supplier;
    }
}
