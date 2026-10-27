package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DetectMitigationActionsTaskStatistics implements Serializable {
    private Long actionsExecuted;
    private Long actionsFailed;
    private Long actionsSkipped;

    public Long getActionsExecuted() {
        return this.actionsExecuted;
    }

    public void setActionsExecuted(Long l) {
        this.actionsExecuted = l;
    }

    public DetectMitigationActionsTaskStatistics withActionsExecuted(Long l) {
        this.actionsExecuted = l;
        return this;
    }

    public Long getActionsSkipped() {
        return this.actionsSkipped;
    }

    public void setActionsSkipped(Long l) {
        this.actionsSkipped = l;
    }

    public DetectMitigationActionsTaskStatistics withActionsSkipped(Long l) {
        this.actionsSkipped = l;
        return this;
    }

    public Long getActionsFailed() {
        return this.actionsFailed;
    }

    public void setActionsFailed(Long l) {
        this.actionsFailed = l;
    }

    public DetectMitigationActionsTaskStatistics withActionsFailed(Long l) {
        this.actionsFailed = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getActionsExecuted() != null) {
            sb.append("actionsExecuted: " + getActionsExecuted() + ",");
        }
        if (getActionsSkipped() != null) {
            sb.append("actionsSkipped: " + getActionsSkipped() + ",");
        }
        if (getActionsFailed() != null) {
            sb.append("actionsFailed: " + getActionsFailed());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getActionsExecuted() == null ? 0 : getActionsExecuted().hashCode()) + 31) * 31) + (getActionsSkipped() == null ? 0 : getActionsSkipped().hashCode())) * 31) + (getActionsFailed() != null ? getActionsFailed().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DetectMitigationActionsTaskStatistics)) {
            return false;
        }
        DetectMitigationActionsTaskStatistics detectMitigationActionsTaskStatistics = (DetectMitigationActionsTaskStatistics) obj;
        if ((detectMitigationActionsTaskStatistics.getActionsExecuted() == null) ^ (getActionsExecuted() == null)) {
            return false;
        }
        if (detectMitigationActionsTaskStatistics.getActionsExecuted() != null && !detectMitigationActionsTaskStatistics.getActionsExecuted().equals(getActionsExecuted())) {
            return false;
        }
        if ((detectMitigationActionsTaskStatistics.getActionsSkipped() == null) ^ (getActionsSkipped() == null)) {
            return false;
        }
        if (detectMitigationActionsTaskStatistics.getActionsSkipped() != null && !detectMitigationActionsTaskStatistics.getActionsSkipped().equals(getActionsSkipped())) {
            return false;
        }
        if ((detectMitigationActionsTaskStatistics.getActionsFailed() == null) ^ (getActionsFailed() == null)) {
            return false;
        }
        return detectMitigationActionsTaskStatistics.getActionsFailed() == null || detectMitigationActionsTaskStatistics.getActionsFailed().equals(getActionsFailed());
    }
}
