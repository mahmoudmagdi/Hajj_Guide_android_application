package com.moshrif.moshrifhajj.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moshrif.moshrifhajj.R;
import com.moshrif.moshrifhajj.adaptors.TasksAdaptor;
import com.moshrif.moshrifhajj.model.Task;

import java.util.ArrayList;
import java.util.List;

import static com.moshrif.moshrifhajj.FirebaseStrings.groupsRefrence;
import static com.moshrif.moshrifhajj.FirebaseStrings.tasksRefrence;
import static com.moshrif.moshrifhajj.model.Group.FirbaseStrings.GROUP_NAME_CHILD;
import static com.moshrif.moshrifhajj.model.Task.FirebaseStrings.TASK_DESCRIPTION_CHILD;
import static com.moshrif.moshrifhajj.model.Task.FirebaseStrings.TASK_ESTIMATED_TIME_CHILD;
import static com.moshrif.moshrifhajj.model.Task.FirebaseStrings.TASK_GROUP_ID_CHILD;
import static com.moshrif.moshrifhajj.model.Task.FirebaseStrings.TASK_ID_CHILD;
import static com.moshrif.moshrifhajj.model.Task.FirebaseStrings.TASK_STARTING_TIME_CHILD;
import static com.moshrif.moshrifhajj.model.Task.FirebaseStrings.TASK_TITLE_CHILD;

public class MyTasks extends AppCompatActivity {

    private RecyclerView all_tasks_recycler_view;
    private RecyclerView.LayoutManager tasksLayoutManager;
    private TasksAdaptor adaptor;
    private List<Task> tasksList;
    private RelativeLayout no_internet_connection;
    private FloatingActionButton add_new_task;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);
        initView();
        getTasks();
    }

    private void initView() {
        no_internet_connection = findViewById(R.id.no_internet_connection);
        all_tasks_recycler_view = findViewById(R.id.all_tasks_recycler_view);
    }

    private void getTasks() {
        tasksRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getAllTasks();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getAllTasks() {
        tasksRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adaptor = new TasksAdaptor(MyTasks.this);
                tasksLayoutManager = new LinearLayoutManager(MyTasks.this);

                tasksList = new ArrayList<>();
                if (dataSnapshot != null) {
                    for (DataSnapshot taskShot : dataSnapshot.getChildren()) {
                        final Task task = new Task();

                        if (taskShot.child(TASK_ID_CHILD).getValue() != null)
                            task.setTaskId((String) taskShot.child(TASK_ID_CHILD).getValue());

                        if (taskShot.child(TASK_TITLE_CHILD).getValue() != null)
                            task.setTaskTitle((String) taskShot.child(TASK_TITLE_CHILD).getValue());

                        if (taskShot.child(TASK_DESCRIPTION_CHILD).getValue() != null)
                            task.setTaskDescription((String) taskShot.child(TASK_DESCRIPTION_CHILD).getValue());

                        if (taskShot.child(TASK_ESTIMATED_TIME_CHILD).getValue() != null)
                            task.setTaskEstimatedTime((String) taskShot.child(TASK_ESTIMATED_TIME_CHILD).getValue());

                        if (taskShot.child(TASK_STARTING_TIME_CHILD).getValue() != null)
                            task.setTaskStartingTime((String) taskShot.child(TASK_STARTING_TIME_CHILD).getValue());

                        if (taskShot.child(TASK_GROUP_ID_CHILD).getValue() != null)
                            groupsRefrence.child((String) taskShot.child(TASK_GROUP_ID_CHILD).getValue()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot groupShot) {
                                    if (groupShot.child(GROUP_NAME_CHILD).getValue() != null)
                                        task.setTaskGroupId((String) groupShot.child(GROUP_NAME_CHILD).getValue());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        tasksList.add(task);
                    }

                    if (tasksList.size() > 0) {
                        no_internet_connection.setVisibility(View.GONE);
                        Task.TaskResult result = new Task.TaskResult(tasksList);
                        adaptor.setTasksList(result.getResults());
                        all_tasks_recycler_view.setLayoutManager(tasksLayoutManager);
                        all_tasks_recycler_view.setAdapter(adaptor);
                    } else {
                        no_internet_connection.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
