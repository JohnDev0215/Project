package com.google.android.projection.protocol;

import com.google.android.projection.protocol.CarWifi;

/* JADX INFO: loaded from: classes.dex */
public class WifiProjectionEndpoint implements CarServiceProvider, WifiProjectionCallbacks {
    private CarWifi mCarWifi;
    private String mCarWifiBssid;
    private long mNativeWifiProjectionEndpoint;

    private native int nativeInit(int i, long j, String str) throws IllegalStateException;

    private native void nativeShutdown();

    public WifiProjectionEndpoint(String carWifiBssid, CarWifi carWifi) {
        this.mCarWifiBssid = carWifiBssid;
        this.mCarWifi = carWifi;
        if (carWifiBssid == null) {
            throw new NullPointerException("Null car wifi BSSID");
        }
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int serviceId, long nativeGalReceiver) {
        return nativeInit(serviceId, nativeGalReceiver, this.mCarWifiBssid) == 0;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        nativeShutdown();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeWifiProjectionEndpoint;
    }

    @Override // com.google.android.projection.protocol.WifiProjectionCallbacks
    public CarWifi.CarWifiCredentials onWifiCredentialsRequest() {
        return this.mCarWifi.getCarWifiCredentials();
    }
}
