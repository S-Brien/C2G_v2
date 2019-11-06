package com.example.myapplication2;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.fragment.app.FragmentActivity;

public class Map extends FragmentActivity implements OnMapReadyCallback{

    GoogleMap map;

    //When activity is created gets data from activity_maps.xml and creates the map
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.map);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map =googleMap;

        LatLng House = new LatLng(53.347992, -6.261025);
        map.addMarker(new MarkerOptions().position(House).title("House").snippet("Two Bedroom House"));
        map.moveCamera(CameraUpdateFactory.newLatLng(House));
    }
}
