package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListProvisioningTemplatesRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListProvisioningTemplatesRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListProvisioningTemplatesRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getMaxResults() == null ? 0 : getMaxResults().hashCode()) + 31) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListProvisioningTemplatesRequest)) {
            return false;
        }
        ListProvisioningTemplatesRequest listProvisioningTemplatesRequest = (ListProvisioningTemplatesRequest) obj;
        if ((listProvisioningTemplatesRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listProvisioningTemplatesRequest.getMaxResults() != null && !listProvisioningTemplatesRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listProvisioningTemplatesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listProvisioningTemplatesRequest.getNextToken() == null || listProvisioningTemplatesRequest.getNextToken().equals(getNextToken());
    }
}
