package com.example.myapplication2;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;

public class ParseServer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("770c8cefa277b5a213a6d41c6c1a65aca38fb2f4")
                .clientKey("5ef4ae152943ce83ef95661e7131dfb5fffb11f1")
                .server("http://3.133.91.73:80/parse")
                .build()
        );

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}