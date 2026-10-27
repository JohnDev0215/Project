package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@DoNotStrip
@ThreadSafe
public class NativeMemoryChunkPool extends MemoryChunkPool {
    @DoNotStrip
    public NativeMemoryChunkPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker nativeMemoryChunkPoolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, nativeMemoryChunkPoolStatsTracker);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.imagepipeline.memory.MemoryChunkPool, com.facebook.imagepipeline.memory.BasePool
    /* JADX INFO: renamed from: alloc */
    public MemoryChunk alloc2(int bucketedSize) {
        return new NativeMemoryChunk(bucketedSize);
    }
}
