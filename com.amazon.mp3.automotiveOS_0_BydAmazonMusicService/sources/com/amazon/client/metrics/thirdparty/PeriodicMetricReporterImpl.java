package com.amazon.client.metrics.thirdparty;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class PeriodicMetricReporterImpl implements PeriodicMetricReporter {
    protected final Collection<MetricEvent> mAdditionalMetricEvents;
    protected final Channel mChannel;
    protected final PeriodicCommand mCommand;
    protected final MetricEvent mMetricEvent;
    protected final MetricsFactory mMetricsFactory;
    protected final Priority mPriority;
    protected final ScheduledExecutorService mThreadPool;

    public PeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2, MetricEventType metricEventType, Priority priority, Channel channel) {
        if (metricsFactory == null) {
            throw new IllegalArgumentException("Argument: factory cannot be null.");
        }
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("Argument: program cannot be null or empty.");
        }
        if (str2 == null || str2.trim().length() == 0) {
            throw new IllegalArgumentException("Argument: source cannot be null or empty.");
        }
        this.mMetricsFactory = metricsFactory;
        this.mCommand = new PeriodicCommand();
        this.mThreadPool = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() { // from class: com.amazon.client.metrics.thirdparty.PeriodicMetricReporterImpl.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "PeriodicMetricReporterThread");
                thread.setPriority(4);
                return thread;
            }
        });
        this.mAdditionalMetricEvents = new HashSet();
        this.mMetricEvent = metricsFactory.createConcurrentMetricEvent(str, str2, metricEventType, true);
        this.mPriority = priority;
        this.mChannel = channel;
    }

    public PeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2, MetricEventType metricEventType) {
        this(metricsFactory, str, str2, metricEventType, Priority.NORMAL, Channel.ANONYMOUS);
    }

    public PeriodicMetricReporterImpl(MetricsFactory metricsFactory, String str, String str2) {
        this(metricsFactory, str, str2, MetricEventType.getDefault());
    }

    @Override // com.amazon.client.metrics.thirdparty.PeriodicMetricReporter
    public void startRecordingPeriodically(long j, TimeUnit timeUnit) {
        this.mThreadPool.scheduleAtFixedRate(this.mCommand, j, j, timeUnit);
    }

    @Override // com.amazon.client.metrics.thirdparty.PeriodicMetricReporter
    public MetricEvent getMetricEvent() {
        return this.mMetricEvent;
    }

    @Override // com.amazon.client.metrics.thirdparty.PeriodicMetricReporter
    public void shutdown() {
        this.mCommand.shutdown();
        this.mThreadPool.shutdown();
        try {
            ScheduledExecutorService scheduledExecutorService = this.mThreadPool;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            if (scheduledExecutorService.awaitTermination(2000L, timeUnit)) {
                return;
            }
            this.mThreadPool.shutdownNow();
            this.mThreadPool.awaitTermination(2000L, timeUnit);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordMetricEvent() {
        HashSet<MetricEvent> hashSet;
        if (!this.mMetricEvent.getAsDataPoints().isEmpty()) {
            this.mMetricsFactory.record(this.mMetricEvent, this.mPriority, this.mChannel);
        }
        synchronized (this.mAdditionalMetricEvents) {
            hashSet = new HashSet(this.mAdditionalMetricEvents);
        }
        for (MetricEvent metricEvent : hashSet) {
            if (!metricEvent.getAsDataPoints().isEmpty()) {
                this.mMetricsFactory.record(metricEvent, this.mPriority, this.mChannel);
            }
        }
    }

    protected final class PeriodicCommand implements Runnable {
        private final AtomicBoolean mIsActive = new AtomicBoolean(true);

        public PeriodicCommand() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mIsActive.get()) {
                try {
                    PeriodicMetricReporterImpl.this.recordMetricEvent();
                } catch (Throwable unused) {
                }
            }
        }

        public void shutdown() {
            this.mIsActive.set(false);
        }
    }
}
