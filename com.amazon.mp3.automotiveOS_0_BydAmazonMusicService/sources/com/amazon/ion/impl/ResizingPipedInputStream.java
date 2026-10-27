package com.amazon.ion.impl;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class ResizingPipedInputStream extends InputStream {
    private static final NotificationConsumer NO_OP_NOTIFICATION_CONSUMER = new NotificationConsumer() { // from class: com.amazon.ion.impl.ResizingPipedInputStream.1
        @Override // com.amazon.ion.impl.ResizingPipedInputStream.NotificationConsumer
        public void bytesConsolidatedToStartOfBuffer(int i) {
        }
    };
    private byte[] buffer;
    private ByteBuffer byteBuffer;
    private int capacity;
    private final int initialBufferSize;
    private final int maximumBufferSize;
    private final boolean useBoundary;
    private NotificationConsumer notificationConsumer = NO_OP_NOTIFICATION_CONSUMER;
    private int readIndex = 0;
    private int writeIndex = 0;
    private int available = 0;
    private int size = 0;
    private int boundary = 0;

    interface NotificationConsumer {
        void bytesConsolidatedToStartOfBuffer(int i);
    }

    ResizingPipedInputStream(int i, int i2, boolean z) {
        if (i < 1) {
            throw new IllegalArgumentException("Initial buffer size must be at least 1.");
        }
        if (i2 < i) {
            throw new IllegalArgumentException("Maximum buffer size cannot be less than the initial buffer size.");
        }
        this.initialBufferSize = i;
        this.maximumBufferSize = i2;
        this.capacity = i;
        byte[] bArr = new byte[i];
        this.buffer = bArr;
        this.byteBuffer = ByteBuffer.wrap(bArr, 0, i);
        this.useBoundary = z;
    }

    private void moveBytesToStartOfBuffer(byte[] bArr) {
        int i = this.size;
        if (i > 0) {
            System.arraycopy(this.buffer, this.readIndex, bArr, 0, i);
        }
        int i2 = this.readIndex;
        if (i2 > 0) {
            this.notificationConsumer.bytesConsolidatedToStartOfBuffer(i2);
        }
        this.readIndex = 0;
        this.boundary = this.available;
        this.writeIndex = this.size;
    }

    private int freeSpaceAtEndOfBuffer() {
        return this.capacity - this.writeIndex;
    }

    private void ensureSpaceInBuffer(int i) {
        if (this.size < 1 || freeSpaceAtEndOfBuffer() < i) {
            int iFreeSpaceAtEndOfBuffer = (i - freeSpaceAtEndOfBuffer()) - this.readIndex;
            if (iFreeSpaceAtEndOfBuffer <= 0) {
                moveBytesToStartOfBuffer(this.buffer);
                return;
            }
            int iMax = Math.max(this.initialBufferSize, iFreeSpaceAtEndOfBuffer);
            int i2 = this.capacity;
            int i3 = i2 + iMax;
            int i4 = this.maximumBufferSize;
            if (i3 <= i4) {
                iFreeSpaceAtEndOfBuffer = iMax;
            } else if (i2 + iFreeSpaceAtEndOfBuffer > i4) {
                throw new BufferOverflowException();
            }
            byte[] bArr = new byte[this.buffer.length + iFreeSpaceAtEndOfBuffer];
            moveBytesToStartOfBuffer(bArr);
            int i5 = this.capacity + iFreeSpaceAtEndOfBuffer;
            this.capacity = i5;
            this.buffer = bArr;
            this.byteBuffer = ByteBuffer.wrap(bArr, this.readIndex, i5);
        }
    }

    public int receive(InputStream inputStream, int i) throws IOException {
        int i2;
        ensureSpaceInBuffer(i);
        try {
            i2 = inputStream.read(this.buffer, this.writeIndex, i);
        } catch (EOFException unused) {
            i2 = -1;
        }
        if (i2 > 0) {
            this.writeIndex += i2;
            this.size += i2;
        } else {
            i2 = 0;
        }
        if (!this.useBoundary) {
            extendBoundary(i2);
        }
        return i2;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (bArr.length == 0 || i2 == 0) {
            return 0;
        }
        int i3 = this.available;
        if (i3 < 1) {
            return -1;
        }
        int iMin = Math.min(i3, i2);
        System.arraycopy(this.buffer, this.readIndex, bArr, i, iMin);
        this.readIndex += iMin;
        this.available -= iMin;
        this.size -= iMin;
        return iMin;
    }

    void seekTo(int i) {
        int i2 = i - this.readIndex;
        this.available -= i2;
        this.size -= i2;
        this.readIndex = i;
    }

    int getReadIndex() {
        return this.readIndex;
    }

    int getWriteIndex() {
        return this.writeIndex;
    }

    void truncate(int i, int i2) {
        this.writeIndex = i;
        this.available = i2;
        this.boundary = i;
        this.size = i2;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        int i;
        if (j < 1 || (i = this.available) < 1) {
            return 0L;
        }
        int iMin = (int) Math.min(i, j);
        this.readIndex += iMin;
        this.available -= iMin;
        this.size -= iMin;
        return iMin;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.available;
    }

    int size() {
        return this.size;
    }

    int availableBeyondBoundary() {
        return this.size - this.available;
    }

    int getBoundary() {
        return this.boundary;
    }

    void extendBoundary(int i) {
        this.boundary += i;
        this.available += i;
    }

    @Override // java.io.InputStream
    public int read() {
        int i = this.available;
        if (i < 1) {
            return -1;
        }
        byte[] bArr = this.buffer;
        int i2 = this.readIndex;
        byte b = bArr[i2];
        this.readIndex = i2 + 1;
        this.available = i - 1;
        this.size--;
        return b & 255;
    }

    int peek(int i) {
        return this.buffer[i] & 255;
    }

    public int capacity() {
        return this.capacity;
    }

    int getInitialBufferSize() {
        return this.initialBufferSize;
    }

    ByteBuffer getByteBuffer(int i, int i2) {
        this.byteBuffer.limit(this.capacity);
        this.byteBuffer.position(i);
        this.byteBuffer.limit(i2);
        return this.byteBuffer;
    }

    void copyBytes(int i, byte[] bArr, int i2, int i3) {
        System.arraycopy(this.buffer, i, bArr, i2, i3);
    }

    void consolidate(int i, int i2) {
        int i3 = this.writeIndex;
        if (i > i3 || i > this.boundary || i2 < this.readIndex) {
            throw new IllegalArgumentException("Tried to consolidate using an index that violates the constraints.");
        }
        int i4 = i - i2;
        byte[] bArr = this.buffer;
        System.arraycopy(bArr, i, bArr, i2, i3 - i);
        this.size -= i4;
        this.available -= i4;
        this.writeIndex -= i4;
        this.boundary -= i4;
    }

    void registerNotificationConsumer(NotificationConsumer notificationConsumer) {
        this.notificationConsumer = notificationConsumer;
    }
}
