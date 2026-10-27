package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeProvisioningTemplateVersionResult implements Serializable {
    private Date creationDate;
    private Boolean isDefaultVersion;
    private String templateBody;
    private Integer versionId;

    public Integer getVersionId() {
        return this.versionId;
    }

    public void setVersionId(Integer num) {
        this.versionId = num;
    }

    public DescribeProvisioningTemplateVersionResult withVersionId(Integer num) {
        this.versionId = num;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public DescribeProvisioningTemplateVersionResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public String getTemplateBody() {
        return this.templateBody;
    }

    public void setTemplateBody(String str) {
        this.templateBody = str;
    }

    public DescribeProvisioningTemplateVersionResult withTemplateBody(String str) {
        this.templateBody = str;
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

    public DescribeProvisioningTemplateVersionResult withIsDefaultVersion(Boolean bool) {
        this.isDefaultVersion = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getVersionId() != null) {
            sb.append("versionId: " + getVersionId() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate() + ",");
        }
        if (getTemplateBody() != null) {
            sb.append("templateBody: " + getTemplateBody() + ",");
        }
        if (getIsDefaultVersion() != null) {
            sb.append("isDefaultVersion: " + getIsDefaultVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getVersionId() == null ? 0 : getVersionId().hashCode()) + 31) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getTemplateBody() == null ? 0 : getTemplateBody().hashCode())) * 31) + (getIsDefaultVersion() != null ? getIsDefaultVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeProvisioningTemplateVersionResult)) {
            return false;
        }
        DescribeProvisioningTemplateVersionResult describeProvisioningTemplateVersionResult = (DescribeProvisioningTemplateVersionResult) obj;
        if ((describeProvisioningTemplateVersionResult.getVersionId() == null) ^ (getVersionId() == null)) {
            return false;
        }
        if (describeProvisioningTemplateVersionResult.getVersionId() != null && !describeProvisioningTemplateVersionResult.getVersionId().equals(getVersionId())) {
            return false;
        }
        if ((describeProvisioningTemplateVersionResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (describeProvisioningTemplateVersionResult.getCreationDate() != null && !describeProvisioningTemplateVersionResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((describeProvisioningTemplateVersionResult.getTemplateBody() == null) ^ (getTemplateBody() == null)) {
            return false;
        }
        if (describeProvisioningTemplateVersionResult.getTemplateBody() != null && !describeProvisioningTemplateVersionResult.getTemplateBody().equals(getTemplateBody())) {
            return false;
        }
        if ((describeProvisioningTemplateVersionResult.getIsDefaultVersion() == null) ^ (getIsDefaultVersion() == null)) {
            return false;
        }
        return describeProvisioningTemplateVersionResult.getIsDefaultVersion() == null || describeProvisioningTemplateVersionResult.getIsDefaultVersion().equals(getIsDefaultVersion());
    }
}
