package com.inderjs.chllng.kisancontacts.data;

import android.provider.BaseColumns;

/**
 * Created by Inderjeet Singh on 19-Jun-18.
 */

public class KisanContract {


    private KisanContract() {}



    /**

     * Inner class that defines constant values for the msg database table.

     * Each entry in the table represents a single msg.

     */

    public static final class MsgEntry implements BaseColumns {



        /** Name of database table for msgs */

        public final static String TABLE_NAME = "msglog";



        /**

         * Unique ID number for the msg (only for use in the database table).

         *

         * Type: INTEGER

         */

        public final static String _ID = BaseColumns._ID;



        /**

         * Name of the contact.

         *

         */

        public final static String COLUMN_CONTACT_NAME ="name";



        /**

         * Time of the msg.

         *

         */

        public final static String COLUMN_MSG_TIME = "time";



        /**

         * OTP sent.

         *

         */

        public final static String COLUMN_OTP = "otp";




    }



}


