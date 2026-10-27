package com.neusoft.na.ehpdbuserver;

/* JADX INFO: loaded from: classes.dex */
public class EhpDbuServer {
    private long mNativeInstance = -1;
    private EhpDbuOnMsg mcallBack;

    private native long EhpDbuStart(String str, String str2, String str3);

    private native void EhpDbuStop();

    private native void RegisterDbuMsg(EhpDbuOnMsg ehpDbuOnMsg);

    public native void DownlinkBroadcast(boolean z);

    public native void EhpStopped();

    public native void SetOverwiteAttributeForUSBUpdate(boolean z);

    public native long SetUSBMountedPath(String str, boolean z);

    public native void SetVehiclePosURID(long j);

    public native void StartUSBUpdate();

    static {
        System.loadLibrary("na_ehpdbuserver_jni");
    }

    public synchronized long start(String str, String str2, String str3) {
        long j = this.mNativeInstance;
        if (j == 0) {
            return j;
        }
        long jEhpDbuStart = EhpDbuStart(str, str2, str3);
        this.mNativeInstance = jEhpDbuStart;
        return jEhpDbuStart;
    }

    public boolean register(EhpDbuOnMsg ehpDbuOnMsg) {
        RegisterDbuMsg(ehpDbuOnMsg);
        this.mcallBack = ehpDbuOnMsg;
        return true;
    }

    public synchronized boolean stop() {
        if (this.mNativeInstance != 0) {
            return true;
        }
        EhpDbuStop();
        this.mNativeInstance = -1L;
        return true;
    }

    public synchronized boolean isRunning() {
        return this.mNativeInstance == 0;
    }
}
