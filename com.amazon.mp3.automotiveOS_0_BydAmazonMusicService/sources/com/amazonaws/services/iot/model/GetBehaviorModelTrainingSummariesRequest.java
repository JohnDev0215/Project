package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class GetBehaviorModelTrainingSummariesRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String securityProfileName;

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public GetBehaviorModelTrainingSummariesRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public GetBehaviorModelTrainingSummariesRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public GetBehaviorModelTrainingSummariesRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileName() != null) {
            sb.append("securityProfileName: " + getSecurityProfileName() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetBehaviorModelTrainingSummariesRequest)) {
            return false;
        }
        GetBehaviorModelTrainingSummariesRequest getBehaviorModelTrainingSummariesRequest = (GetBehaviorModelTrainingSummariesRequest) obj;
        if ((getBehaviorModelTrainingSummariesRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (getBehaviorModelTrainingSummariesRequest.getSecurityProfileName() != null && !getBehaviorModelTrainingSummariesRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((getBehaviorModelTrainingSummariesRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (getBehaviorModelTrainingSummariesRequest.getMaxResults() != null && !getBehaviorModelTrainingSummariesRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((getBehaviorModelTrainingSummariesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return getBehaviorModelTrainingSummariesRequest.getNextToken() == null || getBehaviorModelTrainingSummariesRequest.getNextToken().equals(getNextToken());
    }
}
