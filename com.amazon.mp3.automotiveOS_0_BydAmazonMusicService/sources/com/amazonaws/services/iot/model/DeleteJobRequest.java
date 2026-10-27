package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteJobRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean force;
    private String jobId;
    private String namespaceId;

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public DeleteJobRequest withJobId(String str) {
        this.jobId = str;
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

    public DeleteJobRequest withForce(Boolean bool) {
        this.force = bool;
        return this;
    }

    public String getNamespaceId() {
        return this.namespaceId;
    }

    public void setNamespaceId(String str) {
        this.namespaceId = str;
    }

    public DeleteJobRequest withNamespaceId(String str) {
        this.namespaceId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobId() != null) {
            sb.append("jobId: " + getJobId() + ",");
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
        return (((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getForce() == null ? 0 : getForce().hashCode())) * 31) + (getNamespaceId() != null ? getNamespaceId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteJobRequest)) {
            return false;
        }
        DeleteJobRequest deleteJobRequest = (DeleteJobRequest) obj;
        if ((deleteJobRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (deleteJobRequest.getJobId() != null && !deleteJobRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((deleteJobRequest.getForce() == null) ^ (getForce() == null)) {
            return false;
        }
        if (deleteJobRequest.getForce() != null && !deleteJobRequest.getForce().equals(getForce())) {
            return false;
        }
        if ((deleteJobRequest.getNamespaceId() == null) ^ (getNamespaceId() == null)) {
            return false;
        }
        return deleteJobRequest.getNamespaceId() == null || deleteJobRequest.getNamespaceId().equals(getNamespaceId());
    }
}
