package com.inderjs.chllng.kisancontacts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.inderjs.chllng.kisancontacts.data.KisanContract;
import com.inderjs.chllng.kisancontacts.data.KisanDBHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MessageFragment extends Fragment{

    /** Database helper that will provide us access to the database */

    private KisanDBHelper mDbHelper;

    private  View view;
    private ListView displayListView;
    final List<String> logList = new ArrayList<String>();

    public MessageFragment() {
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
        view = inflater.inflate(R.layout.fragment_message, container, false);

        displayListView = (ListView) view.findViewById(R.id.list_view_msg);

        mDbHelper = new KisanDBHelper(getActivity());



        readFromDb();

        return view;
    }


    public void readFromDb(){

        // Create and/or open a database to read from it

        SQLiteDatabase db = mDbHelper.getReadableDatabase();



        // Define a projection that specifies which columns from the database

        // you will actually use after this query.

        String[] projection = {

                KisanContract.MsgEntry._ID,

                KisanContract.MsgEntry.COLUMN_CONTACT_NAME,

                KisanContract.MsgEntry.COLUMN_MSG_TIME,

                KisanContract.MsgEntry.COLUMN_OTP};




        // Perform a query on the pets table

        Cursor cursor = db.query(

                KisanContract.MsgEntry.TABLE_NAME,   // The table to query

                projection,            // The columns to return

                null,                  // The columns for the WHERE clause

                null,                  // The values for the WHERE clause

                null,                  // Don't group the rows

                null,                  // Don't filter by row groups

                null);                   // The sort order







        try {

            // Create a header in the Text View that looks like this:

            //

            // The pets table contains <number of rows in Cursor> pets.

            // _id - name - time - otp

            //

            // In the while loop below, iterate through the rows of the cursor and display

            // the information from each column in this order.



            // Figure out the index of each column

            int idColumnIndex = cursor.getColumnIndex(KisanContract.MsgEntry._ID);

            int nameColumnIndex = cursor.getColumnIndex(KisanContract.MsgEntry.COLUMN_CONTACT_NAME);

            int timeColumnIndex = cursor.getColumnIndex(KisanContract.MsgEntry.COLUMN_MSG_TIME);

            int otpColumnIndex = cursor.getColumnIndex(KisanContract.MsgEntry.COLUMN_OTP);




            // Iterate through all the returned rows in the cursor

            while (cursor.moveToNext()) {

                // Use that index to extract the String or Int value of the word

                // at the current row the cursor is on.

                int currentID = cursor.getInt(idColumnIndex);

                String currentName = cursor.getString(nameColumnIndex);

                String currentTime = cursor.getString(timeColumnIndex);

                String currentOtp = cursor.getString(otpColumnIndex);


                logList.add(currentID+" - "+currentName+" - "+currentTime+" - "+currentOtp);



            }

        } finally {

            // Always close the cursor when you're done reading from it. This releases all its

            // resources and makes it invalid.

            cursor.close();


            Collections.reverse(logList); // THIS LINE TO REVERSE ORDER!


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    logList);



            displayListView.setAdapter(arrayAdapter);

        }

    }

    }
