package android.content;

import android.app.AppOpsManager;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Binder;
import android.os.Process;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class PermissionChecker {
    public static final int PERMISSION_GRANTED = 0;
    public static final int PERMISSION_HARD_DENIED = -1;
    public static final int PERMISSION_SOFT_DENIED = -2;
    public static final int PID_UNKNOWN = -1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PermissionResult {
    }

    private PermissionChecker() {
    }

    public static int checkPermissionForDataDelivery(Context context, String str, int i, int i2, String str2, String str3, String str4) {
        return checkPermissionCommon(context, str, i, i2, str2, str3, str4, true);
    }

    public static int checkPermissionForPreflight(Context context, String str, int i, int i2, String str2) {
        return checkPermissionCommon(context, str, i, i2, str2, null, null, false);
    }

    public static int checkSelfPermissionForDataDelivery(Context context, String str, String str2) {
        return checkPermissionForDataDelivery(context, str, Process.myPid(), Process.myUid(), context.getPackageName(), context.getAttributionTag(), str2);
    }

    public static int checkSelfPermissionForPreflight(Context context, String str) {
        return checkPermissionForPreflight(context, str, Process.myPid(), Process.myUid(), context.getPackageName());
    }

    public static int checkCallingPermissionForDataDelivery(Context context, String str, String str2, String str3, String str4) {
        if (Binder.getCallingPid() == Process.myPid()) {
            return -1;
        }
        return checkPermissionForDataDelivery(context, str, Binder.getCallingPid(), Binder.getCallingUid(), str2, str3, str4);
    }

    public static int checkCallingPermissionForPreflight(Context context, String str, String str2) {
        if (Binder.getCallingPid() == Process.myPid()) {
            return -1;
        }
        return checkPermissionForPreflight(context, str, Binder.getCallingPid(), Binder.getCallingUid(), str2);
    }

    public static int checkCallingOrSelfPermissionForDataDelivery(Context context, String str, String str2, String str3) {
        String packageName = Binder.getCallingPid() == Process.myPid() ? context.getPackageName() : null;
        if (Binder.getCallingPid() == Process.myPid()) {
            str2 = context.getAttributionTag();
        }
        return checkPermissionForDataDelivery(context, str, Binder.getCallingPid(), Binder.getCallingUid(), packageName, str2, str3);
    }

    public static int checkCallingOrSelfPermissionForPreflight(Context context, String str) {
        return checkPermissionForPreflight(context, str, Binder.getCallingPid(), Binder.getCallingUid(), Binder.getCallingPid() == Process.myPid() ? context.getPackageName() : null);
    }

    static int checkPermissionCommon(Context context, String str, int i, int i2, String str2, String str3, String str4, boolean z) {
        String[] packagesForUid;
        try {
            PermissionInfo permissionInfo = context.getPackageManager().getPermissionInfo(str, 0);
            String str5 = (str2 != null || (packagesForUid = context.getPackageManager().getPackagesForUid(i2)) == null || packagesForUid.length <= 0) ? str2 : packagesForUid[0];
            if (permissionInfo.isAppOp()) {
                return checkAppOpPermission(context, str, i, i2, str5, str3, str4, z);
            }
            if (permissionInfo.isRuntime()) {
                return checkRuntimePermission(context, str, i, i2, str5, str3, str4, z);
            }
            return context.checkPermission(str, i, i2);
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    private static int checkAppOpPermission(Context context, String str, int i, int i2, String str2, String str3, String str4, boolean z) {
        int iUnsafeCheckOpRawNoThrow;
        String strPermissionToOp = AppOpsManager.permissionToOp(str);
        if (strPermissionToOp == null || str2 == null) {
            return -1;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        if (z) {
            iUnsafeCheckOpRawNoThrow = appOpsManager.noteProxyOpNoThrow(strPermissionToOp, str2, i2, str3, str4);
        } else {
            iUnsafeCheckOpRawNoThrow = appOpsManager.unsafeCheckOpRawNoThrow(strPermissionToOp, i2, str2);
        }
        if (iUnsafeCheckOpRawNoThrow != 0) {
            if (iUnsafeCheckOpRawNoThrow == 3) {
                return context.checkPermission(str, i, i2) == 0 ? 0 : -1;
            }
            if (iUnsafeCheckOpRawNoThrow != 4) {
                return -1;
            }
        }
        return 0;
    }

    private static int checkRuntimePermission(Context context, String str, int i, int i2, String str2, String str3, String str4, boolean z) {
        int iUnsafeCheckOpRawNoThrow;
        if (context.checkPermission(str, i, i2) == -1) {
            return -1;
        }
        String strPermissionToOp = AppOpsManager.permissionToOp(str);
        if (strPermissionToOp != null && str2 != null) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
            if (z) {
                iUnsafeCheckOpRawNoThrow = appOpsManager.noteProxyOpNoThrow(strPermissionToOp, str2, i2, str3, str4);
            } else {
                iUnsafeCheckOpRawNoThrow = appOpsManager.unsafeCheckOpRawNoThrow(strPermissionToOp, i2, str2);
            }
            if (iUnsafeCheckOpRawNoThrow != 0 && iUnsafeCheckOpRawNoThrow != 4) {
                return -2;
            }
        }
        return 0;
    }
}
