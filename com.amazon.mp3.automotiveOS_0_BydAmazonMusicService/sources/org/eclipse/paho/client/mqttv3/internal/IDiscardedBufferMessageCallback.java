package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes6.dex */
public interface IDiscardedBufferMessageCallback {
    void messageDiscarded(MqttWireMessage mqttWireMessage);
}
