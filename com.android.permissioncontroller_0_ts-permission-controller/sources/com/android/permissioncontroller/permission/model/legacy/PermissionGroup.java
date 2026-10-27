package com.android.permissioncontroller.permission.model.legacy;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes8.dex */
@Deprecated
public final class PermissionGroup implements Comparable<PermissionGroup> {
    private final String mDeclaringPackage;
    private final int mGranted;
    private final Drawable mIcon;
    private final CharSequence mLabel;
    private final String mName;
    private final PermissionApps mPermApps;
    private final int mTotal;

    PermissionGroup(String name, String declaringPackage, CharSequence label, Drawable icon, int total, int granted, PermissionApps permApps) {
        this.mDeclaringPackage = declaringPackage;
        this.mName = name;
        this.mLabel = label;
        this.mIcon = icon;
        this.mTotal = total;
        this.mGranted = granted;
        this.mPermApps = permApps;
    }

    public String getName() {
        return this.mName;
    }

    public String getDeclaringPackage() {
        return this.mDeclaringPackage;
    }

    public CharSequence getLabel() {
        return this.mLabel;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public int getTotal() {
        return this.mTotal;
    }

    public int getGranted() {
        return this.mGranted;
    }

    @Override // java.lang.Comparable
    public int compareTo(PermissionGroup another) {
        return this.mLabel.toString().compareTo(another.mLabel.toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PermissionGroup other = (PermissionGroup) obj;
        String str = this.mName;
        if (str == null) {
            if (other.mName != null) {
                return false;
            }
        } else if (!str.equals(other.mName)) {
            return false;
        }
        if (this.mTotal == other.mTotal && this.mGranted == other.mGranted) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.mName;
        return (str != null ? str.hashCode() + this.mTotal : this.mTotal) + this.mGranted;
    }
}
