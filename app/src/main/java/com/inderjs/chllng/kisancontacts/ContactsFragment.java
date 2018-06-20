package com.inderjs.chllng.kisancontacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ContactsFragment extends Fragment{

    ListView listView ;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        // Get ListView object from xml
        listView = (ListView) view.findViewById(R.id.list);

        final List<String> namelist = new ArrayList<String>();
        final List<String> phonelist = new ArrayList<String>();

            Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            while(cursor.moveToNext())
            {
                int phone_idx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int name_idx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String phone = cursor.getString(phone_idx);
                String name = cursor.getString(name_idx);

                namelist.add(name);

                phonelist.add(phone);

            }
            cursor.close();



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                namelist);

        listView.setAdapter(arrayAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent contactPage = new Intent(getActivity(), ContactsInfoPage.class);
                contactPage.putExtra("Name",namelist.get(i));
                contactPage.putExtra("Phone",phonelist.get(i));

                startActivity(contactPage);


            }
        });



        return view;
    }

}