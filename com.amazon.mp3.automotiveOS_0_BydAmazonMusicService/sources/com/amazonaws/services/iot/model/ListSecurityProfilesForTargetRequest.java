package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListSecurityProfilesForTargetRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private Boolean recursive;
    private String securityProfileTargetArn;

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListSecurityProfilesForTargetRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListSecurityProfilesForTargetRequest withMaxResults(Integer num) {
        this.maxResults = num;
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

    public ListSecurityProfilesForTargetRequest withRecursive(Boolean bool) {
        this.recursive = bool;
        return this;
    }

    public String getSecurityProfileTargetArn() {
        return this.securityProfileTargetArn;
    }

    public void setSecurityProfileTargetArn(String str) {
        this.securityProfileTargetArn = str;
    }

    public ListSecurityProfilesForTargetRequest withSecurityProfileTargetArn(String str) {
        this.securityProfileTargetArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults() + ",");
        }
        if (getRecursive() != null) {
            sb.append("recursive: " + getRecursive() + ",");
        }
        if (getSecurityProfileTargetArn() != null) {
            sb.append("securityProfileTargetArn: " + getSecurityProfileTargetArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getNextToken() == null ? 0 : getNextToken().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getRecursive() == null ? 0 : getRecursive().hashCode())) * 31) + (getSecurityProfileTargetArn() != null ? getSecurityProfileTargetArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListSecurityProfilesForTargetRequest)) {
            return false;
        }
        ListSecurityProfilesForTargetRequest listSecurityProfilesForTargetRequest = (ListSecurityProfilesForTargetRequest) obj;
        if ((listSecurityProfilesForTargetRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listSecurityProfilesForTargetRequest.getNextToken() != null && !listSecurityProfilesForTargetRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listSecurityProfilesForTargetRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listSecurityProfilesForTargetRequest.getMaxResults() != null && !listSecurityProfilesForTargetRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listSecurityProfilesForTargetRequest.getRecursive() == null) ^ (getRecursive() == null)) {
            return false;
        }
        if (listSecurityProfilesForTargetRequest.getRecursive() != null && !listSecurityProfilesForTargetRequest.getRecursive().equals(getRecursive())) {
            return false;
        }
        if ((listSecurityProfilesForTargetRequest.getSecurityProfileTargetArn() == null) ^ (getSecurityProfileTargetArn() == null)) {
            return false;
        }
        return listSecurityProfilesForTargetRequest.getSecurityProfileTargetArn() == null || listSecurityProfilesForTargetRequest.getSecurityProfileTargetArn().equals(getSecurityProfileTargetArn());
    }
}
