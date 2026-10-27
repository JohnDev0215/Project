package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class ProvisioningTemplateVersionSummary implements Serializable {
    private Date creationDate;
    private Boolean isDefaultVersion;
    private Integer versionId;

    public Integer getVersionId() {
        return this.versionId;
    }

    public void setVersionId(Integer num) {
        this.versionId = num;
    }

    public ProvisioningTemplateVersionSummary withVersionId(Integer num) {
        this.versionId = num;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public ProvisioningTemplateVersionSummary withCreationDate(Date date) {
        this.creationDate = date;
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

    public ProvisioningTemplateVersionSummary withIsDefaultVersion(Boolean bool) {
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
        if (getIsDefaultVersion() != null) {
            sb.append("isDefaultVersion: " + getIsDefaultVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getVersionId() == null ? 0 : getVersionId().hashCode()) + 31) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getIsDefaultVersion() != null ? getIsDefaultVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ProvisioningTemplateVersionSummary)) {
            return false;
        }
        ProvisioningTemplateVersionSummary provisioningTemplateVersionSummary = (ProvisioningTemplateVersionSummary) obj;
        if ((provisioningTemplateVersionSummary.getVersionId() == null) ^ (getVersionId() == null)) {
            return false;
        }
        if (provisioningTemplateVersionSummary.getVersionId() != null && !provisioningTemplateVersionSummary.getVersionId().equals(getVersionId())) {
            return false;
        }
        if ((provisioningTemplateVersionSummary.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (provisioningTemplateVersionSummary.getCreationDate() != null && !provisioningTemplateVersionSummary.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((provisioningTemplateVersionSummary.getIsDefaultVersion() == null) ^ (getIsDefaultVersion() == null)) {
            return false;
        }
        return provisioningTemplateVersionSummary.getIsDefaultVersion() == null || provisioningTemplateVersionSummary.getIsDefaultVersion().equals(getIsDefaultVersion());
    }
}
