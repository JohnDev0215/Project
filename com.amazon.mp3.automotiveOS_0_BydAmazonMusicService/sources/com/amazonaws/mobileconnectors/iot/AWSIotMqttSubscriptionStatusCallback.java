package com.amazonaws.mobileconnectors.iot;

/* JADX INFO: loaded from: classes5.dex */
public interface AWSIotMqttSubscriptionStatusCallback {
    void onFailure(Throwable th);

    void onSuccess();
}
