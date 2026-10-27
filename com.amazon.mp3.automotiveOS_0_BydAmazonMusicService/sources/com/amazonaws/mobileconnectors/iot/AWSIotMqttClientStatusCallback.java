package com.amazonaws.mobileconnectors.iot;

/* JADX INFO: loaded from: classes5.dex */
public interface AWSIotMqttClientStatusCallback {

    public enum AWSIotMqttClientStatus {
        Connecting,
        Connected,
        ConnectionLost,
        Reconnecting
    }

    void onStatusChanged(AWSIotMqttClientStatus aWSIotMqttClientStatus, Throwable th);
}
