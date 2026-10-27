package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeJobResult implements Serializable {
    private String documentSource;
    private Job job;

    public String getDocumentSource() {
        return this.documentSource;
    }

    public void setDocumentSource(String str) {
        this.documentSource = str;
    }

    public DescribeJobResult withDocumentSource(String str) {
        this.documentSource = str;
        return this;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public DescribeJobResult withJob(Job job) {
        this.job = job;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDocumentSource() != null) {
            sb.append("documentSource: " + getDocumentSource() + ",");
        }
        if (getJob() != null) {
            sb.append("job: " + getJob());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getDocumentSource() == null ? 0 : getDocumentSource().hashCode()) + 31) * 31) + (getJob() != null ? getJob().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeJobResult)) {
            return false;
        }
        DescribeJobResult describeJobResult = (DescribeJobResult) obj;
        if ((describeJobResult.getDocumentSource() == null) ^ (getDocumentSource() == null)) {
            return false;
        }
        if (describeJobResult.getDocumentSource() != null && !describeJobResult.getDocumentSource().equals(getDocumentSource())) {
            return false;
        }
        if ((describeJobResult.getJob() == null) ^ (getJob() == null)) {
            return false;
        }
        return describeJobResult.getJob() == null || describeJobResult.getJob().equals(getJob());
    }
}
