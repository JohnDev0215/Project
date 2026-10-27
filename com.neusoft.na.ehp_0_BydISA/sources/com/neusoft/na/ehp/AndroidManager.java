package com.neusoft.na.ehp;

import android.location.Location;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class AndroidManager {
    public static final String LOGTAG = "AndroidManager";
    private final long mNativeInstance;

    private native void ehpControl(long j, boolean z);

    private native void nativeRegister(long j, IEhpInfoFetchCallBack iEhpInfoFetchCallBack);

    private native void positionReplayControl(long j, boolean z);

    private native void setPosition(long j, long j2, double d, double d2, Double d3, Float f, Float f2);

    private native void setPosition(long j, long j2, double d, double d2, Double d3, Float f, Float f2, int i);

    private native void setSystemStatus(long j, int i);

    private native void trafficServiceStatus(long j, boolean z);

    AndroidManager(long j) {
        this.mNativeInstance = j;
    }

    public void setSystemStatus(int i) {
        long j = this.mNativeInstance;
        if (j != 0) {
            setSystemStatus(j, i);
        } else {
            Log.d(LOGTAG, "[setSystemStatus] mNativeInstance=null");
        }
    }

    public void trafficServiceStatus(boolean z) {
        long j = this.mNativeInstance;
        if (j != 0) {
            trafficServiceStatus(j, z);
        } else {
            Log.d(LOGTAG, "[trafficServiceStatus] mNativeInstance=null");
        }
    }

    public void ehpControl(boolean z) {
        long j = this.mNativeInstance;
        if (j != 0) {
            ehpControl(j, z);
        } else {
            Log.d(LOGTAG, "[ehpControl] mNativeInstance=null");
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

    public void positionReplayControl(boolean z) {
        long j = this.mNativeInstance;
        if (j != 0) {
            positionReplayControl(j, z);
        } else {
            Log.d(LOGTAG, "[positionReplayControl] mNativeInstance=null");
        }
    }

    public void setPosition(long j, double d, double d2, Double d3, Float f, Float f2) {
        setPosition(this.mNativeInstance, j, d, d2, d3, f, f2);
    }

    public void setPosition(long j, double d, double d2, Double d3, Float f, Float f2, int i) {
        setPosition(this.mNativeInstance, j, d, d2, d3, f, f2, i);
    }

    public void setLocation(Location location) {
        setPosition(this.mNativeInstance, location.getTime(), location.getLongitude(), location.getLatitude(), location.hasAltitude() ? Double.valueOf(location.getAltitude()) : null, location.hasBearing() ? Float.valueOf(location.getBearing()) : null, location.hasSpeed() ? Float.valueOf(location.getSpeed()) : null);
    }
}
