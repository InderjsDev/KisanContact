package com.inderjs.chllng.kisancontacts;

import android.app.Application;
import android.support.multidex.MultiDex;

/**
 * Created by Inderjeet Singh on 19-Jun-18.
 */


public class KisanContacts extends Application{

    @Override public void onCreate()
    { super.onCreate();
        MultiDex.install(this);
    }
}