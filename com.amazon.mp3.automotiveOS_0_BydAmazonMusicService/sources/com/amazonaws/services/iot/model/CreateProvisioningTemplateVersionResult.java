package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateProvisioningTemplateVersionResult implements Serializable {
    private Boolean isDefaultVersion;
    private String templateArn;
    private String templateName;
    private Integer versionId;

    public String getTemplateArn() {
        return this.templateArn;
    }

    public void setTemplateArn(String str) {
        this.templateArn = str;
    }

    public CreateProvisioningTemplateVersionResult withTemplateArn(String str) {
        this.templateArn = str;
        return this;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public CreateProvisioningTemplateVersionResult withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public Integer getVersionId() {
        return this.versionId;
    }

    public void setVersionId(Integer num) {
        this.versionId = num;
    }

    public CreateProvisioningTemplateVersionResult withVersionId(Integer num) {
        this.versionId = num;
        return this;
    }

    public Boolean isIsDefaultVersion() {
        return this.isDefaultVersion;
    }

    public Boolean getIsDefaultVersion() {
        return this.isDefaultVersion;
    }

    public void setIsDefaultVersion(Boolean bool) {
        this.isDefaultVersion = bool;
    }

    public CreateProvisioningTemplateVersionResult withIsDefaultVersion(Boolean bool) {
        this.isDefaultVersion = bool;
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
        if (getVersionId() != null) {
            sb.append("versionId: " + getVersionId() + ",");
        }
        if (getIsDefaultVersion() != null) {
            sb.append("isDefaultVersion: " + getIsDefaultVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getTemplateArn() == null ? 0 : getTemplateArn().hashCode()) + 31) * 31) + (getTemplateName() == null ? 0 : getTemplateName().hashCode())) * 31) + (getVersionId() == null ? 0 : getVersionId().hashCode())) * 31) + (getIsDefaultVersion() != null ? getIsDefaultVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateProvisioningTemplateVersionResult)) {
            return false;
        }
        CreateProvisioningTemplateVersionResult createProvisioningTemplateVersionResult = (CreateProvisioningTemplateVersionResult) obj;
        if ((createProvisioningTemplateVersionResult.getTemplateArn() == null) ^ (getTemplateArn() == null)) {
            return false;
        }
        if (createProvisioningTemplateVersionResult.getTemplateArn() != null && !createProvisioningTemplateVersionResult.getTemplateArn().equals(getTemplateArn())) {
            return false;
        }
        if ((createProvisioningTemplateVersionResult.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        if (createProvisioningTemplateVersionResult.getTemplateName() != null && !createProvisioningTemplateVersionResult.getTemplateName().equals(getTemplateName())) {
            return false;
        }
        if ((createProvisioningTemplateVersionResult.getVersionId() == null) ^ (getVersionId() == null)) {
            return false;
        }
        if (createProvisioningTemplateVersionResult.getVersionId() != null && !createProvisioningTemplateVersionResult.getVersionId().equals(getVersionId())) {
            return false;
        }
        if ((createProvisioningTemplateVersionResult.getIsDefaultVersion() == null) ^ (getIsDefaultVersion() == null)) {
            return false;
        }
        return createProvisioningTemplateVersionResult.getIsDefaultVersion() == null || createProvisioningTemplateVersionResult.getIsDefaultVersion().equals(getIsDefaultVersion());
    }
}
