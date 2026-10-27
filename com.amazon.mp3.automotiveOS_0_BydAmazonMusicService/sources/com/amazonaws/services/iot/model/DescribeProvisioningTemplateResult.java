package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeProvisioningTemplateResult implements Serializable {
    private Date creationDate;
    private Integer defaultVersionId;
    private String description;
    private Boolean enabled;
    private Date lastModifiedDate;
    private ProvisioningHook preProvisioningHook;
    private String provisioningRoleArn;
    private String templateArn;
    private String templateBody;
    private String templateName;
    private String type;

    public String getTemplateArn() {
        return this.templateArn;
    }

    public void setTemplateArn(String str) {
        this.templateArn = str;
    }

    public DescribeProvisioningTemplateResult withTemplateArn(String str) {
        this.templateArn = str;
        return this;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public DescribeProvisioningTemplateResult withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public DescribeProvisioningTemplateResult withDescription(String str) {
        this.description = str;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public DescribeProvisioningTemplateResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public DescribeProvisioningTemplateResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public Integer getDefaultVersionId() {
        return this.defaultVersionId;
    }

    public void setDefaultVersionId(Integer num) {
        this.defaultVersionId = num;
    }

    public DescribeProvisioningTemplateResult withDefaultVersionId(Integer num) {
        this.defaultVersionId = num;
        return this;
    }

    public String getTemplateBody() {
        return this.templateBody;
    }

    public void setTemplateBody(String str) {
        this.templateBody = str;
    }

    public DescribeProvisioningTemplateResult withTemplateBody(String str) {
        this.templateBody = str;
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

    public DescribeProvisioningTemplateResult withEnabled(Boolean bool) {
        this.enabled = bool;
        return this;
    }

    public String getProvisioningRoleArn() {
        return this.provisioningRoleArn;
    }

    public void setProvisioningRoleArn(String str) {
        this.provisioningRoleArn = str;
    }

    public DescribeProvisioningTemplateResult withProvisioningRoleArn(String str) {
        this.provisioningRoleArn = str;
        return this;
    }

    public ProvisioningHook getPreProvisioningHook() {
        return this.preProvisioningHook;
    }

    public void setPreProvisioningHook(ProvisioningHook provisioningHook) {
        this.preProvisioningHook = provisioningHook;
    }

    public DescribeProvisioningTemplateResult withPreProvisioningHook(ProvisioningHook provisioningHook) {
        this.preProvisioningHook = provisioningHook;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public DescribeProvisioningTemplateResult withType(String str) {
        this.type = str;
        return this;
    }

    public void setType(TemplateType templateType) {
        this.type = templateType.toString();
    }

    public DescribeProvisioningTemplateResult withType(TemplateType templateType) {
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
        if (getDefaultVersionId() != null) {
            sb.append("defaultVersionId: " + getDefaultVersionId() + ",");
        }
        if (getTemplateBody() != null) {
            sb.append("templateBody: " + getTemplateBody() + ",");
        }
        if (getEnabled() != null) {
            sb.append("enabled: " + getEnabled() + ",");
        }
        if (getProvisioningRoleArn() != null) {
            sb.append("provisioningRoleArn: " + getProvisioningRoleArn() + ",");
        }
        if (getPreProvisioningHook() != null) {
            sb.append("preProvisioningHook: " + getPreProvisioningHook() + ",");
        }
        if (getType() != null) {
            sb.append("type: " + getType());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((getTemplateArn() == null ? 0 : getTemplateArn().hashCode()) + 31) * 31) + (getTemplateName() == null ? 0 : getTemplateName().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getDefaultVersionId() == null ? 0 : getDefaultVersionId().hashCode())) * 31) + (getTemplateBody() == null ? 0 : getTemplateBody().hashCode())) * 31) + (getEnabled() == null ? 0 : getEnabled().hashCode())) * 31) + (getProvisioningRoleArn() == null ? 0 : getProvisioningRoleArn().hashCode())) * 31) + (getPreProvisioningHook() == null ? 0 : getPreProvisioningHook().hashCode())) * 31) + (getType() != null ? getType().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeProvisioningTemplateResult)) {
            return false;
        }
        DescribeProvisioningTemplateResult describeProvisioningTemplateResult = (DescribeProvisioningTemplateResult) obj;
        if ((describeProvisioningTemplateResult.getTemplateArn() == null) ^ (getTemplateArn() == null)) {
            return false;
        }
        if (describeProvisioningTemplateResult.getTemplateArn() != null && !describeProvisioningTemplateResult.getTemplateArn().equals(getTemplateArn())) {
            return false;
        }
        if ((describeProvisioningTemplateResult.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        if (describeProvisioningTemplateResult.getTemplateName() != null && !describeProvisioningTemplateResult.getTemplateName().equals(getTemplateName())) {
            return false;
        }
        if ((describeProvisioningTemplateResult.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (describeProvisioningTemplateResult.getDescription() != null && !describeProvisioningTemplateResult.getDescription().equals(getDescription())) {
            return false;
        }
        if ((describeProvisioningTemplateResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (describeProvisioningTemplateResult.getCreationDate() != null && !describeProvisioningTemplateResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((describeProvisioningTemplateResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (describeProvisioningTemplateResult.getLastModifiedDate() != null && !describeProvisioningTemplateResult.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((describeProvisioningTemplateResult.getDefaultVersionId() == null) ^ (getDefaultVersionId() == null)) {
            return false;
        }
        if (describeProvisioningTemplateResult.getDefaultVersionId() != null && !describeProvisioningTemplateResult.getDefaultVersionId().equals(getDefaultVersionId())) {
            return false;
        }
        if ((describeProvisioningTemplateResult.getTemplateBody() == null) ^ (getTemplateBody() == null)) {
            return false;
        }
        if (describeProvisioningTemplateResult.getTemplateBody() != null && !describeProvisioningTemplateResult.getTemplateBody().equals(getTemplateBody())) {
            return false;
        }
        if ((describeProvisioningTemplateResult.getEnabled() == null) ^ (getEnabled() == null)) {
            return false;
        }
        if (describeProvisioningTemplateResult.getEnabled() != null && !describeProvisioningTemplateResult.getEnabled().equals(getEnabled())) {
            return false;
        }
        if ((describeProvisioningTemplateResult.getProvisioningRoleArn() == null) ^ (getProvisioningRoleArn() == null)) {
            return false;
        }
        if (describeProvisioningTemplateResult.getProvisioningRoleArn() != null && !describeProvisioningTemplateResult.getProvisioningRoleArn().equals(getProvisioningRoleArn())) {
            return false;
        }
        if ((describeProvisioningTemplateResult.getPreProvisioningHook() == null) ^ (getPreProvisioningHook() == null)) {
            return false;
        }
        if (describeProvisioningTemplateResult.getPreProvisioningHook() != null && !describeProvisioningTemplateResult.getPreProvisioningHook().equals(getPreProvisioningHook())) {
            return false;
        }
        if ((describeProvisioningTemplateResult.getType() == null) ^ (getType() == null)) {
            return false;
        }
        return describeProvisioningTemplateResult.getType() == null || describeProvisioningTemplateResult.getType().equals(getType());
    }
}
