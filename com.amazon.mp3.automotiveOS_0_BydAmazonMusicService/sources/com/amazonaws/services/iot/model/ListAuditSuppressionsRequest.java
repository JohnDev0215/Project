package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListAuditSuppressionsRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean ascendingOrder;
    private String checkName;
    private Integer maxResults;
    private String nextToken;
    private ResourceIdentifier resourceIdentifier;

    public String getCheckName() {
        return this.checkName;
    }

    public void setCheckName(String str) {
        this.checkName = str;
    }

    public ListAuditSuppressionsRequest withCheckName(String str) {
        this.checkName = str;
        return this;
    }

    public ResourceIdentifier getResourceIdentifier() {
        return this.resourceIdentifier;
    }

    public void setResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
    }

    public ListAuditSuppressionsRequest withResourceIdentifier(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
        return this;
    }

    public Boolean isAscendingOrder() {
        return this.ascendingOrder;
    }

    public Boolean getAscendingOrder() {
        return this.ascendingOrder;
    }

    public void setAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
    }

    public ListAuditSuppressionsRequest withAscendingOrder(Boolean bool) {
        this.ascendingOrder = bool;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListAuditSuppressionsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListAuditSuppressionsRequest withMaxResults(Integer num) {
        this.maxResults = num;
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
        if (getAscendingOrder() != null) {
            sb.append("ascendingOrder: " + getAscendingOrder() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getCheckName() == null ? 0 : getCheckName().hashCode()) + 31) * 31) + (getResourceIdentifier() == null ? 0 : getResourceIdentifier().hashCode())) * 31) + (getAscendingOrder() == null ? 0 : getAscendingOrder().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getMaxResults() != null ? getMaxResults().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuditSuppressionsRequest)) {
            return false;
        }
        ListAuditSuppressionsRequest listAuditSuppressionsRequest = (ListAuditSuppressionsRequest) obj;
        if ((listAuditSuppressionsRequest.getCheckName() == null) ^ (getCheckName() == null)) {
            return false;
        }
        if (listAuditSuppressionsRequest.getCheckName() != null && !listAuditSuppressionsRequest.getCheckName().equals(getCheckName())) {
            return false;
        }
        if ((listAuditSuppressionsRequest.getResourceIdentifier() == null) ^ (getResourceIdentifier() == null)) {
            return false;
        }
        if (listAuditSuppressionsRequest.getResourceIdentifier() != null && !listAuditSuppressionsRequest.getResourceIdentifier().equals(getResourceIdentifier())) {
            return false;
        }
        if ((listAuditSuppressionsRequest.getAscendingOrder() == null) ^ (getAscendingOrder() == null)) {
            return false;
        }
        if (listAuditSuppressionsRequest.getAscendingOrder() != null && !listAuditSuppressionsRequest.getAscendingOrder().equals(getAscendingOrder())) {
            return false;
        }
        if ((listAuditSuppressionsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listAuditSuppressionsRequest.getNextToken() != null && !listAuditSuppressionsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listAuditSuppressionsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listAuditSuppressionsRequest.getMaxResults() == null || listAuditSuppressionsRequest.getMaxResults().equals(getMaxResults());
    }
}
