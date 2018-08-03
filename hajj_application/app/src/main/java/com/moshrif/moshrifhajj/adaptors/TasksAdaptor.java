package com.moshrif.moshrifhajj.adaptors;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moshrif.moshrifhajj.R;
import com.moshrif.moshrifhajj.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TasksAdaptor extends RecyclerView.Adapter<TasksAdaptor.PostsViewHolder> {

    private FragmentActivity mContext;
    private LayoutInflater inflater;
    private List<Task> tasksList;

    public TasksAdaptor(FragmentActivity mContext) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public TasksAdaptor.PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_task, parent, false);
        final TasksAdaptor.PostsViewHolder viewHolder = new TasksAdaptor.PostsViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Task task = tasksList.get(position);
                //mContext.startActivity(new Intent(mContext, TaskDetailsActivity.class).putExtra(PostDetailsActivity.POST, task.getTaskId()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TasksAdaptor.PostsViewHolder holder, int position) {
        final Task task = tasksList.get(position);

        if (task.getTaskTitle() != null)
            holder.task_title.setText(task.getTaskTitle());

        if (task.getTaskDescription() != null)
            holder.task_description.setText(task.getTaskDescription());

        if (task.getTaskEstimatedTime() != null)
            holder.task_estimated_time.setText(task.getTaskEstimatedTime());

        if (task.getTaskStartingTime() != null)
            holder.task_starting_time.setText(task.getTaskStartingTime());

        if (task.getTaskGroupId() != null)
            holder.group_name.setText(task.getTaskGroupId());
    }

    public void setTasksList(List<Task> tasksList) {
        this.tasksList = new ArrayList<>();
        this.tasksList.addAll(tasksList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (tasksList == null) ? 0 : tasksList.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {

        public TextView group_name, task_title, task_description, task_estimated_time, task_starting_time;

        public PostsViewHolder(View itemView) {
            super(itemView);
            group_name = itemView.findViewById(R.id.group_name);
            task_title = itemView.findViewById(R.id.task_title);
            task_description = itemView.findViewById(R.id.task_description);
            task_estimated_time = itemView.findViewById(R.id.task_estimated_time);
            task_starting_time = itemView.findViewById(R.id.task_starting_time);
        }
    }
}
