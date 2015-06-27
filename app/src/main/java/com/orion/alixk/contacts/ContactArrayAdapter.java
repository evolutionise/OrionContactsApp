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
    private Context context;

    ContactArrayAdapter(Context context, ArrayList<ContactObject> contacts){
            super(context, -1, contacts);
            this.context = context;
    }

    private class ViewHolder{
        TextView nameView;
        TextView emailView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        ContactObject contact = getItem(position);

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contact_list_item, parent, false);

            holder = new ViewHolder();
            holder.emailView = (TextView) convertView.findViewById(R.id.contact_list_item_email_address);
            holder.nameView = (TextView) convertView.findViewById(R.id.contact_list_item_names);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameView.setText(contact.getFullName());
        holder.emailView.setText(contact.getEmailAddress());



        return convertView;
    }

}
