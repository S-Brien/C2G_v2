package com.example.Come2Go;

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

import static com.example.Come2Go.ListingsPage.LISTING_ID;

public class FullListingInfo extends AppCompatActivity {

    DatabaseManager dbm;
    static ArrayList<Listing> faveList = new ArrayList<>();
    static ArrayList<Listing> applicationList = new ArrayList<>();
    ArrayList<Listing> listingList;
    Listing faveListing, applicationListing;
    ToggleButton toggle;
    Button applyButton;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_listing_info);

        dbm = new DatabaseManager(this);
        listingList = dbm.getAllPropertyData();
        setInfo(listingList.get(LISTING_ID));

        initializeButtons();


    }

    private void initializeButtons() {

        applyButton = findViewById(R.id.applyButton);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToApplications();
            }
        });

        toggle = findViewById(R.id.faveToggle);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    addToFaves();
                } else {
                    removeFromFaves();
                }
            }
        });
    }

    private void setInfo(Listing listing){

        ImageView image = findViewById(R.id.imageHouse);
        TextView price = findViewById(R.id.textPrice);
        TextView address = findViewById(R.id.textAddress);
        TextView type = findViewById(R.id.depositText);
        TextView description = findViewById(R.id.descriptionText);



        image.setImageBitmap(BitmapFactory.decodeByteArray(listing.getImage(), 0, listing.getImage().length));
        price.setText(listing.getPrice());
        address.setText(listing.getAddress());
        type.setText(listing.getType());
        description.setText(listing.getDescription());


    }

    private void addToApplications(){

        if(duplicateCheck(LISTING_ID)){
            Toast.makeText(FullListingInfo.this, "You have already made this application!", Toast.LENGTH_LONG).show();
            return;
        }
        applicationListing = listingList.get(LISTING_ID);
        applicationList.add(applicationListing);
        Toast.makeText(FullListingInfo.this, "Application made!", Toast.LENGTH_LONG).show();

    }

    private boolean duplicateCheck(int listingId) {

        listingId = listingId + 1;
        Boolean dupeCheck = false;

        for (Listing listing : applicationList) {
            if (listing.getId() == listingId) {
                dupeCheck = true;
                break;
            } else {

                dupeCheck = false;
            }
        }
        return dupeCheck;
    }

    private void addToFaves(){

        faveListing = listingList.get(LISTING_ID);
        faveList.add(faveListing);
        Toast.makeText(FullListingInfo.this, "Added to favourites!", Toast.LENGTH_LONG).show();
    }

    private void removeFromFaves(){

        faveListing = listingList.get(LISTING_ID);
        //if listingList size <=1 then make Listing ID -1
        faveList.remove(faveListing);
        Toast.makeText(FullListingInfo.this, "Removed from favourites!", Toast.LENGTH_LONG).show();

    }
}
