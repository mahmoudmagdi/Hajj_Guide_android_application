package com.moshrif.moshrifguid.model;

public class Group {

    private String groupId;
    private String groupName;
    private String groupGuideId;
    private String groupCountry;
    private String groupCounter;

    public String getGroupCounter() {
        return groupCounter;
    }

    public void setGroupCounter(String groupCounter) {
        this.groupCounter = groupCounter;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupGuideId() {
        return groupGuideId;
    }

    public void setGroupGuideId(String groupGuideId) {
        this.groupGuideId = groupGuideId;
    }

    public String getGroupCountry() {
        return groupCountry;
    }

    public void setGroupCountry(String groupCountry) {
        this.groupCountry = groupCountry;
    }

    public static final class FirbaseStrings {

        public final static String GROUP_ID_CHILD = "groupId";
        public final static String GROUP_NAME_CHILD = "groupName";
        public final static String GROUP_GUIDE_ID_CHILD = "groupGuideId";
        public final static String GROUP_COUNTRY_CHILD = "groupCountry";

        public final static String GROUP_NAME_DEFAULT = "no name";
        public final static String GROUP_COUNTRY_DEFAULT = "unknown";

        public final static String GROUP_MEMBERS = "members";
    }
}