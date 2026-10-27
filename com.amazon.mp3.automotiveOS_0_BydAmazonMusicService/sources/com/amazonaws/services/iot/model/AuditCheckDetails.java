package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AuditCheckDetails implements Serializable {
    private Boolean checkCompliant;
    private String checkRunStatus;
    private String errorCode;
    private String message;
    private Long nonCompliantResourcesCount;
    private Long suppressedNonCompliantResourcesCount;
    private Long totalResourcesCount;

    public String getCheckRunStatus() {
        return this.checkRunStatus;
    }

    public void setCheckRunStatus(String str) {
        this.checkRunStatus = str;
    }

    public AuditCheckDetails withCheckRunStatus(String str) {
        this.checkRunStatus = str;
        return this;
    }

    public void setCheckRunStatus(AuditCheckRunStatus auditCheckRunStatus) {
        this.checkRunStatus = auditCheckRunStatus.toString();
    }

    public AuditCheckDetails withCheckRunStatus(AuditCheckRunStatus auditCheckRunStatus) {
        this.checkRunStatus = auditCheckRunStatus.toString();
        return this;
    }

    public Boolean isCheckCompliant() {
        return this.checkCompliant;
    }

    public Boolean getCheckCompliant() {
        return this.checkCompliant;
    }

    public void setCheckCompliant(Boolean bool) {
        this.checkCompliant = bool;
    }

    public AuditCheckDetails withCheckCompliant(Boolean bool) {
        this.checkCompliant = bool;
        return this;
    }

    public Long getTotalResourcesCount() {
        return this.totalResourcesCount;
    }

    public void setTotalResourcesCount(Long l) {
        this.totalResourcesCount = l;
    }

    public AuditCheckDetails withTotalResourcesCount(Long l) {
        this.totalResourcesCount = l;
        return this;
    }

    public Long getNonCompliantResourcesCount() {
        return this.nonCompliantResourcesCount;
    }

    public void setNonCompliantResourcesCount(Long l) {
        this.nonCompliantResourcesCount = l;
    }

    public AuditCheckDetails withNonCompliantResourcesCount(Long l) {
        this.nonCompliantResourcesCount = l;
        return this;
    }

    public Long getSuppressedNonCompliantResourcesCount() {
        return this.suppressedNonCompliantResourcesCount;
    }

    public void setSuppressedNonCompliantResourcesCount(Long l) {
        this.suppressedNonCompliantResourcesCount = l;
    }

    public AuditCheckDetails withSuppressedNonCompliantResourcesCount(Long l) {
        this.suppressedNonCompliantResourcesCount = l;
        return this;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public AuditCheckDetails withErrorCode(String str) {
        this.errorCode = str;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public AuditCheckDetails withMessage(String str) {
        this.message = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCheckRunStatus() != null) {
            sb.append("checkRunStatus: " + getCheckRunStatus() + ",");
        }
        if (getCheckCompliant() != null) {
            sb.append("checkCompliant: " + getCheckCompliant() + ",");
        }
        if (getTotalResourcesCount() != null) {
            sb.append("totalResourcesCount: " + getTotalResourcesCount() + ",");
        }
        if (getNonCompliantResourcesCount() != null) {
            sb.append("nonCompliantResourcesCount: " + getNonCompliantResourcesCount() + ",");
        }
        if (getSuppressedNonCompliantResourcesCount() != null) {
            sb.append("suppressedNonCompliantResourcesCount: " + getSuppressedNonCompliantResourcesCount() + ",");
        }
        if (getErrorCode() != null) {
            sb.append("errorCode: " + getErrorCode() + ",");
        }
        if (getMessage() != null) {
            sb.append("message: " + getMessage());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getCheckRunStatus() == null ? 0 : getCheckRunStatus().hashCode()) + 31) * 31) + (getCheckCompliant() == null ? 0 : getCheckCompliant().hashCode())) * 31) + (getTotalResourcesCount() == null ? 0 : getTotalResourcesCount().hashCode())) * 31) + (getNonCompliantResourcesCount() == null ? 0 : getNonCompliantResourcesCount().hashCode())) * 31) + (getSuppressedNonCompliantResourcesCount() == null ? 0 : getSuppressedNonCompliantResourcesCount().hashCode())) * 31) + (getErrorCode() == null ? 0 : getErrorCode().hashCode())) * 31) + (getMessage() != null ? getMessage().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuditCheckDetails)) {
            return false;
        }
        AuditCheckDetails auditCheckDetails = (AuditCheckDetails) obj;
        if ((auditCheckDetails.getCheckRunStatus() == null) ^ (getCheckRunStatus() == null)) {
            return false;
        }
        if (auditCheckDetails.getCheckRunStatus() != null && !auditCheckDetails.getCheckRunStatus().equals(getCheckRunStatus())) {
            return false;
        }
        if ((auditCheckDetails.getCheckCompliant() == null) ^ (getCheckCompliant() == null)) {
            return false;
        }
        if (auditCheckDetails.getCheckCompliant() != null && !auditCheckDetails.getCheckCompliant().equals(getCheckCompliant())) {
            return false;
        }
        if ((auditCheckDetails.getTotalResourcesCount() == null) ^ (getTotalResourcesCount() == null)) {
            return false;
        }
        if (auditCheckDetails.getTotalResourcesCount() != null && !auditCheckDetails.getTotalResourcesCount().equals(getTotalResourcesCount())) {
            return false;
        }
        if ((auditCheckDetails.getNonCompliantResourcesCount() == null) ^ (getNonCompliantResourcesCount() == null)) {
            return false;
        }
        if (auditCheckDetails.getNonCompliantResourcesCount() != null && !auditCheckDetails.getNonCompliantResourcesCount().equals(getNonCompliantResourcesCount())) {
            return false;
        }
        if ((auditCheckDetails.getSuppressedNonCompliantResourcesCount() == null) ^ (getSuppressedNonCompliantResourcesCount() == null)) {
            return false;
        }
        if (auditCheckDetails.getSuppressedNonCompliantResourcesCount() != null && !auditCheckDetails.getSuppressedNonCompliantResourcesCount().equals(getSuppressedNonCompliantResourcesCount())) {
            return false;
        }
        if ((auditCheckDetails.getErrorCode() == null) ^ (getErrorCode() == null)) {
            return false;
        }
        if (auditCheckDetails.getErrorCode() != null && !auditCheckDetails.getErrorCode().equals(getErrorCode())) {
            return false;
        }
        if ((auditCheckDetails.getMessage() == null) ^ (getMessage() == null)) {
            return false;
        }
        return auditCheckDetails.getMessage() == null || auditCheckDetails.getMessage().equals(getMessage());
    }
}
