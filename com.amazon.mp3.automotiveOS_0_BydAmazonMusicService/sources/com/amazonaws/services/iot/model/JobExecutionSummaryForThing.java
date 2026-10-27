package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class JobExecutionSummaryForThing implements Serializable {
    private JobExecutionSummary jobExecutionSummary;
    private String jobId;

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public JobExecutionSummaryForThing withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public JobExecutionSummary getJobExecutionSummary() {
        return this.jobExecutionSummary;
    }

    public void setJobExecutionSummary(JobExecutionSummary jobExecutionSummary) {
        this.jobExecutionSummary = jobExecutionSummary;
    }

    public JobExecutionSummaryForThing withJobExecutionSummary(JobExecutionSummary jobExecutionSummary) {
        this.jobExecutionSummary = jobExecutionSummary;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobId() != null) {
            sb.append("jobId: " + getJobId() + ",");
        }
        if (getJobExecutionSummary() != null) {
            sb.append("jobExecutionSummary: " + getJobExecutionSummary());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getJobExecutionSummary() != null ? getJobExecutionSummary().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecutionSummaryForThing)) {
            return false;
        }
        JobExecutionSummaryForThing jobExecutionSummaryForThing = (JobExecutionSummaryForThing) obj;
        if ((jobExecutionSummaryForThing.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (jobExecutionSummaryForThing.getJobId() != null && !jobExecutionSummaryForThing.getJobId().equals(getJobId())) {
            return false;
        }
        if ((jobExecutionSummaryForThing.getJobExecutionSummary() == null) ^ (getJobExecutionSummary() == null)) {
            return false;
        }
        return jobExecutionSummaryForThing.getJobExecutionSummary() == null || jobExecutionSummaryForThing.getJobExecutionSummary().equals(getJobExecutionSummary());
    }
}
