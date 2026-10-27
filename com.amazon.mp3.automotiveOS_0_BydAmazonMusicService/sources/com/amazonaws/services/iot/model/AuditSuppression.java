package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class AuditSuppression implements Serializable {
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

    public AuditSuppression withCheckName(String str) {
        this.checkName = str;
        return this;
    }

    public ResourceIdentifier getResourceIdentifier() {
        return this.resourceIdentifier;
    }

    public void setResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
    }

    public AuditSuppression withResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
        return this;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date date) {
        this.expirationDate = date;
    }

    public AuditSuppression withExpirationDate(Date date) {
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

    public AuditSuppression withSuppressIndefinitely(Boolean bool) {
        this.suppressIndefinitely = bool;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public AuditSuppression withDescription(String str) {
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
        if (obj == null || !(obj instanceof AuditSuppression)) {
            return false;
        }
        AuditSuppression auditSuppression = (AuditSuppression) obj;
        if ((auditSuppression.getCheckName() == null) ^ (getCheckName() == null)) {
            return false;
        }
        if (auditSuppression.getCheckName() != null && !auditSuppression.getCheckName().equals(getCheckName())) {
            return false;
        }
        if ((auditSuppression.getResourceIdentifier() == null) ^ (getResourceIdentifier() == null)) {
            return false;
        }
        if (auditSuppression.getResourceIdentifier() != null && !auditSuppression.getResourceIdentifier().equals(getResourceIdentifier())) {
            return false;
        }
        if ((auditSuppression.getExpirationDate() == null) ^ (getExpirationDate() == null)) {
            return false;
        }
        if (auditSuppression.getExpirationDate() != null && !auditSuppression.getExpirationDate().equals(getExpirationDate())) {
            return false;
        }
        if ((auditSuppression.getSuppressIndefinitely() == null) ^ (getSuppressIndefinitely() == null)) {
            return false;
        }
        if (auditSuppression.getSuppressIndefinitely() != null && !auditSuppression.getSuppressIndefinitely().equals(getSuppressIndefinitely())) {
            return false;
        }
        if ((auditSuppression.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        return auditSuppression.getDescription() == null || auditSuppression.getDescription().equals(getDescription());
    }
}
