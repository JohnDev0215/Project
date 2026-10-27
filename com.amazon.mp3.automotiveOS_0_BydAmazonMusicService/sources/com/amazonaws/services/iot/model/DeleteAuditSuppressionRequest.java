package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteAuditSuppressionRequest extends AmazonWebServiceRequest implements Serializable {
    private String checkName;
    private ResourceIdentifier resourceIdentifier;

    public String getCheckName() {
        return this.checkName;
    }

    public void setCheckName(String str) {
        this.checkName = str;
    }

    public DeleteAuditSuppressionRequest withCheckName(String str) {
        this.checkName = str;
        return this;
    }

    public ResourceIdentifier getResourceIdentifier() {
        return this.resourceIdentifier;
    }

    public void setResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
    }

    public DeleteAuditSuppressionRequest withResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCheckName() != null) {
            sb.append("checkName: " + getCheckName() + ",");
        }
        if (getResourceIdentifier() != null) {
            sb.append("resourceIdentifier: " + getResourceIdentifier());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCheckName() == null ? 0 : getCheckName().hashCode()) + 31) * 31) + (getResourceIdentifier() != null ? getResourceIdentifier().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteAuditSuppressionRequest)) {
            return false;
        }
        DeleteAuditSuppressionRequest deleteAuditSuppressionRequest = (DeleteAuditSuppressionRequest) obj;
        if ((deleteAuditSuppressionRequest.getCheckName() == null) ^ (getCheckName() == null)) {
            return false;
        }
        if (deleteAuditSuppressionRequest.getCheckName() != null && !deleteAuditSuppressionRequest.getCheckName().equals(getCheckName())) {
            return false;
        }
        if ((deleteAuditSuppressionRequest.getResourceIdentifier() == null) ^ (getResourceIdentifier() == null)) {
            return false;
        }
        return deleteAuditSuppressionRequest.getResourceIdentifier() == null || deleteAuditSuppressionRequest.getResourceIdentifier().equals(getResourceIdentifier());
    }
}
