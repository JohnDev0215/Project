package com.amazon.avod.media.framework.resources;

import com.amazon.avod.media.framework.memory.DoublingGrowableBuffer;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.util.DataUnit;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class SurgingBufferPool extends SurgingResourcePool<GrowableBuffer> {
    private static final int INITIAL_BUFFER_SIZE_BYTES = (int) DataUnit.KILOBYTES.toBytes(256.0f);

    public SurgingBufferPool(int i) {
        super(i, new Provider<GrowableBuffer>() { // from class: com.amazon.avod.media.framework.resources.SurgingBufferPool.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public GrowableBuffer get() {
                return new DoublingGrowableBuffer(SurgingBufferPool.INITIAL_BUFFER_SIZE_BYTES);
            }
        });
    }
}
