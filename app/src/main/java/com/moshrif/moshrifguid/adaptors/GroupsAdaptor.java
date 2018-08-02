package com.moshrif.moshrifguid.adaptors;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moshrif.moshrifguid.GroupDetailsActivity;
import com.moshrif.moshrifguid.R;
import com.moshrif.moshrifguid.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupsAdaptor extends RecyclerView.Adapter<GroupsAdaptor.GroupsViewHolder> {

    private FragmentActivity mContext;
    private LayoutInflater inflater;
    private List<Group> groupsList;

    public GroupsAdaptor(FragmentActivity mContext) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public GroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_group, parent, false);
        final GroupsAdaptor.GroupsViewHolder viewHolder = new GroupsAdaptor.GroupsViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Group group = groupsList.get(position);
                mContext.startActivity(new Intent(mContext, GroupDetailsActivity.class).putExtra(GroupDetailsActivity.GROUP, group.getGroupId()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupsViewHolder holder, int position) {
        final Group group = groupsList.get(position);
        holder.group_name.setText(group.getGroupName());
        holder.group_country.setText(group.getGroupCountry());
        holder.group_counter.setText(group.getGroupCounter());
    }

    public void setGroupsList(List<Group> groupsList) {
        this.groupsList = new ArrayList<>();
        this.groupsList.addAll(groupsList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (groupsList == null) ? 0 : groupsList.size();
    }

    public class GroupsViewHolder extends RecyclerView.ViewHolder {

        TextView group_name, group_counter, group_country;

        public GroupsViewHolder(View itemView) {
            super(itemView);
            group_counter = itemView.findViewById(R.id.group_counter);
            group_name = itemView.findViewById(R.id.group_name);
            group_country = itemView.findViewById(R.id.group_country);
        }
    }
}
