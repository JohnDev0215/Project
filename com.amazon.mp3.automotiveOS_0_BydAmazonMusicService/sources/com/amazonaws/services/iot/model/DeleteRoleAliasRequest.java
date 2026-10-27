package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteRoleAliasRequest extends AmazonWebServiceRequest implements Serializable {
    private String roleAlias;

    public String getRoleAlias() {
        return this.roleAlias;
    }

    public void setRoleAlias(String str) {
        this.roleAlias = str;
    }

    public DeleteRoleAliasRequest withRoleAlias(String str) {
        this.roleAlias = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleAlias() != null) {
            sb.append("roleAlias: " + getRoleAlias());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getRoleAlias() == null ? 0 : getRoleAlias().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteRoleAliasRequest)) {
            return false;
        }
        DeleteRoleAliasRequest deleteRoleAliasRequest = (DeleteRoleAliasRequest) obj;
        if ((deleteRoleAliasRequest.getRoleAlias() == null) ^ (getRoleAlias() == null)) {
            return false;
        }
        return deleteRoleAliasRequest.getRoleAlias() == null || deleteRoleAliasRequest.getRoleAlias().equals(getRoleAlias());
    }
}
