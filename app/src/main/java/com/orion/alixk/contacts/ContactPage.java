package com.orion.alixk.contacts;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class ContactPage extends Activity {
    private final static String usernameLabel = "Username";

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
        userNameLabel.setText(usernameLabel);




    }

}
