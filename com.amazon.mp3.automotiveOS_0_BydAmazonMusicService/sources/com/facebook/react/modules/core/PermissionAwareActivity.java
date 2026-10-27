package com.facebook.react.modules.core;

/* JADX INFO: loaded from: classes6.dex */
public interface PermissionAwareActivity {
    void requestPermissions(String[] strArr, int i, PermissionListener permissionListener);

    boolean shouldShowRequestPermissionRationale(String str);
}
