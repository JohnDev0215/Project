package com.amazon.avod.media.framework.memory;

import com.amazon.avod.util.DataUnit;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class SlowGrowingDirectBuffer extends DirectBuffer {
    public SlowGrowingDirectBuffer(int i) {
        super(i);
    }

    @Override // com.amazon.avod.media.framework.memory.DirectBuffer
    public void ensureCapacity(int i) {
        if (this.mByteBuffer.capacity() < i) {
            int iMax = Math.max(this.mByteBuffer.capacity(), 1);
            while (iMax < i) {
                iMax += (int) DataUnit.MEGABYTES.toBytes(1.0f);
            }
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(iMax);
            byteBufferAllocateDirect.put(getByteBufferForReading());
            this.mByteBuffer = byteBufferAllocateDirect;
        }
    }
}
