package android.telephony;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.location.LocationManager;
import android.os.Binder;
import android.os.UserHandle;
import android.util.Log;
import android.widget.Toast;
import com.android.internal.telephony.util.TelephonyUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class LocationAccessPolicy {
    private static final boolean DBG = false;
    public static final int MAX_SDK_FOR_ANY_ENFORCEMENT = 10000;
    private static final String TAG = "LocationAccessPolicy";

    public enum LocationPermissionResult {
        ALLOWED,
        DENIED_SOFT,
        DENIED_HARD
    }

    public static class LocationPermissionQuery {
        public final String callingFeatureId;
        public final String callingPackage;
        public final int callingPid;
        public final int callingUid;
        public final boolean logAsInfo;
        public final String method;
        public final int minSdkVersionForCoarse;
        public final int minSdkVersionForFine;

        private LocationPermissionQuery(String str, String str2, int i, int i2, int i3, int i4, boolean z, String str3) {
            this.callingPackage = str;
            this.callingFeatureId = str2;
            this.callingUid = i;
            this.callingPid = i2;
            this.minSdkVersionForCoarse = i3;
            this.minSdkVersionForFine = i4;
            this.logAsInfo = z;
            this.method = str3;
        }

        public static class Builder {
            private String mCallingFeatureId;
            private String mCallingPackage;
            private int mCallingPid;
            private int mCallingUid;
            private String mMethod;
            private int mMinSdkVersionForCoarse = Integer.MAX_VALUE;
            private int mMinSdkVersionForFine = Integer.MAX_VALUE;
            private boolean mLogAsInfo = false;

            public Builder setCallingPackage(String str) {
                this.mCallingPackage = str;
                return this;
            }

            public Builder setCallingFeatureId(String str) {
                this.mCallingFeatureId = str;
                return this;
            }

            public Builder setCallingUid(int i) {
                this.mCallingUid = i;
                return this;
            }

            public Builder setCallingPid(int i) {
                this.mCallingPid = i;
                return this;
            }

            public Builder setMinSdkVersionForCoarse(int i) {
                this.mMinSdkVersionForCoarse = i;
                return this;
            }

            public Builder setMinSdkVersionForFine(int i) {
                this.mMinSdkVersionForFine = i;
                return this;
            }

            public Builder setMethod(String str) {
                this.mMethod = str;
                return this;
            }

            public Builder setLogAsInfo(boolean z) {
                this.mLogAsInfo = z;
                return this;
            }

            public LocationPermissionQuery build() {
                return new LocationPermissionQuery(this.mCallingPackage, this.mCallingFeatureId, this.mCallingUid, this.mCallingPid, this.mMinSdkVersionForCoarse, this.mMinSdkVersionForFine, this.mLogAsInfo, this.mMethod);
            }
        }
    }

    private static void logError(Context context, LocationPermissionQuery locationPermissionQuery, String str) {
        if (locationPermissionQuery.logAsInfo) {
            Log.i(TAG, str);
            return;
        }
        Log.e(TAG, str);
        try {
            if (TelephonyUtils.IS_DEBUGGABLE) {
                Toast.makeText(context, str, 0).show();
            }
        } catch (Throwable unused) {
        }
    }

    private static LocationPermissionResult appOpsModeToPermissionResult(int i) {
        if (i == 0) {
            return LocationPermissionResult.ALLOWED;
        }
        if (i == 2) {
            return LocationPermissionResult.DENIED_HARD;
        }
        return LocationPermissionResult.DENIED_SOFT;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getAppOpsString(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            r1 = -1888586689(0xffffffff8f6e743f, float:-1.1756694E-29)
            r2 = 1
            if (r0 == r1) goto L1a
            r1 = -63024214(0xfffffffffc3e53aa, float:-3.9529332E36)
            if (r0 == r1) goto L10
            goto L24
        L10:
            java.lang.String r0 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L24
            r3 = r2
            goto L25
        L1a:
            java.lang.String r0 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L24
            r3 = 0
            goto L25
        L24:
            r3 = -1
        L25:
            if (r3 == 0) goto L2e
            if (r3 == r2) goto L2b
            r3 = 0
            return r3
        L2b:
            java.lang.String r3 = "android:coarse_location"
            return r3
        L2e:
            java.lang.String r3 = "android:fine_location"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.LocationAccessPolicy.getAppOpsString(java.lang.String):java.lang.String");
    }

    private static LocationPermissionResult checkAppLocationPermissionHelper(Context context, LocationPermissionQuery locationPermissionQuery, String str) {
        String str2 = Manifest.permission.ACCESS_FINE_LOCATION.equals(str) ? "fine" : "coarse";
        if (checkManifestPermission(context, locationPermissionQuery.callingPid, locationPermissionQuery.callingUid, str)) {
            int iNoteOpNoThrow = ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteOpNoThrow(getAppOpsString(str), locationPermissionQuery.callingUid, locationPermissionQuery.callingPackage, locationPermissionQuery.callingFeatureId, (String) null);
            if (iNoteOpNoThrow == 0) {
                return LocationPermissionResult.ALLOWED;
            }
            Log.i(TAG, locationPermissionQuery.callingPackage + " is aware of " + str2 + " but the app-ops permission is specifically denied.");
            return appOpsModeToPermissionResult(iNoteOpNoThrow);
        }
        int i = Manifest.permission.ACCESS_FINE_LOCATION.equals(str) ? locationPermissionQuery.minSdkVersionForFine : locationPermissionQuery.minSdkVersionForCoarse;
        if (i > 10000) {
            logError(context, locationPermissionQuery, "Allowing " + locationPermissionQuery.callingPackage + " " + str2 + " because we're not enforcing API " + i + " yet. Please fix this app because it will break in the future. Called from " + locationPermissionQuery.method);
            return null;
        }
        if (!isAppAtLeastSdkVersion(context, locationPermissionQuery.callingPackage, i)) {
            logError(context, locationPermissionQuery, "Allowing " + locationPermissionQuery.callingPackage + " " + str2 + " because it doesn't target API " + i + " yet. Please fix this app. Called from " + locationPermissionQuery.method);
            return null;
        }
        return LocationPermissionResult.DENIED_HARD;
    }

    public static LocationPermissionResult checkLocationPermission(Context context, LocationPermissionQuery locationPermissionQuery) {
        LocationPermissionResult locationPermissionResultCheckAppLocationPermissionHelper;
        LocationPermissionResult locationPermissionResultCheckAppLocationPermissionHelper2;
        if (locationPermissionQuery.callingUid == 1001 || locationPermissionQuery.callingUid == 1000 || locationPermissionQuery.callingUid == 1073 || locationPermissionQuery.callingUid == 0) {
            return LocationPermissionResult.ALLOWED;
        }
        if (checkSystemLocationAccess(context, locationPermissionQuery.callingUid, locationPermissionQuery.callingPid)) {
            return (locationPermissionQuery.minSdkVersionForFine >= Integer.MAX_VALUE || (locationPermissionResultCheckAppLocationPermissionHelper2 = checkAppLocationPermissionHelper(context, locationPermissionQuery, Manifest.permission.ACCESS_FINE_LOCATION)) == null) ? (locationPermissionQuery.minSdkVersionForCoarse >= Integer.MAX_VALUE || (locationPermissionResultCheckAppLocationPermissionHelper = checkAppLocationPermissionHelper(context, locationPermissionQuery, Manifest.permission.ACCESS_COARSE_LOCATION)) == null) ? LocationPermissionResult.ALLOWED : locationPermissionResultCheckAppLocationPermissionHelper : locationPermissionResultCheckAppLocationPermissionHelper2;
        }
        return LocationPermissionResult.DENIED_SOFT;
    }

    private static boolean checkManifestPermission(Context context, int i, int i2, String str) {
        return context.checkPermission(str, i, i2) == 0;
    }

    private static boolean checkSystemLocationAccess(Context context, int i, int i2) {
        if (isLocationModeEnabled(context, UserHandle.getUserHandleForUid(i).getIdentifier())) {
            return isCurrentProfile(context, i) || checkInteractAcrossUsersFull(context, i2, i);
        }
        return false;
    }

    private static boolean isLocationModeEnabled(Context context, int i) {
        LocationManager locationManager = (LocationManager) context.getSystemService(LocationManager.class);
        if (locationManager == null) {
            Log.w(TAG, "Couldn't get location manager, denying location access");
            return false;
        }
        return locationManager.isLocationEnabledForUser(UserHandle.of(i));
    }

    private static boolean checkInteractAcrossUsersFull(Context context, int i, int i2) {
        return checkManifestPermission(context, i, i2, Manifest.permission.INTERACT_ACROSS_USERS_FULL);
    }

    private static boolean isCurrentProfile(Context context, int i) {
        boolean z;
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            if (UserHandle.getUserHandleForUid(i).getIdentifier() == ActivityManager.getCurrentUser()) {
                z = true;
            } else {
                ActivityManager activityManager = (ActivityManager) context.getSystemService(ActivityManager.class);
                if (activityManager != null) {
                    return activityManager.isProfileForeground(UserHandle.getUserHandleForUid(ActivityManager.getCurrentUser()));
                }
                z = false;
            }
            return z;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private static boolean isAppAtLeastSdkVersion(Context context, String str, int i) {
        return context.getPackageManager().getApplicationInfo(str, 0).targetSdkVersion >= i;
    }
}
