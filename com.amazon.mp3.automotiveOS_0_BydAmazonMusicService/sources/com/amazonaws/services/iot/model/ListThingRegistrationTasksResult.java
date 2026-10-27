package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingRegistrationTasksResult implements Serializable {
    private String nextToken;
    private List<String> taskIds;

    public List<String> getTaskIds() {
        return this.taskIds;
    }

    public void setTaskIds(Collection<String> collection) {
        if (collection == null) {
            this.taskIds = null;
        } else {
            this.taskIds = new ArrayList(collection);
        }
    }

    public ListThingRegistrationTasksResult withTaskIds(String... strArr) {
        if (getTaskIds() == null) {
            this.taskIds = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.taskIds.add(str);
        }
        return this;
    }

    public ListThingRegistrationTasksResult withTaskIds(Collection<String> collection) {
        setTaskIds(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingRegistrationTasksResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTaskIds() != null) {
            sb.append("taskIds: " + getTaskIds() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTaskIds() == null ? 0 : getTaskIds().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingRegistrationTasksResult)) {
            return false;
        }
        ListThingRegistrationTasksResult listThingRegistrationTasksResult = (ListThingRegistrationTasksResult) obj;
        if ((listThingRegistrationTasksResult.getTaskIds() == null) ^ (getTaskIds() == null)) {
            return false;
        }
        if (listThingRegistrationTasksResult.getTaskIds() != null && !listThingRegistrationTasksResult.getTaskIds().equals(getTaskIds())) {
            return false;
        }
        if ((listThingRegistrationTasksResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listThingRegistrationTasksResult.getNextToken() == null || listThingRegistrationTasksResult.getNextToken().equals(getNextToken());
    }
}
