package com.moshrif.moshrifhajj.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.moshrif.moshrifhajj.R;

import prefs.UserInfo;

public class MyGuidePlaceActivity extends AppCompatActivity implements OnMapReadyCallback {


    Toolbar toolbar;
    private ProgressDialog progressDialog;
    private UserInfo user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_guide_place);
        user = new UserInfo(this);
        setUpMap();
        initToolbar();
    }

    private void initToolbar() {
        getSupportActionBar().setTitle("مكان قائد الحملة");
    }

    private void setUpMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
