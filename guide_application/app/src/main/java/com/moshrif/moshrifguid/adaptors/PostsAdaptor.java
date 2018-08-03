package com.moshrif.moshrifguid.adaptors;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moshrif.moshrifguid.PostDetailsActivity;
import com.moshrif.moshrifguid.R;
import com.moshrif.moshrifguid.model.Post;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostsAdaptor extends RecyclerView.Adapter<PostsAdaptor.PostsViewHolder> {

    private FragmentActivity mContext;
    private LayoutInflater inflater;
    private List<Post> postList;

    public PostsAdaptor(FragmentActivity mContext) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public PostsAdaptor.PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_post, parent, false);
        final PostsAdaptor.PostsViewHolder viewHolder = new PostsAdaptor.PostsViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Post post = postList.get(position);
                mContext.startActivity(new Intent(mContext, PostDetailsActivity.class).putExtra(PostDetailsActivity.POST, post.getPostId()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PostsAdaptor.PostsViewHolder holder, int position) {
        final Post post = postList.get(position);
        holder.post_content.setText(post.getPostContent());
        Date date = new java.util.Date(Long.parseLong(post.getPostedDate()) * 1000L);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd hh:mm aa");
        holder.post_date.setText(sdf.format(date));
        holder.post_group.setText(post.getPostGroupId());
    }

    public void setPostsList(List<Post> trackList) {
        this.postList = new ArrayList<>();
        this.postList.addAll(trackList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (postList == null) ? 0 : postList.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {

        public TextView post_content, post_date, post_group;

        public PostsViewHolder(View itemView) {
            super(itemView);
            post_content = itemView.findViewById(R.id.post_content);
            post_date = itemView.findViewById(R.id.post_date);
            post_group = itemView.findViewById(R.id.post_group);
        }
    }
}
