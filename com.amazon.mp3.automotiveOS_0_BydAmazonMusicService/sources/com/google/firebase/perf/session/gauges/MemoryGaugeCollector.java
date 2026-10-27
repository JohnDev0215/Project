package com.google.firebase.perf.session.gauges;

import android.annotation.SuppressLint;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.perf.logging.AndroidLogger;
import com.google.firebase.perf.util.StorageUnit;
import com.google.firebase.perf.util.Timer;
import com.google.firebase.perf.util.Utils;
import com.google.firebase.perf.v1.AndroidMemoryReading;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes6.dex */
public class MemoryGaugeCollector {
    public static final long INVALID_MEMORY_COLLECTION_FREQUENCY = -1;
    private static final int UNSET_MEMORY_METRIC_COLLECTION_RATE = -1;
    private static final AndroidLogger logger = AndroidLogger.getInstance();
    private long memoryMetricCollectionRateMs;
    private final ScheduledExecutorService memoryMetricCollectorExecutor;

    @Nullable
    private ScheduledFuture memoryMetricCollectorJob;
    public final ConcurrentLinkedQueue<AndroidMemoryReading> memoryMetricReadings;
    private final Runtime runtime;

    public static boolean isInvalidCollectionFrequency(long j) {
        return j <= 0;
    }

    @SuppressLint({"ThreadPoolCreation"})
    MemoryGaugeCollector() {
        this(Executors.newSingleThreadScheduledExecutor(), Runtime.getRuntime());
    }

    @VisibleForTesting
    MemoryGaugeCollector(ScheduledExecutorService scheduledExecutorService, Runtime runtime) {
        this.memoryMetricCollectorJob = null;
        this.memoryMetricCollectionRateMs = -1L;
        this.memoryMetricCollectorExecutor = scheduledExecutorService;
        this.memoryMetricReadings = new ConcurrentLinkedQueue<>();
        this.runtime = runtime;
    }

    public void startCollecting(long j, Timer timer) {
        if (isInvalidCollectionFrequency(j)) {
            return;
        }
        if (this.memoryMetricCollectorJob == null) {
            scheduleMemoryMetricCollectionWithRate(j, timer);
        } else if (this.memoryMetricCollectionRateMs != j) {
            stopCollecting();
            scheduleMemoryMetricCollectionWithRate(j, timer);
        }
    }

    public void stopCollecting() {
        ScheduledFuture scheduledFuture = this.memoryMetricCollectorJob;
        if (scheduledFuture == null) {
            return;
        }
        scheduledFuture.cancel(false);
        this.memoryMetricCollectorJob = null;
        this.memoryMetricCollectionRateMs = -1L;
    }

    public void collectOnce(Timer timer) {
        scheduleMemoryMetricCollectionOnce(timer);
    }

    private synchronized void scheduleMemoryMetricCollectionWithRate(long j, final Timer timer) {
        this.memoryMetricCollectionRateMs = j;
        try {
            this.memoryMetricCollectorJob = this.memoryMetricCollectorExecutor.scheduleAtFixedRate(new Runnable() { // from class: com.google.firebase.perf.session.gauges.MemoryGaugeCollector$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$scheduleMemoryMetricCollectionWithRate$0(timer);
                }
            }, 0L, j, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e) {
            logger.warn("Unable to start collecting Memory Metrics: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleMemoryMetricCollectionWithRate$0(Timer timer) {
        AndroidMemoryReading androidMemoryReadingSyncCollectMemoryMetric = syncCollectMemoryMetric(timer);
        if (androidMemoryReadingSyncCollectMemoryMetric != null) {
            this.memoryMetricReadings.add(androidMemoryReadingSyncCollectMemoryMetric);
        }
    }

    private synchronized void scheduleMemoryMetricCollectionOnce(final Timer timer) {
        try {
            this.memoryMetricCollectorExecutor.schedule(new Runnable() { // from class: com.google.firebase.perf.session.gauges.MemoryGaugeCollector$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$scheduleMemoryMetricCollectionOnce$1(timer);
                }
            }, 0L, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e) {
            logger.warn("Unable to collect Memory Metric: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleMemoryMetricCollectionOnce$1(Timer timer) {
        AndroidMemoryReading androidMemoryReadingSyncCollectMemoryMetric = syncCollectMemoryMetric(timer);
        if (androidMemoryReadingSyncCollectMemoryMetric != null) {
            this.memoryMetricReadings.add(androidMemoryReadingSyncCollectMemoryMetric);
        }
    }

    @Nullable
    private AndroidMemoryReading syncCollectMemoryMetric(Timer timer) {
        if (timer == null) {
            return null;
        }
        return AndroidMemoryReading.newBuilder().setClientTimeUs(timer.getCurrentTimestampMicros()).setUsedAppJavaHeapMemoryKb(getCurrentUsedAppJavaHeapMemoryKb()).build();
    }

    private int getCurrentUsedAppJavaHeapMemoryKb() {
        return Utils.saturatedIntCast(StorageUnit.BYTES.toKilobytes(this.runtime.totalMemory() - this.runtime.freeMemory()));
    }
}
