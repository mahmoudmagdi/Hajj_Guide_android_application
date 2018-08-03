package com.moshrif.moshrifhajj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.moshrif.moshrifhajj.activities.CampPlaceActivity;
import com.moshrif.moshrifhajj.activities.FoodPlacesActivity;
import com.moshrif.moshrifhajj.activities.MyGuidePlaceActivity;
import com.moshrif.moshrifhajj.activities.MyTasks;
import com.moshrif.moshrifhajj.activities.PostsActivity;
import com.moshrif.moshrifhajj.activities.RitesPlacesActivity;
import com.moshrif.moshrifhajj.activities.busPlaceActivity;

public class MainActivity extends AppCompatActivity {

    CardView find_food_place, find_bus_location, my_tasks, request_emergency, request_wheel_chair, find_my_place, find_my_guide, get_all_posts, get_rites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initActions();
    }

    private void initView() {
        find_food_place = findViewById(R.id.find_food_place);
        find_bus_location = findViewById(R.id.find_bus_location);
        my_tasks = findViewById(R.id.my_tasks);
        request_emergency = findViewById(R.id.request_emergency);
        request_wheel_chair = findViewById(R.id.request_wheel_chair);
        find_my_place = findViewById(R.id.find_my_place);
        find_my_guide = findViewById(R.id.find_my_guide);
        get_all_posts = findViewById(R.id.get_all_posts);
        get_rites = findViewById(R.id.get_rites);
    }

    private void initActions() {
        find_food_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FoodPlacesActivity.class));
            }
        });
        find_bus_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, busPlaceActivity.class));
            }
        });
        my_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyTasks.class));
            }
        });
        request_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmRequest();
            }
        });
        request_wheel_chair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChairRequest();
            }
        });
        find_my_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CampPlaceActivity.class));
            }
        });
        find_my_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyGuidePlaceActivity.class));
            }
        });
        get_all_posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PostsActivity.class));
            }
        });
        get_rites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RitesPlacesActivity.class));
            }
        });
    }

    private void AlarmRequest() {

    }

    private void ChairRequest() {

    }
}
