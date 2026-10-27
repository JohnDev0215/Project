package com.amazon.avod.media.framework.memory;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class ByteBufferOutputStream extends OutputStream {
    private final ByteBuffer mByteBuffer;

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
    }

    public ByteBufferOutputStream(ByteBuffer byteBuffer) {
        this.mByteBuffer = byteBuffer;
        byteBuffer.rewind();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        try {
            this.mByteBuffer.put((byte) i);
        } catch (BufferOverflowException e) {
            throw new IOException("Writing past the end of the buffer. " + e.getCause());
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        try {
            this.mByteBuffer.put(bArr);
        } catch (BufferOverflowException e) {
            throw new IOException("Writing past the end of the buffer." + e.getCause());
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.mByteBuffer.put(bArr, i, i2);
        } catch (BufferOverflowException e) {
            throw new IOException("Writing past the end of the buffer." + e.getCause());
        }
    }
}
