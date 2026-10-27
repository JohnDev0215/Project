package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AuthInfo implements Serializable {
    private String actionType;
    private List<String> resources;

    public String getActionType() {
        return this.actionType;
    }

    public void setActionType(String str) {
        this.actionType = str;
    }

    public AuthInfo withActionType(String str) {
        this.actionType = str;
        return this;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType.toString();
    }

    public AuthInfo withActionType(ActionType actionType) {
        this.actionType = actionType.toString();
        return this;
    }

    public List<String> getResources() {
        return this.resources;
    }

    public void setResources(Collection<String> collection) {
        if (collection == null) {
            this.resources = null;
        } else {
            this.resources = new ArrayList(collection);
        }
    }

    public AuthInfo withResources(String... strArr) {
        if (getResources() == null) {
            this.resources = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.resources.add(str);
        }
        return this;
    }

    public AuthInfo withResources(Collection<String> collection) {
        setResources(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getActionType() != null) {
            sb.append("actionType: " + getActionType() + ",");
        }
        if (getResources() != null) {
            sb.append("resources: " + getResources());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getActionType() == null ? 0 : getActionType().hashCode()) + 31) * 31) + (getResources() != null ? getResources().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuthInfo)) {
            return false;
        }
        AuthInfo authInfo = (AuthInfo) obj;
        if ((authInfo.getActionType() == null) ^ (getActionType() == null)) {
            return false;
        }
        if (authInfo.getActionType() != null && !authInfo.getActionType().equals(getActionType())) {
            return false;
        }
        if ((authInfo.getResources() == null) ^ (getResources() == null)) {
            return false;
        }
        return authInfo.getResources() == null || authInfo.getResources().equals(getResources());
    }
}
