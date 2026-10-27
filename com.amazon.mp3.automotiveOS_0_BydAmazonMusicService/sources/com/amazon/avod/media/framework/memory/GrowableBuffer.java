package com.amazon.avod.media.framework.memory;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class GrowableBuffer {
    protected byte[] mBuffer;
    protected ByteBuffer mByteBuffer;

    public abstract void ensureCapacity(int i);

    public GrowableBuffer(int i) {
        byte[] bArr = new byte[i];
        this.mBuffer = bArr;
        this.mByteBuffer = ByteBuffer.wrap(bArr);
    }

    public ByteBuffer getByteBuffer() {
        this.mByteBuffer.clear();
        return this.mByteBuffer;
    }

    @SuppressFBWarnings(justification = "To enforce immutability of this array, it is best to either clone it or use ImmutableList, but then we will be copying it every time we need to use it, which is extra allocation at a time where we care greatly about performance", value = {"EI_EXPOSE_REP"})
    public byte[] getBuffer() {
        return this.mBuffer;
    }

    public int getSize() {
        return this.mBuffer.length;
    }

    public void doubleSize() {
        ensureCapacity(getSize() * 2);
    }
}
