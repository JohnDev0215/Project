package com.amazon.avod.media.playback.util;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class CircularByteBuffer {
    private final byte[] mBuffer;
    private int mReadPosition = 0;
    private int mWritePosition = 0;
    private int mBytesInBuffer = 0;

    public static CircularByteBuffer allocate(int i) {
        return new CircularByteBuffer(i);
    }

    private CircularByteBuffer(int i) {
        this.mBuffer = new byte[i];
    }

    public CircularByteBuffer put(byte[] bArr, int i, int i2) {
        if (remaining() < i2) {
            throw new BufferOverflowException();
        }
        int iMin = Math.min(i2, this.mBuffer.length - this.mWritePosition);
        System.arraycopy(bArr, i, this.mBuffer, this.mWritePosition, iMin);
        this.mWritePosition += iMin;
        int i3 = i2 - iMin;
        if (i3 > 0) {
            System.arraycopy(bArr, i + iMin, this.mBuffer, 0, i3);
            this.mWritePosition = i3;
        }
        this.mBytesInBuffer += i2;
        return this;
    }

    public CircularByteBuffer put(ByteBuffer byteBuffer, int i) {
        if (remaining() < i) {
            throw new BufferOverflowException();
        }
        int iMin = Math.min(i, this.mBuffer.length - this.mWritePosition);
        byteBuffer.get(this.mBuffer, this.mWritePosition, iMin);
        this.mWritePosition += iMin;
        int i2 = i - iMin;
        if (i2 > 0) {
            byteBuffer.get(this.mBuffer, 0, i2);
            this.mWritePosition = i2;
        }
        this.mBytesInBuffer += i;
        return this;
    }

    public CircularByteBuffer get(ByteBuffer byteBuffer, int i) {
        if (position() < i) {
            throw new BufferUnderflowException();
        }
        int iMin = Math.min(i, this.mBuffer.length - this.mReadPosition);
        byteBuffer.put(this.mBuffer, this.mReadPosition, iMin);
        this.mReadPosition += iMin;
        int i2 = i - iMin;
        if (i2 > 0) {
            byteBuffer.put(this.mBuffer, 0, i2);
            this.mReadPosition = i2;
        }
        this.mBytesInBuffer -= i;
        return this;
    }

    public CircularByteBuffer clear() {
        this.mReadPosition = 0;
        this.mWritePosition = 0;
        this.mBytesInBuffer = 0;
        return this;
    }

    public int capacity() {
        return this.mBuffer.length;
    }

    public int position() {
        return this.mBytesInBuffer;
    }

    public int remaining() {
        return capacity() - position();
    }
}
