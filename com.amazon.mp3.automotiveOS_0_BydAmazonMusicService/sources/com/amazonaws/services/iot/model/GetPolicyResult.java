package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class GetPolicyResult implements Serializable {
    private Date creationDate;
    private String defaultVersionId;
    private String generationId;
    private Date lastModifiedDate;
    private String policyArn;
    private String policyDocument;
    private String policyName;

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public GetPolicyResult withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public String getPolicyArn() {
        return this.policyArn;
    }

    public void setPolicyArn(String str) {
        this.policyArn = str;
    }

    public GetPolicyResult withPolicyArn(String str) {
        this.policyArn = str;
        return this;
    }

    public String getPolicyDocument() {
        return this.policyDocument;
    }

    public void setPolicyDocument(String str) {
        this.policyDocument = str;
    }

    public GetPolicyResult withPolicyDocument(String str) {
        this.policyDocument = str;
        return this;
    }

    public String getDefaultVersionId() {
        return this.defaultVersionId;
    }

    public void setDefaultVersionId(String str) {
        this.defaultVersionId = str;
    }

    public GetPolicyResult withDefaultVersionId(String str) {
        this.defaultVersionId = str;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public GetPolicyResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public GetPolicyResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public String getGenerationId() {
        return this.generationId;
    }

    public void setGenerationId(String str) {
        this.generationId = str;
    }

    public GetPolicyResult withGenerationId(String str) {
        this.generationId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicyName() != null) {
            sb.append("policyName: " + getPolicyName() + ",");
        }
        if (getPolicyArn() != null) {
            sb.append("policyArn: " + getPolicyArn() + ",");
        }
        if (getPolicyDocument() != null) {
            sb.append("policyDocument: " + getPolicyDocument() + ",");
        }
        if (getDefaultVersionId() != null) {
            sb.append("defaultVersionId: " + getDefaultVersionId() + ",");
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
        return (((((((((((((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getPolicyArn() == null ? 0 : getPolicyArn().hashCode())) * 31) + (getPolicyDocument() == null ? 0 : getPolicyDocument().hashCode())) * 31) + (getDefaultVersionId() == null ? 0 : getDefaultVersionId().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getGenerationId() != null ? getGenerationId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetPolicyResult)) {
            return false;
        }
        GetPolicyResult getPolicyResult = (GetPolicyResult) obj;
        if ((getPolicyResult.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (getPolicyResult.getPolicyName() != null && !getPolicyResult.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((getPolicyResult.getPolicyArn() == null) ^ (getPolicyArn() == null)) {
            return false;
        }
        if (getPolicyResult.getPolicyArn() != null && !getPolicyResult.getPolicyArn().equals(getPolicyArn())) {
            return false;
        }
        if ((getPolicyResult.getPolicyDocument() == null) ^ (getPolicyDocument() == null)) {
            return false;
        }
        if (getPolicyResult.getPolicyDocument() != null && !getPolicyResult.getPolicyDocument().equals(getPolicyDocument())) {
            return false;
        }
        if ((getPolicyResult.getDefaultVersionId() == null) ^ (getDefaultVersionId() == null)) {
            return false;
        }
        if (getPolicyResult.getDefaultVersionId() != null && !getPolicyResult.getDefaultVersionId().equals(getDefaultVersionId())) {
            return false;
        }
        if ((getPolicyResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (getPolicyResult.getCreationDate() != null && !getPolicyResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((getPolicyResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (getPolicyResult.getLastModifiedDate() != null && !getPolicyResult.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((getPolicyResult.getGenerationId() == null) ^ (getGenerationId() == null)) {
            return false;
        }
        return getPolicyResult.getGenerationId() == null || getPolicyResult.getGenerationId().equals(getGenerationId());
    }
}
