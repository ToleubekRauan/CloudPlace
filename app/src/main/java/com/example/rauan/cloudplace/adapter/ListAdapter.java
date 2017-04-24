package com.example.rauan.cloudplace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rauan.cloudplace.R;
import com.example.rauan.cloudplace.model.PlaceModel;

import java.util.List;

/**
 * Created by Rauan on 015 15.04.2017.
 */

public class ListAdapter extends BaseAdapter {
    Context context;
    List<PlaceModel> placeModelList;

    public ListAdapter(Context context, List<PlaceModel> placeModelList) {
        this.context = context;
        this.placeModelList = placeModelList;
    }


    @Override
    public int getCount() {
        return placeModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return placeModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_row_layout, parent, false);
            holder = new ViewHolder();
            holder.tvName = (TextView)convertView.findViewById(R.id.nameTextView);
            holder.tvPlaceLatitude = (TextView)convertView.findViewById(R.id.latitudeTextView);
            holder.tvPlaceLongitude = (TextView)convertView.findViewById(R.id.longitudeTextView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tvName.setText(placeModelList.get(position).getName());
        holder.tvPlaceLatitude.setText(placeModelList.get(position).getLatitude());
        holder.tvPlaceLongitude.setText(placeModelList.get(position).getLongitude());

        return convertView;


    }

    private class ViewHolder {
        TextView tvName;
        TextView tvPlaceLatitude;
        TextView tvPlaceLongitude;
    }
    }

