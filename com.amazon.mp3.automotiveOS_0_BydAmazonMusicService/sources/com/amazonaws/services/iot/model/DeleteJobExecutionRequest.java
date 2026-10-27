package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteJobExecutionRequest extends AmazonWebServiceRequest implements Serializable {
    private Long executionNumber;
    private Boolean force;
    private String jobId;
    private String namespaceId;
    private String thingName;

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public DeleteJobExecutionRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public DeleteJobExecutionRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public Long getExecutionNumber() {
        return this.executionNumber;
    }

    public void setExecutionNumber(Long l) {
        this.executionNumber = l;
    }

    public DeleteJobExecutionRequest withExecutionNumber(Long l) {
        this.executionNumber = l;
        return this;
    }

    public Boolean isForce() {
        return this.force;
    }

    public Boolean getForce() {
        return this.force;
    }

    public void setForce(Boolean bool) {
        this.force = bool;
    }

    public DeleteJobExecutionRequest withForce(Boolean bool) {
        this.force = bool;
        return this;
    }

    public String getNamespaceId() {
        return this.namespaceId;
    }

    public void setNamespaceId(String str) {
        this.namespaceId = str;
    }

    public DeleteJobExecutionRequest withNamespaceId(String str) {
        this.namespaceId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobId() != null) {
            sb.append("jobId: " + getJobId() + ",");
        }
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getExecutionNumber() != null) {
            sb.append("executionNumber: " + getExecutionNumber() + ",");
        }
        if (getForce() != null) {
            sb.append("force: " + getForce() + ",");
        }
        if (getNamespaceId() != null) {
            sb.append("namespaceId: " + getNamespaceId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getExecutionNumber() == null ? 0 : getExecutionNumber().hashCode())) * 31) + (getForce() == null ? 0 : getForce().hashCode())) * 31) + (getNamespaceId() != null ? getNamespaceId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteJobExecutionRequest)) {
            return false;
        }
        DeleteJobExecutionRequest deleteJobExecutionRequest = (DeleteJobExecutionRequest) obj;
        if ((deleteJobExecutionRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (deleteJobExecutionRequest.getJobId() != null && !deleteJobExecutionRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((deleteJobExecutionRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (deleteJobExecutionRequest.getThingName() != null && !deleteJobExecutionRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((deleteJobExecutionRequest.getExecutionNumber() == null) ^ (getExecutionNumber() == null)) {
            return false;
        }
        if (deleteJobExecutionRequest.getExecutionNumber() != null && !deleteJobExecutionRequest.getExecutionNumber().equals(getExecutionNumber())) {
            return false;
        }
        if ((deleteJobExecutionRequest.getForce() == null) ^ (getForce() == null)) {
            return false;
        }
        if (deleteJobExecutionRequest.getForce() != null && !deleteJobExecutionRequest.getForce().equals(getForce())) {
            return false;
        }
        if ((deleteJobExecutionRequest.getNamespaceId() == null) ^ (getNamespaceId() == null)) {
            return false;
        }
        return deleteJobExecutionRequest.getNamespaceId() == null || deleteJobExecutionRequest.getNamespaceId().equals(getNamespaceId());
    }
}
