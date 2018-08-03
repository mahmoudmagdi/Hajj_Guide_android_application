package com.moshrif.moshrifguid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moshrif.moshrifguid.AddNewGroupActivity;
import com.moshrif.moshrifguid.R;
import com.moshrif.moshrifguid.adaptors.GroupsAdaptor;
import com.moshrif.moshrifguid.model.Group;

import java.util.ArrayList;
import java.util.List;

import static com.moshrif.moshrifguid.FirebaseStrings.groupsRefrence;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_COUNTRY_CHILD;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_GUIDE_ID_CHILD;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_ID_CHILD;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_MEMBERS;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_NAME_CHILD;

public class GroupFragment extends Fragment {

    private RecyclerView all_groups_recycler_view;
    private RecyclerView.LayoutManager groupsLayoutManager;
    private GroupsAdaptor adaptor;
    private List<Group> groupsList;
    private RelativeLayout no_internet_connection;
    private FloatingActionButton add_new_group;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        initView(rootView);
        initActions();
        getGroups();
        return rootView;
    }

    private void initView(View view) {
        no_internet_connection = view.findViewById(R.id.no_internet_connection);
        all_groups_recycler_view = view.findViewById(R.id.all_groups_recycler_view);
        add_new_group = view.findViewById(R.id.add_new_group);
    }

    private void initActions() {
        add_new_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddNewGroupActivity.class));
            }
        });
    }

    private void getGroups() {
        groupsRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getAllGroups();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getAllGroups() {
        groupsRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getActivity() != null) {
                    adaptor = new GroupsAdaptor(getActivity());
                    groupsLayoutManager = new LinearLayoutManager(getActivity());
                }

                groupsList = new ArrayList<>();
                if (dataSnapshot != null) {
                    for (DataSnapshot groupShot : dataSnapshot.getChildren()) {
                        final Group group = new Group();

                        if (groupShot.child(GROUP_ID_CHILD).getValue() != null)
                            group.setGroupId((String) groupShot.child(GROUP_ID_CHILD).getValue());

                        if (groupShot.child(GROUP_COUNTRY_CHILD).getValue() != null)
                            group.setGroupCounter((String) groupShot.child(GROUP_COUNTRY_CHILD).getValue());

                        if (groupShot.child(GROUP_NAME_CHILD).getValue() != null)
                            group.setGroupName((String) groupShot.child(GROUP_NAME_CHILD).getValue());

                        if (groupShot.child(GROUP_GUIDE_ID_CHILD).getValue() != null)
                            group.setGroupGuideId((String) groupShot.child(GROUP_GUIDE_ID_CHILD).getValue());

                        if (groupShot.child(GROUP_ID_CHILD).hasChild(GROUP_MEMBERS))
                            groupsRefrence.child((String) groupShot.child(GROUP_ID_CHILD).getValue()).child(GROUP_MEMBERS).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    group.setGroupCounter(String.valueOf(dataSnapshot.getChildrenCount()));
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        groupsList.add(group);
                    }
                    if (groupsList.size() > 0) {
                        no_internet_connection.setVisibility(View.GONE);
                        Group.GroupResult result = new Group.GroupResult(groupsList);
                        adaptor.setGroupsList(result.getResults());
                        all_groups_recycler_view.setLayoutManager(groupsLayoutManager);
                        all_groups_recycler_view.setAdapter(adaptor);
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
