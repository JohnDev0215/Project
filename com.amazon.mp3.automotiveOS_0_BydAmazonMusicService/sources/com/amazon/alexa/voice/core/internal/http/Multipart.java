package com.amazon.alexa.voice.core.internal.http;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes2.dex */
public final class Multipart {
    private final byte[] data;
    private int lastPartPosition;
    private final int offset;
    private final byte[] separatorEnd;
    private final byte[] separatorStart;
    private final int size;

    public Multipart(byte[] bArr, int i, int i2, byte[] bArr2) {
        this.data = bArr;
        this.offset = i;
        this.size = i2;
        this.separatorStart = createSeparatorStart(bArr2);
        this.separatorEnd = createSeparatorEnd(bArr2);
    }

    public Multipart(byte[] bArr, byte[] bArr2) {
        this(bArr, 0, bArr.length, bArr2);
    }

    public Multipart(byte[] bArr, String str) {
        this(bArr, str.getBytes());
    }

    private byte[] createSeparatorStart(byte[] bArr) {
        int length = bArr.length + 4;
        byte[] bArr2 = new byte[length];
        bArr2[0] = 45;
        bArr2[1] = 45;
        bArr2[length - 2] = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
        bArr2[length - 1] = 10;
        System.arraycopy(bArr, 0, bArr2, 2, bArr.length);
        return bArr2;
    }

    private byte[] createSeparatorEnd(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 4];
        bArr2[0] = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
        bArr2[1] = 10;
        bArr2[2] = 45;
        bArr2[3] = 45;
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        return bArr2;
    }

    public Part nextPart() {
        int iFindBytes;
        int iSkip = skip(findBytes(this.lastPartPosition, this.separatorStart), this.separatorStart.length);
        if (iSkip == -1 || (iFindBytes = findBytes(iSkip, this.separatorEnd)) == -1) {
            return null;
        }
        this.lastPartPosition = iFindBytes;
        return new Part(this.data, iSkip, iFindBytes - iSkip);
    }

    private int skip(int i, int i2) {
        int i3;
        if (i != -1 && (i3 = i + i2) <= this.offset + this.size) {
            return i3;
        }
        return -1;
    }

    private int findBytes(int i, byte[] bArr) {
        int i2 = this.offset + this.size;
        int i3 = 0;
        int i4 = -1;
        while (i >= 0 && i < i2) {
            if (this.data[i] == bArr[i3]) {
                if (i4 == -1) {
                    i4 = i;
                }
                i3++;
                if (i3 == bArr.length) {
                    return i4;
                }
            } else if (i4 != -1) {
                i = i4 + 1;
                i3 = 0;
                i4 = -1;
            }
            i++;
        }
        return -1;
    }

    public static class Part {
        final byte[] data;
        final int offset;
        final int size;

        public Part(byte[] bArr, int i, int i2) {
            this.data = bArr;
            this.offset = i;
            this.size = i2;
        }

        public byte[] data() {
            return this.data;
        }

        public int offset() {
            return this.offset;
        }

        public int size() {
            return this.size;
        }
    }
}
