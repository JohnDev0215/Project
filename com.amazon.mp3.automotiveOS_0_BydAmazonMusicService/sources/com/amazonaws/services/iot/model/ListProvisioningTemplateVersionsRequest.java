package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListProvisioningTemplateVersionsRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String templateName;

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public ListProvisioningTemplateVersionsRequest withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListProvisioningTemplateVersionsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListProvisioningTemplateVersionsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTemplateName() != null) {
            sb.append("templateName: " + getTemplateName() + ",");
        }
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
        return (((((getTemplateName() == null ? 0 : getTemplateName().hashCode()) + 31) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31) + (getNextToken() != null ? getNextToken().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListProvisioningTemplateVersionsRequest)) {
            return false;
        }
        ListProvisioningTemplateVersionsRequest listProvisioningTemplateVersionsRequest = (ListProvisioningTemplateVersionsRequest) obj;
        if ((listProvisioningTemplateVersionsRequest.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        if (listProvisioningTemplateVersionsRequest.getTemplateName() != null && !listProvisioningTemplateVersionsRequest.getTemplateName().equals(getTemplateName())) {
            return false;
        }
        if ((listProvisioningTemplateVersionsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listProvisioningTemplateVersionsRequest.getMaxResults() != null && !listProvisioningTemplateVersionsRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listProvisioningTemplateVersionsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listProvisioningTemplateVersionsRequest.getNextToken() == null || listProvisioningTemplateVersionsRequest.getNextToken().equals(getNextToken());
    }
}
