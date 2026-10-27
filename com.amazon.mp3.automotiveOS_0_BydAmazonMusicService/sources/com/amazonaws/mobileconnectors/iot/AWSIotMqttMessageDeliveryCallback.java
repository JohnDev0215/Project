package com.amazonaws.mobileconnectors.iot;

/* JADX INFO: loaded from: classes5.dex */
public interface AWSIotMqttMessageDeliveryCallback {

    public enum MessageDeliveryStatus {
        Success,
        Fail
    }

    void statusChanged(MessageDeliveryStatus messageDeliveryStatus, Object obj);
}
