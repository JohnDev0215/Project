package com.amazon.minerva.client.thirdparty.storage;

import android.util.Log;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.utils.BackgroundThreadFactory;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractBatchCreatorScheduler {
    private static final String TAG = "AbstractBatchCreatorScheduler";
    private final BatchOpenTimeWatcher mBatchOpenTimeWatcher = new BatchOpenTimeWatcher();
    private final MetricsConfigurationHelper mMetricsConfigurationHelper;
    protected final AtomicLong mTimeSinceLastPublish;
    protected final ScheduledExecutorService threadPool;

    protected abstract void checkBatchOpenTimeAndEnqueueIfReady();

    public AbstractBatchCreatorScheduler(MetricsConfigurationHelper metricsConfigurationHelper, String str) {
        Objects.requireNonNull(metricsConfigurationHelper, "metricsConfigurationHelper cannot be null.");
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        AtomicLong atomicLong = new AtomicLong();
        this.mTimeSinceLastPublish = atomicLong;
        atomicLong.set(System.currentTimeMillis());
        this.threadPool = Executors.newSingleThreadScheduledExecutor(new BackgroundThreadFactory("MnvBchOpnWch_" + str));
    }

    protected void scheduleBatchOpenTimeWatcher() {
        try {
            this.threadPool.schedule(this.mBatchOpenTimeWatcher, this.mMetricsConfigurationHelper.getStorageConfiguration().getCheckBatchOpenTimeMillis(), TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException unused) {
            Log.e(TAG, "Unexpected rejected execution exception while scheduling LastPublishTimeWatcher");
        }
    }

    protected void shutdown() {
        this.threadPool.shutdown();
        try {
            ScheduledExecutorService scheduledExecutorService = this.threadPool;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            if (scheduledExecutorService.awaitTermination(CoroutineLiveDataKt.DEFAULT_TIMEOUT, timeUnit)) {
                return;
            }
            this.threadPool.shutdownNow();
            if (this.threadPool.awaitTermination(CoroutineLiveDataKt.DEFAULT_TIMEOUT, timeUnit)) {
                return;
            }
            Log.e(TAG, "Thread pool for BatchCreator did not terminate.");
        } catch (InterruptedException e) {
            this.threadPool.shutdownNow();
            Log.e(TAG, "Thread pool interrupted on shutdown.", e);
            Thread.currentThread().interrupt();
        }
    }

    private class BatchOpenTimeWatcher implements Callable<Void> {
        private BatchOpenTimeWatcher() {
        }

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            AbstractBatchCreatorScheduler.this.checkBatchOpenTimeAndEnqueueIfReady();
            AbstractBatchCreatorScheduler.this.scheduleBatchOpenTimeWatcher();
            return null;
        }
    }

    protected boolean maxBatchOpenTimeReached() {
        return System.currentTimeMillis() - this.mTimeSinceLastPublish.get() >= this.mMetricsConfigurationHelper.getStorageConfiguration().getMaxBatchOpenTimeMillis();
    }
}
