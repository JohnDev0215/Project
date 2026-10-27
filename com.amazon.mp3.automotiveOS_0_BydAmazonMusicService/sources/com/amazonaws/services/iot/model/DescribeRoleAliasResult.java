package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeRoleAliasResult implements Serializable {
    private RoleAliasDescription roleAliasDescription;

    public RoleAliasDescription getRoleAliasDescription() {
        return this.roleAliasDescription;
    }

    public void setRoleAliasDescription(RoleAliasDescription roleAliasDescription) {
        this.roleAliasDescription = roleAliasDescription;
    }

    public DescribeRoleAliasResult withRoleAliasDescription(RoleAliasDescription roleAliasDescription) {
        this.roleAliasDescription = roleAliasDescription;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleAliasDescription() != null) {
            sb.append("roleAliasDescription: " + getRoleAliasDescription());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getRoleAliasDescription() == null ? 0 : getRoleAliasDescription().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeRoleAliasResult)) {
            return false;
        }
        DescribeRoleAliasResult describeRoleAliasResult = (DescribeRoleAliasResult) obj;
        if ((describeRoleAliasResult.getRoleAliasDescription() == null) ^ (getRoleAliasDescription() == null)) {
            return false;
        }
        return describeRoleAliasResult.getRoleAliasDescription() == null || describeRoleAliasResult.getRoleAliasDescription().equals(getRoleAliasDescription());
    }
}
