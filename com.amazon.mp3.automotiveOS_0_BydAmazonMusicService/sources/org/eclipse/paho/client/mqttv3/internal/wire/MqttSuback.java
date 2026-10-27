package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

/* JADX INFO: loaded from: classes6.dex */
public class MqttSuback extends MqttAck {
    private int[] grantedQos;

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() throws MqttException {
        return new byte[0];
    }

    public MqttSuback(byte b, byte[] bArr) throws IOException {
        super((byte) 9);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        this.grantedQos = new int[bArr.length - 2];
        int i = 0;
        for (int i2 = dataInputStream.read(); i2 != -1; i2 = dataInputStream.read()) {
            this.grantedQos[i] = i2;
            i++;
        }
        dataInputStream.close();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttAck, org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append(" granted Qos");
        for (int i : this.grantedQos) {
            stringBuffer.append(" ");
            stringBuffer.append(i);
        }
        return stringBuffer.toString();
    }

    public int[] getGrantedQos() {
        return this.grantedQos;
    }
}
