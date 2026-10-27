package com.amazonaws.mobileconnectors.iot;

/* JADX INFO: loaded from: classes5.dex */
class AWSIotMqttTopic {
    private AWSIotMqttNewMessageCallback callback;
    private AWSIotMqttQos qos;
    private String topic;

    public AWSIotMqttTopic(String str, AWSIotMqttQos aWSIotMqttQos, AWSIotMqttNewMessageCallback aWSIotMqttNewMessageCallback) {
        this.topic = str;
        this.qos = aWSIotMqttQos;
        this.callback = aWSIotMqttNewMessageCallback;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public AWSIotMqttQos getQos() {
        return this.qos;
    }

    public void setQos(AWSIotMqttQos aWSIotMqttQos) {
        this.qos = aWSIotMqttQos;
    }

    public AWSIotMqttNewMessageCallback getCallback() {
        return this.callback;
    }

    public void setCallback(AWSIotMqttNewMessageCallback aWSIotMqttNewMessageCallback) {
        this.callback = aWSIotMqttNewMessageCallback;
    }
}
