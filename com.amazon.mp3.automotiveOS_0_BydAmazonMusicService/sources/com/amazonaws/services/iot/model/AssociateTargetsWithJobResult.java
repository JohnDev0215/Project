package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AssociateTargetsWithJobResult implements Serializable {
    private String description;
    private String jobArn;
    private String jobId;

    public String getJobArn() {
        return this.jobArn;
    }

    public void setJobArn(String str) {
        this.jobArn = str;
    }

    public AssociateTargetsWithJobResult withJobArn(String str) {
        this.jobArn = str;
        return this;
    }

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public AssociateTargetsWithJobResult withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public AssociateTargetsWithJobResult withDescription(String str) {
        this.description = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobArn() != null) {
            sb.append("jobArn: " + getJobArn() + ",");
        }
        if (getJobId() != null) {
            sb.append("jobId: " + getJobId() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getJobArn() == null ? 0 : getJobArn().hashCode()) + 31) * 31) + (getJobId() == null ? 0 : getJobId().hashCode())) * 31) + (getDescription() != null ? getDescription().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssociateTargetsWithJobResult)) {
            return false;
        }
        AssociateTargetsWithJobResult associateTargetsWithJobResult = (AssociateTargetsWithJobResult) obj;
        if ((associateTargetsWithJobResult.getJobArn() == null) ^ (getJobArn() == null)) {
            return false;
        }
        if (associateTargetsWithJobResult.getJobArn() != null && !associateTargetsWithJobResult.getJobArn().equals(getJobArn())) {
            return false;
        }
        if ((associateTargetsWithJobResult.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (associateTargetsWithJobResult.getJobId() != null && !associateTargetsWithJobResult.getJobId().equals(getJobId())) {
            return false;
        }
        if ((associateTargetsWithJobResult.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        return associateTargetsWithJobResult.getDescription() == null || associateTargetsWithJobResult.getDescription().equals(getDescription());
    }
}
