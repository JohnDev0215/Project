package org.eclipse.paho.client.mqttv3.internal.wire;

/* JADX INFO: loaded from: classes6.dex */
public class MultiByteInteger {
    private int length;
    private int value;

    public MultiByteInteger(int i) {
        this(i, -1);
    }

    public MultiByteInteger(int i, int i2) {
        this.value = i;
        this.length = i2;
    }

    public int getEncodedLength() {
        return this.length;
    }

    public int getValue() {
        return this.value;
    }
}
