package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class ProvisioningTemplateSummary implements Serializable {
    private Date creationDate;
    private String description;
    private Boolean enabled;
    private Date lastModifiedDate;
    private String templateArn;
    private String templateName;
    private String type;

    public String getTemplateArn() {
        return this.templateArn;
    }

    public void setTemplateArn(String str) {
        this.templateArn = str;
    }

    public ProvisioningTemplateSummary withTemplateArn(String str) {
        this.templateArn = str;
        return this;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public ProvisioningTemplateSummary withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public ProvisioningTemplateSummary withDescription(String str) {
        this.description = str;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public ProvisioningTemplateSummary withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public ProvisioningTemplateSummary withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean bool) {
        this.enabled = bool;
    }

    public ProvisioningTemplateSummary withEnabled(Boolean bool) {
        this.enabled = bool;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public ProvisioningTemplateSummary withType(String str) {
        this.type = str;
        return this;
    }

    public void setType(TemplateType templateType) {
        this.type = templateType.toString();
    }

    public ProvisioningTemplateSummary withType(TemplateType templateType) {
        this.type = templateType.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTemplateArn() != null) {
            sb.append("templateArn: " + getTemplateArn() + ",");
        }
        if (getTemplateName() != null) {
            sb.append("templateName: " + getTemplateName() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate() + ",");
        }
        if (getLastModifiedDate() != null) {
            sb.append("lastModifiedDate: " + getLastModifiedDate() + ",");
        }
        if (getEnabled() != null) {
            sb.append("enabled: " + getEnabled() + ",");
        }
        if (getType() != null) {
            sb.append("type: " + getType());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getTemplateArn() == null ? 0 : getTemplateArn().hashCode()) + 31) * 31) + (getTemplateName() == null ? 0 : getTemplateName().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getEnabled() == null ? 0 : getEnabled().hashCode())) * 31) + (getType() != null ? getType().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ProvisioningTemplateSummary)) {
            return false;
        }
        ProvisioningTemplateSummary provisioningTemplateSummary = (ProvisioningTemplateSummary) obj;
        if ((provisioningTemplateSummary.getTemplateArn() == null) ^ (getTemplateArn() == null)) {
            return false;
        }
        if (provisioningTemplateSummary.getTemplateArn() != null && !provisioningTemplateSummary.getTemplateArn().equals(getTemplateArn())) {
            return false;
        }
        if ((provisioningTemplateSummary.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        if (provisioningTemplateSummary.getTemplateName() != null && !provisioningTemplateSummary.getTemplateName().equals(getTemplateName())) {
            return false;
        }
        if ((provisioningTemplateSummary.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (provisioningTemplateSummary.getDescription() != null && !provisioningTemplateSummary.getDescription().equals(getDescription())) {
            return false;
        }
        if ((provisioningTemplateSummary.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (provisioningTemplateSummary.getCreationDate() != null && !provisioningTemplateSummary.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((provisioningTemplateSummary.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (provisioningTemplateSummary.getLastModifiedDate() != null && !provisioningTemplateSummary.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((provisioningTemplateSummary.getEnabled() == null) ^ (getEnabled() == null)) {
            return false;
        }
        if (provisioningTemplateSummary.getEnabled() != null && !provisioningTemplateSummary.getEnabled().equals(getEnabled())) {
            return false;
        }
        if ((provisioningTemplateSummary.getType() == null) ^ (getType() == null)) {
            return false;
        }
        return provisioningTemplateSummary.getType() == null || provisioningTemplateSummary.getType().equals(getType());
    }
}
