package com.moshrif.moshrifguid.model;

import java.util.List;

public class Post {

    private String postId;
    private String postType;
    private String postGuideId;
    private String postGroupId;
    private String postedDate;
    private String postContent;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostGuideId() {
        return postGuideId;
    }

    public void setPostGuideId(String postGuideId) {
        this.postGuideId = postGuideId;
    }

    public String getPostGroupId() {
        return postGroupId;
    }

    public void setPostGroupId(String postGroupId) {
        this.postGroupId = postGroupId;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public static final class FirebaseStrings {
        public static final String POST_ID_CHILD = "postId";
        public static final String POST_TYPE_CHILD = "postType";
        public static final String POST_GUIDE_ID_CHILD = "postGuideId";
        public static final String POST_GROUP_ID_CHILD = "postGroupId";
        public static final String POSTED_DATE_CHILD = "postedDate";
        public static final String POST_CONTENT_CHILD = "postContent";

        public static final String POST_TYPE_DEFAULT = "postType";
        public static final String POST_GUIDE_ID_DEFAULT = "postGuideId";
        public static final String POST_GROUP_ID_DEFAULT = "postGroupId";
        public static final Long POSTED_DATE_DEFAULT = System.currentTimeMillis() / 1000L;
        public static final String POST_CONTENT_DEFAULT = "no content";
    }

    public static class PostResult {
        private List<Post> results;

        public PostResult(List<Post> results) {
            this.results = results;
        }

        public List<Post> getResults() {
            return results;
        }
    }
}
