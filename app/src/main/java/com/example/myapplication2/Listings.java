package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Listings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listings_page);

        fillListview();

        Button homePageButton = findViewById(R.id.homePageButton);
        homePageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });

        ImageView profilePicture = findViewById(R.id.profilePicture);
        profilePicture.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfilePage.class));
            }
        });

        //Other buttons to be implemented later.
        //ListView as possible implementation of viewing database information.
    }

    public void fillListview() {

        ListView myListview = findViewById(R.id.myListView);
        DatabaseManager dbm = new DatabaseManager(this);

        ArrayList<Listing> listingList = dbm.getAllData();

        CustomAdapter myAdapter = new CustomAdapter(listingList, this);
        myListview.setAdapter(myAdapter);
    }

}
