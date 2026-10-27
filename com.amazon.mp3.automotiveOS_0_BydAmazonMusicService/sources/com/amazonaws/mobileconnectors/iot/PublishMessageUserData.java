package com.amazonaws.mobileconnectors.iot;

/* JADX INFO: loaded from: classes5.dex */
class PublishMessageUserData {
    private AWSIotMqttMessageDeliveryCallback userCallback;
    private Object userData;

    PublishMessageUserData(AWSIotMqttMessageDeliveryCallback aWSIotMqttMessageDeliveryCallback, Object obj) {
        this.userCallback = aWSIotMqttMessageDeliveryCallback;
        this.userData = obj;
    }

    AWSIotMqttMessageDeliveryCallback getUserCallback() {
        return this.userCallback;
    }

    Object getUserData() {
        return this.userData;
    }
}
