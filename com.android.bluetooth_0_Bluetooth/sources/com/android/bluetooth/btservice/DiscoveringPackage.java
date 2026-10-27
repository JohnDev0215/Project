package com.android.bluetooth.btservice;

/* JADX INFO: loaded from: classes.dex */
final class DiscoveringPackage {
    private String mPackageName;
    private String mPermission;

    DiscoveringPackage(String packageName, String permission) {
        this.mPackageName = packageName;
        this.mPermission = permission;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getPermission() {
        return this.mPermission;
    }
}
