package com.ts.carplay.lib.interfaces;

/* JADX INFO: loaded from: classes.dex */
public interface ReconnectionFactor {

    public static final class ServiceSessionUpdateReason {
        public static final int NOERROR = 0;
        public static final int RECONNECT = 18;
        public static final int USER_CONNECT = 17;
        public static final int USER_DISCONNECT = 16;
    }

    void onAccessoryTurnOff();

    void onBtPairStateChanged(String str, boolean z);

    void onBtProfileStateChanged(String str, int i, int i2);

    void onBtStateChanged(boolean z);

    void onDisconnect(String str, int i);

    void onProjectionConnectionStateChanged(String str, int i, int i2, int i3);

    void onReconnectionReady();

    void onWifiApStateChanged(boolean z);

    void onWifiDeviceAttach(String str);

    void onWirelessCarPlayUpdate(String str, boolean z);
}
