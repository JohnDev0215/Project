package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreatePolicyVersionResult implements Serializable {
    private Boolean isDefaultVersion;
    private String policyArn;
    private String policyDocument;
    private String policyVersionId;

    public String getPolicyArn() {
        return this.policyArn;
    }

    public void setPolicyArn(String str) {
        this.policyArn = str;
    }

    public CreatePolicyVersionResult withPolicyArn(String str) {
        this.policyArn = str;
        return this;
    }

    public String getPolicyDocument() {
        return this.policyDocument;
    }

    public void setPolicyDocument(String str) {
        this.policyDocument = str;
    }

    public CreatePolicyVersionResult withPolicyDocument(String str) {
        this.policyDocument = str;
        return this;
    }

    public String getPolicyVersionId() {
        return this.policyVersionId;
    }

    public void setPolicyVersionId(String str) {
        this.policyVersionId = str;
    }

    public CreatePolicyVersionResult withPolicyVersionId(String str) {
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

    public CreatePolicyVersionResult withIsDefaultVersion(Boolean bool) {
        this.isDefaultVersion = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPolicyArn() != null) {
            sb.append("policyArn: " + getPolicyArn() + ",");
        }
        if (getPolicyDocument() != null) {
            sb.append("policyDocument: " + getPolicyDocument() + ",");
        }
        if (getPolicyVersionId() != null) {
            sb.append("policyVersionId: " + getPolicyVersionId() + ",");
        }
        if (getIsDefaultVersion() != null) {
            sb.append("isDefaultVersion: " + getIsDefaultVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getPolicyArn() == null ? 0 : getPolicyArn().hashCode()) + 31) * 31) + (getPolicyDocument() == null ? 0 : getPolicyDocument().hashCode())) * 31) + (getPolicyVersionId() == null ? 0 : getPolicyVersionId().hashCode())) * 31) + (getIsDefaultVersion() != null ? getIsDefaultVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreatePolicyVersionResult)) {
            return false;
        }
        CreatePolicyVersionResult createPolicyVersionResult = (CreatePolicyVersionResult) obj;
        if ((createPolicyVersionResult.getPolicyArn() == null) ^ (getPolicyArn() == null)) {
            return false;
        }
        if (createPolicyVersionResult.getPolicyArn() != null && !createPolicyVersionResult.getPolicyArn().equals(getPolicyArn())) {
            return false;
        }
        if ((createPolicyVersionResult.getPolicyDocument() == null) ^ (getPolicyDocument() == null)) {
            return false;
        }
        if (createPolicyVersionResult.getPolicyDocument() != null && !createPolicyVersionResult.getPolicyDocument().equals(getPolicyDocument())) {
            return false;
        }
        if ((createPolicyVersionResult.getPolicyVersionId() == null) ^ (getPolicyVersionId() == null)) {
            return false;
        }
        if (createPolicyVersionResult.getPolicyVersionId() != null && !createPolicyVersionResult.getPolicyVersionId().equals(getPolicyVersionId())) {
            return false;
        }
        if ((createPolicyVersionResult.getIsDefaultVersion() == null) ^ (getIsDefaultVersion() == null)) {
            return false;
        }
        return createPolicyVersionResult.getIsDefaultVersion() == null || createPolicyVersionResult.getIsDefaultVersion().equals(getIsDefaultVersion());
    }
}
