package org.chromium.components.permissions;

import J.N;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class PermissionDialogDelegate {
    public long a;
    public PermissionDialogController b;
    public final WindowAndroid c;
    public final int d;
    public final String e;
    public final String f;
    public final String g;
    public final int[] h;

    public PermissionDialogDelegate(long j, WindowAndroid windowAndroid, int[] iArr, int i, String str, String str2, String str3) {
        this.a = j;
        this.c = windowAndroid;
        this.h = iArr;
        this.d = i;
        this.e = str;
        this.f = str2;
        this.g = str3;
    }

    @CalledByNative
    public static PermissionDialogDelegate create(long j, WindowAndroid windowAndroid, int[] iArr, int i, String str, String str2, String str3) {
        return new PermissionDialogDelegate(j, windowAndroid, iArr, i, str, str2, str3);
    }

    @CalledByNative
    public final void dismissFromNative() {
        PermissionDialogController permissionDialogController = this.b;
        if (permissionDialogController.h == this) {
            permissionDialogController.h = null;
            if (permissionDialogController.k == 2) {
                permissionDialogController.i.b(4, permissionDialogController.f);
            }
        } else {
            permissionDialogController.j.remove(this);
        }
        N.MLMIuACo(this.a, this);
        this.a = 0L;
    }
}
