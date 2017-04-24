package com.example.rauan.cloudplace.services;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Rauan on 020 20.04.2017.
 */

public class CloudPlaceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

    }
}
