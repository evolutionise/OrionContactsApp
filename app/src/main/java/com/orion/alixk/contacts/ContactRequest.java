package com.orion.alixk.contacts;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by alixk on 2/06/15.
 */
public class ContactRequest {
    private static final String URL_ADDRESS = "http://jsonplaceholder.typicode.com/users";
    private static final ContactsJSONParser PARSER = new ContactsJSONParser();
    private ContactList mainActivity;

    public ContactRequest(ContactList mainActivity){
        this.mainActivity = mainActivity;
    }

    public void establishConnection(){

        try {
            URL url = new URL(URL_ADDRESS);
            new HttpConnectionTask().execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    private class HttpConnectionTask extends AsyncTask<URL, ArrayList<ContactObject>, ArrayList<ContactObject>> {


        @Override
        protected ArrayList<ContactObject> doInBackground(URL... params) {

            URL url = params[0];
            ArrayList<ContactObject> contactList = new ArrayList<ContactObject>();
            HttpURLConnection httpConnection = null;

            try {
                httpConnection = (HttpURLConnection) url.openConnection();
                BufferedInputStream httpResponseStream = new BufferedInputStream(httpConnection.getInputStream());
                contactList = PARSER.parseContactList(httpResponseStream);
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                httpConnection.disconnect();
            }

            return contactList;
        }

        protected void onPostExecute(ArrayList<ContactObject> contactList){
            mainActivity.populateContactsList(contactList);
        }

        protected void onPreExecute(){}


    }



}
