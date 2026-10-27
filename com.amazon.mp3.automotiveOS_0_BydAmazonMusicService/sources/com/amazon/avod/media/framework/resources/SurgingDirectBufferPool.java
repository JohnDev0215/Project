package com.amazon.avod.media.framework.resources;

import com.amazon.avod.media.framework.memory.DirectBuffer;
import com.amazon.avod.media.framework.memory.SlowGrowingDirectBuffer;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class SurgingDirectBufferPool extends SurgingResourcePool<DirectBuffer> {
    public SurgingDirectBufferPool(int i, final int i2) {
        super(i, new Provider<DirectBuffer>() { // from class: com.amazon.avod.media.framework.resources.SurgingDirectBufferPool.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javax.inject.Provider
            public DirectBuffer get() {
                return new SlowGrowingDirectBuffer(i2);
            }
        });
    }
}
