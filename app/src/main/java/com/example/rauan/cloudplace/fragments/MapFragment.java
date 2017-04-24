package com.example.rauan.cloudplace.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rauan.cloudplace.R;
import com.example.rauan.cloudplace.model.PlaceModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private SupportMapFragment mapFragment;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map,container,false);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        return v;
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMyLocationEnabled(true);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        this.googleMap.getUiSettings().setCompassEnabled(true);
        this.googleMap.getUiSettings().setZoomControlsEnabled(true);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("places").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<PlaceModel> placeModelList = new ArrayList<PlaceModel>();
                for (DataSnapshot placeDataSnap : dataSnapshot.getChildren()) {
                    PlaceModel placeModel = placeDataSnap.getValue(PlaceModel.class);
                    placeModelList.add(placeModel);
                    if(placeModelList != null){
                        for(int i = 0; i< placeModelList.size();i++){
                            double lat = Double.parseDouble(placeModelList.get(i).getLatitude());
                            double lng = Double.parseDouble(placeModelList.get(i).getLongitude());
                            String title = placeModelList.get(i).getName();
                            LatLng latLng = new LatLng(lat, lng);
                            googleMap.addMarker(new MarkerOptions().position(latLng).title(title));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}