package com.example.rauan.cloudplace.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rauan.cloudplace.fragments.ListFragment;
import com.example.rauan.cloudplace.fragments.MapFragment;

/**
 * Created by Rauan on 007 07.04.2017.
 */

public class PageAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MapFragment tab1 = new MapFragment();
                return tab1;
            case 1:
                ListFragment tab2 = new ListFragment();
                return tab2;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Title " + position;
    }
}
