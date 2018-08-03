package com.moshrif.moshrifguid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static com.moshrif.moshrifguid.FirebaseStrings.hajjiesRefrence;
import static com.moshrif.moshrifguid.GroupDetailsActivity.GROUP;
import static com.moshrif.moshrifguid.model.Hajj.FirebaseStrings.HAJJ_COUNTRY_CHILD;
import static com.moshrif.moshrifguid.model.Hajj.FirebaseStrings.HAJJ_FIRST_NAME_CHILD;
import static com.moshrif.moshrifguid.model.Hajj.FirebaseStrings.HAJJ_GENDER_CHILD;
import static com.moshrif.moshrifguid.model.Hajj.FirebaseStrings.HAJJ_ID_CHILD;
import static com.moshrif.moshrifguid.model.Hajj.FirebaseStrings.HAJJ_LAST_NAME_CHILD;
import static com.moshrif.moshrifguid.model.Hajj.FirebaseStrings.HAJJ_PASSPORT_NUMBER_CHILD;
import static com.moshrif.moshrifguid.model.Hajj.FirebaseStrings.HAJJ_PHONE_NUMBER_CHILD;

class AddNewHajj extends AppCompatActivity {

    EditText first_name, last_name, phone_number, passport_no;
    Spinner country, gender;
    Button post;

    String groupId;
    String genderString = "الجنس";
    String countryString = "اختر بلد";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_hajj);
        if (getIntent().hasExtra(GROUP)) {
            groupId = getIntent().getStringExtra(GROUP);
        }
        initView();
        initActions();
    }

    private void initView() {
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        phone_number = findViewById(R.id.phone_number);
        passport_no = findViewById(R.id.passport_no);
        country = findViewById(R.id.country);
        gender = findViewById(R.id.gender);
        post = findViewById(R.id.post);
    }

    private void initActions() {
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHajj();
            }
        });
    }

    private void addHajj() {
        if (first_name.getText().equals("") || first_name.getText().equals(null)) {
            Toast.makeText(this, R.string.enter_hajj_first_name, Toast.LENGTH_LONG).show();
            return;
        }

        if (last_name.getText().equals("") || last_name.getText().equals(null)) {
            Toast.makeText(this, R.string.enter_hajj_last_name, Toast.LENGTH_LONG).show();
            return;
        }

        if (passport_no.getText().equals("") || passport_no.getText().equals(null)) {
            Toast.makeText(this, R.string.enter_hajj_pass_no, Toast.LENGTH_LONG).show();
            return;
        }

        String key = hajjiesRefrence.push().getKey();

        hajjiesRefrence.child(key).child(HAJJ_ID_CHILD).setValue(key);
        hajjiesRefrence.child(key).child(HAJJ_FIRST_NAME_CHILD).setValue(first_name.getText().toString());
        hajjiesRefrence.child(key).child(HAJJ_LAST_NAME_CHILD).setValue(last_name.getText().toString());
        hajjiesRefrence.child(key).child(HAJJ_PASSPORT_NUMBER_CHILD).setValue(passport_no.getText().toString());
        hajjiesRefrence.child(key).child(HAJJ_COUNTRY_CHILD).setValue(countryString);
        hajjiesRefrence.child(key).child(HAJJ_PHONE_NUMBER_CHILD).setValue(phone_number.getText().toString());
        hajjiesRefrence.child(key).child(HAJJ_GENDER_CHILD).setValue(genderString);
        finish();
    }

    private void getGenders() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        gender.setAdapter(adapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getCountries() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        country.setAdapter(adapter);
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
