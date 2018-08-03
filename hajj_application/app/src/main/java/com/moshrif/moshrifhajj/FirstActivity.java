package com.moshrif.moshrifhajj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import prefs.UserInfo;
import prefs.UserSession;

import static com.moshrif.moshrifhajj.FirebaseStrings.hajjiesRefrence;

public class FirstActivity extends AppCompatActivity {

    EditText passport;
    Button btn_login;

    private UserSession session;
    private UserInfo user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        session = new UserSession(this);
        user = new UserInfo(this);
        initView();
        initActions();
    }

    private void initView() {
        passport = findViewById(R.id.passport);
        btn_login = findViewById(R.id.btn_login);
    }

    private void initActions() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        hajjiesRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(passport.getText().toString())) {
                    session.setLoggedin(true);
                    user.setUserId(passport.getText().toString());
                    startActivity(new Intent(FirstActivity.this, MainActivity.class));
                    finish();
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
