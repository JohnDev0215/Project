package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class GetPolicyVersionResult implements Serializable {
    private Date creationDate;
    private String generationId;
    private Boolean isDefaultVersion;
    private Date lastModifiedDate;
    private String policyArn;
    private String policyDocument;
    private String policyName;
    private String policyVersionId;

    public String getPolicyArn() {
        return this.policyArn;
    }

    public void setPolicyArn(String str) {
        this.policyArn = str;
    }

    public GetPolicyVersionResult withPolicyArn(String str) {
        this.policyArn = str;
        return this;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public GetPolicyVersionResult withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public String getPolicyDocument() {
        return this.policyDocument;
    }

    public void setPolicyDocument(String str) {
        this.policyDocument = str;
    }

    public GetPolicyVersionResult withPolicyDocument(String str) {
        this.policyDocument = str;
        return this;
    }

    public String getPolicyVersionId() {
        return this.policyVersionId;
    }

    public void setPolicyVersionId(String str) {
        this.policyVersionId = str;
    }

    public GetPolicyVersionResult withPolicyVersionId(String str) {
        this.policyVersionId = str;
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

    public GetPolicyVersionResult withIsDefaultVersion(Boolean bool) {
        this.isDefaultVersion = bool;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public GetPolicyVersionResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public GetPolicyVersionResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public String getGenerationId() {
        return this.generationId;
    }

    public void setGenerationId(String str) {
        this.generationId = str;
    }

    public GetPolicyVersionResult withGenerationId(String str) {
        this.generationId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicyArn() != null) {
            sb.append("policyArn: " + getPolicyArn() + ",");
        }
        if (getPolicyName() != null) {
            sb.append("policyName: " + getPolicyName() + ",");
        }
        if (getPolicyDocument() != null) {
            sb.append("policyDocument: " + getPolicyDocument() + ",");
        }
        if (getPolicyVersionId() != null) {
            sb.append("policyVersionId: " + getPolicyVersionId() + ",");
        }
        if (getIsDefaultVersion() != null) {
            sb.append("isDefaultVersion: " + getIsDefaultVersion() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate() + ",");
        }
        if (getLastModifiedDate() != null) {
            sb.append("lastModifiedDate: " + getLastModifiedDate() + ",");
        }
        if (getGenerationId() != null) {
            sb.append("generationId: " + getGenerationId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((getPolicyArn() == null ? 0 : getPolicyArn().hashCode()) + 31) * 31) + (getPolicyName() == null ? 0 : getPolicyName().hashCode())) * 31) + (getPolicyDocument() == null ? 0 : getPolicyDocument().hashCode())) * 31) + (getPolicyVersionId() == null ? 0 : getPolicyVersionId().hashCode())) * 31) + (getIsDefaultVersion() == null ? 0 : getIsDefaultVersion().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getGenerationId() != null ? getGenerationId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetPolicyVersionResult)) {
            return false;
        }
        GetPolicyVersionResult getPolicyVersionResult = (GetPolicyVersionResult) obj;
        if ((getPolicyVersionResult.getPolicyArn() == null) ^ (getPolicyArn() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getPolicyArn() != null && !getPolicyVersionResult.getPolicyArn().equals(getPolicyArn())) {
            return false;
        }
        if ((getPolicyVersionResult.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getPolicyName() != null && !getPolicyVersionResult.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((getPolicyVersionResult.getPolicyDocument() == null) ^ (getPolicyDocument() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getPolicyDocument() != null && !getPolicyVersionResult.getPolicyDocument().equals(getPolicyDocument())) {
            return false;
        }
        if ((getPolicyVersionResult.getPolicyVersionId() == null) ^ (getPolicyVersionId() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getPolicyVersionId() != null && !getPolicyVersionResult.getPolicyVersionId().equals(getPolicyVersionId())) {
            return false;
        }
        if ((getPolicyVersionResult.getIsDefaultVersion() == null) ^ (getIsDefaultVersion() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getIsDefaultVersion() != null && !getPolicyVersionResult.getIsDefaultVersion().equals(getIsDefaultVersion())) {
            return false;
        }
        if ((getPolicyVersionResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getCreationDate() != null && !getPolicyVersionResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((getPolicyVersionResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getLastModifiedDate() != null && !getPolicyVersionResult.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((getPolicyVersionResult.getGenerationId() == null) ^ (getGenerationId() == null)) {
            return false;
        }
        return getPolicyVersionResult.getGenerationId() == null || getPolicyVersionResult.getGenerationId().equals(getGenerationId());
    }
}
