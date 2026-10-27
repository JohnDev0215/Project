package com.google.android.exoplayer2.util;

/* JADX INFO: loaded from: classes6.dex */
public final class CircularByteQueue {
    private int availableCount;
    private int capacity;
    private byte[] data;
    private int readOffset;
    private int writeOffset;

    public CircularByteQueue(int i) {
        int i2 = i + 1;
        this.data = new byte[i2];
        this.capacity = i2;
    }

    public void reset() {
        this.readOffset = 0;
        this.availableCount = 0;
        this.writeOffset = 0;
    }

    public boolean canWrite() {
        return this.availableCount != this.capacity - 1;
    }

    public boolean canRead(int i) {
        return this.availableCount >= i;
    }

    public boolean write(byte b) {
        if (!canWrite()) {
            return false;
        }
        byte[] bArr = this.data;
        int i = this.writeOffset;
        bArr[i] = b;
        this.availableCount++;
        this.writeOffset = (i + 1) % this.capacity;
        return true;
    }

    public int read() {
        if (!canRead(1)) {
            return 0;
        }
        byte[] bArr = this.data;
        int i = this.readOffset;
        int i2 = bArr[i] & 255;
        this.availableCount--;
        this.readOffset = (i + 1) % this.capacity;
        return i2;
    }

    public int size() {
        return this.availableCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("capacity = " + this.capacity + " ,availableCount = " + this.availableCount + ", readOffset = " + this.readOffset + ", writeOffset = " + this.writeOffset);
        for (int i = 0; i < this.data.length; i++) {
            sb.append(" data[" + i + "] = " + ((int) this.data[i]));
        }
        return sb.toString();
    }
}
