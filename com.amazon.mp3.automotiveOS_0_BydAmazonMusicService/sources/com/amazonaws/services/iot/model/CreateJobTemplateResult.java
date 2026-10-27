package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateJobTemplateResult implements Serializable {
    private String jobTemplateArn;
    private String jobTemplateId;

    public String getJobTemplateArn() {
        return this.jobTemplateArn;
    }

    public void setJobTemplateArn(String str) {
        this.jobTemplateArn = str;
    }

    public CreateJobTemplateResult withJobTemplateArn(String str) {
        this.jobTemplateArn = str;
        return this;
    }

    public String getJobTemplateId() {
        return this.jobTemplateId;
    }

    public void setJobTemplateId(String str) {
        this.jobTemplateId = str;
    }

    public CreateJobTemplateResult withJobTemplateId(String str) {
        this.jobTemplateId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobTemplateArn() != null) {
            sb.append("jobTemplateArn: " + getJobTemplateArn() + ",");
        }
        if (getJobTemplateId() != null) {
            sb.append("jobTemplateId: " + getJobTemplateId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getJobTemplateArn() == null ? 0 : getJobTemplateArn().hashCode()) + 31) * 31) + (getJobTemplateId() != null ? getJobTemplateId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateJobTemplateResult)) {
            return false;
        }
        CreateJobTemplateResult createJobTemplateResult = (CreateJobTemplateResult) obj;
        if ((createJobTemplateResult.getJobTemplateArn() == null) ^ (getJobTemplateArn() == null)) {
            return false;
        }
        if (createJobTemplateResult.getJobTemplateArn() != null && !createJobTemplateResult.getJobTemplateArn().equals(getJobTemplateArn())) {
            return false;
        }
        if ((createJobTemplateResult.getJobTemplateId() == null) ^ (getJobTemplateId() == null)) {
            return false;
        }
        return createJobTemplateResult.getJobTemplateId() == null || createJobTemplateResult.getJobTemplateId().equals(getJobTemplateId());
    }
}
