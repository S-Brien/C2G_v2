package com.example.myapplication2;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.myapplication2.ListingsPage.LISTING_ID;

public class FullListingInfo extends AppCompatActivity {

    ImageView image;
    TextView price, address;
    static ArrayList<Listing> faveList = new ArrayList<>();
    static ArrayList<Listing> applicationList = new ArrayList<>();
    ArrayList<Listing> listingList;
    Listing faveListing, applicationListing;
    ToggleButton toggle;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_listing_info);

        DatabaseManager dbm = new DatabaseManager(this);

        listingList = dbm.getAllPropertyData();

        setInfo(listingList.get(LISTING_ID));

        Button applyButton = findViewById(R.id.applyButton);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToApplications();
            }
        });

        toggle = (ToggleButton) findViewById(R.id.faveToggle);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    addToFaves();
                } else {
                    // The toggle is disabled
                    removeFromFaves();
                }
            }
        });
    }

    private void setInfo(Listing listing){

        ImageView image = findViewById(R.id.imageHouse);
        TextView price = findViewById(R.id.textPrice);
        TextView address = findViewById(R.id.textAddress);

        image.setImageBitmap(BitmapFactory.decodeByteArray(listing.getImage(), 0, listing.getImage().length));
        price.setText(listing.getPrice());
        address.setText(listing.getAddress());


    }

    private void addToApplications(){

        applicationListing = listingList.get(LISTING_ID);
        applicationList.add(applicationListing);
        Toast.makeText(FullListingInfo.this, "Application made!", Toast.LENGTH_LONG).show();

    }

    private void addToFaves(){

        faveListing = listingList.get(LISTING_ID);
        faveListing.setFavourite(true);
        faveList.add(faveListing);
        Toast.makeText(FullListingInfo.this, "Added to favourites!", Toast.LENGTH_LONG).show();

        System.out.println("Testing favourites" + faveListing.getId());

    }

    private void removeFromFaves(){

        faveListing = listingList.get(LISTING_ID);
        faveListing.setFavourite(false);
        faveList.remove(LISTING_ID);
        Toast.makeText(FullListingInfo.this, "Removed from favourites!", Toast.LENGTH_LONG).show();

    }

    private void checkFave(){

        System.out.println("I got here");
        if(faveListing.isFavourite()){
            toggle.setChecked(true);
        }else{
            toggle.setChecked(false);
        }

    }
}
