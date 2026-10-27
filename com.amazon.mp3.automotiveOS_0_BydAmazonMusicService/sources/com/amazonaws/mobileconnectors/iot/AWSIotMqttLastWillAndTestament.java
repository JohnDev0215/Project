package com.amazonaws.mobileconnectors.iot;

/* JADX INFO: loaded from: classes5.dex */
public class AWSIotMqttLastWillAndTestament {
    private String message;
    private AWSIotMqttQos qos;
    private String topic;

    public AWSIotMqttLastWillAndTestament(String str, String str2, AWSIotMqttQos aWSIotMqttQos) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("LWT topic cannot be null");
        }
        if (str2 == null) {
            throw new IllegalArgumentException("LWT message cannot be null");
        }
        if (aWSIotMqttQos == null) {
            throw new IllegalArgumentException("LWT QoS cannot be null");
        }
        this.topic = str;
        this.message = str2;
        this.qos = aWSIotMqttQos;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getMessage() {
        return this.message;
    }

    public AWSIotMqttQos getQos() {
        return this.qos;
    }
}
