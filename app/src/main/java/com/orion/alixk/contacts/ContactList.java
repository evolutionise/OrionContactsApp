package com.orion.alixk.contacts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ContactList extends Activity {
    private ContactArrayAdapter adapter;
    private  ArrayList<ContactObject> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ContactsJSONParser parser = new ContactsJSONParser();
        new ContactRequest(this, parser).establishConnection();
    }

    /*
    On creation of the activity, the options menu is created as well
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contact_list, menu);
        return super.onCreateOptionsMenu((menu));
    }

    /*
    When an option is selected, one of the cases is activated.
    TODO: add a default
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        boolean alphabetical;
        switch(item.getItemId()) {
            case R.id.az_sort:
                sortContactsAlphabetically(true);
                break;
            case R.id.za_sort:
                sortContactsAlphabetically(false);
        }

        adapter.notifyDataSetChanged();
        return true;
    }

    private void sortContactsAlphabetically(final boolean alphabetical){
        Collections.sort(contacts, new Comparator<ContactObject>() {
            public int compare(ContactObject contact1, ContactObject contact2) {
                int comparisonResult = contact1.getFullName().compareTo(contact2.getFullName());

                if (!alphabetical) {
                    comparisonResult *= -1;
                }

                return comparisonResult;
            }
        });
    }

    public void populateContactsList(ArrayList<ContactObject> contactList) {
        ContactArrayAdapter arrayAdapter = new ContactArrayAdapter(this,contacts);
        final ListView listView = (ListView) findViewById(R.id.contacts_list_view);

        listView.setAdapter(adapter);

    }

    private void removeLoadingScreen(){

    }
}
