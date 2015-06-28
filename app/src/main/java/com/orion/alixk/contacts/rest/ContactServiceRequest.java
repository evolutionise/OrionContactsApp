package com.orion.alixk.contacts.rest;

import android.os.AsyncTask;

import com.orion.alixk.contacts.util.Constants;
import com.orion.alixk.contacts.frontend.ContactListActivity;
import com.orion.alixk.contacts.model.ContactObject;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@EBean
public class ContactServiceRequest {

    private static final ContactsJSONParser PARSER = new ContactsJSONParser();
    @RootContext
    ContactListActivity mainActivity;

    public void establishConnection(){

        try {
            URL url = new URL(Constants.URL_ADDRESS);
            new makeRestCall().execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private class makeRestCall extends AsyncTask<URL, ArrayList<ContactObject>, ArrayList<ContactObject>>{

        @Override
        protected ArrayList<ContactObject> doInBackground(URL... params) {

            URL url = params[0];
            ArrayList<ContactObject> contactList = new ArrayList<ContactObject>();
            HttpURLConnection httpConnection = null;

            try {
                httpConnection = (HttpURLConnection) url.openConnection();
                httpConnection.setConnectTimeout(5000);
                BufferedInputStream httpResponseStream = new BufferedInputStream(httpConnection.getInputStream());
                contactList = PARSER.parseContactList(httpResponseStream);
                mainActivity.populateContactsList(contactList);
            } catch (IOException e){
                mainActivity.showConnectionFailedDialog();
            } finally {
                httpConnection.disconnect();
            }
            return contactList;
        }
    }
}
