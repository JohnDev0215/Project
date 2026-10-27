package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX INFO: loaded from: classes6.dex */
public class MqttInputStream extends InputStream {
    private final String CLASS_NAME;
    private ByteArrayOutputStream bais;
    private ClientState clientState;
    private DataInputStream in;
    private final Logger log;
    private byte[] packet;
    private int packetLen;
    private int remLen;

    public MqttInputStream(ClientState clientState, InputStream inputStream) {
        String name = MqttInputStream.class.getName();
        this.CLASS_NAME = name;
        this.log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
        this.clientState = clientState;
        this.in = new DataInputStream(inputStream);
        this.bais = new ByteArrayOutputStream();
        this.remLen = -1;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.in.read();
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    public MqttWireMessage readMqttWireMessage() throws MqttException, IOException {
        try {
            if (this.remLen < 0) {
                this.bais.reset();
                byte b = this.in.readByte();
                this.clientState.notifyReceivedBytes(1);
                byte b2 = (byte) ((b >>> 4) & 15);
                if (b2 < 1 || b2 > 14) {
                    throw ExceptionHelper.createMqttException(32108);
                }
                this.remLen = MqttWireMessage.readMBI(this.in).getValue();
                this.bais.write(b);
                this.bais.write(MqttWireMessage.encodeMBI(this.remLen));
                this.packet = new byte[this.bais.size() + this.remLen];
                this.packetLen = 0;
            }
            if (this.remLen < 0) {
                return null;
            }
            readFully();
            this.remLen = -1;
            byte[] byteArray = this.bais.toByteArray();
            System.arraycopy(byteArray, 0, this.packet, 0, byteArray.length);
            MqttWireMessage mqttWireMessageCreateWireMessage = MqttWireMessage.createWireMessage(this.packet);
            this.log.fine(this.CLASS_NAME, "readMqttWireMessage", "301", new Object[]{mqttWireMessageCreateWireMessage});
            return mqttWireMessageCreateWireMessage;
        } catch (SocketTimeoutException unused) {
            return null;
        }
    }

    private void readFully() throws IOException {
        int size = this.bais.size();
        int i = this.packetLen;
        int i2 = size + i;
        int i3 = this.remLen - i;
        if (i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i4 = 0;
        while (i4 < i3) {
            try {
                int i5 = this.in.read(this.packet, i2 + i4, i3 - i4);
                if (i5 < 0) {
                    throw new EOFException();
                }
                this.clientState.notifyReceivedBytes(i5);
                i4 += i5;
            } catch (SocketTimeoutException e) {
                this.packetLen += i4;
                throw e;
            }
        }
    }
}
