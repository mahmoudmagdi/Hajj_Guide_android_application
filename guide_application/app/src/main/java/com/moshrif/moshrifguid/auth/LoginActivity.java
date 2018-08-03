package com.moshrif.moshrifguid.auth;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moshrif.moshrifguid.MainActivity;
import com.moshrif.moshrifguid.R;

@SuppressLint("Registered")
public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private ProgressDialog progressDialog;
    private EditText email, password;
    private Button btn_login, btn_signup, btn_reset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initFirebase();
        initView();
        initActions();
    }

    private void initFirebase() {
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            goMainScreen();
        }
    }


    private void initView() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        btn_reset = findViewById(R.id.btn_reset);
    }

    private void initActions() {
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSignup();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResetPasswordWindow();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("جاري تسجيل الدخول...");
        progressDialog.show();

        final String sEmail = email.getText().toString();
        final String sPassword = password.getText().toString();

        if (TextUtils.isEmpty(sEmail)) {
            Toast.makeText(getApplicationContext(), "ادخل عنوان بريدك الالكتروني", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(sPassword)) {
            Toast.makeText(getApplicationContext(), "ادخل كلمة المرور الخاصة بك", Toast.LENGTH_SHORT).show();
            return;
        }

        //authenticate user
        FirebaseAuth.getInstance().signInWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    // there was an error
                    onLoginFailed(task.getException());
                } else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                progressDialog.dismiss();
            }
        });
    }

    private void onLoginFailed(Exception exception) {
        progressDialog.dismiss();
        Toast.makeText(LoginActivity.this, "لا يوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();
    }

    private void gotoSignup() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void openResetPasswordWindow() {
        //TODO: complete reset password window
    }

    private void goMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
        progressDialog.dismiss();
        finish();
    }
}
