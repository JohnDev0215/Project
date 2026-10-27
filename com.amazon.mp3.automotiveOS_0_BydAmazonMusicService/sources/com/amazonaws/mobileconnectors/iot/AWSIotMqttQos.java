package com.amazonaws.mobileconnectors.iot;

/* JADX INFO: loaded from: classes5.dex */
public enum AWSIotMqttQos {
    QOS0,
    QOS1;

    public int asInt() {
        return this == QOS0 ? 0 : 1;
    }
}
