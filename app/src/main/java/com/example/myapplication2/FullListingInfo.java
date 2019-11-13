package com.example.myapplication2;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.myapplication2.Listings.LISTING_ID;

public class FullListingInfo extends AppCompatActivity {

    ImageView image;
    TextView price, address;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_listing_info);

        DatabaseManager dbm = new DatabaseManager(this);


        ArrayList<Listing> listingList = dbm.getAllPropertyData();

        setInfo(listingList.get(LISTING_ID));

    }

    private void setInfo(Listing listing){

        ImageView image = findViewById(R.id.imageHouse);
        TextView price = findViewById(R.id.textPrice);
        TextView address = findViewById(R.id.textAddress);

        image.setImageBitmap(BitmapFactory.decodeByteArray(listing.getImage(), 0, listing.getImage().length));
        price.setText(listing.getPrice());
        address.setText(listing.getAddress());


    }

}
