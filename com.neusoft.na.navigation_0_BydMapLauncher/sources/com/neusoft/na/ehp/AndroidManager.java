package com.neusoft.na.ehp;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidManager {
    public static final String LOGTAG = "AndroidManager";
    private final long mNativeInstance;

    private native void nativeRegister(long j, IEhpInfoFetchCallBack iEhpInfoFetchCallBack);

    private native void resetAdasis(long j);

    private native void setCCPWeatherInfo(long j, double d, double d2, long j2, int i);

    private native void setSystemStatus(long j, int i);

    AndroidManager(long j) {
        this.mNativeInstance = j;
    }

    public void setSystemStatus(int i) {
        long j = this.mNativeInstance;
        if (j != 0) {
            setSystemStatus(j, i);
        } else {
            Log.d("AndroidManager", "[setSystemStatus] mNativeInstance=null");
        }
    }

    public void resetAdasis() {
        long j = this.mNativeInstance;
        if (j != 0) {
            resetAdasis(j);
        } else {
            Log.d("AndroidManager", "[resetAdasis] mNativeInstance=null");
        }
    }

    public boolean register(IEhpInfoFetchCallBack iEhpInfoFetchCallBack) {
        long j = this.mNativeInstance;
        if (j == 0) {
            return false;
        }
        nativeRegister(j, iEhpInfoFetchCallBack);
        return true;
    }

    public void setCCPWeatherInfo(double d, double d2, long j, int i) {
        setCCPWeatherInfo(this.mNativeInstance, d, d2, j, i);
    }
}
