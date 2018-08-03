package com.moshrif.moshrifhajj.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moshrif.moshrifhajj.R;
import com.moshrif.moshrifhajj.adaptors.PostsAdaptor;
import com.moshrif.moshrifhajj.model.Post;

import java.util.ArrayList;
import java.util.List;

import static com.moshrif.moshrifhajj.FirebaseStrings.groupsRefrence;
import static com.moshrif.moshrifhajj.FirebaseStrings.guidesRefrence;
import static com.moshrif.moshrifhajj.FirebaseStrings.postsRefrence;
import static com.moshrif.moshrifhajj.model.Group.FirbaseStrings.GROUP_NAME_CHILD;
import static com.moshrif.moshrifhajj.model.Guide.FirebaseStrings.GUIDE_FIRST_NAME_CHILD;
import static com.moshrif.moshrifhajj.model.Guide.FirebaseStrings.GUIDE_LAST_NAME_CHILD;
import static com.moshrif.moshrifhajj.model.Post.FirebaseStrings.POSTED_DATE_CHILD;
import static com.moshrif.moshrifhajj.model.Post.FirebaseStrings.POST_CONTENT_CHILD;
import static com.moshrif.moshrifhajj.model.Post.FirebaseStrings.POST_GROUP_ID_CHILD;
import static com.moshrif.moshrifhajj.model.Post.FirebaseStrings.POST_GUIDE_ID_CHILD;

public class PostsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        initView();
        getPosts();
    }

    private void initView() {
        no_internet_connection = findViewById(R.id.no_internet_connection);
        all_posts_recycler_view = findViewById(R.id.all_posts_recycler_view);
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

                adaptor = new PostsAdaptor(PostsActivity.this);
                postsLayoutManager = new LinearLayoutManager(PostsActivity.this);

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
}
