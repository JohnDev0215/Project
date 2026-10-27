package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CancelJobRequest extends AmazonWebServiceRequest implements Serializable {
    private String comment;
    private Boolean force;
    private String jobId;
    private String reasonCode;

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public CancelJobRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public String getReasonCode() {
        return this.reasonCode;
    }

    public void setReasonCode(String str) {
        this.reasonCode = str;
    }

    public CancelJobRequest withReasonCode(String str) {
        this.reasonCode = str;
        return this;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public CancelJobRequest withComment(String str) {
        this.comment = str;
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

    public CancelJobRequest withForce(Boolean bool) {
        this.force = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobId() != null) {
            sb.append("jobId: " + getJobId() + ",");
        }
        if (getReasonCode() != null) {
            sb.append("reasonCode: " + getReasonCode() + ",");
        }
        if (getComment() != null) {
            sb.append("comment: " + getComment() + ",");
        }
        if (getForce() != null) {
            sb.append("force: " + getForce());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getReasonCode() == null ? 0 : getReasonCode().hashCode())) * 31) + (getComment() == null ? 0 : getComment().hashCode())) * 31) + (getForce() != null ? getForce().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CancelJobRequest)) {
            return false;
        }
        CancelJobRequest cancelJobRequest = (CancelJobRequest) obj;
        if ((cancelJobRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (cancelJobRequest.getJobId() != null && !cancelJobRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((cancelJobRequest.getReasonCode() == null) ^ (getReasonCode() == null)) {
            return false;
        }
        if (cancelJobRequest.getReasonCode() != null && !cancelJobRequest.getReasonCode().equals(getReasonCode())) {
            return false;
        }
        if ((cancelJobRequest.getComment() == null) ^ (getComment() == null)) {
            return false;
        }
        if (cancelJobRequest.getComment() != null && !cancelJobRequest.getComment().equals(getComment())) {
            return false;
        }
        if ((cancelJobRequest.getForce() == null) ^ (getForce() == null)) {
            return false;
        }
        return cancelJobRequest.getForce() == null || cancelJobRequest.getForce().equals(getForce());
    }
}
