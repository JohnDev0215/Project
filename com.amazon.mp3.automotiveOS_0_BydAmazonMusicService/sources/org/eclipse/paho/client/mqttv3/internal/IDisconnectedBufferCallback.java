package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.BufferedMessage;
import org.eclipse.paho.client.mqttv3.MqttException;

/* JADX INFO: loaded from: classes6.dex */
public interface IDisconnectedBufferCallback {
    void publishBufferedMessage(BufferedMessage bufferedMessage) throws MqttException;
}
