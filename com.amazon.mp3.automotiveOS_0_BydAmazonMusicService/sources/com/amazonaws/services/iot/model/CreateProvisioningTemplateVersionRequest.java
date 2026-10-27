package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateProvisioningTemplateVersionRequest extends AmazonWebServiceRequest implements Serializable {
    private Boolean setAsDefault;
    private String templateBody;
    private String templateName;

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public CreateProvisioningTemplateVersionRequest withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public String getTemplateBody() {
        return this.templateBody;
    }

    public void setTemplateBody(String str) {
        this.templateBody = str;
    }

    public CreateProvisioningTemplateVersionRequest withTemplateBody(String str) {
        this.templateBody = str;
        return this;
    }

    public Boolean isSetAsDefault() {
        return this.setAsDefault;
    }

    public Boolean getSetAsDefault() {
        return this.setAsDefault;
    }

    public void setSetAsDefault(Boolean bool) {
        this.setAsDefault = bool;
    }

    public CreateProvisioningTemplateVersionRequest withSetAsDefault(Boolean bool) {
        this.setAsDefault = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTemplateName() != null) {
            sb.append("templateName: " + getTemplateName() + ",");
        }
        if (getTemplateBody() != null) {
            sb.append("templateBody: " + getTemplateBody() + ",");
        }
        if (getSetAsDefault() != null) {
            sb.append("setAsDefault: " + getSetAsDefault());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getTemplateName() == null ? 0 : getTemplateName().hashCode()) + 31) * 31) + (getTemplateBody() == null ? 0 : getTemplateBody().hashCode())) * 31) + (getSetAsDefault() != null ? getSetAsDefault().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateProvisioningTemplateVersionRequest)) {
            return false;
        }
        CreateProvisioningTemplateVersionRequest createProvisioningTemplateVersionRequest = (CreateProvisioningTemplateVersionRequest) obj;
        if ((createProvisioningTemplateVersionRequest.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        if (createProvisioningTemplateVersionRequest.getTemplateName() != null && !createProvisioningTemplateVersionRequest.getTemplateName().equals(getTemplateName())) {
            return false;
        }
        if ((createProvisioningTemplateVersionRequest.getTemplateBody() == null) ^ (getTemplateBody() == null)) {
            return false;
        }
        if (createProvisioningTemplateVersionRequest.getTemplateBody() != null && !createProvisioningTemplateVersionRequest.getTemplateBody().equals(getTemplateBody())) {
            return false;
        }
        if ((createProvisioningTemplateVersionRequest.getSetAsDefault() == null) ^ (getSetAsDefault() == null)) {
            return false;
        }
        return createProvisioningTemplateVersionRequest.getSetAsDefault() == null || createProvisioningTemplateVersionRequest.getSetAsDefault().equals(getSetAsDefault());
    }
}
