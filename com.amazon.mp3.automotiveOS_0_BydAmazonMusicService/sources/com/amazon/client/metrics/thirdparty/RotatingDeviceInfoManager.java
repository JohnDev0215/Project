package com.amazon.client.metrics.thirdparty;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.amazon.device.utils.thirdparty.DeviceUtil;

/* JADX INFO: loaded from: classes2.dex */
public class RotatingDeviceInfoManager extends OverridingDeviceInfoManager {
    private long mExpirationPeriodMillis;

    public RotatingDeviceInfoManager(Context context, DeviceUtil deviceUtil, String str, String str2, String str3) {
        super(context, deviceUtil, str, str2, str3, false);
        this.mExpirationPeriodMillis = 86400000L;
    }

    @Override // com.amazon.client.metrics.thirdparty.OverridingDeviceInfoManager, com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager
    public String getSessionID() {
        ensureSharedPrefsRotationUpToDate();
        return super.getSessionID();
    }

    @Override // com.amazon.client.metrics.thirdparty.OverridingDeviceInfoManager, com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager
    protected String getDeviceSerialNumber() {
        ensureSharedPrefsRotationUpToDate();
        return super.getDeviceSerialNumber();
    }

    @Override // com.amazon.client.metrics.thirdparty.OverridingDeviceInfoManager, com.amazon.client.metrics.thirdparty.AndroidDeviceInfoManager
    public String getCustomerID() {
        ensureSharedPrefsRotationUpToDate();
        return super.getCustomerID();
    }

    private void ensureSharedPrefsRotationUpToDate() {
        if (System.currentTimeMillis() - getLastRotationTime() > this.mExpirationPeriodMillis) {
            this.mCachedDSN = null;
            SharedPreferences.Editor editorEdit = this.mSharedPrefs.edit();
            editorEdit.remove(this.mSharedPrefsDSNKey);
            editorEdit.remove(this.mSharedPrefsSessionIDKey);
            editorEdit.remove(this.mSharedPrefsCustomerIDKey);
            editorEdit.putLong("lastDSNRotationTime", System.currentTimeMillis());
            editorEdit.apply();
            Log.d("rotateCachedIDs", "Rotated the DSN and SessionID reported in non-anonymous metrics");
        }
    }

    private long getLastRotationTime() {
        return this.mSharedPrefs.getLong("lastDSNRotationTime", 0L);
    }
}
