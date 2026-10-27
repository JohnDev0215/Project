package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class TaskStatistics implements Serializable {
    private Integer canceledChecks;
    private Integer compliantChecks;
    private Integer failedChecks;
    private Integer inProgressChecks;
    private Integer nonCompliantChecks;
    private Integer totalChecks;
    private Integer waitingForDataCollectionChecks;

    public Integer getTotalChecks() {
        return this.totalChecks;
    }

    public void setTotalChecks(Integer num) {
        this.totalChecks = num;
    }

    public TaskStatistics withTotalChecks(Integer num) {
        this.totalChecks = num;
        return this;
    }

    public Integer getInProgressChecks() {
        return this.inProgressChecks;
    }

    public void setInProgressChecks(Integer num) {
        this.inProgressChecks = num;
    }

    public TaskStatistics withInProgressChecks(Integer num) {
        this.inProgressChecks = num;
        return this;
    }

    public Integer getWaitingForDataCollectionChecks() {
        return this.waitingForDataCollectionChecks;
    }

    public void setWaitingForDataCollectionChecks(Integer num) {
        this.waitingForDataCollectionChecks = num;
    }

    public TaskStatistics withWaitingForDataCollectionChecks(Integer num) {
        this.waitingForDataCollectionChecks = num;
        return this;
    }

    public Integer getCompliantChecks() {
        return this.compliantChecks;
    }

    public void setCompliantChecks(Integer num) {
        this.compliantChecks = num;
    }

    public TaskStatistics withCompliantChecks(Integer num) {
        this.compliantChecks = num;
        return this;
    }

    public Integer getNonCompliantChecks() {
        return this.nonCompliantChecks;
    }

    public void setNonCompliantChecks(Integer num) {
        this.nonCompliantChecks = num;
    }

    public TaskStatistics withNonCompliantChecks(Integer num) {
        this.nonCompliantChecks = num;
        return this;
    }

    public Integer getFailedChecks() {
        return this.failedChecks;
    }

    public void setFailedChecks(Integer num) {
        this.failedChecks = num;
    }

    public TaskStatistics withFailedChecks(Integer num) {
        this.failedChecks = num;
        return this;
    }

    public Integer getCanceledChecks() {
        return this.canceledChecks;
    }

    public void setCanceledChecks(Integer num) {
        this.canceledChecks = num;
    }

    public TaskStatistics withCanceledChecks(Integer num) {
        this.canceledChecks = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTotalChecks() != null) {
            sb.append("totalChecks: " + getTotalChecks() + ",");
        }
        if (getInProgressChecks() != null) {
            sb.append("inProgressChecks: " + getInProgressChecks() + ",");
        }
        if (getWaitingForDataCollectionChecks() != null) {
            sb.append("waitingForDataCollectionChecks: " + getWaitingForDataCollectionChecks() + ",");
        }
        if (getCompliantChecks() != null) {
            sb.append("compliantChecks: " + getCompliantChecks() + ",");
        }
        if (getNonCompliantChecks() != null) {
            sb.append("nonCompliantChecks: " + getNonCompliantChecks() + ",");
        }
        if (getFailedChecks() != null) {
            sb.append("failedChecks: " + getFailedChecks() + ",");
        }
        if (getCanceledChecks() != null) {
            sb.append("canceledChecks: " + getCanceledChecks());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getTotalChecks() == null ? 0 : getTotalChecks().hashCode()) + 31) * 31) + (getInProgressChecks() == null ? 0 : getInProgressChecks().hashCode())) * 31) + (getWaitingForDataCollectionChecks() == null ? 0 : getWaitingForDataCollectionChecks().hashCode())) * 31) + (getCompliantChecks() == null ? 0 : getCompliantChecks().hashCode())) * 31) + (getNonCompliantChecks() == null ? 0 : getNonCompliantChecks().hashCode())) * 31) + (getFailedChecks() == null ? 0 : getFailedChecks().hashCode())) * 31) + (getCanceledChecks() != null ? getCanceledChecks().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TaskStatistics)) {
            return false;
        }
        TaskStatistics taskStatistics = (TaskStatistics) obj;
        if ((taskStatistics.getTotalChecks() == null) ^ (getTotalChecks() == null)) {
            return false;
        }
        if (taskStatistics.getTotalChecks() != null && !taskStatistics.getTotalChecks().equals(getTotalChecks())) {
            return false;
        }
        if ((taskStatistics.getInProgressChecks() == null) ^ (getInProgressChecks() == null)) {
            return false;
        }
        if (taskStatistics.getInProgressChecks() != null && !taskStatistics.getInProgressChecks().equals(getInProgressChecks())) {
            return false;
        }
        if ((taskStatistics.getWaitingForDataCollectionChecks() == null) ^ (getWaitingForDataCollectionChecks() == null)) {
            return false;
        }
        if (taskStatistics.getWaitingForDataCollectionChecks() != null && !taskStatistics.getWaitingForDataCollectionChecks().equals(getWaitingForDataCollectionChecks())) {
            return false;
        }
        if ((taskStatistics.getCompliantChecks() == null) ^ (getCompliantChecks() == null)) {
            return false;
        }
        if (taskStatistics.getCompliantChecks() != null && !taskStatistics.getCompliantChecks().equals(getCompliantChecks())) {
            return false;
        }
        if ((taskStatistics.getNonCompliantChecks() == null) ^ (getNonCompliantChecks() == null)) {
            return false;
        }
        if (taskStatistics.getNonCompliantChecks() != null && !taskStatistics.getNonCompliantChecks().equals(getNonCompliantChecks())) {
            return false;
        }
        if ((taskStatistics.getFailedChecks() == null) ^ (getFailedChecks() == null)) {
            return false;
        }
        if (taskStatistics.getFailedChecks() != null && !taskStatistics.getFailedChecks().equals(getFailedChecks())) {
            return false;
        }
        if ((taskStatistics.getCanceledChecks() == null) ^ (getCanceledChecks() == null)) {
            return false;
        }
        return taskStatistics.getCanceledChecks() == null || taskStatistics.getCanceledChecks().equals(getCanceledChecks());
    }
}
