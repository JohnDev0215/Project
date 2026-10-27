package com.facebook.imagepipeline.core;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultExecutorSupplier implements ExecutorSupplier {
    private final Executor mBackgroundExecutor;
    private final ScheduledExecutorService mBackgroundScheduledExecutorService;
    private final Executor mDecodeExecutor;
    private final Executor mIoBoundExecutor = Executors.newFixedThreadPool(2, new PriorityThreadFactory(10, "FrescoIoBoundExecutor", true));
    private final Executor mLightWeightBackgroundExecutor = Executors.newFixedThreadPool(1, new PriorityThreadFactory(10, "FrescoLightWeightBackgroundExecutor", true));

    public DefaultExecutorSupplier(int numCpuBoundThreads) {
        this.mDecodeExecutor = Executors.newFixedThreadPool(numCpuBoundThreads, new PriorityThreadFactory(10, "FrescoDecodeExecutor", true));
        this.mBackgroundExecutor = Executors.newFixedThreadPool(numCpuBoundThreads, new PriorityThreadFactory(10, "FrescoBackgroundExecutor", true));
        this.mBackgroundScheduledExecutorService = Executors.newScheduledThreadPool(numCpuBoundThreads, new PriorityThreadFactory(10, "FrescoBackgroundExecutor", true));
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forLocalStorageRead() {
        return this.mIoBoundExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forLocalStorageWrite() {
        return this.mIoBoundExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forDecode() {
        return this.mDecodeExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forBackgroundTasks() {
        return this.mBackgroundExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public ScheduledExecutorService scheduledExecutorServiceForBackgroundTasks() {
        return this.mBackgroundScheduledExecutorService;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forLightweightBackgroundTasks() {
        return this.mLightWeightBackgroundExecutor;
    }

    @Override // com.facebook.imagepipeline.core.ExecutorSupplier
    public Executor forThumbnailProducer() {
        return this.mIoBoundExecutor;
    }
}
