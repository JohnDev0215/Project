package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListTargetsForSecurityProfileRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String securityProfileName;

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public ListTargetsForSecurityProfileRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListTargetsForSecurityProfileRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListTargetsForSecurityProfileRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileName() != null) {
            sb.append("securityProfileName: " + getSecurityProfileName() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getMaxResults() != null ? getMaxResults().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTargetsForSecurityProfileRequest)) {
            return false;
        }
        ListTargetsForSecurityProfileRequest listTargetsForSecurityProfileRequest = (ListTargetsForSecurityProfileRequest) obj;
        if ((listTargetsForSecurityProfileRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (listTargetsForSecurityProfileRequest.getSecurityProfileName() != null && !listTargetsForSecurityProfileRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((listTargetsForSecurityProfileRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listTargetsForSecurityProfileRequest.getNextToken() != null && !listTargetsForSecurityProfileRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listTargetsForSecurityProfileRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listTargetsForSecurityProfileRequest.getMaxResults() == null || listTargetsForSecurityProfileRequest.getMaxResults().equals(getMaxResults());
    }
}
