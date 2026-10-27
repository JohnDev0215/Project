package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class JobExecutionSummaryForJob implements Serializable {
    private JobExecutionSummary jobExecutionSummary;
    private String thingArn;

    public String getThingArn() {
        return this.thingArn;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public JobExecutionSummaryForJob withThingArn(String str) {
        this.thingArn = str;
        return this;
    }

    public JobExecutionSummary getJobExecutionSummary() {
        return this.jobExecutionSummary;
    }

    public void setJobExecutionSummary(JobExecutionSummary jobExecutionSummary) {
        this.jobExecutionSummary = jobExecutionSummary;
    }

    public JobExecutionSummaryForJob withJobExecutionSummary(JobExecutionSummary jobExecutionSummary) {
        this.jobExecutionSummary = jobExecutionSummary;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingArn() != null) {
            sb.append("thingArn: " + getThingArn() + ",");
        }
        if (getJobExecutionSummary() != null) {
            sb.append("jobExecutionSummary: " + getJobExecutionSummary());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getThingArn() == null ? 0 : getThingArn().hashCode()) + 31) * 31) + (getJobExecutionSummary() != null ? getJobExecutionSummary().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecutionSummaryForJob)) {
            return false;
        }
        JobExecutionSummaryForJob jobExecutionSummaryForJob = (JobExecutionSummaryForJob) obj;
        if ((jobExecutionSummaryForJob.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        if (jobExecutionSummaryForJob.getThingArn() != null && !jobExecutionSummaryForJob.getThingArn().equals(getThingArn())) {
            return false;
        }
        if ((jobExecutionSummaryForJob.getJobExecutionSummary() == null) ^ (getJobExecutionSummary() == null)) {
            return false;
        }
        return jobExecutionSummaryForJob.getJobExecutionSummary() == null || jobExecutionSummaryForJob.getJobExecutionSummary().equals(getJobExecutionSummary());
    }
}
