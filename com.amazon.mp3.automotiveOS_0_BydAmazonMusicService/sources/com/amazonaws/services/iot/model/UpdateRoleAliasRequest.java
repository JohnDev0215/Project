package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateRoleAliasRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer credentialDurationSeconds;
    private String roleAlias;
    private String roleArn;

    public String getRoleAlias() {
        return this.roleAlias;
    }

    public void setRoleAlias(String str) {
        this.roleAlias = str;
    }

    public UpdateRoleAliasRequest withRoleAlias(String str) {
        this.roleAlias = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public UpdateRoleAliasRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public Integer getCredentialDurationSeconds() {
        return this.credentialDurationSeconds;
    }

    public void setCredentialDurationSeconds(Integer num) {
        this.credentialDurationSeconds = num;
    }

    public UpdateRoleAliasRequest withCredentialDurationSeconds(Integer num) {
        this.credentialDurationSeconds = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleAlias() != null) {
            sb.append("roleAlias: " + getRoleAlias() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getCredentialDurationSeconds() != null) {
            sb.append("credentialDurationSeconds: " + getCredentialDurationSeconds());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getRoleAlias() == null ? 0 : getRoleAlias().hashCode()) + 31) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getCredentialDurationSeconds() != null ? getCredentialDurationSeconds().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateRoleAliasRequest)) {
            return false;
        }
        UpdateRoleAliasRequest updateRoleAliasRequest = (UpdateRoleAliasRequest) obj;
        if ((updateRoleAliasRequest.getRoleAlias() == null) ^ (getRoleAlias() == null)) {
            return false;
        }
        if (updateRoleAliasRequest.getRoleAlias() != null && !updateRoleAliasRequest.getRoleAlias().equals(getRoleAlias())) {
            return false;
        }
        if ((updateRoleAliasRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (updateRoleAliasRequest.getRoleArn() != null && !updateRoleAliasRequest.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((updateRoleAliasRequest.getCredentialDurationSeconds() == null) ^ (getCredentialDurationSeconds() == null)) {
            return false;
        }
        return updateRoleAliasRequest.getCredentialDurationSeconds() == null || updateRoleAliasRequest.getCredentialDurationSeconds().equals(getCredentialDurationSeconds());
    }
}
