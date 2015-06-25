package com.orion.alixk.contacts;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("email")
    public ContactObject setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
        return this;
    }

    @JsonProperty("username")
    public ContactObject setUserName(String userName){
        this.userName = userName;
        return this;
    }

    @JsonProperty("phone")
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

    @JsonProperty("company")
    public ContactObject setCompany(Company company) {
        this.company = company;
        return this;
    }

    public String getFullName() {
        return name;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public static class Company {
        String companyName;
        String companyCatchPhrase;
        String companyBs;

        public String getCompanyName() {
            return companyName;
        }

        @JsonProperty("name")
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyCatchPhrase() {
            return companyCatchPhrase;
        }

        @JsonProperty("catchPhrase")
        public void setCompanyCatchPhrase(String companyCatchPhrase) {
            this.companyCatchPhrase = companyCatchPhrase;
        }

        @JsonProperty("bs")
        public String getCompanyBs() {
            return companyBs;
        }

        public void setCompanyBs(String companyBs) {
            this.companyBs = companyBs;
        }

    }


    public static class Address {
        String street;
        String suite;
        String city;
        String zipcode;
        Geo geo;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getSuite() {
            return suite;
        }

        public void setSuite(String suite) {
            this.suite = suite;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public Geo getGeo() {
            return geo;
        }

        public void setGeo(Geo geo) {
            this.geo = geo;
        }


        public static class Geo {
            String latitude;
            String longitude;

            @JsonProperty("lat")
            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            @JsonProperty("lng")
            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }
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
