package com.facebook.imagepipeline.memory;

import android.annotation.TargetApi;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@DoNotStrip
@ThreadSafe
@TargetApi(27)
public class AshmemMemoryChunkPool extends MemoryChunkPool {
    @DoNotStrip
    public AshmemMemoryChunkPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker ashmemMemoryChunkPoolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, ashmemMemoryChunkPoolStatsTracker);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.imagepipeline.memory.MemoryChunkPool, com.facebook.imagepipeline.memory.BasePool
    /* JADX INFO: renamed from: alloc */
    public MemoryChunk alloc2(int bucketedSize) {
        return new AshmemMemoryChunk(bucketedSize);
    }
}
