package org.eclipse.paho.client.mqttv3.internal.wire;

import com.amplifyframework.core.model.ModelIdentifier;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

/* JADX INFO: loaded from: classes6.dex */
public class MqttUnsubscribe extends MqttWireMessage {
    private int count;
    private String[] names;

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public boolean isRetryable() {
        return true;
    }

    public MqttUnsubscribe(String[] strArr) {
        super((byte) 10);
        if (strArr != null) {
            this.names = (String[]) strArr.clone();
        }
    }

    public MqttUnsubscribe(byte b, byte[] bArr) throws IOException {
        super((byte) 10);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        boolean z = false;
        this.count = 0;
        this.names = new String[10];
        while (!z) {
            try {
                this.names[this.count] = MqttWireMessage.decodeUTF8(dataInputStream);
            } catch (Exception unused) {
                z = true;
            }
        }
        dataInputStream.close();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append(" names:[");
        for (int i = 0; i < this.count; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR + this.names[i] + ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte getMessageInfo() {
        return (byte) ((this.duplicate ? 8 : 0) | 2);
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(this.msgId);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte[] getPayload() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            for (String str : this.names) {
                MqttWireMessage.encodeUTF8(dataOutputStream, str);
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }
}
