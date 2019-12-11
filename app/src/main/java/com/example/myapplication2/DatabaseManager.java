package com.example.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import static com.example.myapplication2.FullListingInfo.faveList;

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "App Database";

    //Customer information table.
    public static final String ACCOUNTS_TABLE = "Account_Info";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Username";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "Password";
    public static final String COL_5 = "Balance";
    public static final String COL_6 = "Biography";
    public static final String COL_7 = "Profile_Picture";

    //ListingsPage information table.
    public static final String LISTINGS_TABLE = "Listings_Info";
    public static final String listings_COL_1 = "Property ID#";
    public static final String listings_COL_2 = "Address";
    public static final String listings_COL_3 = "Price per month";
    public static final String listings_COL_4 = "Type";
    public static final String listings_COL_5 = "Description";
    public static final String listings_COL_6 = "Favourite";

    //Review table
    public static final String REVIEWS_TABLE = "review_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "property";
    public static final String COL4 = "review";


    public DatabaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE [" + ACCOUNTS_TABLE + "](ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT, BALANCE INTEGER, BIOGRAPHY TEXT, PROFILEPICTURE BLOB)");
        db.execSQL("CREATE TABLE [" + LISTINGS_TABLE + "](ID INTEGER PRIMARY KEY AUTOINCREMENT, ADDRESS VARCHAR, PRICE VARHCAR, IMAGE BLOB, TYPE TEXT, DESCRIPTION TEXT, FAVOURITE INTEGER)");
        db.execSQL("CREATE TABLE [" + REVIEWS_TABLE + "](ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PROPERTY TEXT, REVIEW TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LISTINGS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + REVIEWS_TABLE);
        onCreate(db);
    }

    //Accounts table METHODS

    public void insertAccountData(String username, String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, password);
        contentValues.put(COL_5, 0);
        contentValues.put(COL_6, "");
        contentValues.put(COL_7, "");
        db.insert(ACCOUNTS_TABLE, null, contentValues);

    }

    public void addBalance(Double loanRequest, int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5, loanRequest);
        //db.execSQL("UPDATE ["+ TABLE_NAME + "] SET BALANCE = " + loanRequest + " WHERE ID = " + id);
        System.out.println("TEST " + loanRequest + " " + id);
        db.update(ACCOUNTS_TABLE, contentValues,"ID = " + id, null);

    }

    public void editEmail(String updatedEmail, int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3, updatedEmail);
        System.out.println("TEST " + updatedEmail + " " + id);
        db.update(ACCOUNTS_TABLE, contentValues,"ID = " + id, null);

    }

    public void editBio(String updatedBio, int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6, updatedBio);
        System.out.println("TEST " + updatedBio + " " + id);
        db.update(ACCOUNTS_TABLE, contentValues,"ID = " + id, null);

    }

    public Cursor getID(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT ID FROM " + ACCOUNTS_TABLE + " ORDER BY ID DESC LIMIT 1", null);
        result.moveToFirst();
        return result;

    }

    public ArrayList<User> generateUserArray() {

        ArrayList<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ACCOUNTS_TABLE,null);


        while(res.moveToNext()) {
            int id = res.getInt(0);   //0 is the number of id column.
            String username = res.getString(1);
            String email = res.getString(2);
            String password = res.getString(3);
            Double balance = res.getDouble(4);
            String bio = res.getString(5);
            byte[] profilePic = res.getBlob(6);

            System.out.println("TESTING: " + id + " " + username + " " + email);

            User newUser = new User(id, username, email, password, balance, bio, profilePic);
            userList.add(newUser);
        }


        return userList;
    }


    // Property Table METHODS
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

    public ArrayList<Listing> getFavesData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+LISTINGS_TABLE,null);

        while(res.moveToNext()) {
            int id = res.getInt(0);   //0 is the number of id column in your database table
            String address = res.getString(1);
            String price = res.getString(2);
            byte[] image = res.getBlob(3);

            System.out.println("TESTING: " + id + " " + address + " " + price);

            Listing newListing = new Listing(id, address, price, image);
            faveList.add(newListing);
        }
        return faveList;
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
            String type = res.getString(4);
            String description = res.getString(5);
            int favourited = res.getInt(6);

            System.out.println("TESTING: " + id + " " + address + " " + price);

            Listing newListing = new Listing(id, address, price, image, type, description, favourited);
            listingList.add(newListing);
        }

        return listingList;
    }



    // REVIEW TABLE METHODS
    public boolean addReviewData(String name, String property, String review){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,property);
        contentValues.put(COL3,review);

        long result  = db.insert(REVIEWS_TABLE, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor showReviewData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + REVIEWS_TABLE, null);
        return data;
    }

    public boolean updateReviewData(String id, String name, String property, String review){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,property);
        contentValues.put(COL4,review);
        db.update(REVIEWS_TABLE, contentValues, "ID = ?", new String[] {id});
        return true;
    }

    public Integer deleteReviewData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(REVIEWS_TABLE, "ID = ?", new String[] {id});
    }


}
