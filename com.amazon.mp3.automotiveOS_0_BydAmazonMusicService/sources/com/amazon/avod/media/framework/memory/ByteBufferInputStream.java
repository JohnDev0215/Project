package com.amazon.avod.media.framework.memory;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ByteBufferInputStream extends InputStream {
    private final ByteBuffer mByteBuffer;

    public ByteBufferInputStream(@Nonnull ByteBuffer byteBuffer) {
        this.mByteBuffer = (ByteBuffer) Preconditions.checkNotNull(byteBuffer, "byteBuffer");
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.mByteBuffer.hasRemaining()) {
            return this.mByteBuffer.get() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!this.mByteBuffer.hasRemaining()) {
            return -1;
        }
        int iMin = Math.min(i2, this.mByteBuffer.remaining());
        this.mByteBuffer.get(bArr, i, iMin);
        return iMin;
    }
}
