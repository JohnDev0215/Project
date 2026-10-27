package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListAttachedPoliciesRequest extends AmazonWebServiceRequest implements Serializable {
    private String marker;
    private Integer pageSize;
    private Boolean recursive;
    private String target;

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public ListAttachedPoliciesRequest withTarget(String str) {
        this.target = str;
        return this;
    }

    public Boolean isRecursive() {
        return this.recursive;
    }

    public Boolean getRecursive() {
        return this.recursive;
    }

    public void setRecursive(Boolean bool) {
        this.recursive = bool;
    }

    public ListAttachedPoliciesRequest withRecursive(Boolean bool) {
        this.recursive = bool;
        return this;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String str) {
        this.marker = str;
    }

    public ListAttachedPoliciesRequest withMarker(String str) {
        this.marker = str;
        return this;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer num) {
        this.pageSize = num;
    }

    public ListAttachedPoliciesRequest withPageSize(Integer num) {
        this.pageSize = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTarget() != null) {
            sb.append("target: " + getTarget() + ",");
        }
        if (getRecursive() != null) {
            sb.append("recursive: " + getRecursive() + ",");
        }
        if (getMarker() != null) {
            sb.append("marker: " + getMarker() + ",");
        }
        if (getPageSize() != null) {
            sb.append("pageSize: " + getPageSize());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getTarget() == null ? 0 : getTarget().hashCode()) + 31) * 31) + (getRecursive() == null ? 0 : getRecursive().hashCode())) * 31) + (getMarker() == null ? 0 : getMarker().hashCode())) * 31) + (getPageSize() != null ? getPageSize().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAttachedPoliciesRequest)) {
            return false;
        }
        ListAttachedPoliciesRequest listAttachedPoliciesRequest = (ListAttachedPoliciesRequest) obj;
        if ((listAttachedPoliciesRequest.getTarget() == null) ^ (getTarget() == null)) {
            return false;
        }
        if (listAttachedPoliciesRequest.getTarget() != null && !listAttachedPoliciesRequest.getTarget().equals(getTarget())) {
            return false;
        }
        if ((listAttachedPoliciesRequest.getRecursive() == null) ^ (getRecursive() == null)) {
            return false;
        }
        if (listAttachedPoliciesRequest.getRecursive() != null && !listAttachedPoliciesRequest.getRecursive().equals(getRecursive())) {
            return false;
        }
        if ((listAttachedPoliciesRequest.getMarker() == null) ^ (getMarker() == null)) {
            return false;
        }
        if (listAttachedPoliciesRequest.getMarker() != null && !listAttachedPoliciesRequest.getMarker().equals(getMarker())) {
            return false;
        }
        if ((listAttachedPoliciesRequest.getPageSize() == null) ^ (getPageSize() == null)) {
            return false;
        }
        return listAttachedPoliciesRequest.getPageSize() == null || listAttachedPoliciesRequest.getPageSize().equals(getPageSize());
    }
}
