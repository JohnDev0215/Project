package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateAuditSuppressionRequest extends AmazonWebServiceRequest implements Serializable {
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

    public UpdateAuditSuppressionRequest withCheckName(String str) {
        this.checkName = str;
        return this;
    }

    public ResourceIdentifier getResourceIdentifier() {
        return this.resourceIdentifier;
    }

    public void setResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
    }

    public UpdateAuditSuppressionRequest withResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
        return this;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date date) {
        this.expirationDate = date;
    }

    public UpdateAuditSuppressionRequest withExpirationDate(Date date) {
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

    public UpdateAuditSuppressionRequest withSuppressIndefinitely(Boolean bool) {
        this.suppressIndefinitely = bool;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public UpdateAuditSuppressionRequest withDescription(String str) {
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
        if (obj == null || !(obj instanceof UpdateAuditSuppressionRequest)) {
            return false;
        }
        UpdateAuditSuppressionRequest updateAuditSuppressionRequest = (UpdateAuditSuppressionRequest) obj;
        if ((updateAuditSuppressionRequest.getCheckName() == null) ^ (getCheckName() == null)) {
            return false;
        }
        if (updateAuditSuppressionRequest.getCheckName() != null && !updateAuditSuppressionRequest.getCheckName().equals(getCheckName())) {
            return false;
        }
        if ((updateAuditSuppressionRequest.getResourceIdentifier() == null) ^ (getResourceIdentifier() == null)) {
            return false;
        }
        if (updateAuditSuppressionRequest.getResourceIdentifier() != null && !updateAuditSuppressionRequest.getResourceIdentifier().equals(getResourceIdentifier())) {
            return false;
        }
        if ((updateAuditSuppressionRequest.getExpirationDate() == null) ^ (getExpirationDate() == null)) {
            return false;
        }
        if (updateAuditSuppressionRequest.getExpirationDate() != null && !updateAuditSuppressionRequest.getExpirationDate().equals(getExpirationDate())) {
            return false;
        }
        if ((updateAuditSuppressionRequest.getSuppressIndefinitely() == null) ^ (getSuppressIndefinitely() == null)) {
            return false;
        }
        if (updateAuditSuppressionRequest.getSuppressIndefinitely() != null && !updateAuditSuppressionRequest.getSuppressIndefinitely().equals(getSuppressIndefinitely())) {
            return false;
        }
        if ((updateAuditSuppressionRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        return updateAuditSuppressionRequest.getDescription() == null || updateAuditSuppressionRequest.getDescription().equals(getDescription());
    }
}
