package com.android.bluetooth;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceWorkArounds {
    public static final String BREZZA_ZDI_CARKIT = "28:a1:83";
    public static final String FORD_SYNC_CARKIT = "00:1E:AE";
    public static final String HONDA_CARKIT = "64:D4:BD";
    public static final String MERCEDES_BENZ_CARKIT = "00:26:e8";
    public static final String PCM_CARKIT = "9C:DF:03";
    public static final String SYNC_CARKIT = "D0:39:72";

    public static boolean addressStartsWith(String bdAddr, String carkitAddr) {
        return bdAddr.toLowerCase().startsWith(carkitAddr.toLowerCase());
    }
}
