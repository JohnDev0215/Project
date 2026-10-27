package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GroupNameAndArn implements Serializable {
    private String groupArn;
    private String groupName;

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public GroupNameAndArn withGroupName(String str) {
        this.groupName = str;
        return this;
    }

    public String getGroupArn() {
        return this.groupArn;
    }

    public void setGroupArn(String str) {
        this.groupArn = str;
    }

    public GroupNameAndArn withGroupArn(String str) {
        this.groupArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getGroupName() != null) {
            sb.append("groupName: " + getGroupName() + ",");
        }
        if (getGroupArn() != null) {
            sb.append("groupArn: " + getGroupArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getGroupName() == null ? 0 : getGroupName().hashCode()) + 31) * 31) + (getGroupArn() != null ? getGroupArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GroupNameAndArn)) {
            return false;
        }
        GroupNameAndArn groupNameAndArn = (GroupNameAndArn) obj;
        if ((groupNameAndArn.getGroupName() == null) ^ (getGroupName() == null)) {
            return false;
        }
        if (groupNameAndArn.getGroupName() != null && !groupNameAndArn.getGroupName().equals(getGroupName())) {
            return false;
        }
        if ((groupNameAndArn.getGroupArn() == null) ^ (getGroupArn() == null)) {
            return false;
        }
        return groupNameAndArn.getGroupArn() == null || groupNameAndArn.getGroupArn().equals(getGroupArn());
    }
}
