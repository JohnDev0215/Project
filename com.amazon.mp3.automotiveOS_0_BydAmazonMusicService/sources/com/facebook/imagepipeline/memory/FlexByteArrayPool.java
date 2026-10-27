package com.facebook.imagepipeline.memory;

import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class FlexByteArrayPool {

    @VisibleForTesting
    final SoftRefByteArrayPool mDelegatePool;
    private final ResourceReleaser<byte[]> mResourceReleaser;

    public FlexByteArrayPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams params) {
        Preconditions.checkArgument(Boolean.valueOf(params.maxNumThreads > 0));
        this.mDelegatePool = new SoftRefByteArrayPool(memoryTrimmableRegistry, params, NoOpPoolStatsTracker.getInstance());
        this.mResourceReleaser = new ResourceReleaser<byte[]>() { // from class: com.facebook.imagepipeline.memory.FlexByteArrayPool.1
            @Override // com.facebook.common.references.ResourceReleaser
            public void release(byte[] unused) {
                FlexByteArrayPool.this.release(unused);
            }
        };
    }

    public CloseableReference<byte[]> get(int size) {
        return CloseableReference.of(this.mDelegatePool.get(size), this.mResourceReleaser);
    }

    public void release(byte[] value) {
        this.mDelegatePool.release(value);
    }

    public int getMinBufferSize() {
        return this.mDelegatePool.getMinBufferSize();
    }

    @VisibleForTesting
    static class SoftRefByteArrayPool extends GenericByteArrayPool {
        public SoftRefByteArrayPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
            super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        }

        @Override // com.facebook.imagepipeline.memory.BasePool
        Bucket<byte[]> newBucket(int bucketedSize) {
            return new OOMSoftReferenceBucket(getSizeInBytes(bucketedSize), this.mPoolParams.maxNumThreads, 0);
        }
    }
}
