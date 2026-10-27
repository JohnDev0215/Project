package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class RoleAliasDescription implements Serializable {
    private Date creationDate;
    private Integer credentialDurationSeconds;
    private Date lastModifiedDate;
    private String owner;
    private String roleAlias;
    private String roleAliasArn;
    private String roleArn;

    public String getRoleAlias() {
        return this.roleAlias;
    }

    public void setRoleAlias(String str) {
        this.roleAlias = str;
    }

    public RoleAliasDescription withRoleAlias(String str) {
        this.roleAlias = str;
        return this;
    }

    public String getRoleAliasArn() {
        return this.roleAliasArn;
    }

    public void setRoleAliasArn(String str) {
        this.roleAliasArn = str;
    }

    public RoleAliasDescription withRoleAliasArn(String str) {
        this.roleAliasArn = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public RoleAliasDescription withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String str) {
        this.owner = str;
    }

    public RoleAliasDescription withOwner(String str) {
        this.owner = str;
        return this;
    }

    public Integer getCredentialDurationSeconds() {
        return this.credentialDurationSeconds;
    }

    public void setCredentialDurationSeconds(Integer num) {
        this.credentialDurationSeconds = num;
    }

    public RoleAliasDescription withCredentialDurationSeconds(Integer num) {
        this.credentialDurationSeconds = num;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public RoleAliasDescription withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public RoleAliasDescription withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleAlias() != null) {
            sb.append("roleAlias: " + getRoleAlias() + ",");
        }
        if (getRoleAliasArn() != null) {
            sb.append("roleAliasArn: " + getRoleAliasArn() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getOwner() != null) {
            sb.append("owner: " + getOwner() + ",");
        }
        if (getCredentialDurationSeconds() != null) {
            sb.append("credentialDurationSeconds: " + getCredentialDurationSeconds() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate() + ",");
        }
        if (getLastModifiedDate() != null) {
            sb.append("lastModifiedDate: " + getLastModifiedDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getRoleAlias() == null ? 0 : getRoleAlias().hashCode()) + 31) * 31) + (getRoleAliasArn() == null ? 0 : getRoleAliasArn().hashCode())) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getOwner() == null ? 0 : getOwner().hashCode())) * 31) + (getCredentialDurationSeconds() == null ? 0 : getCredentialDurationSeconds().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() != null ? getLastModifiedDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RoleAliasDescription)) {
            return false;
        }
        RoleAliasDescription roleAliasDescription = (RoleAliasDescription) obj;
        if ((roleAliasDescription.getRoleAlias() == null) ^ (getRoleAlias() == null)) {
            return false;
        }
        if (roleAliasDescription.getRoleAlias() != null && !roleAliasDescription.getRoleAlias().equals(getRoleAlias())) {
            return false;
        }
        if ((roleAliasDescription.getRoleAliasArn() == null) ^ (getRoleAliasArn() == null)) {
            return false;
        }
        if (roleAliasDescription.getRoleAliasArn() != null && !roleAliasDescription.getRoleAliasArn().equals(getRoleAliasArn())) {
            return false;
        }
        if ((roleAliasDescription.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (roleAliasDescription.getRoleArn() != null && !roleAliasDescription.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((roleAliasDescription.getOwner() == null) ^ (getOwner() == null)) {
            return false;
        }
        if (roleAliasDescription.getOwner() != null && !roleAliasDescription.getOwner().equals(getOwner())) {
            return false;
        }
        if ((roleAliasDescription.getCredentialDurationSeconds() == null) ^ (getCredentialDurationSeconds() == null)) {
            return false;
        }
        if (roleAliasDescription.getCredentialDurationSeconds() != null && !roleAliasDescription.getCredentialDurationSeconds().equals(getCredentialDurationSeconds())) {
            return false;
        }
        if ((roleAliasDescription.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (roleAliasDescription.getCreationDate() != null && !roleAliasDescription.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((roleAliasDescription.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return roleAliasDescription.getLastModifiedDate() == null || roleAliasDescription.getLastModifiedDate().equals(getLastModifiedDate());
    }
}
