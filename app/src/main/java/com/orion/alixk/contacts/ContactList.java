package com.orion.alixk.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ContactList extends Activity {
    private ContactArrayAdapter arrayAdapter;
    private  ArrayList<ContactObject> contactList;
    private View spinnerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        spinnerView = (View)findViewById(R.id.spinner_layout);
        spinnerView.setVisibility(View.VISIBLE);
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
        spinnerView.setVisibility(View.GONE);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactObject contact = (ContactObject) parent.getItemAtPosition(position);
                Intent intent = new Intent(ContactList.this, ContactPage.class);
                intent.putExtra(Constants.CONTACT_KEY, contact);
                startActivity(intent);
            }

        });
    }

}
