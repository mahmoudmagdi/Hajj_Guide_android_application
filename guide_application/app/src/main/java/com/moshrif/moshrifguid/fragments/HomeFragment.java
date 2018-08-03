package com.moshrif.moshrifguid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moshrif.moshrifguid.R;
import com.moshrif.moshrifguid.adaptors.PostsAdaptor;
import com.moshrif.moshrifguid.model.Group;
import com.moshrif.moshrifguid.model.Post;

import java.util.ArrayList;
import java.util.List;

import static com.moshrif.moshrifguid.FirebaseStrings.groupsRefrence;
import static com.moshrif.moshrifguid.FirebaseStrings.guidesRefrence;
import static com.moshrif.moshrifguid.FirebaseStrings.postsRefrence;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_COUNTRY_CHILD;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_GUIDE_ID_CHILD;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_ID_CHILD;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_MEMBERS;
import static com.moshrif.moshrifguid.model.Group.FirbaseStrings.GROUP_NAME_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_FIRST_NAME_CHILD;
import static com.moshrif.moshrifguid.model.Guide.FirebaseStrings.GUIDE_LAST_NAME_CHILD;
import static com.moshrif.moshrifguid.model.Post.FirebaseStrings.POSTED_DATE_CHILD;
import static com.moshrif.moshrifguid.model.Post.FirebaseStrings.POSTED_DATE_DEFAULT;
import static com.moshrif.moshrifguid.model.Post.FirebaseStrings.POST_CONTENT_CHILD;
import static com.moshrif.moshrifguid.model.Post.FirebaseStrings.POST_GROUP_ID_CHILD;
import static com.moshrif.moshrifguid.model.Post.FirebaseStrings.POST_GUIDE_ID_CHILD;
import static com.moshrif.moshrifguid.model.Post.FirebaseStrings.POST_ID_CHILD;
import static com.moshrif.moshrifguid.model.Post.FirebaseStrings.POST_TYPE_CHILD;

public class HomeFragment extends Fragment {

    public final static int POST_TYPE = 0;
    public final static int POST_GROUP = 1;

    private RecyclerView all_posts_recycler_view;
    private RecyclerView.LayoutManager postsLayoutManager;
    private PostsAdaptor adaptor;
    private List<Post> postsList;
    private RelativeLayout no_internet_connection;

    private EditText post_content;
    private Spinner post_type, post_group;
    private Button post;

    private String postType;
    private String postGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initView(rootView);
        initActions();
        getPosts();
        getPostTypes(rootView);
        return rootView;
    }

    private void initView(View view) {
        no_internet_connection = view.findViewById(R.id.no_internet_connection);
        all_posts_recycler_view = view.findViewById(R.id.all_posts_recycler_view);
        post_content = view.findViewById(R.id.post_content);
        post_group = view.findViewById(R.id.post_group);
        post_type = view.findViewById(R.id.post_type);
        post = view.findViewById(R.id.post);
    }

    private void initActions() {
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPost();
                getAllPosts();
            }
        });
    }

    private void getPosts() {
        postsRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getAllPosts();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getAllPosts() {
        postsRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (getActivity() != null) {
                    adaptor = new PostsAdaptor(getActivity());
                    postsLayoutManager = new LinearLayoutManager(getActivity());
                }

                postsList = new ArrayList<>();
                for (DataSnapshot postShot : dataSnapshot.getChildren()) {
                    final Post post = new Post();

                    if (postShot.child(POST_CONTENT_CHILD).getValue() != null)
                        post.setPostContent((String) postShot.child(POST_CONTENT_CHILD).getValue());

                    if (postShot.child(POSTED_DATE_CHILD).getValue() != null)
                        post.setPostedDate(String.valueOf((Long) postShot.child(POSTED_DATE_CHILD).getValue()));

                    if (postShot.child(POST_GUIDE_ID_CHILD).getValue() != null)
                        guidesRefrence.child((String) postShot.child(POST_GUIDE_ID_CHILD).getValue()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot guideShot) {
                                post.setPostGuideId((String) guideShot.child(GUIDE_FIRST_NAME_CHILD).getValue() + (String) guideShot.child(GUIDE_LAST_NAME_CHILD).getValue());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    if (postShot.child(POST_GROUP_ID_CHILD).getValue() != null)
                        groupsRefrence.child(POST_GROUP_ID_CHILD).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot groupShot) {
                                post.setPostGroupId((String) groupShot.child(GROUP_NAME_CHILD).getValue());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    postsList.add(post);
                }
                if (postsList.size() > 0) {
                    no_internet_connection.setVisibility(View.GONE);
                    Post.PostResult result = new Post.PostResult(postsList);
                    adaptor.setPostsList(result.getResults());
                    all_posts_recycler_view.setLayoutManager(postsLayoutManager);
                    all_posts_recycler_view.setAdapter(adaptor);
                } else {
                    no_internet_connection.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void newPost() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String key = postsRefrence.push().getKey();

        postsRefrence.child(key).child(POST_ID_CHILD).setValue(key);
        postsRefrence.child(key).child(POST_CONTENT_CHILD).setValue(post_content.getText().toString());
        postsRefrence.child(key).child(POST_GROUP_ID_CHILD).setValue(postGroup);
        postsRefrence.child(key).child(POST_TYPE_CHILD).setValue(postType);
        postsRefrence.child(key).child(POST_GUIDE_ID_CHILD).setValue(user.getUid());
        postsRefrence.child(key).child(POSTED_DATE_CHILD).setValue(POSTED_DATE_DEFAULT);

    }

    private void getPostTypes(View view) {
        Spinner spinner = view.findViewById(R.id.post_type);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.post_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                postType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
