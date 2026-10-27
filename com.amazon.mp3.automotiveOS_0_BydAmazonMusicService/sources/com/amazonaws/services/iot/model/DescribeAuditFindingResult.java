package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditFindingResult implements Serializable {
    private AuditFinding finding;

    public AuditFinding getFinding() {
        return this.finding;
    }

    public void setFinding(AuditFinding auditFinding) {
        this.finding = auditFinding;
    }

    public DescribeAuditFindingResult withFinding(AuditFinding auditFinding) {
        this.finding = auditFinding;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getFinding() != null) {
            sb.append("finding: " + getFinding());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getFinding() == null ? 0 : getFinding().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeAuditFindingResult)) {
            return false;
        }
        DescribeAuditFindingResult describeAuditFindingResult = (DescribeAuditFindingResult) obj;
        if ((describeAuditFindingResult.getFinding() == null) ^ (getFinding() == null)) {
            return false;
        }
        return describeAuditFindingResult.getFinding() == null || describeAuditFindingResult.getFinding().equals(getFinding());
    }
}
