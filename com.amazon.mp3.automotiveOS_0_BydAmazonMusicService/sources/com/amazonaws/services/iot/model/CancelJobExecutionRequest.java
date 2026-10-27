package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class CancelJobExecutionRequest extends AmazonWebServiceRequest implements Serializable {
    private Long expectedVersion;
    private Boolean force;
    private String jobId;
    private Map<String, String> statusDetails;
    private String thingName;

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public CancelJobExecutionRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public CancelJobExecutionRequest withThingName(String str) {
        this.thingName = str;
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

    public CancelJobExecutionRequest withForce(Boolean bool) {
        this.force = bool;
        return this;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public CancelJobExecutionRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
        return this;
    }

    public Map<String, String> getStatusDetails() {
        return this.statusDetails;
    }

    public void setStatusDetails(Map<String, String> map) {
        this.statusDetails = map;
    }

    public CancelJobExecutionRequest withStatusDetails(Map<String, String> map) {
        this.statusDetails = map;
        return this;
    }

    public CancelJobExecutionRequest addstatusDetailsEntry(String str, String str2) {
        if (this.statusDetails == null) {
            this.statusDetails = new HashMap();
        }
        if (this.statusDetails.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.statusDetails.put(str, str2);
        return this;
    }

    public CancelJobExecutionRequest clearstatusDetailsEntries() {
        this.statusDetails = null;
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
        if (getForce() != null) {
            sb.append("force: " + getForce() + ",");
        }
        if (getExpectedVersion() != null) {
            sb.append("expectedVersion: " + getExpectedVersion() + ",");
        }
        if (getStatusDetails() != null) {
            sb.append("statusDetails: " + getStatusDetails());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getForce() == null ? 0 : getForce().hashCode())) * 31) + (getExpectedVersion() == null ? 0 : getExpectedVersion().hashCode())) * 31) + (getStatusDetails() != null ? getStatusDetails().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CancelJobExecutionRequest)) {
            return false;
        }
        CancelJobExecutionRequest cancelJobExecutionRequest = (CancelJobExecutionRequest) obj;
        if ((cancelJobExecutionRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (cancelJobExecutionRequest.getJobId() != null && !cancelJobExecutionRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((cancelJobExecutionRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (cancelJobExecutionRequest.getThingName() != null && !cancelJobExecutionRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((cancelJobExecutionRequest.getForce() == null) ^ (getForce() == null)) {
            return false;
        }
        if (cancelJobExecutionRequest.getForce() != null && !cancelJobExecutionRequest.getForce().equals(getForce())) {
            return false;
        }
        if ((cancelJobExecutionRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        if (cancelJobExecutionRequest.getExpectedVersion() != null && !cancelJobExecutionRequest.getExpectedVersion().equals(getExpectedVersion())) {
            return false;
        }
        if ((cancelJobExecutionRequest.getStatusDetails() == null) ^ (getStatusDetails() == null)) {
            return false;
        }
        return cancelJobExecutionRequest.getStatusDetails() == null || cancelJobExecutionRequest.getStatusDetails().equals(getStatusDetails());
    }
}
