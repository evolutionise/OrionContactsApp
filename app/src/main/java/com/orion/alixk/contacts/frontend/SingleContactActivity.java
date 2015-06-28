package com.orion.alixk.contacts.frontend;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.orion.alixk.contacts.util.Constants;
import com.orion.alixk.contacts.model.ContactObject;
import com.orion.alixk.contacts.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Activity that displays information about a single contact.
 * Based on the visible information per the Android Junior Test document supplied.
 */

@EActivity
public class SingleContactActivity extends Activity {
    @ViewById(R.id.username_text) TextView userNameText;
    @ViewById(R.id.username_label) TextView userNameLabel;
    @ViewById(R.id.phone_text) TextView phoneText;
    @ViewById(R.id.phone_label) TextView phoneLabel;
    @ViewById(R.id.address_text) TextView addressText;
    @ViewById(R.id.address_label) TextView addressLabel;
    @ViewById(R.id.website_text) TextView websiteText;
    @ViewById(R.id.website_label) TextView websiteLabel;
    @ViewById(R.id.company_text) TextView companyText;
    @ViewById(R.id.company_label) TextView companyLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);

        Intent intent = getIntent();
        ContactObject contact = ContactListActivity.contacts.get((int) intent.getSerializableExtra(Constants.CONTACT_KEY));

        ActionBar actionBar = getActionBar();
        actionBar.setTitle(contact.getFullName());

        populateTextFields(contact);

    }

    private void populateTextFields(ContactObject contact) {

        userNameText.setText(contact.getUserName());
        userNameLabel.setText(Constants.USERNAME);

        phoneText.setText(contact.getPhoneNumber());
        phoneLabel.setText(Constants.PHONE);

        addressText.setText(formatAddress(contact.getAddress()));
        addressLabel.setText(Constants.ADDRESS);

        websiteText.setText(contact.getWebsite());
        websiteLabel.setText(Constants.WEBSITE);

        companyText.setText(formatCompany(contact.getCompany()));
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
