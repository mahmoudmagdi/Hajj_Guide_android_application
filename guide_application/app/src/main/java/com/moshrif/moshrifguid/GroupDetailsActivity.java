package com.moshrif.moshrifguid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("Registered")
public class GroupDetailsActivity extends AppCompatActivity {

    public static final String GROUP = "group";

    private TextView group_name, group_country, group_counter;
    private LinearLayout add_new_hajj, edit_group;
    private RecyclerView hajj_recycler_view;
    private String groupId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        if (getIntent().hasExtra(GROUP)) {
            groupId = getIntent().getStringExtra(GROUP);
        }
        initView();
        initActions();
    }

    private void initView() {
        group_counter = findViewById(R.id.group_counter);
        group_country = findViewById(R.id.group_country);
        group_name = findViewById(R.id.group_name);
        add_new_hajj = findViewById(R.id.add_new_hajj);
        edit_group = findViewById(R.id.edit_group);
        hajj_recycler_view = findViewById(R.id.hajj_recycler_view);
    }

    private void initActions() {
        add_new_hajj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GroupDetailsActivity.this, AddNewHajj.class).putExtra(GROUP, groupId));
            }
        });
        edit_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
