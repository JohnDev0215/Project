package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditSuppressionResult implements Serializable {
    private String checkName;
    private String description;
    private Date expirationDate;
    private ResourceIdentifier resourceIdentifier;
    private Boolean suppressIndefinitely;

    public String getCheckName() {
        return this.checkName;
    }

    public void setCheckName(String str) {
        this.checkName = str;
    }

    public DescribeAuditSuppressionResult withCheckName(String str) {
        this.checkName = str;
        return this;
    }

    public ResourceIdentifier getResourceIdentifier() {
        return this.resourceIdentifier;
    }

    public void setResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
    }

    public DescribeAuditSuppressionResult withResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
        return this;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date date) {
        this.expirationDate = date;
    }

    public DescribeAuditSuppressionResult withExpirationDate(Date date) {
        this.expirationDate = date;
        return this;
    }

    public Boolean isSuppressIndefinitely() {
        return this.suppressIndefinitely;
    }

    public Boolean getSuppressIndefinitely() {
        return this.suppressIndefinitely;
    }

    public void setSuppressIndefinitely(Boolean bool) {
        this.suppressIndefinitely = bool;
    }

    public DescribeAuditSuppressionResult withSuppressIndefinitely(Boolean bool) {
        this.suppressIndefinitely = bool;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public DescribeAuditSuppressionResult withDescription(String str) {
        this.description = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCheckName() != null) {
            sb.append("checkName: " + getCheckName() + ",");
        }
        if (getResourceIdentifier() != null) {
            sb.append("resourceIdentifier: " + getResourceIdentifier() + ",");
        }
        if (getExpirationDate() != null) {
            sb.append("expirationDate: " + getExpirationDate() + ",");
        }
        if (getSuppressIndefinitely() != null) {
            sb.append("suppressIndefinitely: " + getSuppressIndefinitely() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getCheckName() == null ? 0 : getCheckName().hashCode()) + 31) * 31) + (getResourceIdentifier() == null ? 0 : getResourceIdentifier().hashCode())) * 31) + (getExpirationDate() == null ? 0 : getExpirationDate().hashCode())) * 31) + (getSuppressIndefinitely() == null ? 0 : getSuppressIndefinitely().hashCode())) * 31) + (getDescription() != null ? getDescription().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeAuditSuppressionResult)) {
            return false;
        }
        DescribeAuditSuppressionResult describeAuditSuppressionResult = (DescribeAuditSuppressionResult) obj;
        if ((describeAuditSuppressionResult.getCheckName() == null) ^ (getCheckName() == null)) {
            return false;
        }
        if (describeAuditSuppressionResult.getCheckName() != null && !describeAuditSuppressionResult.getCheckName().equals(getCheckName())) {
            return false;
        }
        if ((describeAuditSuppressionResult.getResourceIdentifier() == null) ^ (getResourceIdentifier() == null)) {
            return false;
        }
        if (describeAuditSuppressionResult.getResourceIdentifier() != null && !describeAuditSuppressionResult.getResourceIdentifier().equals(getResourceIdentifier())) {
            return false;
        }
        if ((describeAuditSuppressionResult.getExpirationDate() == null) ^ (getExpirationDate() == null)) {
            return false;
        }
        if (describeAuditSuppressionResult.getExpirationDate() != null && !describeAuditSuppressionResult.getExpirationDate().equals(getExpirationDate())) {
            return false;
        }
        if ((describeAuditSuppressionResult.getSuppressIndefinitely() == null) ^ (getSuppressIndefinitely() == null)) {
            return false;
        }
        if (describeAuditSuppressionResult.getSuppressIndefinitely() != null && !describeAuditSuppressionResult.getSuppressIndefinitely().equals(getSuppressIndefinitely())) {
            return false;
        }
        if ((describeAuditSuppressionResult.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        return describeAuditSuppressionResult.getDescription() == null || describeAuditSuppressionResult.getDescription().equals(getDescription());
    }
}
