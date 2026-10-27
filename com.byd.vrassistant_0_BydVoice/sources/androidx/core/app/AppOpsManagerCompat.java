package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class AppOpsManagerCompat {
    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_ERRORED = 2;
    public static final int MODE_IGNORED = 1;

    private AppOpsManagerCompat() {
    }

    public static String permissionToOp(String permission) {
        return AppOpsManager.permissionToOp(permission);
    }

    public static int noteOp(Context context, String op, int uid, String packageName) {
        return ((AppOpsManager) context.getSystemService("appops")).noteOp(op, uid, packageName);
    }

    public static int noteOpNoThrow(Context context, String op, int uid, String packageName) {
        return ((AppOpsManager) context.getSystemService("appops")).noteOpNoThrow(op, uid, packageName);
    }

    public static int noteProxyOp(Context context, String op, String proxiedPackageName) {
        return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOp(op, proxiedPackageName);
    }

    public static int noteProxyOpNoThrow(Context context, String op, String proxiedPackageName) {
        return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(op, proxiedPackageName);
    }
}
