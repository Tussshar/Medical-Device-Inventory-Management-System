/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Enterprise.Enterprise;
import Business.Enterprise.HospitalEnterprise;
import Business.Enterprise.WareHouseEnterprise;
import Business.Network.Network;
import Business.Network.NetworkLocation;
import Business.Organization.Organization;
import Business.Person.Person;
import Business.Person.PersonDirectory;
import Business.Role.Role;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccount;
import Business.UserAccount.UserAccountDirectory;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Tushar
 */
public class EcoSystem extends Organization {

    public static EcoSystem ecoSystem;
    private PersonDirectory personDirectory;
    private UserAccountDirectory userAccountDirectory;
    private ArrayList<Network> networkList;
    private ArrayList<Enterprise> enterpriseList;
    private ArrayList<String> UDIList;

    private EcoSystem() {
        super(OrganizationType.SystemAdmin.getValue());
        networkList = new ArrayList<>();
        UDIList = new ArrayList<>();
        enterpriseList = new ArrayList<>();
    }

    public static EcoSystem getEcoSystem() {
        return ecoSystem;
    }

    public static void setEcoSystem(EcoSystem ecoSystem) {
        EcoSystem.ecoSystem = ecoSystem;
    }

    public static boolean sendMail(String from, String password, String message, String to) {
        String host = "smtp.gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, null);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(from));
//                InternetAddress[] toAddress = new InternetAddress(to.length);
//                toAddress = new InternetAddress();
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            mimeMessage.setSubject("Welcome to Partner Health Care System");
            mimeMessage.setText(message);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
//                transport.send(mimeMessage);
            transport.close();
            return true;
        } catch (MessagingException me) {
            me.printStackTrace();
        }

        return false;
    }

    public void maintenanceCheck() {
        for (Enterprise enterprise : ecoSystem.getEnterpriseList()) {
            if (enterprise instanceof WareHouseEnterprise) {
                WareHouseEnterprise wareHouseEnterprise = (WareHouseEnterprise) enterprise;
                for (Device device : wareHouseEnterprise.getDevList()) {
                    Date d = device.getNextMaintainedDate();
                    d.setHours(00);
                    d.setMinutes(00);
                    d.setSeconds(00);
                    if (d.before(new Date())) {
                        device.setDeviceStatus(Device.DeviceStatus.Nonusable.getStatus());
                        for(Person person : wareHouseEnterprise.getPersonDirectory().getPersonList()){
                            String emailAddress = person.getEmailAddress();
                            
                            ecoSystem.sendMail("tusharkale52@gmail.com",
                    "tussh122", "Hello, "+person.getFirstName()+" \nFew have not been maintained properly Please check", emailAddress);
                        } 
                    }
                }
            }
        }
    }

    public ArrayList<String> getUDIList() {
        return UDIList;
    }

    public void setUDIList(ArrayList<String> UDIList) {
        this.UDIList = UDIList;
    }

    public static EcoSystem getInstance() {
        if (ecoSystem == null) {
            ecoSystem = new EcoSystem();
        }
        return ecoSystem;
    }

    public ArrayList<Network> getNetworkList() {
        return networkList;
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }

    public Network createAndAddNetwork(NetworkLocation.LocationsForNetwork name) {
        Network network = new Network();
        network.setName(String.valueOf(name));
        networkList.add(network);
        return network;
    }

    public Enterprise createAndAddWareHouseEnterprise(String name, Enterprise.EnterpriseType enterpriseType) {
        Enterprise enterprise = null;
        if (enterpriseType.getValue().equals(Enterprise.EnterpriseType.WareHouse.getValue())) {
            enterprise = new WareHouseEnterprise(name, enterpriseType);
            enterpriseList.add(enterprise);
        }
        return enterprise;
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new SystemAdminRole());
        return roles;
    }
}
