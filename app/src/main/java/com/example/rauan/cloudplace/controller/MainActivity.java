package com.example.rauan.cloudplace.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.rauan.cloudplace.R;
import com.example.rauan.cloudplace.adapter.PageAdapter;
import com.example.rauan.cloudplace.model.PlaceModel;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    FloatingActionButton fab;
    private DatabaseReference databaseReference;
    private static final int ACCESS_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("places");

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        viewPager = (ViewPager) findViewById(R.id.viewPagerCloud);
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), 2);
        viewPager.setAdapter(pagerAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(MainActivity.this), ACCESS_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ACCESS_CODE) {
                Place place = PlacePicker.getPlace(MainActivity.this, data);
                String id = place.getId().toString();
                String name = place.getName().toString();
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                Log.d("place", place.toString());
                PlaceModel placeModel = new PlaceModel(id,name,null,null,latitude,longitude);
                String userId = databaseReference.push().getKey();
                databaseReference.child(userId).setValue(placeModel);

            }
        }
    }
}
