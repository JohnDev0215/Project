package com.ts.carplay.adapter.wifi;

/* JADX INFO: loaded from: classes.dex */
public interface IWifiAdapterListener {

    public static final class WifiApStationState {
        public static final int WIFI_AP_STA_LEAVE_DEVICE_DISCONNECT = 2;
        public static final int WIFI_AP_STA_STATE_JOIN = 0;
        public static final int WIFI_AP_STA_STATE_LEAVE = 1;
    }

    default void onWifiConnectStatus(String mac, int status) {
    }
}
