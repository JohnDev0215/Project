package org.chromium.ui.permissions;

/* JADX INFO: loaded from: classes3.dex */
public interface AndroidPermissionDelegate {
    void b(String[] strArr, PermissionCallback permissionCallback);

    boolean canRequestPermission(String str);

    default boolean g(String str) {
        return false;
    }

    boolean h(int i, String[] strArr, int[] iArr);

    boolean hasPermission(String str);

    boolean i(String str);
}
