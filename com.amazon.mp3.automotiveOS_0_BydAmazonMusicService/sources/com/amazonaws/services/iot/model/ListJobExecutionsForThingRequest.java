package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListJobExecutionsForThingRequest extends AmazonWebServiceRequest implements Serializable {
    private String jobId;
    private Integer maxResults;
    private String namespaceId;
    private String nextToken;
    private String status;
    private String thingName;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public ListJobExecutionsForThingRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public ListJobExecutionsForThingRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
    }

    public ListJobExecutionsForThingRequest withStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
        return this;
    }

    public String getNamespaceId() {
        return this.namespaceId;
    }

    public void setNamespaceId(String str) {
        this.namespaceId = str;
    }

    public ListJobExecutionsForThingRequest withNamespaceId(String str) {
        this.namespaceId = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListJobExecutionsForThingRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListJobExecutionsForThingRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public ListJobExecutionsForThingRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus() + ",");
        }
        if (getNamespaceId() != null) {
            sb.append("namespaceId: " + getNamespaceId() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getJobId() != null) {
            sb.append("jobId: " + getJobId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getNamespaceId() == null ? 0 : getNamespaceId().hashCode())) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getJobId() != null ? getJobId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListJobExecutionsForThingRequest)) {
            return false;
        }
        ListJobExecutionsForThingRequest listJobExecutionsForThingRequest = (ListJobExecutionsForThingRequest) obj;
        if ((listJobExecutionsForThingRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (listJobExecutionsForThingRequest.getThingName() != null && !listJobExecutionsForThingRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((listJobExecutionsForThingRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (listJobExecutionsForThingRequest.getStatus() != null && !listJobExecutionsForThingRequest.getStatus().equals(getStatus())) {
            return false;
        }
        if ((listJobExecutionsForThingRequest.getNamespaceId() == null) ^ (getNamespaceId() == null)) {
            return false;
        }
        if (listJobExecutionsForThingRequest.getNamespaceId() != null && !listJobExecutionsForThingRequest.getNamespaceId().equals(getNamespaceId())) {
            return false;
        }
        if ((listJobExecutionsForThingRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listJobExecutionsForThingRequest.getMaxResults() != null && !listJobExecutionsForThingRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listJobExecutionsForThingRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listJobExecutionsForThingRequest.getNextToken() != null && !listJobExecutionsForThingRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listJobExecutionsForThingRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        return listJobExecutionsForThingRequest.getJobId() == null || listJobExecutionsForThingRequest.getJobId().equals(getJobId());
    }
}
