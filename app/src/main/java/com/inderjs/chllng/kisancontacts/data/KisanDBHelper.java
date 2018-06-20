package com.inderjs.chllng.kisancontacts.data;

/**
 * Created by Inderjeet Singh on 19-Jun-18.
 */

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;



import com.inderjs.chllng.kisancontacts.data.KisanContract.MsgEntry;



/**

 * Database helper for msg log. Manages database creation and version management.

 */

public class KisanDBHelper extends SQLiteOpenHelper {



    public static final String LOG_TAG = KisanDBHelper.class.getSimpleName();



    /** Name of the database file */

    private static final String DATABASE_NAME = "msglog.db";



    /**

     * Database version. If you change the database schema, you must increment the database version.

     */

    private static final int DATABASE_VERSION = 1;



    /**

     * Constructs a new instance of {@link KisanDBHelper}.

     *

     * @param context of the app

     */

    public KisanDBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }



    /**

     * This is called when the database is created for the first time.

     */

    @Override

    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the msg table

        String SQL_CREATE_MSG_TABLE =  "CREATE TABLE " + MsgEntry.TABLE_NAME + " ("

                + MsgEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + MsgEntry.COLUMN_CONTACT_NAME + " TEXT NOT NULL, "

                + MsgEntry.COLUMN_MSG_TIME + " TEXT, "

                + MsgEntry.COLUMN_OTP + " TEXT);";





        // Execute the SQL statement

        db.execSQL(SQL_CREATE_MSG_TABLE);

    }



    /**

     * This is called when the database needs to be upgraded.

     */

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // The database is still at version 1, so there's nothing to do be done here.

    }

}
