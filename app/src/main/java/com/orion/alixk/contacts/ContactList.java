package com.orion.alixk.contacts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ContactList extends Activity {
    private ContactArrayAdapter arrayAdapter;
    private  ArrayList<ContactObject> contactList;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        spinner = (ProgressBar)findViewById(R.id.progress_bar);
        spinner.setVisibility(View.VISIBLE);
        new ContactRequest(this).establishConnection();
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

        arrayAdapter.notifyDataSetChanged();
        return true;
    }

    private void sortContactsAlphabetically(final boolean alphabetical){
        Collections.sort(contactList, new Comparator<ContactObject>() {
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
        this.contactList = contactList;
        arrayAdapter = new ContactArrayAdapter(this, contactList);
        final ListView listView = (ListView) findViewById(R.id.contacts_list_view);
        spinner.setVisibility(View.GONE);
        listView.setAdapter(arrayAdapter);
    }

}
