package com.moshrif.moshrifguid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import static com.moshrif.moshrifguid.FirebaseStrings.groupsRefrence;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_COUNTRY_CHILD;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_GUIDE_ID_CHILD;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_ID_CHILD;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_NAME_CHILD;

public class AddNewGroupActivity extends AppCompatActivity {

    EditText group_title, group_country;
    Button post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_group);
        initView();
        initActions();
    }

    private void initView() {
        group_title = findViewById(R.id.group_title);
        group_country = findViewById(R.id.group_country);
        post = findViewById(R.id.post);
    }

    private void initActions() {
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewGroup();
            }
        });
    }

    private void addNewGroup() {
        String key = groupsRefrence.push().getKey();
        groupsRefrence.child(key).child(GROUP_ID_CHILD).setValue(key);
        groupsRefrence.child(key).child(GROUP_NAME_CHILD).setValue(group_title.getText().toString());
        groupsRefrence.child(key).child(GROUP_COUNTRY_CHILD).setValue(group_country.getText().toString());
        groupsRefrence.child(key).child(GROUP_GUIDE_ID_CHILD).setValue(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        finish();
    }
}
