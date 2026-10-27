package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteJobTemplateRequest extends AmazonWebServiceRequest implements Serializable {
    private String jobTemplateId;

    public String getJobTemplateId() {
        return this.jobTemplateId;
    }

    public void setJobTemplateId(String str) {
        this.jobTemplateId = str;
    }

    public DeleteJobTemplateRequest withJobTemplateId(String str) {
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
        if (obj == null || !(obj instanceof DeleteJobTemplateRequest)) {
            return false;
        }
        DeleteJobTemplateRequest deleteJobTemplateRequest = (DeleteJobTemplateRequest) obj;
        if ((deleteJobTemplateRequest.getJobTemplateId() == null) ^ (getJobTemplateId() == null)) {
            return false;
        }
        return deleteJobTemplateRequest.getJobTemplateId() == null || deleteJobTemplateRequest.getJobTemplateId().equals(getJobTemplateId());
    }
}
