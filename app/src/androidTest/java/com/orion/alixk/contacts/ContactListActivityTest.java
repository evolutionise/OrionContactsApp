package com.orion.alixk.contacts;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;

import com.orion.alixk.contacts.frontend.ContactArrayAdapter;
import com.orion.alixk.contacts.frontend.ContactListActivity_;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ContactListActivityTest extends ActivityInstrumentationTestCase2 {

    public ContactListActivityTest() {
        super(ContactListActivity_.class);
    }
    private ContactListActivity_ contactListActivity;
    private  ListView listViewOfContacts;
    private View loadingView;
    private ContactArrayAdapter arrayAdapter;

    protected void setUp() throws Exception {
        super.setUp();
        contactListActivity = (ContactListActivity_) getActivity();
        loadingView = (View) contactListActivity.findViewById(R.id.spinner_layout);
        listViewOfContacts = (ListView) contactListActivity.findViewById(R.id.contacts_list_view);
        arrayAdapter = (ContactArrayAdapter) listViewOfContacts.getAdapter();
    }

    public void testActivityLoads() {
        getInstrumentation().waitForIdleSync();
        assertTrue(loadingView.isShown());
    }

    public void testContactsAreLoaded(){
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(6000);
                        assertFalse(arrayAdapter.isEmpty());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


}