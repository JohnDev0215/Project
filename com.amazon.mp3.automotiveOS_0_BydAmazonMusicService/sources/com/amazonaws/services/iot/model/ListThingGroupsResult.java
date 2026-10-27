package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListThingGroupsResult implements Serializable {
    private String nextToken;
    private List<GroupNameAndArn> thingGroups;

    public List<GroupNameAndArn> getThingGroups() {
        return this.thingGroups;
    }

    public void setThingGroups(Collection<GroupNameAndArn> collection) {
        if (collection == null) {
            this.thingGroups = null;
        } else {
            this.thingGroups = new ArrayList(collection);
        }
    }

    public ListThingGroupsResult withThingGroups(GroupNameAndArn... groupNameAndArnArr) {
        if (getThingGroups() == null) {
            this.thingGroups = new ArrayList(groupNameAndArnArr.length);
        }
        for (GroupNameAndArn groupNameAndArn : groupNameAndArnArr) {
            this.thingGroups.add(groupNameAndArn);
        }
        return this;
    }

    public ListThingGroupsResult withThingGroups(Collection<GroupNameAndArn> collection) {
        setThingGroups(collection);
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListThingGroupsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingGroups() != null) {
            sb.append("thingGroups: " + getThingGroups() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThingGroups() == null ? 0 : getThingGroups().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingGroupsResult)) {
            return false;
        }
        ListThingGroupsResult listThingGroupsResult = (ListThingGroupsResult) obj;
        if ((listThingGroupsResult.getThingGroups() == null) ^ (getThingGroups() == null)) {
            return false;
        }
        if (listThingGroupsResult.getThingGroups() != null && !listThingGroupsResult.getThingGroups().equals(getThingGroups())) {
            return false;
        }
        if ((listThingGroupsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listThingGroupsResult.getNextToken() == null || listThingGroupsResult.getNextToken().equals(getNextToken());
    }
}
