package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeManagedJobTemplateRequest extends AmazonWebServiceRequest implements Serializable {
    private String templateName;
    private String templateVersion;

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public DescribeManagedJobTemplateRequest withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public String getTemplateVersion() {
        return this.templateVersion;
    }

    public void setTemplateVersion(String str) {
        this.templateVersion = str;
    }

    public DescribeManagedJobTemplateRequest withTemplateVersion(String str) {
        this.templateVersion = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTemplateName() != null) {
            sb.append("templateName: " + getTemplateName() + ",");
        }
        if (getTemplateVersion() != null) {
            sb.append("templateVersion: " + getTemplateVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTemplateName() == null ? 0 : getTemplateName().hashCode()) + 31) * 31) + (getTemplateVersion() != null ? getTemplateVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeManagedJobTemplateRequest)) {
            return false;
        }
        DescribeManagedJobTemplateRequest describeManagedJobTemplateRequest = (DescribeManagedJobTemplateRequest) obj;
        if ((describeManagedJobTemplateRequest.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        if (describeManagedJobTemplateRequest.getTemplateName() != null && !describeManagedJobTemplateRequest.getTemplateName().equals(getTemplateName())) {
            return false;
        }
        if ((describeManagedJobTemplateRequest.getTemplateVersion() == null) ^ (getTemplateVersion() == null)) {
            return false;
        }
        return describeManagedJobTemplateRequest.getTemplateVersion() == null || describeManagedJobTemplateRequest.getTemplateVersion().equals(getTemplateVersion());
    }
}
