package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class OpenSearchAction implements Serializable {
    private String endpoint;
    private String id;
    private String index;
    private String roleArn;
    private String type;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public OpenSearchAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(String str) {
        this.endpoint = str;
    }

    public OpenSearchAction withEndpoint(String str) {
        this.endpoint = str;
        return this;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String str) {
        this.index = str;
    }

    public OpenSearchAction withIndex(String str) {
        this.index = str;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public OpenSearchAction withType(String str) {
        this.type = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public OpenSearchAction withId(String str) {
        this.id = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getEndpoint() != null) {
            sb.append("endpoint: " + getEndpoint() + ",");
        }
        if (getIndex() != null) {
            sb.append("index: " + getIndex() + ",");
        }
        if (getType() != null) {
            sb.append("type: " + getType() + ",");
        }
        if (getId() != null) {
            sb.append("id: " + getId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getEndpoint() == null ? 0 : getEndpoint().hashCode())) * 31) + (getIndex() == null ? 0 : getIndex().hashCode())) * 31) + (getType() == null ? 0 : getType().hashCode())) * 31) + (getId() != null ? getId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OpenSearchAction)) {
            return false;
        }
        OpenSearchAction openSearchAction = (OpenSearchAction) obj;
        if ((openSearchAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (openSearchAction.getRoleArn() != null && !openSearchAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((openSearchAction.getEndpoint() == null) ^ (getEndpoint() == null)) {
            return false;
        }
        if (openSearchAction.getEndpoint() != null && !openSearchAction.getEndpoint().equals(getEndpoint())) {
            return false;
        }
        if ((openSearchAction.getIndex() == null) ^ (getIndex() == null)) {
            return false;
        }
        if (openSearchAction.getIndex() != null && !openSearchAction.getIndex().equals(getIndex())) {
            return false;
        }
        if ((openSearchAction.getType() == null) ^ (getType() == null)) {
            return false;
        }
        if (openSearchAction.getType() != null && !openSearchAction.getType().equals(getType())) {
            return false;
        }
        if ((openSearchAction.getId() == null) ^ (getId() == null)) {
            return false;
        }
        return openSearchAction.getId() == null || openSearchAction.getId().equals(getId());
    }
}
