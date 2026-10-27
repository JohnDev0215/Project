package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListManagedJobTemplatesRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer maxResults;
    private String nextToken;
    private String templateName;

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public ListManagedJobTemplatesRequest withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListManagedJobTemplatesRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListManagedJobTemplatesRequest withNextToken(String str) {
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
        if (obj == null || !(obj instanceof ListManagedJobTemplatesRequest)) {
            return false;
        }
        ListManagedJobTemplatesRequest listManagedJobTemplatesRequest = (ListManagedJobTemplatesRequest) obj;
        if ((listManagedJobTemplatesRequest.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        if (listManagedJobTemplatesRequest.getTemplateName() != null && !listManagedJobTemplatesRequest.getTemplateName().equals(getTemplateName())) {
            return false;
        }
        if ((listManagedJobTemplatesRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listManagedJobTemplatesRequest.getMaxResults() != null && !listManagedJobTemplatesRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listManagedJobTemplatesRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listManagedJobTemplatesRequest.getNextToken() == null || listManagedJobTemplatesRequest.getNextToken().equals(getNextToken());
    }
}
