package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class PolicyVersion implements Serializable {
    private Date createDate;
    private Boolean isDefaultVersion;
    private String versionId;

    public String getVersionId() {
        return this.versionId;
    }

    public void setVersionId(String str) {
        this.versionId = str;
    }

    public PolicyVersion withVersionId(String str) {
        this.versionId = str;
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

    public PolicyVersion withIsDefaultVersion(Boolean bool) {
        this.isDefaultVersion = bool;
        return this;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date date) {
        this.createDate = date;
    }

    public PolicyVersion withCreateDate(Date date) {
        this.createDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getVersionId() != null) {
            sb.append("versionId: " + getVersionId() + ",");
        }
        if (getIsDefaultVersion() != null) {
            sb.append("isDefaultVersion: " + getIsDefaultVersion() + ",");
        }
        if (getCreateDate() != null) {
            sb.append("createDate: " + getCreateDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getVersionId() == null ? 0 : getVersionId().hashCode()) + 31) * 31) + (getIsDefaultVersion() == null ? 0 : getIsDefaultVersion().hashCode())) * 31) + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PolicyVersion)) {
            return false;
        }
        PolicyVersion policyVersion = (PolicyVersion) obj;
        if ((policyVersion.getVersionId() == null) ^ (getVersionId() == null)) {
            return false;
        }
        if (policyVersion.getVersionId() != null && !policyVersion.getVersionId().equals(getVersionId())) {
            return false;
        }
        if ((policyVersion.getIsDefaultVersion() == null) ^ (getIsDefaultVersion() == null)) {
            return false;
        }
        if (policyVersion.getIsDefaultVersion() != null && !policyVersion.getIsDefaultVersion().equals(getIsDefaultVersion())) {
            return false;
        }
        if ((policyVersion.getCreateDate() == null) ^ (getCreateDate() == null)) {
            return false;
        }
        return policyVersion.getCreateDate() == null || policyVersion.getCreateDate().equals(getCreateDate());
    }
}
