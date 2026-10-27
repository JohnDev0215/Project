package com.amazon.alexa.voice.core.internal.util;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class SimpleBuffer {
    private byte[] buffer = new byte[16];
    private volatile int position;
    private volatile int size;

    public int size() {
        return this.size;
    }

    public byte[] buffer() {
        return this.buffer;
    }

    public synchronized void reset() {
        this.position = 0;
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        int i3 = this.position + i2;
        byte[] bArr2 = this.buffer;
        if (i3 > bArr2.length) {
            this.buffer = Arrays.copyOf(bArr2, Math.max(bArr2.length << 1, this.position + i2));
        }
        System.arraycopy(bArr, i, this.buffer, this.position, i2);
        this.position += i2;
        if (this.position > this.size) {
            this.size = this.position;
        }
    }

    public synchronized int read(byte[] bArr, int i, int i2) {
        if (this.position == this.size) {
            return -1;
        }
        int iMin = Math.min(this.size - this.position, i2);
        System.arraycopy(this.buffer, this.position, bArr, i, iMin);
        this.position += iMin;
        return iMin;
    }
}
