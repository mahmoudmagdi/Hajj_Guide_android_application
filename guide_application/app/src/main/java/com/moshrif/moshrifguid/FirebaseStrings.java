package com.moshrif.moshrifguid;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseStrings {

    public final static DatabaseReference postsRefrence = FirebaseDatabase.getInstance().getReference().child("posts");
    public final static DatabaseReference tasksRefrence = FirebaseDatabase.getInstance().getReference().child("tasks");
    public final static DatabaseReference guidesRefrence = FirebaseDatabase.getInstance().getReference().child("guides");
    public final static DatabaseReference groupsRefrence = FirebaseDatabase.getInstance().getReference().child("groups");
    public final static DatabaseReference hajjiesRefrence = FirebaseDatabase.getInstance().getReference().child("hajjies");

}
