package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeJobTemplateRequest extends AmazonWebServiceRequest implements Serializable {
    private String jobTemplateId;

    public String getJobTemplateId() {
        return this.jobTemplateId;
    }

    public void setJobTemplateId(String str) {
        this.jobTemplateId = str;
    }

    public DescribeJobTemplateRequest withJobTemplateId(String str) {
        this.jobTemplateId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobTemplateId() != null) {
            sb.append("jobTemplateId: " + getJobTemplateId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getJobTemplateId() == null ? 0 : getJobTemplateId().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeJobTemplateRequest)) {
            return false;
        }
        DescribeJobTemplateRequest describeJobTemplateRequest = (DescribeJobTemplateRequest) obj;
        if ((describeJobTemplateRequest.getJobTemplateId() == null) ^ (getJobTemplateId() == null)) {
            return false;
        }
        return describeJobTemplateRequest.getJobTemplateId() == null || describeJobTemplateRequest.getJobTemplateId().equals(getJobTemplateId());
    }
}
