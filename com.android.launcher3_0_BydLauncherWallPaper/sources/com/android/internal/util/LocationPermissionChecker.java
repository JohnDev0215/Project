package com.android.internal.util;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Binder;
import android.os.UserHandle;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes4.dex */
public class LocationPermissionChecker {
    public static final int ERROR_LOCATION_MODE_OFF = 1;
    public static final int ERROR_LOCATION_PERMISSION_MISSING = 2;
    public static final int SUCCEEDED = 0;
    private static final String TAG = "LocationPermissionChecker";
    private final AppOpsManager mAppOpsManager;
    private final Context mContext;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LocationPermissionCheckStatus {
    }

    public LocationPermissionChecker(Context context) {
        this.mContext = context;
        this.mAppOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
    }

    public boolean checkLocationPermission(String str, String str2, int i, String str3) {
        return checkLocationPermissionInternal(str, str2, i, str3) == 0;
    }

    public int checkLocationPermissionWithDetailInfo(String str, String str2, int i, String str3) {
        int iCheckLocationPermissionInternal = checkLocationPermissionInternal(str, str2, i, str3);
        if (iCheckLocationPermissionInternal == 1) {
            Log.e(TAG, "Location mode is disabled for the device");
        } else if (iCheckLocationPermissionInternal == 2) {
            Log.e(TAG, "UID " + i + " has no location permission");
        }
        return iCheckLocationPermissionInternal;
    }

    public void enforceLocationPermission(String str, String str2, int i, String str3) throws SecurityException {
        int iCheckLocationPermissionInternal = checkLocationPermissionInternal(str, str2, i, str3);
        if (iCheckLocationPermissionInternal == 1) {
            throw new SecurityException("Location mode is disabled for the device");
        }
        if (iCheckLocationPermissionInternal == 2) {
            throw new SecurityException("UID " + i + " has no location permission");
        }
    }

    private int checkLocationPermissionInternal(String str, String str2, int i, String str3) {
        checkPackage(i, str);
        if (isLocationModeEnabled()) {
            return !checkCallersLocationPermission(str, str2, i, true, str3) ? 2 : 0;
        }
        return 1;
    }

    public boolean checkCallersLocationPermission(String str, String str2, int i, boolean z, String str3) {
        boolean zIsTargetSdkLessThan = isTargetSdkLessThan(str, 29, i);
        if (getUidPermission((z && zIsTargetSdkLessThan) ? Manifest.permission.ACCESS_COARSE_LOCATION : Manifest.permission.ACCESS_FINE_LOCATION, i) == -1) {
            return false;
        }
        if (noteAppOpAllowed(AppOpsManager.OPSTR_FINE_LOCATION, str, str2, i, str3)) {
            return true;
        }
        if (z && zIsTargetSdkLessThan) {
            return noteAppOpAllowed(AppOpsManager.OPSTR_COARSE_LOCATION, str, str2, i, str3);
        }
        return false;
    }

    public boolean isLocationModeEnabled() {
        try {
            return ((LocationManager) this.mContext.getSystemService("location")).isLocationEnabledForUser(UserHandle.of(getCurrentUser()));
        } catch (Exception e) {
            Log.e(TAG, "Failure to get location mode via API, falling back to settings", e);
            return false;
        }
    }

    private boolean isTargetSdkLessThan(String str, int i, int i2) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (this.mContext.getPackageManager().getApplicationInfoAsUser(str, 0, UserHandle.getUserHandleForUid(i2)).targetSdkVersion < i) {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                return true;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            throw th;
        }
        Binder.restoreCallingIdentity(jClearCallingIdentity);
        return false;
    }

    private boolean noteAppOpAllowed(String str, String str2, String str3, int i, String str4) {
        return this.mAppOpsManager.noteOp(str, i, str2, str3, str4) == 0;
    }

    private void checkPackage(int i, String str) throws SecurityException {
        if (str == null) {
            throw new SecurityException("Checking UID " + i + " but Package Name is Null");
        }
        this.mAppOpsManager.checkPackage(i, str);
    }

    protected int getCurrentUser() {
        return ActivityManager.getCurrentUser();
    }

    private int getUidPermission(String str, int i) {
        return this.mContext.checkPermission(str, -1, i);
    }
}
