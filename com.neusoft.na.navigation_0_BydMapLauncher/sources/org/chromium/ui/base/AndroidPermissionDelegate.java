package org.chromium.ui.base;

import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes4.dex */
public interface AndroidPermissionDelegate {
    boolean canRequestPermission(String str);

    boolean hasPermission(String str);

    boolean isPermissionRevokedByPolicy(String str);

    void requestPermissions(String[] strArr, WindowAndroid.PermissionCallback permissionCallback);
}
