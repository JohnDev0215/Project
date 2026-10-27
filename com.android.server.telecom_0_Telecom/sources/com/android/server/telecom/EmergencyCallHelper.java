package com.android.server.telecom;

import android.content.Context;
import android.os.UserHandle;
import android.telecom.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.server.telecom.Timeouts;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public class EmergencyCallHelper {
    private final Context mContext;
    private final DefaultDialerCache mDefaultDialerCache;
    private long mLastEmergencyCallTimestampMillis;
    private UserHandle mLocationPermissionGrantedToUser;
    private final Timeouts.Adapter mTimeoutsAdapter;
    private boolean mHadFineLocation = false;
    private boolean mHadBackgroundLocation = false;

    @VisibleForTesting
    public EmergencyCallHelper(Context context, DefaultDialerCache defaultDialerCache, Timeouts.Adapter adapter) {
        this.mContext = context;
        this.mDefaultDialerCache = defaultDialerCache;
        this.mTimeoutsAdapter = adapter;
    }

    void maybeGrantTemporaryLocationPermission(Call call, UserHandle userHandle) {
        if (shouldGrantTemporaryLocationPermission(call)) {
            grantLocationPermission(userHandle);
        }
        if (call == null || !call.isEmergencyCall()) {
            return;
        }
        recordEmergencyCallTime();
    }

    void maybeRevokeTemporaryLocationPermission() {
        if (wasGrantedTemporaryLocationPermission()) {
            revokeLocationPermission();
        }
    }

    long getLastEmergencyCallTimeMillis() {
        return this.mLastEmergencyCallTimestampMillis;
    }

    private void recordEmergencyCallTime() {
        this.mLastEmergencyCallTimestampMillis = System.currentTimeMillis();
    }

    private boolean isInEmergencyCallbackWindow() {
        return System.currentTimeMillis() - getLastEmergencyCallTimeMillis() < this.mTimeoutsAdapter.getEmergencyCallbackWindowMillis(this.mContext.getContentResolver());
    }

    private boolean shouldGrantTemporaryLocationPermission(Call call) {
        if (!this.mContext.getResources().getBoolean(R.bool.grant_location_permission_enabled)) {
            Log.i(this, "ShouldGrantTemporaryLocationPermission, disabled by config", new Object[0]);
            return false;
        }
        if (call == null) {
            Log.i(this, "ShouldGrantTemporaryLocationPermission, no call", new Object[0]);
            return false;
        }
        if (!call.isEmergencyCall() && !isInEmergencyCallbackWindow()) {
            Log.i(this, "ShouldGrantTemporaryLocationPermission, not emergency", new Object[0]);
            return false;
        }
        Log.i(this, "ShouldGrantTemporaryLocationPermission, returning true", new Object[0]);
        return true;
    }

    private void grantLocationPermission(UserHandle userHandle) {
        String systemDialerApplication = this.mDefaultDialerCache.getSystemDialerApplication();
        Log.i(this, "Granting temporary location permission to " + systemDialerApplication + ", user: " + userHandle, new Object[0]);
        try {
            boolean zHasBackgroundLocationPermission = hasBackgroundLocationPermission();
            boolean zHasFineLocationPermission = hasFineLocationPermission();
            if (zHasBackgroundLocationPermission && zHasFineLocationPermission) {
                Log.i(this, "Skipping location grant because the system dialer already holds sufficient permissions", new Object[0]);
                return;
            }
            if (!zHasFineLocationPermission) {
                this.mContext.getPackageManager().grantRuntimePermission(systemDialerApplication, "android.permission.ACCESS_FINE_LOCATION", userHandle);
            }
            if (!zHasBackgroundLocationPermission) {
                this.mContext.getPackageManager().grantRuntimePermission(systemDialerApplication, "android.permission.ACCESS_BACKGROUND_LOCATION", userHandle);
            }
            this.mHadFineLocation = zHasFineLocationPermission;
            this.mHadBackgroundLocation = zHasBackgroundLocationPermission;
            recordPermissionGrant(userHandle);
        } catch (Exception e) {
            Log.e(this, e, "Failed to grant location permissions to " + systemDialerApplication + ", user: " + userHandle, new Object[0]);
        }
    }

    private void revokeLocationPermission() {
        String systemDialerApplication = this.mDefaultDialerCache.getSystemDialerApplication();
        Log.i(this, "Revoking temporary location permission from " + systemDialerApplication + ", user: " + this.mLocationPermissionGrantedToUser, new Object[0]);
        UserHandle userHandle = this.mLocationPermissionGrantedToUser;
        try {
            if (!this.mHadFineLocation) {
                this.mContext.getPackageManager().revokeRuntimePermission(systemDialerApplication, "android.permission.ACCESS_FINE_LOCATION", userHandle);
            }
            if (!this.mHadBackgroundLocation) {
                this.mContext.getPackageManager().revokeRuntimePermission(systemDialerApplication, "android.permission.ACCESS_BACKGROUND_LOCATION", userHandle);
            }
        } catch (Exception e) {
            Log.e(this, e, "Failed to revoke location permission from " + systemDialerApplication + ", user: " + userHandle, new Object[0]);
        }
        clearPermissionGrant();
    }

    private boolean hasBackgroundLocationPermission() {
        return this.mContext.getPackageManager().checkPermission("android.permission.ACCESS_BACKGROUND_LOCATION", this.mDefaultDialerCache.getSystemDialerApplication()) == 0;
    }

    private boolean hasFineLocationPermission() {
        return this.mContext.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", this.mDefaultDialerCache.getSystemDialerApplication()) == 0;
    }

    private void recordPermissionGrant(UserHandle userHandle) {
        this.mLocationPermissionGrantedToUser = userHandle;
    }

    private boolean wasGrantedTemporaryLocationPermission() {
        return this.mLocationPermissionGrantedToUser != null;
    }

    private void clearPermissionGrant() {
        this.mLocationPermissionGrantedToUser = null;
        this.mHadBackgroundLocation = false;
        this.mHadFineLocation = false;
    }
}
