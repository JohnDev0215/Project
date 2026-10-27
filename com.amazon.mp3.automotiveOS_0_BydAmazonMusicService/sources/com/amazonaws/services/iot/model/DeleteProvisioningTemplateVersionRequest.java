package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteProvisioningTemplateVersionRequest extends AmazonWebServiceRequest implements Serializable {
    private String templateName;
    private Integer versionId;

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public DeleteProvisioningTemplateVersionRequest withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public Integer getVersionId() {
        return this.versionId;
    }

    public void setVersionId(Integer num) {
        this.versionId = num;
    }

    public DeleteProvisioningTemplateVersionRequest withVersionId(Integer num) {
        this.versionId = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTemplateName() != null) {
            sb.append("templateName: " + getTemplateName() + ",");
        }
        if (getVersionId() != null) {
            sb.append("versionId: " + getVersionId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTemplateName() == null ? 0 : getTemplateName().hashCode()) + 31) * 31) + (getVersionId() != null ? getVersionId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteProvisioningTemplateVersionRequest)) {
            return false;
        }
        DeleteProvisioningTemplateVersionRequest deleteProvisioningTemplateVersionRequest = (DeleteProvisioningTemplateVersionRequest) obj;
        if ((deleteProvisioningTemplateVersionRequest.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        if (deleteProvisioningTemplateVersionRequest.getTemplateName() != null && !deleteProvisioningTemplateVersionRequest.getTemplateName().equals(getTemplateName())) {
            return false;
        }
        if ((deleteProvisioningTemplateVersionRequest.getVersionId() == null) ^ (getVersionId() == null)) {
            return false;
        }
        return deleteProvisioningTemplateVersionRequest.getVersionId() == null || deleteProvisioningTemplateVersionRequest.getVersionId().equals(getVersionId());
    }
}
