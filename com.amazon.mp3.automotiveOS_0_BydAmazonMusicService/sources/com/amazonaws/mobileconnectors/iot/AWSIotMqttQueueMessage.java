package com.amazonaws.mobileconnectors.iot;

/* JADX INFO: loaded from: classes5.dex */
class AWSIotMqttQueueMessage {
    private byte[] message;
    private AWSIotMqttQos qos;
    private String topic;
    private PublishMessageUserData userData;

    public AWSIotMqttQueueMessage(String str, byte[] bArr, AWSIotMqttQos aWSIotMqttQos, PublishMessageUserData publishMessageUserData) {
        this.topic = str;
        this.message = bArr;
        this.qos = aWSIotMqttQos;
        this.userData = publishMessageUserData;
    }

    String getTopic() {
        return this.topic;
    }

    byte[] getMessage() {
        return this.message;
    }

    AWSIotMqttQos getQos() {
        return this.qos;
    }

    PublishMessageUserData getUserData() {
        return this.userData;
    }
}
