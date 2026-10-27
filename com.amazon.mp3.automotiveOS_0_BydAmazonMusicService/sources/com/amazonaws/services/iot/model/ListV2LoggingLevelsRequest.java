package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListV2LoggingLevelsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String targetType;

    public String getTargetType() {
        return this.targetType;
    }

    public void setTargetType(String str) {
        this.targetType = str;
    }

    public ListV2LoggingLevelsRequest withTargetType(String str) {
        this.targetType = str;
        return this;
    }

    public void setTargetType(LogTargetType logTargetType) {
        this.targetType = logTargetType.toString();
    }

    public ListV2LoggingLevelsRequest withTargetType(LogTargetType logTargetType) {
        this.targetType = logTargetType.toString();
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListV2LoggingLevelsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListV2LoggingLevelsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTargetType() != null) {
            sb.append("targetType: " + getTargetType() + ",");
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
        return (((((getTargetType() == null ? 0 : getTargetType().hashCode()) + 31) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getMaxResults() != null ? getMaxResults().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListV2LoggingLevelsRequest)) {
            return false;
        }
        ListV2LoggingLevelsRequest listV2LoggingLevelsRequest = (ListV2LoggingLevelsRequest) obj;
        if ((listV2LoggingLevelsRequest.getTargetType() == null) ^ (getTargetType() == null)) {
            return false;
        }
        if (listV2LoggingLevelsRequest.getTargetType() != null && !listV2LoggingLevelsRequest.getTargetType().equals(getTargetType())) {
            return false;
        }
        if ((listV2LoggingLevelsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listV2LoggingLevelsRequest.getNextToken() != null && !listV2LoggingLevelsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listV2LoggingLevelsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listV2LoggingLevelsRequest.getMaxResults() == null || listV2LoggingLevelsRequest.getMaxResults().equals(getMaxResults());
    }
}
