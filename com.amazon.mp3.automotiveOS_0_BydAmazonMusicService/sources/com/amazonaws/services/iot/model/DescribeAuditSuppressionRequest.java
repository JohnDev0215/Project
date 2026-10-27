package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditSuppressionRequest extends AmazonWebServiceRequest implements Serializable {
    private String checkName;
    private ResourceIdentifier resourceIdentifier;

    public String getCheckName() {
        return this.checkName;
    }

    public void setCheckName(String str) {
        this.checkName = str;
    }

    public DescribeAuditSuppressionRequest withCheckName(String str) {
        this.checkName = str;
        return this;
    }

    public ResourceIdentifier getResourceIdentifier() {
        return this.resourceIdentifier;
    }

    public void setResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
    }

    public DescribeAuditSuppressionRequest withResourceIdentifier(ResourceIdentifier resourceIdentifier) {
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
        if (obj == null || !(obj instanceof DescribeAuditSuppressionRequest)) {
            return false;
        }
        DescribeAuditSuppressionRequest describeAuditSuppressionRequest = (DescribeAuditSuppressionRequest) obj;
        if ((describeAuditSuppressionRequest.getCheckName() == null) ^ (getCheckName() == null)) {
            return false;
        }
        if (describeAuditSuppressionRequest.getCheckName() != null && !describeAuditSuppressionRequest.getCheckName().equals(getCheckName())) {
            return false;
        }
        if ((describeAuditSuppressionRequest.getResourceIdentifier() == null) ^ (getResourceIdentifier() == null)) {
            return false;
        }
        return describeAuditSuppressionRequest.getResourceIdentifier() == null || describeAuditSuppressionRequest.getResourceIdentifier().equals(getResourceIdentifier());
    }
}
