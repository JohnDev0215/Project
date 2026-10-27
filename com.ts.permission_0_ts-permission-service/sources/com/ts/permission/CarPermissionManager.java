package com.ts.permission;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class CarPermissionManager {
    public static final int PERMISSION_DEFAULT = 4;
    public static final int PERMISSION_DENIED = 2;
    public static final int PERMISSION_GRANTED = 1;
    public static final int PERMISSION_INQUIRY = 3;
    protected static final String TAG = "TSPS_TSPermissionManager";
    private static volatile CarPermissionManagerImpl mPermissionManager;

    public abstract int checkPermission(String str, String str2);

    public abstract void handlePackagePostInstall(String str, boolean z);

    public abstract void installPackageByPi(String str, String str2);

    public abstract void installStageByPms(String str, String str2);

    public static CarPermissionManager getPermissionManager(Context context) {
        if (mPermissionManager == null) {
            synchronized (CarPermissionManager.class) {
                if (mPermissionManager == null) {
                    mPermissionManager = new CarPermissionManagerImpl(context);
                }
            }
        }
        return mPermissionManager;
    }
}
