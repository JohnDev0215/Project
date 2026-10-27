package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateRoleAliasResult implements Serializable {
    private String roleAlias;
    private String roleAliasArn;

    public String getRoleAlias() {
        return this.roleAlias;
    }

    public void setRoleAlias(String str) {
        this.roleAlias = str;
    }

    public UpdateRoleAliasResult withRoleAlias(String str) {
        this.roleAlias = str;
        return this;
    }

    public String getRoleAliasArn() {
        return this.roleAliasArn;
    }

    public void setRoleAliasArn(String str) {
        this.roleAliasArn = str;
    }

    public UpdateRoleAliasResult withRoleAliasArn(String str) {
        this.roleAliasArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleAlias() != null) {
            sb.append("roleAlias: " + getRoleAlias() + ",");
        }
        if (getRoleAliasArn() != null) {
            sb.append("roleAliasArn: " + getRoleAliasArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getRoleAlias() == null ? 0 : getRoleAlias().hashCode()) + 31) * 31) + (getRoleAliasArn() != null ? getRoleAliasArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateRoleAliasResult)) {
            return false;
        }
        UpdateRoleAliasResult updateRoleAliasResult = (UpdateRoleAliasResult) obj;
        if ((updateRoleAliasResult.getRoleAlias() == null) ^ (getRoleAlias() == null)) {
            return false;
        }
        if (updateRoleAliasResult.getRoleAlias() != null && !updateRoleAliasResult.getRoleAlias().equals(getRoleAlias())) {
            return false;
        }
        if ((updateRoleAliasResult.getRoleAliasArn() == null) ^ (getRoleAliasArn() == null)) {
            return false;
        }
        return updateRoleAliasResult.getRoleAliasArn() == null || updateRoleAliasResult.getRoleAliasArn().equals(getRoleAliasArn());
    }
}
