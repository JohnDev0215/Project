package org.eclipse.paho.client.mqttv3;

/* JADX INFO: loaded from: classes6.dex */
public interface MqttPersistable {
    byte[] getHeaderBytes() throws MqttPersistenceException;

    int getHeaderLength() throws MqttPersistenceException;

    int getHeaderOffset() throws MqttPersistenceException;

    byte[] getPayloadBytes() throws MqttPersistenceException;

    int getPayloadLength() throws MqttPersistenceException;

    int getPayloadOffset() throws MqttPersistenceException;
}
