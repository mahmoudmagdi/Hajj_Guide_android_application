package com.moshrif.moshrifguid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import static com.moshrif.moshrifguid.FirebaseStrings.tasksRefrence;
import static com.moshrif.moshrifguid.model.Task.FirebaseStrings.TASK_DESCRIPTION_CHILD;
import static com.moshrif.moshrifguid.model.Task.FirebaseStrings.TASK_ESTIMATED_TIME_CHILD;
import static com.moshrif.moshrifguid.model.Task.FirebaseStrings.TASK_GROUP_ID_CHILD;
import static com.moshrif.moshrifguid.model.Task.FirebaseStrings.TASK_GUIDE_ID_CHILD;
import static com.moshrif.moshrifguid.model.Task.FirebaseStrings.TASK_STARTING_TIME_CHILD;
import static com.moshrif.moshrifguid.model.Task.FirebaseStrings.TASK_TITLE_CHILD;

public class AddNewTaskActivity extends AppCompatActivity {

    private EditText task_title, task_description, task_estimated_time, task_starting_time, task_group;
    private Button post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        initView();
        initActions();
    }

    private void initView() {
        task_title = findViewById(R.id.task_title);
        task_description = findViewById(R.id.task_description);
        task_estimated_time = findViewById(R.id.task_estimated_time);
        task_starting_time = findViewById(R.id.task_starting_time);
        task_group = findViewById(R.id.task_group);
        post = findViewById(R.id.post);
    }

    private void initActions() {
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPost();
            }
        });
    }

    private void addNewPost() {
        String key = tasksRefrence.push().getKey();
        tasksRefrence.child(key).child(TASK_TITLE_CHILD).setValue(task_title.getText().toString());
        tasksRefrence.child(key).child(TASK_DESCRIPTION_CHILD).setValue(task_description.getText().toString());
        tasksRefrence.child(key).child(TASK_STARTING_TIME_CHILD).setValue(task_starting_time.getText().toString());
        tasksRefrence.child(key).child(TASK_ESTIMATED_TIME_CHILD).setValue(task_estimated_time.getText().toString());
        tasksRefrence.child(key).child(TASK_GROUP_ID_CHILD).setValue(task_group.getText().toString());
        tasksRefrence.child(key).child(TASK_GUIDE_ID_CHILD).setValue(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        finish();
    }
}
