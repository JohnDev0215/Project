package com.ecarx.xui.adaptapi.ota;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class OtaSession implements IOtaSession {
    private static final String TAG = "neuapi";
    boolean isInstallationStarted = false;

    @Override // com.ecarx.xui.adaptapi.ota.IOtaSession
    public int getOtaPriority() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.ota.IOtaSession
    public boolean isInstallationStarted() {
        Log.d(TAG, "isInstallationStarted = " + this.isInstallationStarted);
        return this.isInstallationStarted;
    }

    @Override // com.ecarx.xui.adaptapi.ota.IOtaSession
    public boolean couldBeginInstallRightNow() {
        Log.d(TAG, "couldBeginInstallRightNow = true");
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.ota.IOtaSession
    public boolean isRecoveryOta() {
        Log.d(TAG, "isRecoveryOta = true");
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.ota.IOtaSession
    public int getOtaProgress() {
        Log.d(TAG, "getOtaProgress = 0");
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.ota.IOtaSession
    public boolean ifSystemWillRebootAfterOta() {
        Log.d(TAG, "ifSystemWillRebootAfterOta = true");
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.ota.IOtaSession
    public boolean cancel() {
        Log.d(TAG, "cancel = false");
        return false;
    }

    public void setInstallationStatus(boolean z) {
        this.isInstallationStarted = z;
    }
}
