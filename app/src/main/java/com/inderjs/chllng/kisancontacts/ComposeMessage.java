package com.inderjs.chllng.kisancontacts;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.inderjs.chllng.kisancontacts.data.KisanContract;
import com.inderjs.chllng.kisancontacts.data.KisanDBHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ComposeMessage extends AppCompatActivity {

    private EditText msgEt;
    private Button sendBtn;
    private OkHttpClient mClient = new OkHttpClient();
    private Context mContext;
    private String Phone , Name;
    private String API_KEY = "inder7MpvZVP4qUjftJbo";
    private String PASS = "R3398P";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);


        Intent intent = getIntent();

        Phone = intent.getStringExtra("Phone");
        Name = intent.getStringExtra("Name");


        msgEt = (EditText)findViewById(R.id.msgEt);
        sendBtn = (Button)findViewById(R.id.sendBtn);
        mContext = getApplicationContext();


        // generating random six digit number

        Random r = new Random();


        final int dig1 = r.nextInt(9);
        final int dig2 = r.nextInt(9);
        final int dig3 = r.nextInt(9);

        final int dig4 = r.nextInt(9);
        final int dig5 = r.nextInt(9);
        final int dig6 = r.nextInt(9);

        final String OTP = dig1+""+dig2+""+dig3+""+dig4+""+dig5+""+dig6;

        msgEt.setText("Hi.  Your  OTP  is: "+OTP);



        final String Sender = "9971792703"; // Number must be registered to send
        final String Reciever = "9971792703";    // Number must be registered to receive



        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {


                            String url ="https://smsapi.engineeringtgr.com/send/?Mobile="+Sender+"&Password="+PASS+"&Message="+msgEt.getText().toString()+"&To="+Reciever+"&Key="+API_KEY;

                            OkHttpClient client=new OkHttpClient();

                            Request request=new Request.Builder().url(url).build();



                            Response response=client.newCall(request).execute();

                            String data=response.body().string();


                            insertMsg(Name, OTP);

                            if (!response.isSuccessful())

                            {

                                Toast.makeText(ComposeMessage.this, "Not Sent", Toast.LENGTH_SHORT).show();

                                throw new IOException("Unexpected code " + response);

                            }


                            Toast.makeText(ComposeMessage.this, "Message sent Successfully!", Toast.LENGTH_SHORT).show();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();





            }
        });


    }






    /**

     * Get user input from editor and save new msg into database.

     */

    private void insertMsg(String Name, String Otp) {

        // Read from input fields

        // Use trim to eliminate leading or trailing white space




        String name = Name ;



        Date currentTime = Calendar.getInstance().getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");
        String currentDateandTime = sdf.format(currentTime);

        String time = currentDateandTime;

        String otp = Otp;



        // Create database helper

        KisanDBHelper mDbHelper = new KisanDBHelper(this);



        // Gets the database in write mode

        SQLiteDatabase db = mDbHelper.getWritableDatabase();



        // Create a ContentValues object where column names are the keys,

        // and pet attributes from the editor are the values.

        ContentValues values = new ContentValues();

        values.put(KisanContract.MsgEntry.COLUMN_CONTACT_NAME, name);

        values.put(KisanContract.MsgEntry.COLUMN_MSG_TIME, time);

        values.put(KisanContract.MsgEntry.COLUMN_OTP, otp);



        // Insert a new row for msg in the database, returning the ID of that new row.

        long newRowId = db.insert(KisanContract.MsgEntry.TABLE_NAME, null, values);



        // Show a toast message depending on whether or not the insertion was successful

        if (newRowId == -1) {

            // If the row ID is -1, then there was an error with insertion.

            Toast.makeText(this, "Error with saving msg", Toast.LENGTH_SHORT).show();

        } else {

            // Otherwise, the insertion was successful and we can display a toast with the row ID.

            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();

        }

    }


}
