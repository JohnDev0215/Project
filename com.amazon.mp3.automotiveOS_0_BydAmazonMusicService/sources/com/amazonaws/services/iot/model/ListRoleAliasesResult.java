package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListRoleAliasesResult implements Serializable {
    private String nextMarker;
    private List<String> roleAliases;

    public List<String> getRoleAliases() {
        return this.roleAliases;
    }

    public void setRoleAliases(Collection<String> collection) {
        if (collection == null) {
            this.roleAliases = null;
        } else {
            this.roleAliases = new ArrayList(collection);
        }
    }

    public ListRoleAliasesResult withRoleAliases(String... strArr) {
        if (getRoleAliases() == null) {
            this.roleAliases = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.roleAliases.add(str);
        }
        return this;
    }

    public ListRoleAliasesResult withRoleAliases(Collection<String> collection) {
        setRoleAliases(collection);
        return this;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public ListRoleAliasesResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleAliases() != null) {
            sb.append("roleAliases: " + getRoleAliases() + ",");
        }
        if (getNextMarker() != null) {
            sb.append("nextMarker: " + getNextMarker());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getRoleAliases() == null ? 0 : getRoleAliases().hashCode()) + 31) * 31) + (getNextMarker() != null ? getNextMarker().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListRoleAliasesResult)) {
            return false;
        }
        ListRoleAliasesResult listRoleAliasesResult = (ListRoleAliasesResult) obj;
        if ((listRoleAliasesResult.getRoleAliases() == null) ^ (getRoleAliases() == null)) {
            return false;
        }
        if (listRoleAliasesResult.getRoleAliases() != null && !listRoleAliasesResult.getRoleAliases().equals(getRoleAliases())) {
            return false;
        }
        if ((listRoleAliasesResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listRoleAliasesResult.getNextMarker() == null || listRoleAliasesResult.getNextMarker().equals(getNextMarker());
    }
}
