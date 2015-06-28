package com.orion.alixk.contacts.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orion.alixk.contacts.model.ContactObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by alixk on 3/06/15.
 *
 * Parses an input stream of JSON data into an ArrayList of Contact Objects.
 * Uses Jackson parsing library to do so.
 */

public class ContactsJSONParser{

    public ArrayList<ContactObject> parseContactList(BufferedInputStream input){

        ArrayList<ContactObject> contacts = new ArrayList<ContactObject>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            contacts = mapper.readValue(input, new TypeReference<ArrayList<ContactObject>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contacts;
    }



}
