package com.orion.alixk.contacts;

import android.location.Address;
import android.util.JsonReader;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by alixk on 3/06/15.
 */
public class ContactsJSONParser{

    public ArrayList<ContactObject> parseContactList(BufferedInputStream input){

        ArrayList<ContactObject> contacts = new ArrayList<ContactObject>();
        ObjectMapper mapper = new ObjectMapper();
        ContactObject contact = mapper.readValue(input, ContactObject.class);

        try {
            JsonReader reader = new JsonReader(new InputStreamReader(input, "UTF-8"));
            JSONArray jsonArray = new JSONArray();
            reader.beginArray();
            ContactObject newContact = new ContactObject();
            while(reader.hasNext()) {
                reader.beginObject();
                newContact.setId(reader.nextInt());
                newContact.setName(reader.nextString());
                newContact.setEmailAddress(reader.nextString());

                //address is a nested object
                Address newAddress = new Address(Locale.US);
                reader.beginObject();
                newAddress.setAddressLine(0, reader.nextString());
                newAddress.setAddressLine(1, reader.nextString());
                newAddress.setAddressLine(2, reader.nextString());
                newAddress.setPostalCode(reader.nextString());
                reader.beginObject();
                newAddress.setLatitude(reader.nextDouble());
                newAddress.setLongitude(reader.nextDouble());
                newContact.setAddress(newAddress);
                reader.endObject();
                reader.endObject();

                newContact.setPhoneNumber(reader.nextString());
                newContact.setWebsite(reader.nextString());

                reader.beginObject();
                newContact.setCompany(
                        reader.nextString(), reader.nextString(), reader.nextString()
                );
                reader.endObject();
                reader.endObject();
            }
            reader.endArray();
        } catch (IOException e){
            e.printStackTrace();
        }


        return contacts;
    }



}
