package com.amazon.avod.media.framework.memory;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DirectBuffer {
    protected ByteBuffer mByteBuffer;

    public abstract void ensureCapacity(int i);

    public DirectBuffer(int i) {
        Preconditions.checkState(i >= 0, "numberOfBytes cannot be negative");
        this.mByteBuffer = ByteBuffer.allocateDirect(i);
    }

    @Nonnull
    public ByteBuffer getByteBufferForWriting() {
        this.mByteBuffer.clear();
        return this.mByteBuffer;
    }

    @Nonnull
    public ByteBuffer getByteBufferForReading() {
        this.mByteBuffer.flip();
        return this.mByteBuffer;
    }

    public int getSize() {
        return this.mByteBuffer.capacity();
    }
}
