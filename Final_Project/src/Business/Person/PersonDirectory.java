/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Person;

import Business.EcoSystem;
import Business.Enterprise.HospitalEnterprise;
import Business.Enterprise.SupplierEnterprise;
import Business.Enterprise.WareHouseEnterprise;
import Business.Organization.AccountOrganization;
import Business.Organization.CareTeamOrganization;
import Business.Organization.DoctorOrganization;
import Business.Organization.InventoryOrganization;
import Business.Organization.Organization;
import Business.Organization.SalesOrganization;
import Business.Organization.SupplierOrganization;
import java.util.ArrayList;

/**
 *
 * @author Tushar
 */
public class PersonDirectory {

    private ArrayList<Person> personList;

    public PersonDirectory() {
        personList = new ArrayList<>();
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

//    public Person createAndAddAccountSpecialist(String firstName, String lastName, String streetName, String apartment, String city, String state, String zipCode, String phoneNumber, String emailAddress){
//        Person person = new AccountSpecialist();
//        person.setFirstName(firstName);
//        person.setLastName(lastName);
//        person.setPhoneNumber(String.valueOf(phoneNumber));
//        person.setEmailAddress(emailAddress);
//        person.getAddress().setStreetName(streetName);
//        person.getAddress().setApartment(apartment);
//        person.getAddress().setCity(city);
//        person.getAddress().setState(state);
//        person.getAddress().setZip(zipCode);
//        //personList.add(person);
//        return person;
//    }
//
//    public Person createAndAddCareTeamSpecialist(String firstName, String lastName, String streetName, String apartment, String city, String state, String zipCode, String phoneNumber, String emailAddress){
//        Person person = new CareTeamSpecialist();
//        person.setFirstName(firstName);
//        person.setLastName(lastName);
//        person.setPhoneNumber(String.valueOf(phoneNumber));
//        person.setEmailAddress(emailAddress);
//        person.getAddress().setStreetName(streetName);
//        person.getAddress().setApartment(apartment);
//        person.getAddress().setCity(city);
//        person.getAddress().setState(state);
//        person.getAddress().setZip(zipCode);
//        //personList.add(person);
//        return person;
//    }
//    
//    public Person createAndAddDoctor(String firstName, String lastName, String streetName, String apartment, String city, String state, String zipCode, String phoneNumber, String emailAddress){
//        Person person = new Doctor();
//        person.setFirstName(firstName);
//        person.setLastName(lastName);
//        person.setPhoneNumber(String.valueOf(phoneNumber));
//        person.setEmailAddress(emailAddress);
//        person.getAddress().setStreetName(streetName);
//        person.getAddress().setApartment(apartment);
//        person.getAddress().setCity(city);
//        person.getAddress().setState(state);
//        person.getAddress().setZip(zipCode);
//        //personList.add(person);
//        return person;
//    }
//    
//    public Person createAndAddEnterpriseAdmin(String firstName, String lastName, String streetName, String apartment, String city, String state, String zipCode, String phoneNumber, String emailAddress){
//        Person person = new HospitalEnterpriseAdmin();
//        person.setFirstName(firstName);
//        person.setLastName(lastName);
//        person.setPhoneNumber(String.valueOf(phoneNumber));
//        person.setEmailAddress(emailAddress);
//        person.getAddress().setStreetName(streetName);
//        person.getAddress().setApartment(apartment);
//        person.getAddress().setCity(city);
//        person.getAddress().setState(state);
//        person.getAddress().setZip(zipCode);
//        //personList.add(person);
//        return person;
//    }
//    
//    public Person createAndAddInventorySpecialist(String firstName, String lastName, String streetName, String apartment, String city, String state, String zipCode, String phoneNumber, String emailAddress){
//        Person person = new InventorySpecialist();
//        person.setFirstName(firstName);
//        person.setLastName(lastName);
//        person.setPhoneNumber(String.valueOf(phoneNumber));
//        person.setEmailAddress(emailAddress);
//        person.getAddress().setStreetName(streetName);
//        person.getAddress().setApartment(apartment);
//        person.getAddress().setCity(city);
//        person.getAddress().setState(state);
//        person.getAddress().setZip(zipCode);
//        //personList.add(person);
//        return person;
//    }
//    
//    public Person createAndAddSalesSpecialist(String firstName, String lastName, String streetName, String apartment, String city, String state, String zipCode, String phoneNumber, String emailAddress){
//        Person person = new SalesSpecialist();
//        person.setFirstName(firstName);
//        person.setLastName(lastName);
//        person.setPhoneNumber(String.valueOf(phoneNumber));
//        person.setEmailAddress(emailAddress);
//        person.getAddress().setStreetName(streetName);
//        person.getAddress().setApartment(apartment);
//        person.getAddress().setCity(city);
//        person.getAddress().setState(state);
//        person.getAddress().setZip(zipCode);
//        //personList.add(person);
//        return person;
//    }
//    public Person createAndAddShippingSpecialist(String firstName, String lastName, String streetName, String apartment, String city, String state, String zipCode, String phoneNumber, String emailAddress){
//        Person person = new ShippingSpecialist();
//        person.setFirstName(firstName);
//        person.setLastName(lastName);
//        person.setPhoneNumber(String.valueOf(phoneNumber));
//        person.setEmailAddress(emailAddress);
//        person.getAddress().setStreetName(streetName);
//        person.getAddress().setApartment(apartment);
//        person.getAddress().setCity(city);
//        person.getAddress().setState(state);
//        person.getAddress().setZip(zipCode);
//        personList.add(person);
//        return person;
//    }
//    public Person createAndAddSupplier(String firstName, String lastName, String streetName, String apartment, String city, String state, String zipCode, String phoneNumber, String emailAddress){
//        Person person = new Supplier();
//        person.setFirstName(firstName);
//        person.setLastName(lastName);
//        person.setPhoneNumber(String.valueOf(phoneNumber));
//        person.setEmailAddress(emailAddress);
//        person.getAddress().setStreetName(streetName);
//        person.getAddress().setApartment(apartment);
//        person.getAddress().setCity(city);
//        person.getAddress().setState(state);
//        person.getAddress().setZip(zipCode);
//        //personList.add(person);
//        return person;
//    }
    public Person createAndAddStaffPerson(String firstName, String lastName, String streetName, String apartment, String city, String state, String zipCode, String phoneNumber, String emailAddress, Organization organization) {
        Person person = null;
        if (organization instanceof CareTeamOrganization) {
            person = new Staff();
        }
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPhoneNumber(String.valueOf(phoneNumber));
        person.setEmailAddress(emailAddress);
        person.getAddress().setStreetName(streetName);
        person.getAddress().setApartment(apartment);
        person.getAddress().setCity(city);
        person.getAddress().setState(state);
        person.getAddress().setZip(zipCode);
        return person;
    }

    public Person createAndAddPerson(String firstName, String lastName, String streetName, String apartment, String city, String state, String zipCode, String phoneNumber, String emailAddress, Organization organization) {
        Person person = null;
        if (organization instanceof EcoSystem) {
            person = new SystemAdmin();
        }
        if (organization instanceof HospitalEnterprise) {
            person = new HospitalEnterpriseAdmin();
        }
        if (organization instanceof SupplierEnterprise) {
            person = new SupplierEnterpriseAdmin();
        }
        if (organization instanceof WareHouseEnterprise) {
            person = new WareHouseEnterpriseAdmin();
        }
        if (organization instanceof AccountOrganization) {
            person = new AccountSpecialist();
        }
        if (organization instanceof CareTeamOrganization) {
            person = new CareTeamSpecialist();
        }
        if (organization instanceof DoctorOrganization) {
            person = new Doctor();
        }
        if (organization instanceof InventoryOrganization) {
            person = new InventorySpecialist();
        }
        if (organization instanceof SalesOrganization) {
            person = new SalesSpecialist();
        }
        if (organization instanceof SupplierOrganization) {
            person = new Supplier();
        }
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPhoneNumber(String.valueOf(phoneNumber));
        person.setEmailAddress(emailAddress);
        person.getAddress().setStreetName(streetName);
        person.getAddress().setApartment(apartment);
        person.getAddress().setCity(city);
        person.getAddress().setState(state);
        person.getAddress().setZip(zipCode);
        return person;
    }
}
