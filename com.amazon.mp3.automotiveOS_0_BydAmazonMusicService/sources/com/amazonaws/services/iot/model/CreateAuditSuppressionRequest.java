package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class CreateAuditSuppressionRequest extends AmazonWebServiceRequest implements Serializable {
    private String checkName;
    private String clientRequestToken;
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

    public CreateAuditSuppressionRequest withCheckName(String str) {
        this.checkName = str;
        return this;
    }

    public ResourceIdentifier getResourceIdentifier() {
        return this.resourceIdentifier;
    }

    public void setResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
    }

    public CreateAuditSuppressionRequest withResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
        return this;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date date) {
        this.expirationDate = date;
    }

    public CreateAuditSuppressionRequest withExpirationDate(Date date) {
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

    public CreateAuditSuppressionRequest withSuppressIndefinitely(Boolean bool) {
        this.suppressIndefinitely = bool;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public CreateAuditSuppressionRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public String getClientRequestToken() {
        return this.clientRequestToken;
    }

    public void setClientRequestToken(String str) {
        this.clientRequestToken = str;
    }

    public CreateAuditSuppressionRequest withClientRequestToken(String str) {
        this.clientRequestToken = str;
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
            sb.append("description: " + getDescription() + ",");
        }
        if (getClientRequestToken() != null) {
            sb.append("clientRequestToken: " + getClientRequestToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getCheckName() == null ? 0 : getCheckName().hashCode()) + 31) * 31) + (getResourceIdentifier() == null ? 0 : getResourceIdentifier().hashCode())) * 31) + (getExpirationDate() == null ? 0 : getExpirationDate().hashCode())) * 31) + (getSuppressIndefinitely() == null ? 0 : getSuppressIndefinitely().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getClientRequestToken() != null ? getClientRequestToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateAuditSuppressionRequest)) {
            return false;
        }
        CreateAuditSuppressionRequest createAuditSuppressionRequest = (CreateAuditSuppressionRequest) obj;
        if ((createAuditSuppressionRequest.getCheckName() == null) ^ (getCheckName() == null)) {
            return false;
        }
        if (createAuditSuppressionRequest.getCheckName() != null && !createAuditSuppressionRequest.getCheckName().equals(getCheckName())) {
            return false;
        }
        if ((createAuditSuppressionRequest.getResourceIdentifier() == null) ^ (getResourceIdentifier() == null)) {
            return false;
        }
        if (createAuditSuppressionRequest.getResourceIdentifier() != null && !createAuditSuppressionRequest.getResourceIdentifier().equals(getResourceIdentifier())) {
            return false;
        }
        if ((createAuditSuppressionRequest.getExpirationDate() == null) ^ (getExpirationDate() == null)) {
            return false;
        }
        if (createAuditSuppressionRequest.getExpirationDate() != null && !createAuditSuppressionRequest.getExpirationDate().equals(getExpirationDate())) {
            return false;
        }
        if ((createAuditSuppressionRequest.getSuppressIndefinitely() == null) ^ (getSuppressIndefinitely() == null)) {
            return false;
        }
        if (createAuditSuppressionRequest.getSuppressIndefinitely() != null && !createAuditSuppressionRequest.getSuppressIndefinitely().equals(getSuppressIndefinitely())) {
            return false;
        }
        if ((createAuditSuppressionRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (createAuditSuppressionRequest.getDescription() != null && !createAuditSuppressionRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((createAuditSuppressionRequest.getClientRequestToken() == null) ^ (getClientRequestToken() == null)) {
            return false;
        }
        return createAuditSuppressionRequest.getClientRequestToken() == null || createAuditSuppressionRequest.getClientRequestToken().equals(getClientRequestToken());
    }
}
