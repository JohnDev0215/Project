package com.ecarx.xui.adaptapi.bt.hid;

/* JADX INFO: loaded from: classes2.dex */
public interface IHid {
    String getHidConnectedAddress();

    int getHidConnectionState();

    boolean isHidConnected();

    boolean isHidServiceReady();

    boolean registerHidCallback(IHidCallback iHidCallback);

    boolean reqHidConnect(String str);

    boolean reqHidDisconnect(String str);

    boolean reqSendHidMouseCommand(int i, int i2, int i3, int i4);

    boolean reqSendHidVirtualKeyCommand(int i, int i2);

    boolean unregisterHidCallback(IHidCallback iHidCallback);
}
