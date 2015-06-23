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
    private ContactsJSONParser parser;
    private ContactList mainActivity;

    public ContactRequest(ContactList mainActivity, ContactsJSONParser parser){
        this.mainActivity = mainActivity;
        this.parser = parser;
    }

    public void establishConnection(){

        try {
            URL url = new URL(URL_ADDRESS);
            new HttpConnectionTask().execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    private class HttpConnectionTask extends AsyncTask<URL, Void, Void> {


        @Override
        protected Void doInBackground(URL... params) {

            URL url = params[0];

            HttpURLConnection connection = connectToUrl(url);

            try {
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedInputStream httpResponseStream = retrieveDataFromURL(connection);
                    ArrayList<ContactObject> contactList = parser.parseContactList(httpResponseStream);
                    mainActivity.populateContactsList(contactList);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(){}

        protected void onPreExecute(){}

        protected HttpURLConnection connectToUrl(URL url)  {

            try {

                return (HttpURLConnection) url.openConnection();

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected BufferedInputStream retrieveDataFromURL(HttpURLConnection httpConnection)throws IOException {

            try {
                return new BufferedInputStream(httpConnection.getInputStream());

            } catch (IOException e){
                e.printStackTrace();
                return null;
            } finally {
                httpConnection.disconnect();
            }
        }

    }



}
