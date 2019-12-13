package com.example.Come2Go;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.Come2Go.FullListingInfo.faveList;

import java.util.ArrayList;

public class ListingsPage extends AppCompatActivity {

    public static int LISTING_ID;
    ListView myListView;
    ListView faveListView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listings_page);
        initializeButtons();

        myListView = findViewById(R.id.myListView);
        faveListView = findViewById(R.id.faveListView);

        faveListView.setVisibility(View.GONE);
        myListView.setVisibility(View.GONE);

        fillListView();


    }

    private void initializeButtons() {
        Button reviewButton = findViewById(R.id.ReviewButton);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ReviewPage.class));
            }
        });

        Button homePageButton = findViewById(R.id.homePageButton);
        homePageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });

        Button loanPageButton = findViewById(R.id.loanPageButton);
        loanPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoanPage.class));
            }
        });


        ImageView profilePicture = findViewById(R.id.profilePicture);
        profilePicture.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfilePage.class));
            }
        });

        TextView helpPageButton = findViewById(R.id.helpTxt);
        helpPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HelpPage.class));
            }
        });

        ToggleButton toggle = (ToggleButton) findViewById(R.id.faveToggle);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    enableFaveListView();
                } else {
                    // The toggle is disabled
                    fillListView();
                }
            }
        });
    }

    public void fillListView() {


        faveListView.setVisibility(View.GONE);
        myListView.setVisibility(View.VISIBLE);

        DatabaseManager dbm = new DatabaseManager(this);
        ArrayList<Listing> listingList = dbm.getLessData();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Listing listing = listingList.get(position);
                LISTING_ID = listing.getId() - 1;
                startActivity(new Intent(getApplicationContext(), FullListingInfo.class));
            }
        });

        CustomAdapter myAdapter = new CustomAdapter(listingList, this);
        myListView.setAdapter(myAdapter);
    }

    public void enableFaveListView(){


        myListView.setVisibility(View.GONE);
        faveListView.setVisibility(View.VISIBLE);

        faveListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Listing listing = faveList.get(position);
                LISTING_ID = listing.getId() - 1;
                startActivity(new Intent(getApplicationContext(), FullListingInfo.class));
            }
        });
        CustomAdapter myAdapter = new CustomAdapter(faveList, this);
        faveListView.setAdapter(myAdapter);

    }

}
