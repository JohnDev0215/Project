package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AuditFinding implements Serializable {
    private String checkName;
    private String findingId;
    private Date findingTime;
    private Boolean isSuppressed;
    private NonCompliantResource nonCompliantResource;
    private String reasonForNonCompliance;
    private String reasonForNonComplianceCode;
    private List<RelatedResource> relatedResources;
    private String severity;
    private String taskId;
    private Date taskStartTime;

    public String getFindingId() {
        return this.findingId;
    }

    public void setFindingId(String str) {
        this.findingId = str;
    }

    public AuditFinding withFindingId(String str) {
        this.findingId = str;
        return this;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public AuditFinding withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public String getCheckName() {
        return this.checkName;
    }

    public void setCheckName(String str) {
        this.checkName = str;
    }

    public AuditFinding withCheckName(String str) {
        this.checkName = str;
        return this;
    }

    public Date getTaskStartTime() {
        return this.taskStartTime;
    }

    public void setTaskStartTime(Date date) {
        this.taskStartTime = date;
    }

    public AuditFinding withTaskStartTime(Date date) {
        this.taskStartTime = date;
        return this;
    }

    public Date getFindingTime() {
        return this.findingTime;
    }

    public void setFindingTime(Date date) {
        this.findingTime = date;
    }

    public AuditFinding withFindingTime(Date date) {
        this.findingTime = date;
        return this;
    }

    public String getSeverity() {
        return this.severity;
    }

    public void setSeverity(String str) {
        this.severity = str;
    }

    public AuditFinding withSeverity(String str) {
        this.severity = str;
        return this;
    }

    public void setSeverity(AuditFindingSeverity auditFindingSeverity) {
        this.severity = auditFindingSeverity.toString();
    }

    public AuditFinding withSeverity(AuditFindingSeverity auditFindingSeverity) {
        this.severity = auditFindingSeverity.toString();
        return this;
    }

    public NonCompliantResource getNonCompliantResource() {
        return this.nonCompliantResource;
    }

    public void setNonCompliantResource(NonCompliantResource nonCompliantResource) {
        this.nonCompliantResource = nonCompliantResource;
    }

    public AuditFinding withNonCompliantResource(NonCompliantResource nonCompliantResource) {
        this.nonCompliantResource = nonCompliantResource;
        return this;
    }

    public List<RelatedResource> getRelatedResources() {
        return this.relatedResources;
    }

    public void setRelatedResources(Collection<RelatedResource> collection) {
        if (collection == null) {
            this.relatedResources = null;
        } else {
            this.relatedResources = new ArrayList(collection);
        }
    }

    public AuditFinding withRelatedResources(RelatedResource... relatedResourceArr) {
        if (getRelatedResources() == null) {
            this.relatedResources = new ArrayList(relatedResourceArr.length);
        }
        for (RelatedResource relatedResource : relatedResourceArr) {
            this.relatedResources.add(relatedResource);
        }
        return this;
    }

    public AuditFinding withRelatedResources(Collection<RelatedResource> collection) {
        setRelatedResources(collection);
        return this;
    }

    public String getReasonForNonCompliance() {
        return this.reasonForNonCompliance;
    }

    public void setReasonForNonCompliance(String str) {
        this.reasonForNonCompliance = str;
    }

    public AuditFinding withReasonForNonCompliance(String str) {
        this.reasonForNonCompliance = str;
        return this;
    }

    public String getReasonForNonComplianceCode() {
        return this.reasonForNonComplianceCode;
    }

    public void setReasonForNonComplianceCode(String str) {
        this.reasonForNonComplianceCode = str;
    }

    public AuditFinding withReasonForNonComplianceCode(String str) {
        this.reasonForNonComplianceCode = str;
        return this;
    }

    public Boolean isIsSuppressed() {
        return this.isSuppressed;
    }

    public Boolean getIsSuppressed() {
        return this.isSuppressed;
    }

    public void setIsSuppressed(Boolean bool) {
        this.isSuppressed = bool;
    }

    public AuditFinding withIsSuppressed(Boolean bool) {
        this.isSuppressed = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getFindingId() != null) {
            sb.append("findingId: " + getFindingId() + ",");
        }
        if (getTaskId() != null) {
            sb.append("taskId: " + getTaskId() + ",");
        }
        if (getCheckName() != null) {
            sb.append("checkName: " + getCheckName() + ",");
        }
        if (getTaskStartTime() != null) {
            sb.append("taskStartTime: " + getTaskStartTime() + ",");
        }
        if (getFindingTime() != null) {
            sb.append("findingTime: " + getFindingTime() + ",");
        }
        if (getSeverity() != null) {
            sb.append("severity: " + getSeverity() + ",");
        }
        if (getNonCompliantResource() != null) {
            sb.append("nonCompliantResource: " + getNonCompliantResource() + ",");
        }
        if (getRelatedResources() != null) {
            sb.append("relatedResources: " + getRelatedResources() + ",");
        }
        if (getReasonForNonCompliance() != null) {
            sb.append("reasonForNonCompliance: " + getReasonForNonCompliance() + ",");
        }
        if (getReasonForNonComplianceCode() != null) {
            sb.append("reasonForNonComplianceCode: " + getReasonForNonComplianceCode() + ",");
        }
        if (getIsSuppressed() != null) {
            sb.append("isSuppressed: " + getIsSuppressed());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((getFindingId() == null ? 0 : getFindingId().hashCode()) + 31) * 31) + (getTaskId() == null ? 0 : getTaskId().hashCode())) * 31) + (getCheckName() == null ? 0 : getCheckName().hashCode())) * 31) + (getTaskStartTime() == null ? 0 : getTaskStartTime().hashCode())) * 31) + (getFindingTime() == null ? 0 : getFindingTime().hashCode())) * 31) + (getSeverity() == null ? 0 : getSeverity().hashCode())) * 31) + (getNonCompliantResource() == null ? 0 : getNonCompliantResource().hashCode())) * 31) + (getRelatedResources() == null ? 0 : getRelatedResources().hashCode())) * 31) + (getReasonForNonCompliance() == null ? 0 : getReasonForNonCompliance().hashCode())) * 31) + (getReasonForNonComplianceCode() == null ? 0 : getReasonForNonComplianceCode().hashCode())) * 31) + (getIsSuppressed() != null ? getIsSuppressed().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuditFinding)) {
            return false;
        }
        AuditFinding auditFinding = (AuditFinding) obj;
        if ((auditFinding.getFindingId() == null) ^ (getFindingId() == null)) {
            return false;
        }
        if (auditFinding.getFindingId() != null && !auditFinding.getFindingId().equals(getFindingId())) {
            return false;
        }
        if ((auditFinding.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (auditFinding.getTaskId() != null && !auditFinding.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((auditFinding.getCheckName() == null) ^ (getCheckName() == null)) {
            return false;
        }
        if (auditFinding.getCheckName() != null && !auditFinding.getCheckName().equals(getCheckName())) {
            return false;
        }
        if ((auditFinding.getTaskStartTime() == null) ^ (getTaskStartTime() == null)) {
            return false;
        }
        if (auditFinding.getTaskStartTime() != null && !auditFinding.getTaskStartTime().equals(getTaskStartTime())) {
            return false;
        }
        if ((auditFinding.getFindingTime() == null) ^ (getFindingTime() == null)) {
            return false;
        }
        if (auditFinding.getFindingTime() != null && !auditFinding.getFindingTime().equals(getFindingTime())) {
            return false;
        }
        if ((auditFinding.getSeverity() == null) ^ (getSeverity() == null)) {
            return false;
        }
        if (auditFinding.getSeverity() != null && !auditFinding.getSeverity().equals(getSeverity())) {
            return false;
        }
        if ((auditFinding.getNonCompliantResource() == null) ^ (getNonCompliantResource() == null)) {
            return false;
        }
        if (auditFinding.getNonCompliantResource() != null && !auditFinding.getNonCompliantResource().equals(getNonCompliantResource())) {
            return false;
        }
        if ((auditFinding.getRelatedResources() == null) ^ (getRelatedResources() == null)) {
            return false;
        }
        if (auditFinding.getRelatedResources() != null && !auditFinding.getRelatedResources().equals(getRelatedResources())) {
            return false;
        }
        if ((auditFinding.getReasonForNonCompliance() == null) ^ (getReasonForNonCompliance() == null)) {
            return false;
        }
        if (auditFinding.getReasonForNonCompliance() != null && !auditFinding.getReasonForNonCompliance().equals(getReasonForNonCompliance())) {
            return false;
        }
        if ((auditFinding.getReasonForNonComplianceCode() == null) ^ (getReasonForNonComplianceCode() == null)) {
            return false;
        }
        if (auditFinding.getReasonForNonComplianceCode() != null && !auditFinding.getReasonForNonComplianceCode().equals(getReasonForNonComplianceCode())) {
            return false;
        }
        if ((auditFinding.getIsSuppressed() == null) ^ (getIsSuppressed() == null)) {
            return false;
        }
        return auditFinding.getIsSuppressed() == null || auditFinding.getIsSuppressed().equals(getIsSuppressed());
    }
}
