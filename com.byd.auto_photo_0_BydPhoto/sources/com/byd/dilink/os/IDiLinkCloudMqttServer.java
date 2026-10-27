package com.byd.dilink.os;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkCloudMqttServer {
    void addListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void handlePingreq();

    int isConnected();

    void notifyNw(int i);

    void pubFidVpuMsg(int i, byte[] bArr, int i2);

    void pubMsg(int i, byte[] bArr, int i2, int i3);

    void registerFidAutoHalEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void registerFidConfigEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void registerFidControlEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void registerFidDiagEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void registerFidInfoEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void registerFidLogEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void registerFidMcuEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void registerFidStatusEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void registerFidStrategyEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void registerFidVpuEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void registerListener(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    boolean registerListenerByFid(Context context, int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void removeListener(IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void sendFidAutoHalMsg(int i, byte[] bArr, int i2);

    void sendFidConfigMsg(int i, byte[] bArr, int i2);

    void sendFidControlMsg(int i, byte[] bArr, int i2);

    void sendFidDiagMsg(int i, byte[] bArr, int i2);

    void sendFidInfoMsg(int i, byte[] bArr, int i2);

    void sendFidLogMsg(int i, byte[] bArr, int i2);

    void sendFidMcuMsg(int i, byte[] bArr, int i2);

    void sendFidStatusMsg(int i, byte[] bArr, int i2);

    void sendFidStrategyMsg(int i, byte[] bArr, int i2);

    void setMqttBrokerConfigure(byte[] bArr, int i);

    void setMqttBrokerConfigureEnv(byte[] bArr, int i);

    void unregisterFidAutoHalEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void unregisterFidConfigEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void unregisterFidControlEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void unregisterFidDiagEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void unregisterFidInfoEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void unregisterFidLogEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void unregisterFidMcuEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void unregisterFidStatusEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void unregisterFidStrategyEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void unregisterFidVpuEvent(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    void unregisterListener(int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);

    boolean unregisterListenerByFid(Context context, int i, IDiLinkCloudMqttListener iDiLinkCloudMqttListener);
}
