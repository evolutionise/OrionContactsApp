package com.orion.alixk.contacts;

import android.location.Address;

import java.util.ArrayList;

/**
 * Created by alixk on 2/06/15.
 */
public class ContactObject {
    private String name;
    private String emailAddress;
    private String userName;
    private String phoneNumber;
    private int id;
    private Address address;
    private String website;
    private Company company;


    public ContactObject setName(String name){
        this.name = name;
        return this;
    }

    public ContactObject setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
        return this;
    }

    public ContactObject setUserName(String userName){
        this.userName = userName;
        return this;
    }

    public ContactObject setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ContactObject setId(int id){
        this.id = id;
        return this;
    }

    public ContactObject setAddress(Address address){
        this.address = address;
        return this;
    }

    public ContactObject setWebsite(String website){
        this.website = website;
        return this;
    }

    public ContactObject setCompany(String companyName, String companyCatchPhrase, String companyBs){
        Company company = new Company(companyName, companyCatchPhrase, companyBs);
        return this;
    }

    public String getFullName() {
        return name;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public class Company {
        String companyName;
        String companyCatchPhrase;
        String companyBs;

        protected Company(String companyName, String companyCatchPhrase, String companyBs){
            this.companyName = companyName;
            this.companyCatchPhrase = companyCatchPhrase;
            this.companyBs = companyBs;
        }
    }

//    public static ContactObject getTestObject(){
//       return new ContactObject("ALIX K", "ALIX@EMAIL.COM");
//    }

    public static ArrayList<ContactObject> getTestList(){
        ArrayList<ContactObject> testContactList = new ArrayList<ContactObject>();
        testContactList.add(new ContactObject().setName("BLIX K").setEmailAddress("ALIXK@EMAIL.COM"));
        testContactList.add(new ContactObject().setName("ALIX L").setEmailAddress("ALIXL@EMAIL.COM"));
        testContactList.add(new ContactObject().setName("DLIX M").setEmailAddress("ALIXM@EMAIL.COM"));
        testContactList.add(new ContactObject().setName("ELIX N").setEmailAddress("ALIXN@EMAIL.COM"));
        testContactList.add(new ContactObject().setName("CLIX O").setEmailAddress("ALIXO@EMAIL.COM"));
        return testContactList;
    }


}
