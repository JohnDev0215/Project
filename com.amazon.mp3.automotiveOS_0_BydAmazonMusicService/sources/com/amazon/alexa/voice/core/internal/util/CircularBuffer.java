package com.amazon.alexa.voice.core.internal.util;

/* JADX INFO: loaded from: classes2.dex */
public final class CircularBuffer {
    private final byte[] buffer;
    private final int capacity;
    private volatile int size;

    public CircularBuffer(int i) {
        this.capacity = i;
        this.buffer = new byte[i];
    }

    public boolean empty() {
        return this.size == 0;
    }

    public synchronized int read(byte[] bArr, int i, int i2) {
        if (this.size == 0) {
            return -1;
        }
        int iMin = Math.min(this.size, i2);
        System.arraycopy(this.buffer, 0, bArr, i, iMin);
        int i3 = this.size - iMin;
        if (i3 > 0) {
            byte[] bArr2 = this.buffer;
            System.arraycopy(bArr2, iMin, bArr2, 0, i3);
        }
        this.size = i3;
        return iMin;
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        int i3 = this.capacity;
        if (i2 > i3) {
            i += i2 - i3;
            i2 = i3;
        }
        int i4 = this.size + i2;
        int i5 = this.capacity;
        if (i4 > i5) {
            int i6 = i5 - i2;
            if (i6 > 0) {
                System.arraycopy(this.buffer, this.size - i6, this.buffer, 0, i6);
            }
            this.size = i6;
        }
        System.arraycopy(bArr, i, this.buffer, this.size, i2);
        this.size += i2;
    }
}
