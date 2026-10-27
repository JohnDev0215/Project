package org.eclipse.paho.client.mqttv3.internal.wire;

import com.google.firebase.perf.FirebasePerformance;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.jvm.internal.ByteCompanionObject;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;

/* JADX INFO: loaded from: classes6.dex */
public abstract class MqttWireMessage {
    private static final long FOUR_BYTE_INT_MAX = 4294967295L;
    public static final byte MESSAGE_TYPE_CONNACK = 2;
    public static final byte MESSAGE_TYPE_CONNECT = 1;
    public static final byte MESSAGE_TYPE_DISCONNECT = 14;
    public static final byte MESSAGE_TYPE_PINGREQ = 12;
    public static final byte MESSAGE_TYPE_PINGRESP = 13;
    public static final byte MESSAGE_TYPE_PUBACK = 4;
    public static final byte MESSAGE_TYPE_PUBCOMP = 7;
    public static final byte MESSAGE_TYPE_PUBLISH = 3;
    public static final byte MESSAGE_TYPE_PUBREC = 5;
    public static final byte MESSAGE_TYPE_PUBREL = 6;
    public static final byte MESSAGE_TYPE_SUBACK = 9;
    public static final byte MESSAGE_TYPE_SUBSCRIBE = 8;
    public static final byte MESSAGE_TYPE_UNSUBACK = 11;
    public static final byte MESSAGE_TYPE_UNSUBSCRIBE = 10;
    private static final int VARIABLE_BYTE_INT_MAX = 268435455;
    protected boolean duplicate = false;
    protected int msgId = 0;
    private MqttToken token;
    private byte type;
    protected static final Charset STRING_ENCODING = StandardCharsets.UTF_8;
    private static final String[] PACKET_NAMES = {"reserved", FirebasePerformance.HttpMethod.CONNECT, "CONNACK", "PUBLISH", "PUBACK", "PUBREC", "PUBREL", "PUBCOMP", "SUBSCRIBE", "SUBACK", "UNSUBSCRIBE", "UNSUBACK", "PINGREQ", "PINGRESP", "DISCONNECT"};

    protected abstract byte getMessageInfo();

    public byte[] getPayload() throws MqttException {
        return new byte[0];
    }

    protected abstract byte[] getVariableHeader() throws MqttException;

    public boolean isMessageIdRequired() {
        return true;
    }

    public boolean isRetryable() {
        return false;
    }

    public MqttWireMessage(byte b) {
        this.type = b;
    }

    public byte getType() {
        return this.type;
    }

    public int getMessageId() {
        return this.msgId;
    }

    public void setMessageId(int i) {
        this.msgId = i;
    }

    public String getKey() {
        return Integer.toString(getMessageId());
    }

    public byte[] getHeader() throws MqttException {
        try {
            int type = ((getType() & 15) << 4) ^ (getMessageInfo() & 15);
            byte[] variableHeader = getVariableHeader();
            int length = variableHeader.length + getPayload().length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeByte(type);
            dataOutputStream.write(encodeMBI(length));
            dataOutputStream.write(variableHeader);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    public static MqttWireMessage createWireMessage(MqttPersistable mqttPersistable) throws MqttException {
        byte[] payloadBytes = mqttPersistable.getPayloadBytes();
        if (payloadBytes == null) {
            payloadBytes = new byte[0];
        }
        return createWireMessage(new MultiByteArrayInputStream(mqttPersistable.getHeaderBytes(), mqttPersistable.getHeaderOffset(), mqttPersistable.getHeaderLength(), payloadBytes, mqttPersistable.getPayloadOffset(), mqttPersistable.getPayloadLength()));
    }

    public static MqttWireMessage createWireMessage(byte[] bArr) throws MqttException {
        return createWireMessage(new ByteArrayInputStream(bArr));
    }

    private static MqttWireMessage createWireMessage(InputStream inputStream) throws MqttException {
        try {
            CountingInputStream countingInputStream = new CountingInputStream(inputStream);
            DataInputStream dataInputStream = new DataInputStream(countingInputStream);
            int unsignedByte = dataInputStream.readUnsignedByte();
            byte b = (byte) (unsignedByte >> 4);
            byte b2 = (byte) (unsignedByte & 15);
            long counter = (((long) countingInputStream.getCounter()) + ((long) readMBI(dataInputStream).getValue())) - ((long) countingInputStream.getCounter());
            byte[] bArr = new byte[0];
            if (counter > 0) {
                int i = (int) counter;
                byte[] bArr2 = new byte[i];
                dataInputStream.readFully(bArr2, 0, i);
                bArr = bArr2;
            }
            if (b == 1) {
                return new MqttConnect(b2, bArr);
            }
            if (b == 3) {
                return new MqttPublish(b2, bArr);
            }
            if (b == 4) {
                return new MqttPubAck(b2, bArr);
            }
            if (b == 7) {
                return new MqttPubComp(b2, bArr);
            }
            if (b == 2) {
                return new MqttConnack(b2, bArr);
            }
            if (b == 12) {
                return new MqttPingReq(b2, bArr);
            }
            if (b == 13) {
                return new MqttPingResp(b2, bArr);
            }
            if (b == 8) {
                return new MqttSubscribe(b2, bArr);
            }
            if (b == 9) {
                return new MqttSuback(b2, bArr);
            }
            if (b == 10) {
                return new MqttUnsubscribe(b2, bArr);
            }
            if (b == 11) {
                return new MqttUnsubAck(b2, bArr);
            }
            if (b == 6) {
                return new MqttPubRel(b2, bArr);
            }
            if (b == 5) {
                return new MqttPubRec(b2, bArr);
            }
            if (b == 14) {
                return new MqttDisconnect(b2, bArr);
            }
            throw ExceptionHelper.createMqttException(6);
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    public static byte[] encodeMBI(long j) {
        validateVariableByteInt((int) j);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        do {
            byte b = (byte) (j % 128);
            j /= 128;
            if (j > 0) {
                b = (byte) (b | ByteCompanionObject.MIN_VALUE);
            }
            byteArrayOutputStream.write(b);
            i++;
            if (j <= 0) {
                break;
            }
        } while (i < 4);
        return byteArrayOutputStream.toByteArray();
    }

    public static MultiByteInteger readMBI(DataInputStream dataInputStream) throws IOException {
        byte b;
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        do {
            b = dataInputStream.readByte();
            i++;
            i3 += (b & 127) * i2;
            i2 *= 128;
        } while ((b & ByteCompanionObject.MIN_VALUE) != 0);
        if (i3 < 0 || i3 > VARIABLE_BYTE_INT_MAX) {
            throw new IOException("This property must be a number between 0 and 268435455. Read value was: " + i3);
        }
        return new MultiByteInteger(i3, i);
    }

    protected byte[] encodeMessageId() throws MqttException {
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

    public void setDuplicate(boolean z) {
        this.duplicate = z;
    }

    public static void encodeUTF8(DataOutputStream dataOutputStream, String str) throws MqttException {
        validateUTF8String(str);
        try {
            byte[] bytes = str.getBytes(STRING_ENCODING);
            byte length = (byte) ((bytes.length >>> 8) & 255);
            byte length2 = (byte) ((bytes.length >>> 0) & 255);
            dataOutputStream.write(length);
            dataOutputStream.write(length2);
            dataOutputStream.write(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new MqttException(e);
        } catch (IOException e2) {
            throw new MqttException(e2);
        }
    }

    public static String decodeUTF8(DataInputStream dataInputStream) throws MqttException {
        try {
            byte[] bArr = new byte[dataInputStream.readUnsignedShort()];
            dataInputStream.readFully(bArr);
            String str = new String(bArr, STRING_ENCODING);
            validateUTF8String(str);
            return str;
        } catch (IOException e) {
            throw new MqttException(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0057 A[PHI: r1
  0x0057: PHI (r1v6 int) = (r1v1 int), (r1v1 int), (r1v7 int) binds: [B:24:0x004b, B:28:0x0054, B:16:0x0038] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void validateUTF8String(java.lang.String r7) throws java.lang.IllegalArgumentException {
        /*
            r0 = 0
            r1 = r0
        L2:
            int r2 = r7.length()
            if (r1 < r2) goto L9
            return
        L9:
            char r2 = r7.charAt(r1)
            boolean r3 = java.lang.Character.isHighSurrogate(r2)
            r4 = 65534(0xfffe, float:9.1833E-41)
            r5 = 1
            if (r3 == 0) goto L3b
            int r1 = r1 + 1
            int r3 = r7.length()
            if (r1 != r3) goto L20
            goto L59
        L20:
            char r3 = r7.charAt(r1)
            boolean r6 = java.lang.Character.isLowSurrogate(r3)
            if (r6 == 0) goto L2b
            goto L59
        L2b:
            r6 = r2 & 1023(0x3ff, float:1.434E-42)
            int r6 = r6 << 10
            r3 = r3 & 1023(0x3ff, float:1.434E-42)
            r3 = r3 | r6
            r6 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r6
            if (r3 == r6) goto L59
            if (r3 != r4) goto L57
            goto L59
        L3b:
            boolean r3 = java.lang.Character.isISOControl(r2)
            if (r3 != 0) goto L59
            boolean r3 = java.lang.Character.isLowSurrogate(r2)
            if (r3 == 0) goto L48
            goto L59
        L48:
            r3 = 64976(0xfdd0, float:9.1051E-41)
            if (r2 < r3) goto L57
            if (r2 == r4) goto L59
            if (r2 >= r3) goto L59
            r3 = 64991(0xfddf, float:9.1072E-41)
            if (r2 > r3) goto L57
            goto L59
        L57:
            r3 = r0
            goto L5a
        L59:
            r3 = r5
        L5a:
            if (r3 != 0) goto L5e
            int r1 = r1 + r5
            goto L2
        L5e:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.Object[] r1 = new java.lang.Object[r5]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r0] = r2
            java.lang.String r0 = "Invalid UTF-8 char: [%x]"
            java.lang.String r0 = java.lang.String.format(r0, r1)
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage.validateUTF8String(java.lang.String):void");
    }

    public static void validateVariableByteInt(int i) throws IllegalArgumentException {
        if (i < 0 || i > VARIABLE_BYTE_INT_MAX) {
            throw new IllegalArgumentException("This property must be a number between 0 and 268435455");
        }
    }

    public MqttToken getToken() {
        return this.token;
    }

    public void setToken(MqttToken mqttToken) {
        this.token = mqttToken;
    }

    public String toString() {
        return PACKET_NAMES[this.type];
    }
}
