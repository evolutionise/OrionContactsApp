package com.orion.alixk.contacts;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class ContactPage extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);

        Intent intent = getIntent();
        ContactObject contact = (ContactObject) intent.getSerializableExtra(Constants.CONTACT_KEY);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle(contact.getFullName());

        populateTextFields(contact);

    }

    private void populateTextFields(ContactObject contact) {
        //username
        TextView userNameText = (TextView) findViewById(R.id.username_text);
        userNameText.setText(contact.getUserName());

        TextView userNameLabel = (TextView) findViewById(R.id.username_label);
        userNameLabel.setText(Constants.USERNAME);

        //phone
        TextView phoneText = (TextView) findViewById(R.id.phone_text);
        phoneText.setText(contact.getPhoneNumber());

        TextView phoneLabel = (TextView) findViewById(R.id.phone_label);
        phoneLabel.setText(Constants.PHONE);

        //address
        TextView addressText = (TextView) findViewById(R.id.address_text);
        addressText.setText(formatAddress(contact.getAddress()));

        TextView addressLabel = (TextView) findViewById(R.id.address_label);
        addressLabel.setText(Constants.ADDRESS);

        //website
        TextView websiteText = (TextView) findViewById(R.id.website_text);
        websiteText.setText(contact.getWebsite());

        TextView websiteLabel = (TextView) findViewById(R.id.website_label);
        websiteLabel.setText(Constants.WEBSITE);

        //company
        TextView companyText = (TextView) findViewById(R.id.company_text);
        companyText.setText(formatCompany(contact.getCompany()));

        TextView companyLabel = (TextView) findViewById(R.id.company_label);
        companyLabel.setText(Constants.COMPANY);

    }

    private String formatAddress(ContactObject.Address address) {
        String formattedAddress = address.getSuite() + ", ";
        formattedAddress += address.getStreet() + ",\n";
        formattedAddress += address.getCity() + ", ";
        formattedAddress += address.getZipcode();
        return formattedAddress;
    }

    private String formatCompany(ContactObject.Company company){
        String formattedCompany = company.getCompanyName() + "\n";
        formattedCompany += company.getCompanyCatchPhrase() + "\n";
        formattedCompany += company.getCompanyBs();
        return formattedCompany;
    }

}
