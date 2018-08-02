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

import static com.moshrif.moshrifguid.FirebaseStrings.guidesRefrence;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_ADDRESS_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_ADDRESS_DEFAULT;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_AGE_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_AGE_DEFAULT;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_COMPANY_NAME_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_COMPANY_NAME_DEFAULT;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_COUNTRY_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_COUNTRY_DEFAULT;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_EMAIL_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_FIRST_NAME_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_GENDER_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_GENDER_DEFAULT;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_ID_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_IMAGE_URL_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_IMAGE_URL_DEFAULT;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_LAST_NAME_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_PASSPORT_IMAGE_URL_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_PASSPORT_IMAGE_URL_DEFAULT;

@SuppressLint("Registered")
public class SignupActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;

    private EditText fName, lName, email, password, confirmPassword;
    private Button btn_signup, btn_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
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
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
    }

    private void initActions() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: check the internet connection
                signup();
            }
        });
    }

    private void signup() {
        final String sEmail = email.getText().toString();
        final String sPassword = password.getText().toString();
        final String sConfirmPassword = confirmPassword.getText().toString();
        final String sFName = fName.getText().toString();
        final String sLName = lName.getText().toString();

        if (TextUtils.isEmpty(sEmail)) {
            Toast.makeText(getApplicationContext(), "ادخل عنوان بريدك الالكتروني", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(sFName)) {
            Toast.makeText(getApplicationContext(), "ادخل اسمك الاول", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(sLName)) {
            Toast.makeText(getApplicationContext(), "ادخل اسمك الاخير", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(sPassword)) {
            Toast.makeText(getApplicationContext(), "ادخل كلمة المرور الخاصة بك", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.equals(sPassword, sConfirmPassword)) {
            Toast.makeText(getApplicationContext(), "أكد كلمة المرور", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "كلمة المرور الخاصة بك ضعيفة جدا", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog = new ProgressDialog(SignupActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("جاري تسجيل الدخول...");
        progressDialog.show();

        //create user
        auth.createUserWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    // On complete call either onSignupSuccess or onSignupFailed
                                    // depending on success
                                    // onSignupFailed();
                                    createNewUserWithEmail();
                                }
                            }, 3000);
                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    fileList();
                } else {
                    Toast.makeText(SignupActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    private void goMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
        progressDialog.dismiss();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authStateListener);
    }

    public void createNewUserWithEmail() {
        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String nameEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        int index = FirebaseAuth.getInstance().getCurrentUser().getEmail().trim().indexOf('@');
        nameEmail = nameEmail.substring(0, index);

        if (UID != null) {
            guidesRefrence.child(UID).child(GUIDE_ID_CHILD).setValue(UID);

            if (fName.getText() == null) {
                guidesRefrence.child(UID).child(GUIDE_FIRST_NAME_CHILD).setValue(nameEmail);
            } else {
                guidesRefrence.child(UID).child(GUIDE_FIRST_NAME_CHILD).setValue(fName.getText().toString());
            }

            if (lName.getText() == null) {
                guidesRefrence.child(UID).child(GUIDE_LAST_NAME_CHILD).setValue("no last name");
            } else {
                guidesRefrence.child(UID).child(GUIDE_LAST_NAME_CHILD).setValue(fName.getText().toString());
            }

            guidesRefrence.child(UID).child(GUIDE_EMAIL_CHILD).setValue(email);
            guidesRefrence.child(UID).child(GUIDE_ADDRESS_CHILD).setValue(GUIDE_ADDRESS_DEFAULT);
            guidesRefrence.child(UID).child(GUIDE_AGE_CHILD).setValue(GUIDE_AGE_DEFAULT);
            guidesRefrence.child(UID).child(GUIDE_COMPANY_NAME_CHILD).setValue(GUIDE_COMPANY_NAME_DEFAULT);
            guidesRefrence.child(UID).child(GUIDE_COUNTRY_CHILD).setValue(GUIDE_COUNTRY_DEFAULT);
            guidesRefrence.child(UID).child(GUIDE_GENDER_CHILD).setValue(GUIDE_GENDER_DEFAULT);
            guidesRefrence.child(UID).child(GUIDE_IMAGE_URL_CHILD).setValue(GUIDE_IMAGE_URL_DEFAULT);
            guidesRefrence.child(UID).child(GUIDE_PASSPORT_IMAGE_URL_CHILD).setValue(GUIDE_PASSPORT_IMAGE_URL_DEFAULT);
        }
    }
}
