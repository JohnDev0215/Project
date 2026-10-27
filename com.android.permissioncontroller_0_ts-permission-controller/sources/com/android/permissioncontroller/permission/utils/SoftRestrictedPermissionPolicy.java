package com.android.permissioncontroller.permission.utils;

/* JADX INFO: loaded from: classes8.dex */
public abstract class SoftRestrictedPermissionPolicy {
    /* JADX WARN: Removed duplicated region for block: B:13:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean shouldShow(android.content.pm.PackageInfo r5, com.android.permissioncontroller.permission.model.Permission r6) {
        /*
            java.lang.String r0 = r6.getName()
            int r1 = r0.hashCode()
            r2 = -406040016(0xffffffffe7cc5230, float:-1.9297577E24)
            r3 = 0
            r4 = 1
            if (r1 == r2) goto L1f
            r2 = 1365911975(0x516a29a7, float:6.2857572E10)
            if (r1 == r2) goto L15
        L14:
            goto L29
        L15:
            java.lang.String r1 = "android.permission.WRITE_EXTERNAL_STORAGE"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L14
            r0 = r4
            goto L2a
        L1f:
            java.lang.String r1 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L14
            r0 = r3
            goto L2a
        L29:
            r0 = -1
        L2a:
            if (r0 == 0) goto L2f
            if (r0 == r4) goto L2f
            return r4
        L2f:
            int r0 = r6.getFlags()
            r0 = r0 & 14336(0x3800, float:2.0089E-41)
            if (r0 == 0) goto L3a
            r0 = r4
            goto L3b
        L3a:
            r0 = r3
        L3b:
            android.content.pm.ApplicationInfo r1 = r5.applicationInfo
            int r1 = r1.targetSdkVersion
            if (r0 != 0) goto L45
            r2 = 29
            if (r1 < r2) goto L46
        L45:
            r3 = r4
        L46:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.utils.SoftRestrictedPermissionPolicy.shouldShow(android.content.pm.PackageInfo, com.android.permissioncontroller.permission.model.Permission):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean shouldShow(com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo r5, java.lang.String r6, int r7) {
        /*
            int r0 = r6.hashCode()
            r1 = -406040016(0xffffffffe7cc5230, float:-1.9297577E24)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L1b
            r1 = 1365911975(0x516a29a7, float:6.2857572E10)
            if (r0 == r1) goto L11
        L10:
            goto L25
        L11:
            java.lang.String r0 = "android.permission.WRITE_EXTERNAL_STORAGE"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L10
            r0 = r3
            goto L26
        L1b:
            java.lang.String r0 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L10
            r0 = r2
            goto L26
        L25:
            r0 = -1
        L26:
            if (r0 == 0) goto L2b
            if (r0 == r3) goto L2b
            return r3
        L2b:
            r0 = r7 & 14336(0x3800, float:2.0089E-41)
            if (r0 == 0) goto L31
            r0 = r3
            goto L32
        L31:
            r0 = r2
        L32:
            if (r0 != 0) goto L3c
            int r1 = r5.getTargetSdkVersion()
            r4 = 29
            if (r1 < r4) goto L3d
        L3c:
            r2 = r3
        L3d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.utils.SoftRestrictedPermissionPolicy.shouldShow(com.android.permissioncontroller.permission.model.livedatatypes.LightPackageInfo, java.lang.String, int):boolean");
    }
}
