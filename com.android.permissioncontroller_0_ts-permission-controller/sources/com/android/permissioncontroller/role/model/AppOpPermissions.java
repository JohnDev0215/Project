package com.android.permissioncontroller.role.model;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.android.permissioncontroller.permission.utils.ArrayUtils;
import com.android.permissioncontroller.role.utils.PackageUtils;

/* JADX INFO: loaded from: classes8.dex */
public class AppOpPermissions {
    private AppOpPermissions() {
    }

    public static boolean grant(String packageName, String appOpPermission, Context context) {
        PackageInfo packageInfo = PackageUtils.getPackageInfo(packageName, 4096, context);
        if (packageInfo == null || !ArrayUtils.contains(packageInfo.requestedPermissions, appOpPermission)) {
            return false;
        }
        String appOp = AppOpsManager.permissionToOp(appOpPermission);
        return setAppOpMode(packageName, appOp, 0, context);
    }

    public static boolean revoke(String packageName, String appOpPermission, Context context) {
        String appOp = AppOpsManager.permissionToOp(appOpPermission);
        int defaultMode = Permissions.getDefaultAppOpMode(appOp);
        return setAppOpMode(packageName, appOp, defaultMode, context);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean setAppOpMode(java.lang.String r1, java.lang.String r2, int r3, android.content.Context r4) {
        /*
            int r0 = r2.hashCode()
            switch(r0) {
                case -2076577416: goto L59;
                case -1531656520: goto L4f;
                case -1212115380: goto L45;
                case -856993554: goto L3b;
                case -405764756: goto L31;
                case 1083108455: goto L27;
                case 1239003751: goto L1d;
                case 1791142635: goto L13;
                case 2127582528: goto L8;
                default: goto L7;
            }
        L7:
            goto L63
        L8:
            java.lang.String r0 = "android:loader_usage_stats"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L7
            r0 = 8
            goto L64
        L13:
            java.lang.String r0 = "android:start_foreground"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L7
            r0 = 4
            goto L64
        L1d:
            java.lang.String r0 = "android:manage_ipsec_tunnels"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L7
            r0 = 5
            goto L64
        L27:
            java.lang.String r0 = "android:instant_app_start_foreground"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L7
            r0 = 6
            goto L64
        L31:
            java.lang.String r0 = "android:request_install_packages"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L7
            r0 = 3
            goto L64
        L3b:
            java.lang.String r0 = "android:write_settings"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L7
            r0 = 2
            goto L64
        L45:
            java.lang.String r0 = "android:interact_across_profiles"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L7
            r0 = 7
            goto L64
        L4f:
            java.lang.String r0 = "android:system_alert_window"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L7
            r0 = 1
            goto L64
        L59:
            java.lang.String r0 = "android:access_notifications"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L7
            r0 = 0
            goto L64
        L63:
            r0 = -1
        L64:
            switch(r0) {
                case 0: goto L6c;
                case 1: goto L6c;
                case 2: goto L6c;
                case 3: goto L6c;
                case 4: goto L6c;
                case 5: goto L6c;
                case 6: goto L6c;
                case 7: goto L6c;
                case 8: goto L6c;
                default: goto L67;
            }
        L67:
            boolean r0 = com.android.permissioncontroller.role.model.Permissions.setAppOpUidMode(r1, r2, r3, r4)
            return r0
        L6c:
            boolean r0 = com.android.permissioncontroller.role.model.Permissions.setAppOpPackageMode(r1, r2, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.role.model.AppOpPermissions.setAppOpMode(java.lang.String, java.lang.String, int, android.content.Context):boolean");
    }
}
