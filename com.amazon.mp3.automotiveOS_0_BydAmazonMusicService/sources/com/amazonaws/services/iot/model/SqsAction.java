package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class SqsAction implements Serializable {
    private String queueUrl;
    private String roleArn;
    private Boolean useBase64;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public SqsAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public void setQueueUrl(String str) {
        this.queueUrl = str;
    }

    public SqsAction withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }

    public Boolean isUseBase64() {
        return this.useBase64;
    }

    public Boolean getUseBase64() {
        return this.useBase64;
    }

    public void setUseBase64(Boolean bool) {
        this.useBase64 = bool;
    }

    public SqsAction withUseBase64(Boolean bool) {
        this.useBase64 = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getQueueUrl() != null) {
            sb.append("queueUrl: " + getQueueUrl() + ",");
        }
        if (getUseBase64() != null) {
            sb.append("useBase64: " + getUseBase64());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getQueueUrl() == null ? 0 : getQueueUrl().hashCode())) * 31) + (getUseBase64() != null ? getUseBase64().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SqsAction)) {
            return false;
        }
        SqsAction sqsAction = (SqsAction) obj;
        if ((sqsAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (sqsAction.getRoleArn() != null && !sqsAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((sqsAction.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (sqsAction.getQueueUrl() != null && !sqsAction.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((sqsAction.getUseBase64() == null) ^ (getUseBase64() == null)) {
            return false;
        }
        return sqsAction.getUseBase64() == null || sqsAction.getUseBase64().equals(getUseBase64());
    }
}
