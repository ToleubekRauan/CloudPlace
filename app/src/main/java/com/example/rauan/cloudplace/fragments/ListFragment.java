package com.example.rauan.cloudplace.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rauan.cloudplace.R;
import com.example.rauan.cloudplace.adapter.ListAdapter;
import com.example.rauan.cloudplace.model.PlaceModel;
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
public class ListFragment extends Fragment {
    private DatabaseReference databaseReference;
    private List<PlaceModel> placeModelList = new ArrayList<>();
    private ListAdapter listAdapter;
    private ListView listViewPlace;
    private TextView tvEmpty;
    private ProgressBar progressBarList;


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        listViewPlace = (ListView) v.findViewById(R.id.listViewPlace);
        tvEmpty = (TextView) v.findViewById(R.id.tvEmpty);
        listViewPlace.setEmptyView(tvEmpty);
        progressBarList = (ProgressBar) v.findViewById(R.id.progressBarList);

        //tvPlace = (TextView) v.findViewById(R.id.tvPLace);
        getAllPlaces();

        listViewPlace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*PlaceModel placeModel = placeModelList.get(position);
                String lat = placeModel.getLatitude();
                String lng = placeModel.getLongitude();
                String latlng = lat+","+lng;
                Intent intent = new Intent(.this,GoogleMapWithMarkers.class);
                intent.putExtra("latlng", latlng);
                startActivity(intent);*/


            }
        });


        return v;
    }
    public void getAllPlaces(){
        progressBarList.setVisibility(View.VISIBLE);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("places").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<PlaceModel> placeModelList = new ArrayList<PlaceModel>();
                for(DataSnapshot placeDataSnap: dataSnapshot.getChildren()){
                    PlaceModel placeModel = placeDataSnap.getValue(PlaceModel.class);
                    placeModelList.add(placeModel);
                    listAdapter = new ListAdapter(getActivity(),placeModelList);
                    listViewPlace.setAdapter(listAdapter);
                    listAdapter.notifyDataSetChanged();
                    progressBarList.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }







}
