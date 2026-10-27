package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeJobExecutionRequest extends AmazonWebServiceRequest implements Serializable {
    private Long executionNumber;
    private String jobId;
    private String thingName;

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public DescribeJobExecutionRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public DescribeJobExecutionRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public Long getExecutionNumber() {
        return this.executionNumber;
    }

    public void setExecutionNumber(Long l) {
        this.executionNumber = l;
    }

    public DescribeJobExecutionRequest withExecutionNumber(Long l) {
        this.executionNumber = l;
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
            sb.append("executionNumber: " + getExecutionNumber());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getExecutionNumber() != null ? getExecutionNumber().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeJobExecutionRequest)) {
            return false;
        }
        DescribeJobExecutionRequest describeJobExecutionRequest = (DescribeJobExecutionRequest) obj;
        if ((describeJobExecutionRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (describeJobExecutionRequest.getJobId() != null && !describeJobExecutionRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((describeJobExecutionRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (describeJobExecutionRequest.getThingName() != null && !describeJobExecutionRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((describeJobExecutionRequest.getExecutionNumber() == null) ^ (getExecutionNumber() == null)) {
            return false;
        }
        return describeJobExecutionRequest.getExecutionNumber() == null || describeJobExecutionRequest.getExecutionNumber().equals(getExecutionNumber());
    }
}
