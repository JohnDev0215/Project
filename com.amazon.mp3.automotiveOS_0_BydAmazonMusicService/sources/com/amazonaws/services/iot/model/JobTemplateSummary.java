package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class JobTemplateSummary implements Serializable {
    private Date createdAt;
    private String description;
    private String jobTemplateArn;
    private String jobTemplateId;

    public String getJobTemplateArn() {
        return this.jobTemplateArn;
    }

    public void setJobTemplateArn(String str) {
        this.jobTemplateArn = str;
    }

    public JobTemplateSummary withJobTemplateArn(String str) {
        this.jobTemplateArn = str;
        return this;
    }

    public String getJobTemplateId() {
        return this.jobTemplateId;
    }

    public void setJobTemplateId(String str) {
        this.jobTemplateId = str;
    }

    public JobTemplateSummary withJobTemplateId(String str) {
        this.jobTemplateId = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public JobTemplateSummary withDescription(String str) {
        this.description = str;
        return this;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public JobTemplateSummary withCreatedAt(Date date) {
        this.createdAt = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobTemplateArn() != null) {
            sb.append("jobTemplateArn: " + getJobTemplateArn() + ",");
        }
        if (getJobTemplateId() != null) {
            sb.append("jobTemplateId: " + getJobTemplateId() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription() + ",");
        }
        if (getCreatedAt() != null) {
            sb.append("createdAt: " + getCreatedAt());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getJobTemplateArn() == null ? 0 : getJobTemplateArn().hashCode()) + 31) * 31) + (getJobTemplateId() == null ? 0 : getJobTemplateId().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobTemplateSummary)) {
            return false;
        }
        JobTemplateSummary jobTemplateSummary = (JobTemplateSummary) obj;
        if ((jobTemplateSummary.getJobTemplateArn() == null) ^ (getJobTemplateArn() == null)) {
            return false;
        }
        if (jobTemplateSummary.getJobTemplateArn() != null && !jobTemplateSummary.getJobTemplateArn().equals(getJobTemplateArn())) {
            return false;
        }
        if ((jobTemplateSummary.getJobTemplateId() == null) ^ (getJobTemplateId() == null)) {
            return false;
        }
        if (jobTemplateSummary.getJobTemplateId() != null && !jobTemplateSummary.getJobTemplateId().equals(getJobTemplateId())) {
            return false;
        }
        if ((jobTemplateSummary.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (jobTemplateSummary.getDescription() != null && !jobTemplateSummary.getDescription().equals(getDescription())) {
            return false;
        }
        if ((jobTemplateSummary.getCreatedAt() == null) ^ (getCreatedAt() == null)) {
            return false;
        }
        return jobTemplateSummary.getCreatedAt() == null || jobTemplateSummary.getCreatedAt().equals(getCreatedAt());
    }
}
