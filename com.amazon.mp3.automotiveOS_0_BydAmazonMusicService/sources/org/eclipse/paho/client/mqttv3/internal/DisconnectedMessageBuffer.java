package org.eclipse.paho.client.mqttv3.internal;

import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.BufferedMessage;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class DisconnectedMessageBuffer implements Runnable {
    private final String CLASS_NAME;
    private final Object bufLock;
    private ArrayList<BufferedMessage> buffer;
    private DisconnectedBufferOptions bufferOpts;
    private IDisconnectedBufferCallback callback;
    private Logger log;
    private IDiscardedBufferMessageCallback messageDiscardedCallBack;
    private int mycount;

    public DisconnectedMessageBuffer(DisconnectedBufferOptions disconnectedBufferOptions) {
        String name = DisconnectedMessageBuffer.class.getName();
        this.CLASS_NAME = name;
        this.log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
        this.bufLock = new Object();
        this.mycount = 0;
        this.bufferOpts = disconnectedBufferOptions;
        this.buffer = new ArrayList<>();
    }

    public void putMessage(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (mqttToken != null) {
            mqttWireMessage.setToken(mqttToken);
            mqttToken.internalTok.setMessageID(mqttWireMessage.getMessageId());
        }
        BufferedMessage bufferedMessage = new BufferedMessage(mqttWireMessage, mqttToken);
        synchronized (this.bufLock) {
            if (this.buffer.size() < this.bufferOpts.getBufferSize()) {
                this.buffer.add(bufferedMessage);
            } else if (this.bufferOpts.isDeleteOldestMessages()) {
                if (this.messageDiscardedCallBack != null) {
                    this.messageDiscardedCallBack.messageDiscarded(this.buffer.get(0).getMessage());
                }
                this.buffer.remove(0);
                this.buffer.add(bufferedMessage);
            } else {
                throw new MqttException(32203);
            }
        }
    }

    public BufferedMessage getMessage(int i) {
        BufferedMessage bufferedMessage;
        synchronized (this.bufLock) {
            bufferedMessage = this.buffer.get(i);
        }
        return bufferedMessage;
    }

    public void deleteMessage(int i) {
        synchronized (this.bufLock) {
            this.buffer.remove(i);
        }
    }

    public int getMessageCount() {
        int size;
        synchronized (this.bufLock) {
            size = this.buffer.size();
        }
        return size;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.log.fine(this.CLASS_NAME, "run", "516");
        while (getMessageCount() > 0) {
            try {
                this.callback.publishBufferedMessage(getMessage(0));
                deleteMessage(0);
            } catch (MqttException e) {
                if (e.getReasonCode() == 32202) {
                    try {
                        Thread.sleep(100L);
                    } catch (Exception unused) {
                    }
                } else {
                    this.log.severe(this.CLASS_NAME, "run", "519", new Object[]{Integer.valueOf(e.getReasonCode()), e.getMessage()});
                    return;
                }
            }
        }
    }

    public void setPublishCallback(IDisconnectedBufferCallback iDisconnectedBufferCallback) {
        this.callback = iDisconnectedBufferCallback;
    }

    public boolean isPersistBuffer() {
        return this.bufferOpts.isPersistBuffer();
    }

    public void setMessageDiscardedCallBack(IDiscardedBufferMessageCallback iDiscardedBufferMessageCallback) {
        this.messageDiscardedCallBack = iDiscardedBufferMessageCallback;
    }
}
