package com.moshrif.moshrifguid.model;

import java.util.List;

public class Task {

    private String taskId;
    private String taskTitle;
    private String taskDescription;
    private String taskEstimatedTime;
    private String taskStartingTime;
    private String taskGroupId;
    private String taskGuideCreatedId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskEstimatedTime() {
        return taskEstimatedTime;
    }

    public void setTaskEstimatedTime(String taskEstimatedTime) {
        this.taskEstimatedTime = taskEstimatedTime;
    }

    public String getTaskStartingTime() {
        return taskStartingTime;
    }

    public void setTaskStartingTime(String taskStartingTime) {
        this.taskStartingTime = taskStartingTime;
    }

    public String getTaskGroupId() {
        return taskGroupId;
    }

    public void setTaskGroupId(String taskGroupId) {
        this.taskGroupId = taskGroupId;
    }

    public String getTaskGuideCreatedId() {
        return taskGuideCreatedId;
    }

    public void setTaskGuideCreatedId(String taskGuideCreatedId) {
        this.taskGuideCreatedId = taskGuideCreatedId;
    }

    public final static class FirebaseStrings {

        public static final String TASK_ID_CHILD = "taskId";
        public static final String TASK_TITLE_CHILD = "taskTitle";
        public static final String TASK_DESCRIPTION_CHILD = "taskDescription";
        public static final String TASK_ESTIMATED_TIME_CHILD = "taskEstimatedTime";
        public static final String TASK_STARTING_TIME_CHILD = "taskStartingTime";
        public static final String TASK_GROUP_ID_CHILD = "taskGroupId";
        public static final String TASK_GUIDE_ID_CHILD = "taskGuideCreatedId";

        public static final String TASK_TITLE_DEFAULT = "no title";
        public static final String TASK_DESCRIPTION_DEFAULT = "no description";
        public static final String TASK_ESTIMATED_TIME_DEFAULT = "didn't estimated, yet.";
        public static final String TASK_STARTING_TIME_DEFAULT = "no time to start.";
        public static final String TASK_GROUP_ID_DEFAULT = "unknown";
        public static final String TASK_GUIDE_ID_DEFAULT = "unknown";
    }

    public static class TaskResult {
        private List<Task> results;

        public TaskResult(List<Task> results) {
            this.results = results;
        }

        public List<Task> getResults() {
            return results;
        }
    }
}
