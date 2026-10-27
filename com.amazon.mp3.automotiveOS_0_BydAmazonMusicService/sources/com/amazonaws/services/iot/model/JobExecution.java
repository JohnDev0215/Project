package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class JobExecution implements Serializable {
    private Long approximateSecondsBeforeTimedOut;
    private Long executionNumber;
    private Boolean forceCanceled;
    private String jobId;
    private Date lastUpdatedAt;
    private Date queuedAt;
    private Date startedAt;
    private String status;
    private JobExecutionStatusDetails statusDetails;
    private String thingArn;
    private Long versionNumber;

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public JobExecution withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public JobExecution withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
    }

    public JobExecution withStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
        return this;
    }

    public Boolean isForceCanceled() {
        return this.forceCanceled;
    }

    public Boolean getForceCanceled() {
        return this.forceCanceled;
    }

    public void setForceCanceled(Boolean bool) {
        this.forceCanceled = bool;
    }

    public JobExecution withForceCanceled(Boolean bool) {
        this.forceCanceled = bool;
        return this;
    }

    public JobExecutionStatusDetails getStatusDetails() {
        return this.statusDetails;
    }

    public void setStatusDetails(JobExecutionStatusDetails jobExecutionStatusDetails) {
        this.statusDetails = jobExecutionStatusDetails;
    }

    public JobExecution withStatusDetails(JobExecutionStatusDetails jobExecutionStatusDetails) {
        this.statusDetails = jobExecutionStatusDetails;
        return this;
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public JobExecution withThingArn(String str) {
        this.thingArn = str;
        return this;
    }

    public Date getQueuedAt() {
        return this.queuedAt;
    }

    public void setQueuedAt(Date date) {
        this.queuedAt = date;
    }

    public JobExecution withQueuedAt(Date date) {
        this.queuedAt = date;
        return this;
    }

    public Date getStartedAt() {
        return this.startedAt;
    }

    public void setStartedAt(Date date) {
        this.startedAt = date;
    }

    public JobExecution withStartedAt(Date date) {
        this.startedAt = date;
        return this;
    }

    public Date getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
    }

    public JobExecution withLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
        return this;
    }

    public Long getExecutionNumber() {
        return this.executionNumber;
    }

    public void setExecutionNumber(Long l) {
        this.executionNumber = l;
    }

    public JobExecution withExecutionNumber(Long l) {
        this.executionNumber = l;
        return this;
    }

    public Long getVersionNumber() {
        return this.versionNumber;
    }

    public void setVersionNumber(Long l) {
        this.versionNumber = l;
    }

    public JobExecution withVersionNumber(Long l) {
        this.versionNumber = l;
        return this;
    }

    public Long getApproximateSecondsBeforeTimedOut() {
        return this.approximateSecondsBeforeTimedOut;
    }

    public void setApproximateSecondsBeforeTimedOut(Long l) {
        this.approximateSecondsBeforeTimedOut = l;
    }

    public JobExecution withApproximateSecondsBeforeTimedOut(Long l) {
        this.approximateSecondsBeforeTimedOut = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobId() != null) {
            sb.append("jobId: " + getJobId() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus() + ",");
        }
        if (getForceCanceled() != null) {
            sb.append("forceCanceled: " + getForceCanceled() + ",");
        }
        if (getStatusDetails() != null) {
            sb.append("statusDetails: " + getStatusDetails() + ",");
        }
        if (getThingArn() != null) {
            sb.append("thingArn: " + getThingArn() + ",");
        }
        if (getQueuedAt() != null) {
            sb.append("queuedAt: " + getQueuedAt() + ",");
        }
        if (getStartedAt() != null) {
            sb.append("startedAt: " + getStartedAt() + ",");
        }
        if (getLastUpdatedAt() != null) {
            sb.append("lastUpdatedAt: " + getLastUpdatedAt() + ",");
        }
        if (getExecutionNumber() != null) {
            sb.append("executionNumber: " + getExecutionNumber() + ",");
        }
        if (getVersionNumber() != null) {
            sb.append("versionNumber: " + getVersionNumber() + ",");
        }
        if (getApproximateSecondsBeforeTimedOut() != null) {
            sb.append("approximateSecondsBeforeTimedOut: " + getApproximateSecondsBeforeTimedOut());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getForceCanceled() == null ? 0 : getForceCanceled().hashCode())) * 31) + (getStatusDetails() == null ? 0 : getStatusDetails().hashCode())) * 31) + (getThingArn() == null ? 0 : getThingArn().hashCode())) * 31) + (getQueuedAt() == null ? 0 : getQueuedAt().hashCode())) * 31) + (getStartedAt() == null ? 0 : getStartedAt().hashCode())) * 31) + (getLastUpdatedAt() == null ? 0 : getLastUpdatedAt().hashCode())) * 31) + (getExecutionNumber() == null ? 0 : getExecutionNumber().hashCode())) * 31) + (getVersionNumber() == null ? 0 : getVersionNumber().hashCode())) * 31) + (getApproximateSecondsBeforeTimedOut() != null ? getApproximateSecondsBeforeTimedOut().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecution)) {
            return false;
        }
        JobExecution jobExecution = (JobExecution) obj;
        if ((jobExecution.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (jobExecution.getJobId() != null && !jobExecution.getJobId().equals(getJobId())) {
            return false;
        }
        if ((jobExecution.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (jobExecution.getStatus() != null && !jobExecution.getStatus().equals(getStatus())) {
            return false;
        }
        if ((jobExecution.getForceCanceled() == null) ^ (getForceCanceled() == null)) {
            return false;
        }
        if (jobExecution.getForceCanceled() != null && !jobExecution.getForceCanceled().equals(getForceCanceled())) {
            return false;
        }
        if ((jobExecution.getStatusDetails() == null) ^ (getStatusDetails() == null)) {
            return false;
        }
        if (jobExecution.getStatusDetails() != null && !jobExecution.getStatusDetails().equals(getStatusDetails())) {
            return false;
        }
        if ((jobExecution.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        if (jobExecution.getThingArn() != null && !jobExecution.getThingArn().equals(getThingArn())) {
            return false;
        }
        if ((jobExecution.getQueuedAt() == null) ^ (getQueuedAt() == null)) {
            return false;
        }
        if (jobExecution.getQueuedAt() != null && !jobExecution.getQueuedAt().equals(getQueuedAt())) {
            return false;
        }
        if ((jobExecution.getStartedAt() == null) ^ (getStartedAt() == null)) {
            return false;
        }
        if (jobExecution.getStartedAt() != null && !jobExecution.getStartedAt().equals(getStartedAt())) {
            return false;
        }
        if ((jobExecution.getLastUpdatedAt() == null) ^ (getLastUpdatedAt() == null)) {
            return false;
        }
        if (jobExecution.getLastUpdatedAt() != null && !jobExecution.getLastUpdatedAt().equals(getLastUpdatedAt())) {
            return false;
        }
        if ((jobExecution.getExecutionNumber() == null) ^ (getExecutionNumber() == null)) {
            return false;
        }
        if (jobExecution.getExecutionNumber() != null && !jobExecution.getExecutionNumber().equals(getExecutionNumber())) {
            return false;
        }
        if ((jobExecution.getVersionNumber() == null) ^ (getVersionNumber() == null)) {
            return false;
        }
        if (jobExecution.getVersionNumber() != null && !jobExecution.getVersionNumber().equals(getVersionNumber())) {
            return false;
        }
        if ((jobExecution.getApproximateSecondsBeforeTimedOut() == null) ^ (getApproximateSecondsBeforeTimedOut() == null)) {
            return false;
        }
        return jobExecution.getApproximateSecondsBeforeTimedOut() == null || jobExecution.getApproximateSecondsBeforeTimedOut().equals(getApproximateSecondsBeforeTimedOut());
    }
}
