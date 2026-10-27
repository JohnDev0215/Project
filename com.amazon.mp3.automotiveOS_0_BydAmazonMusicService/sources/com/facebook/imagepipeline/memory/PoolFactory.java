package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.imagepipeline.core.NativeCodeSetup;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@NotThreadSafe
public class PoolFactory {

    @Nullable
    private MemoryChunkPool mAshmemMemoryChunkPool;

    @Nullable
    private BitmapPool mBitmapPool;

    @Nullable
    private MemoryChunkPool mBufferMemoryChunkPool;
    private final PoolConfig mConfig;

    @Nullable
    private FlexByteArrayPool mFlexByteArrayPool;

    @Nullable
    private MemoryChunkPool mNativeMemoryChunkPool;

    @Nullable
    private PooledByteBufferFactory mPooledByteBufferFactory;

    @Nullable
    private PooledByteStreams mPooledByteStreams;

    @Nullable
    private ByteArrayPool mSmallByteArrayPool;

    public PoolFactory(PoolConfig config) {
        this.mConfig = (PoolConfig) Preconditions.checkNotNull(config);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public BitmapPool getBitmapPool() {
        if (this.mBitmapPool == null) {
            String bitmapPoolType = this.mConfig.getBitmapPoolType();
            byte b = -1;
            switch (bitmapPoolType.hashCode()) {
                case -1868884870:
                    if (bitmapPoolType.equals("legacy_default_params")) {
                        b = 3;
                    }
                    break;
                case -1106578487:
                    if (bitmapPoolType.equals("legacy")) {
                        b = 4;
                    }
                    break;
                case -404562712:
                    if (bitmapPoolType.equals("experimental")) {
                        b = 2;
                    }
                    break;
                case -402149703:
                    if (bitmapPoolType.equals("dummy_with_tracking")) {
                        b = 1;
                    }
                    break;
                case 95945896:
                    if (bitmapPoolType.equals("dummy")) {
                        b = 0;
                    }
                    break;
            }
            if (b == 0) {
                this.mBitmapPool = new DummyBitmapPool();
            } else if (b == 1) {
                this.mBitmapPool = new DummyTrackingInUseBitmapPool();
            } else if (b == 2) {
                this.mBitmapPool = new LruBitmapPool(this.mConfig.getBitmapPoolMaxPoolSize(), this.mConfig.getBitmapPoolMaxBitmapSize(), NoOpPoolStatsTracker.getInstance(), this.mConfig.isRegisterLruBitmapPoolAsMemoryTrimmable() ? this.mConfig.getMemoryTrimmableRegistry() : null);
            } else if (b == 3) {
                this.mBitmapPool = new BucketsBitmapPool(this.mConfig.getMemoryTrimmableRegistry(), DefaultBitmapPoolParams.get(), this.mConfig.getBitmapPoolStatsTracker(), this.mConfig.isIgnoreBitmapPoolHardCap());
            } else {
                this.mBitmapPool = new BucketsBitmapPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getBitmapPoolParams(), this.mConfig.getBitmapPoolStatsTracker(), this.mConfig.isIgnoreBitmapPoolHardCap());
            }
        }
        return this.mBitmapPool;
    }

    @Nullable
    public MemoryChunkPool getBufferMemoryChunkPool() {
        if (this.mBufferMemoryChunkPool == null) {
            try {
                this.mBufferMemoryChunkPool = (MemoryChunkPool) BufferMemoryChunkPool.class.getConstructor(MemoryTrimmableRegistry.class, PoolParams.class, PoolStatsTracker.class).newInstance(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getMemoryChunkPoolParams(), this.mConfig.getMemoryChunkPoolStatsTracker());
            } catch (ClassNotFoundException unused) {
                this.mBufferMemoryChunkPool = null;
            } catch (IllegalAccessException unused2) {
                this.mBufferMemoryChunkPool = null;
            } catch (InstantiationException unused3) {
                this.mBufferMemoryChunkPool = null;
            } catch (NoSuchMethodException unused4) {
                this.mBufferMemoryChunkPool = null;
            } catch (InvocationTargetException unused5) {
                this.mBufferMemoryChunkPool = null;
            }
        }
        return this.mBufferMemoryChunkPool;
    }

    public FlexByteArrayPool getFlexByteArrayPool() {
        if (this.mFlexByteArrayPool == null) {
            this.mFlexByteArrayPool = new FlexByteArrayPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getFlexByteArrayPoolParams());
        }
        return this.mFlexByteArrayPool;
    }

    public int getFlexByteArrayPoolMaxNumThreads() {
        return this.mConfig.getFlexByteArrayPoolParams().maxNumThreads;
    }

    @Nullable
    public MemoryChunkPool getNativeMemoryChunkPool() {
        if (this.mNativeMemoryChunkPool == null) {
            try {
                this.mNativeMemoryChunkPool = (MemoryChunkPool) NativeMemoryChunkPool.class.getConstructor(MemoryTrimmableRegistry.class, PoolParams.class, PoolStatsTracker.class).newInstance(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getMemoryChunkPoolParams(), this.mConfig.getMemoryChunkPoolStatsTracker());
            } catch (ClassNotFoundException e) {
                FLog.e("PoolFactory", "", e);
                this.mNativeMemoryChunkPool = null;
            } catch (IllegalAccessException e2) {
                FLog.e("PoolFactory", "", e2);
                this.mNativeMemoryChunkPool = null;
            } catch (InstantiationException e3) {
                FLog.e("PoolFactory", "", e3);
                this.mNativeMemoryChunkPool = null;
            } catch (NoSuchMethodException e4) {
                FLog.e("PoolFactory", "", e4);
                this.mNativeMemoryChunkPool = null;
            } catch (InvocationTargetException e5) {
                FLog.e("PoolFactory", "", e5);
                this.mNativeMemoryChunkPool = null;
            }
        }
        return this.mNativeMemoryChunkPool;
    }

    @Nullable
    private MemoryChunkPool getAshmemMemoryChunkPool() {
        if (this.mAshmemMemoryChunkPool == null) {
            try {
                this.mAshmemMemoryChunkPool = (MemoryChunkPool) AshmemMemoryChunkPool.class.getConstructor(MemoryTrimmableRegistry.class, PoolParams.class, PoolStatsTracker.class).newInstance(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getMemoryChunkPoolParams(), this.mConfig.getMemoryChunkPoolStatsTracker());
            } catch (ClassNotFoundException unused) {
                this.mAshmemMemoryChunkPool = null;
            } catch (IllegalAccessException unused2) {
                this.mAshmemMemoryChunkPool = null;
            } catch (InstantiationException unused3) {
                this.mAshmemMemoryChunkPool = null;
            } catch (NoSuchMethodException unused4) {
                this.mAshmemMemoryChunkPool = null;
            } catch (InvocationTargetException unused5) {
                this.mAshmemMemoryChunkPool = null;
            }
        }
        return this.mAshmemMemoryChunkPool;
    }

    public PooledByteBufferFactory getPooledByteBufferFactory() {
        return getPooledByteBufferFactory(!NativeCodeSetup.getUseNativeCode() ? 1 : 0);
    }

    public PooledByteBufferFactory getPooledByteBufferFactory(int memoryChunkType) {
        if (this.mPooledByteBufferFactory == null) {
            MemoryChunkPool memoryChunkPool = getMemoryChunkPool(memoryChunkType);
            Preconditions.checkNotNull(memoryChunkPool, "failed to get pool for chunk type: " + memoryChunkType);
            this.mPooledByteBufferFactory = new MemoryPooledByteBufferFactory(memoryChunkPool, getPooledByteStreams());
        }
        return this.mPooledByteBufferFactory;
    }

    public PooledByteStreams getPooledByteStreams() {
        if (this.mPooledByteStreams == null) {
            this.mPooledByteStreams = new PooledByteStreams(getSmallByteArrayPool());
        }
        return this.mPooledByteStreams;
    }

    public ByteArrayPool getSmallByteArrayPool() {
        if (this.mSmallByteArrayPool == null) {
            this.mSmallByteArrayPool = new GenericByteArrayPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getSmallByteArrayPoolParams(), this.mConfig.getSmallByteArrayPoolStatsTracker());
        }
        return this.mSmallByteArrayPool;
    }

    @Nullable
    private MemoryChunkPool getMemoryChunkPool(int memoryChunkType) {
        if (memoryChunkType == 0) {
            return getNativeMemoryChunkPool();
        }
        if (memoryChunkType == 1) {
            return getBufferMemoryChunkPool();
        }
        if (memoryChunkType == 2) {
            return getAshmemMemoryChunkPool();
        }
        throw new IllegalArgumentException("Invalid MemoryChunkType");
    }
}
