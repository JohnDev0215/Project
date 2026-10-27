package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditMitigationActionsTaskResult implements Serializable {
    private List<MitigationAction> actionsDefinition;
    private Map<String, List<String>> auditCheckToActionsMapping;
    private Date endTime;
    private Date startTime;
    private AuditMitigationActionsTaskTarget target;
    private Map<String, TaskStatisticsForAuditCheck> taskStatistics;
    private String taskStatus;

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(String str) {
        this.taskStatus = str;
    }

    public DescribeAuditMitigationActionsTaskResult withTaskStatus(String str) {
        this.taskStatus = str;
        return this;
    }

    public void setTaskStatus(AuditMitigationActionsTaskStatus auditMitigationActionsTaskStatus) {
        this.taskStatus = auditMitigationActionsTaskStatus.toString();
    }

    public DescribeAuditMitigationActionsTaskResult withTaskStatus(AuditMitigationActionsTaskStatus auditMitigationActionsTaskStatus) {
        this.taskStatus = auditMitigationActionsTaskStatus.toString();
        return this;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public DescribeAuditMitigationActionsTaskResult withStartTime(Date date) {
        this.startTime = date;
        return this;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date date) {
        this.endTime = date;
    }

    public DescribeAuditMitigationActionsTaskResult withEndTime(Date date) {
        this.endTime = date;
        return this;
    }

    public Map<String, TaskStatisticsForAuditCheck> getTaskStatistics() {
        return this.taskStatistics;
    }

    public void setTaskStatistics(Map<String, TaskStatisticsForAuditCheck> map) {
        this.taskStatistics = map;
    }

    public DescribeAuditMitigationActionsTaskResult withTaskStatistics(Map<String, TaskStatisticsForAuditCheck> map) {
        this.taskStatistics = map;
        return this;
    }

    public DescribeAuditMitigationActionsTaskResult addtaskStatisticsEntry(String str, TaskStatisticsForAuditCheck taskStatisticsForAuditCheck) {
        if (this.taskStatistics == null) {
            this.taskStatistics = new HashMap();
        }
        if (this.taskStatistics.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.taskStatistics.put(str, taskStatisticsForAuditCheck);
        return this;
    }

    public DescribeAuditMitigationActionsTaskResult cleartaskStatisticsEntries() {
        this.taskStatistics = null;
        return this;
    }

    public AuditMitigationActionsTaskTarget getTarget() {
        return this.target;
    }

    public void setTarget(AuditMitigationActionsTaskTarget auditMitigationActionsTaskTarget) {
        this.target = auditMitigationActionsTaskTarget;
    }

    public DescribeAuditMitigationActionsTaskResult withTarget(AuditMitigationActionsTaskTarget auditMitigationActionsTaskTarget) {
        this.target = auditMitigationActionsTaskTarget;
        return this;
    }

    public Map<String, List<String>> getAuditCheckToActionsMapping() {
        return this.auditCheckToActionsMapping;
    }

    public void setAuditCheckToActionsMapping(Map<String, List<String>> map) {
        this.auditCheckToActionsMapping = map;
    }

    public DescribeAuditMitigationActionsTaskResult withAuditCheckToActionsMapping(Map<String, List<String>> map) {
        this.auditCheckToActionsMapping = map;
        return this;
    }

    public DescribeAuditMitigationActionsTaskResult addauditCheckToActionsMappingEntry(String str, List<String> list) {
        if (this.auditCheckToActionsMapping == null) {
            this.auditCheckToActionsMapping = new HashMap();
        }
        if (this.auditCheckToActionsMapping.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.auditCheckToActionsMapping.put(str, list);
        return this;
    }

    public DescribeAuditMitigationActionsTaskResult clearauditCheckToActionsMappingEntries() {
        this.auditCheckToActionsMapping = null;
        return this;
    }

    public List<MitigationAction> getActionsDefinition() {
        return this.actionsDefinition;
    }

    public void setActionsDefinition(Collection<MitigationAction> collection) {
        if (collection == null) {
            this.actionsDefinition = null;
        } else {
            this.actionsDefinition = new ArrayList(collection);
        }
    }

    public DescribeAuditMitigationActionsTaskResult withActionsDefinition(MitigationAction... mitigationActionArr) {
        if (getActionsDefinition() == null) {
            this.actionsDefinition = new ArrayList(mitigationActionArr.length);
        }
        for (MitigationAction mitigationAction : mitigationActionArr) {
            this.actionsDefinition.add(mitigationAction);
        }
        return this;
    }

    public DescribeAuditMitigationActionsTaskResult withActionsDefinition(Collection<MitigationAction> collection) {
        setActionsDefinition(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTaskStatus() != null) {
            sb.append("taskStatus: " + getTaskStatus() + ",");
        }
        if (getStartTime() != null) {
            sb.append("startTime: " + getStartTime() + ",");
        }
        if (getEndTime() != null) {
            sb.append("endTime: " + getEndTime() + ",");
        }
        if (getTaskStatistics() != null) {
            sb.append("taskStatistics: " + getTaskStatistics() + ",");
        }
        if (getTarget() != null) {
            sb.append("target: " + getTarget() + ",");
        }
        if (getAuditCheckToActionsMapping() != null) {
            sb.append("auditCheckToActionsMapping: " + getAuditCheckToActionsMapping() + ",");
        }
        if (getActionsDefinition() != null) {
            sb.append("actionsDefinition: " + getActionsDefinition());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getTaskStatus() == null ? 0 : getTaskStatus().hashCode()) + 31) * 31) + (getStartTime() == null ? 0 : getStartTime().hashCode())) * 31) + (getEndTime() == null ? 0 : getEndTime().hashCode())) * 31) + (getTaskStatistics() == null ? 0 : getTaskStatistics().hashCode())) * 31) + (getTarget() == null ? 0 : getTarget().hashCode())) * 31) + (getAuditCheckToActionsMapping() == null ? 0 : getAuditCheckToActionsMapping().hashCode())) * 31) + (getActionsDefinition() != null ? getActionsDefinition().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeAuditMitigationActionsTaskResult)) {
            return false;
        }
        DescribeAuditMitigationActionsTaskResult describeAuditMitigationActionsTaskResult = (DescribeAuditMitigationActionsTaskResult) obj;
        if ((describeAuditMitigationActionsTaskResult.getTaskStatus() == null) ^ (getTaskStatus() == null)) {
            return false;
        }
        if (describeAuditMitigationActionsTaskResult.getTaskStatus() != null && !describeAuditMitigationActionsTaskResult.getTaskStatus().equals(getTaskStatus())) {
            return false;
        }
        if ((describeAuditMitigationActionsTaskResult.getStartTime() == null) ^ (getStartTime() == null)) {
            return false;
        }
        if (describeAuditMitigationActionsTaskResult.getStartTime() != null && !describeAuditMitigationActionsTaskResult.getStartTime().equals(getStartTime())) {
            return false;
        }
        if ((describeAuditMitigationActionsTaskResult.getEndTime() == null) ^ (getEndTime() == null)) {
            return false;
        }
        if (describeAuditMitigationActionsTaskResult.getEndTime() != null && !describeAuditMitigationActionsTaskResult.getEndTime().equals(getEndTime())) {
            return false;
        }
        if ((describeAuditMitigationActionsTaskResult.getTaskStatistics() == null) ^ (getTaskStatistics() == null)) {
            return false;
        }
        if (describeAuditMitigationActionsTaskResult.getTaskStatistics() != null && !describeAuditMitigationActionsTaskResult.getTaskStatistics().equals(getTaskStatistics())) {
            return false;
        }
        if ((describeAuditMitigationActionsTaskResult.getTarget() == null) ^ (getTarget() == null)) {
            return false;
        }
        if (describeAuditMitigationActionsTaskResult.getTarget() != null && !describeAuditMitigationActionsTaskResult.getTarget().equals(getTarget())) {
            return false;
        }
        if ((describeAuditMitigationActionsTaskResult.getAuditCheckToActionsMapping() == null) ^ (getAuditCheckToActionsMapping() == null)) {
            return false;
        }
        if (describeAuditMitigationActionsTaskResult.getAuditCheckToActionsMapping() != null && !describeAuditMitigationActionsTaskResult.getAuditCheckToActionsMapping().equals(getAuditCheckToActionsMapping())) {
            return false;
        }
        if ((describeAuditMitigationActionsTaskResult.getActionsDefinition() == null) ^ (getActionsDefinition() == null)) {
            return false;
        }
        return describeAuditMitigationActionsTaskResult.getActionsDefinition() == null || describeAuditMitigationActionsTaskResult.getActionsDefinition().equals(getActionsDefinition());
    }
}
