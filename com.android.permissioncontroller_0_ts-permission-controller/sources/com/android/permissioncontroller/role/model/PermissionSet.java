package com.android.permissioncontroller.role.model;

import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class PermissionSet {
    private final String mName;
    private final List<String> mPermissions;

    public PermissionSet(String name, List<String> permissions) {
        this.mName = name;
        this.mPermissions = permissions;
    }

    public String getName() {
        return this.mName;
    }

    public List<String> getPermissions() {
        return this.mPermissions;
    }

    public String toString() {
        return "PermissionSet{mName='" + this.mName + "', mPermissions=" + this.mPermissions + '}';
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        PermissionSet that = (PermissionSet) object;
        if (Objects.equals(this.mName, that.mName) && Objects.equals(this.mPermissions, that.mPermissions)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.mName, this.mPermissions);
    }
}
