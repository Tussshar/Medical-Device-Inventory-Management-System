/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccount;

import Business.EcoSystem;
import Business.Person.Person;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public class UserAccountDirectory {

    private ArrayList<UserAccount> userAccountList;

    public UserAccountDirectory() {
        userAccountList = new ArrayList<>();
    }

    public ArrayList<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(ArrayList<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }

    public enum AdminAuthentication {

        FirstPassword("123"),
        SecondPassword("456");

        private String value;

        private AdminAuthentication(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public UserAccount authenticateUser(String userName, String password) {
        
            for (UserAccount userAccount : userAccountList) {
                if (userAccount.getUserName().equals(userName) && userAccount.getPassword().equals(password)) {
                    return userAccount;
                }
            }
        return null;
    }

    public UserAccount createAndAddUserAccount(String userName, String password, Person person, Role role) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName(userName);
        userAccount.setPassword(password);
        userAccount.setPerson(person);
        userAccount.setRole(role);
        return userAccount;
    }
}
