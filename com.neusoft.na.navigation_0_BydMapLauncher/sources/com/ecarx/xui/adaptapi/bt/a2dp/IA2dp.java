package com.ecarx.xui.adaptapi.bt.a2dp;

/* JADX INFO: loaded from: classes2.dex */
public interface IA2dp {
    String getA2dpConnectedAddress();

    int getA2dpConnectionState();

    boolean isA2dpConnected();

    boolean isA2dpServiceReady();

    void pauseA2dpRender();

    boolean registerA2dpCallback(IA2dpCallback iA2dpCallback);

    boolean reqA2dpConnect(String str);

    boolean reqA2dpDisconnect(String str);

    boolean setA2dpLocalVolume(float f);

    void startA2dpRender();

    boolean unregisterA2dpCallback(IA2dpCallback iA2dpCallback);
}
