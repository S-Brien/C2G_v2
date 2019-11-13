package com.example.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class DatabaseManager extends SQLiteOpenHelper {





    public static final String DATABASE_NAME = "App Database";

    //Customer information Database.
    public static final String TABLE_NAME = "Account_Info";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Username";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "Password";

    //Listings information database.
    public static final String LISTINGS_TABLE = "Listings_Info";
    public static final String listings_COL_1 = "Property ID#";
    public static final String listings_COL_2 = "Address";
    public static final String listings_COL_3 = "Price per month";


    public DatabaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE [" + TABLE_NAME + "](ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
        db.execSQL("CREATE TABLE [" + LISTINGS_TABLE + "](ID INTEGER PRIMARY KEY AUTOINCREMENT, ADDRESS VARCHAR, PRICE VARHCAR, IMAGE BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LISTINGS_TABLE);
        onCreate(db);
    }

    public boolean insertData(String username, String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, password);
        Long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1) {
            return false;
        }
        else {
            return true;

        }
    }

    public Cursor getInfo(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT ID FROM " + TABLE_NAME + " ORDER BY ID DESC LIMIT 1", null);
        result.moveToFirst();
        return result;

    }

    public ArrayList<Listing> getLessData() {
        ArrayList<Listing> listingList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+LISTINGS_TABLE,null);

        while(res.moveToNext()) {
            int id = res.getInt(0);   //0 is the number of id column in your database table
            String address = res.getString(1);
            String price = res.getString(2);
            byte[] image = res.getBlob(3);

            System.out.println("TESTING: " + id + " " + address + " " + price);

            Listing newListing = new Listing(id, address, price, image);
            listingList.add(newListing);
        }
        return listingList;
    }

    public ArrayList<Listing> getAllPropertyData() {

        ArrayList<Listing> listingList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+LISTINGS_TABLE,null);


        while(res.moveToNext()) {
            int id = res.getInt(0);   //0 is the number of id column in your database table
            String address = res.getString(1);
            String price = res.getString(2);
            byte[] image = res.getBlob(3);

            System.out.println("TESTING: " + id + " " + address + " " + price);

            Listing newListing = new Listing(id, address, price, image);
            listingList.add(newListing);
        }

        return listingList;
    }

}
