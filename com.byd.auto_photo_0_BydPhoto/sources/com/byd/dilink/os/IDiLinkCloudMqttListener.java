package com.byd.dilink.os;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkCloudMqttListener {
    void msgReceived(byte[] bArr, int i);

    void notify_mqtt_connect_status(int i);
}
