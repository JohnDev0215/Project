package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class JobExecutionSummary implements Serializable {
    private Long executionNumber;
    private Date lastUpdatedAt;
    private Date queuedAt;
    private Integer retryAttempt;
    private Date startedAt;
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public JobExecutionSummary withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
    }

    public JobExecutionSummary withStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
        return this;
    }

    public Date getQueuedAt() {
        return this.queuedAt;
    }

    public void setQueuedAt(Date date) {
        this.queuedAt = date;
    }

    public JobExecutionSummary withQueuedAt(Date date) {
        this.queuedAt = date;
        return this;
    }

    public Date getStartedAt() {
        return this.startedAt;
    }

    public void setStartedAt(Date date) {
        this.startedAt = date;
    }

    public JobExecutionSummary withStartedAt(Date date) {
        this.startedAt = date;
        return this;
    }

    public Date getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
    }

    public JobExecutionSummary withLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
        return this;
    }

    public Long getExecutionNumber() {
        return this.executionNumber;
    }

    public void setExecutionNumber(Long l) {
        this.executionNumber = l;
    }

    public JobExecutionSummary withExecutionNumber(Long l) {
        this.executionNumber = l;
        return this;
    }

    public Integer getRetryAttempt() {
        return this.retryAttempt;
    }

    public void setRetryAttempt(Integer num) {
        this.retryAttempt = num;
    }

    public JobExecutionSummary withRetryAttempt(Integer num) {
        this.retryAttempt = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStatus() != null) {
            sb.append("status: " + getStatus() + ",");
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
        if (getRetryAttempt() != null) {
            sb.append("retryAttempt: " + getRetryAttempt());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getStatus() == null ? 0 : getStatus().hashCode()) + 31) * 31) + (getQueuedAt() == null ? 0 : getQueuedAt().hashCode())) * 31) + (getStartedAt() == null ? 0 : getStartedAt().hashCode())) * 31) + (getLastUpdatedAt() == null ? 0 : getLastUpdatedAt().hashCode())) * 31) + (getExecutionNumber() == null ? 0 : getExecutionNumber().hashCode())) * 31) + (getRetryAttempt() != null ? getRetryAttempt().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecutionSummary)) {
            return false;
        }
        JobExecutionSummary jobExecutionSummary = (JobExecutionSummary) obj;
        if ((jobExecutionSummary.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (jobExecutionSummary.getStatus() != null && !jobExecutionSummary.getStatus().equals(getStatus())) {
            return false;
        }
        if ((jobExecutionSummary.getQueuedAt() == null) ^ (getQueuedAt() == null)) {
            return false;
        }
        if (jobExecutionSummary.getQueuedAt() != null && !jobExecutionSummary.getQueuedAt().equals(getQueuedAt())) {
            return false;
        }
        if ((jobExecutionSummary.getStartedAt() == null) ^ (getStartedAt() == null)) {
            return false;
        }
        if (jobExecutionSummary.getStartedAt() != null && !jobExecutionSummary.getStartedAt().equals(getStartedAt())) {
            return false;
        }
        if ((jobExecutionSummary.getLastUpdatedAt() == null) ^ (getLastUpdatedAt() == null)) {
            return false;
        }
        if (jobExecutionSummary.getLastUpdatedAt() != null && !jobExecutionSummary.getLastUpdatedAt().equals(getLastUpdatedAt())) {
            return false;
        }
        if ((jobExecutionSummary.getExecutionNumber() == null) ^ (getExecutionNumber() == null)) {
            return false;
        }
        if (jobExecutionSummary.getExecutionNumber() != null && !jobExecutionSummary.getExecutionNumber().equals(getExecutionNumber())) {
            return false;
        }
        if ((jobExecutionSummary.getRetryAttempt() == null) ^ (getRetryAttempt() == null)) {
            return false;
        }
        return jobExecutionSummary.getRetryAttempt() == null || jobExecutionSummary.getRetryAttempt().equals(getRetryAttempt());
    }
}
