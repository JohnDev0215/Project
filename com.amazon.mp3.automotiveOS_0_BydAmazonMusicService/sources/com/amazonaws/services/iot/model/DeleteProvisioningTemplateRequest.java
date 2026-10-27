package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteProvisioningTemplateRequest extends AmazonWebServiceRequest implements Serializable {
    private String templateName;

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public DeleteProvisioningTemplateRequest withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTemplateName() != null) {
            sb.append("templateName: " + getTemplateName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getTemplateName() == null ? 0 : getTemplateName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteProvisioningTemplateRequest)) {
            return false;
        }
        DeleteProvisioningTemplateRequest deleteProvisioningTemplateRequest = (DeleteProvisioningTemplateRequest) obj;
        if ((deleteProvisioningTemplateRequest.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        return deleteProvisioningTemplateRequest.getTemplateName() == null || deleteProvisioningTemplateRequest.getTemplateName().equals(getTemplateName());
    }
}
