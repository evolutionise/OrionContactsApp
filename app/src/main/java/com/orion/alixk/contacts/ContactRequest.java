package com.orion.alixk.contacts;

import android.os.AsyncTask;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@EBean
public class ContactRequest {
    private static final String URL_ADDRESS = "http://jsonplaceholder.typicode.com/users";
    private static final ContactsJSONParser PARSER = new ContactsJSONParser();
    @RootContext
    ContactList mainActivity;

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

    private void httpCallFailed(){
        
    }



}
