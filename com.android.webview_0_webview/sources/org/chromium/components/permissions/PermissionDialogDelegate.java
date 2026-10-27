package org.chromium.components.permissions;

import J.N;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PermissionDialogDelegate {
    public long a;
    public PermissionDialogController b;
    public WindowAndroid c;
    public int d;
    public String e;
    public String f;
    public String g;
    public PermissionsClient h;
    public int[] i;

    public PermissionDialogDelegate(long j, WindowAndroid windowAndroid, int[] iArr, int i, String str, String str2, String str3, PermissionsClient permissionsClient) {
        this.a = j;
        this.c = windowAndroid;
        this.i = iArr;
        this.d = i;
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.h = permissionsClient;
    }

    public static PermissionDialogDelegate create(long j, WindowAndroid windowAndroid, int[] iArr, int i, String str, String str2, String str3, PermissionsClient permissionsClient) {
        return new PermissionDialogDelegate(j, windowAndroid, iArr, i, str, str2, str3, permissionsClient);
    }

    private void dismissFromNative() {
        PermissionDialogController permissionDialogController = this.b;
        if (permissionDialogController.b == this) {
            permissionDialogController.b = null;
            if (permissionDialogController.e == 2) {
                permissionDialogController.c.b(permissionDialogController.a, 4);
            }
        } else {
            permissionDialogController.d.remove(this);
        }
        N.MLMIuACo(this.a, this);
        this.a = 0L;
    }
}
