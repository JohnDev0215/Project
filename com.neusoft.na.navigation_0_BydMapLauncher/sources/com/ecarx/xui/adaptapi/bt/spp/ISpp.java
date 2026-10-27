package com.ecarx.xui.adaptapi.bt.spp;

/* JADX INFO: loaded from: classes2.dex */
public interface ISpp {
    boolean isSppConnected(String str);

    boolean isSppServiceReady();

    boolean registerSppCallback(ISppCallback iSppCallback);

    boolean reqSppConnect(String str);

    void reqSppConnectedDeviceAddressList();

    boolean reqSppDisconnect(String str);

    void reqSppSendData(String str, byte[] bArr);

    boolean unregisterSppCallback(ISppCallback iSppCallback);
}
