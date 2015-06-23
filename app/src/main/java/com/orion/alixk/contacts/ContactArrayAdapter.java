package com.orion.alixk.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by alixk on 2/06/15.
 */
public class ContactArrayAdapter extends ArrayAdapter<ContactObject>{
    private ArrayList<ContactObject> contacts;
    private Context context;

    ContactArrayAdapter(Context context, ArrayList<ContactObject> contacts){
            super(context, -1, contacts);
            this.contacts = contacts;
            this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.contact_list_item, parent, false);

        TextView nameView = (TextView) rowView.findViewById(R.id.contact_list_item_names);
        nameView.setText(contacts.get(position).getFullName());

        TextView emailView = (TextView) rowView.findViewById(R.id.contact_list_item_email_address);
        emailView.setText(contacts.get(position).getEmailAddress());

        return rowView;
    }

}
