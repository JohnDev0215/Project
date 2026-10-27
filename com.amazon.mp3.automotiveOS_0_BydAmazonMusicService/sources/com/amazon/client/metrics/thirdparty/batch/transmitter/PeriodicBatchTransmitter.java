package com.amazon.client.metrics.thirdparty.batch.transmitter;

import android.content.Context;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import com.amazon.client.metrics.thirdparty.transport.MetricsTransport;
import com.amazon.device.utils.thirdparty.BackgroundThreadFactory;
import com.amazon.dp.logger.DPLogger;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class PeriodicBatchTransmitter extends ThreadPoolBatchTransmitter {
    private static final DPLogger log = new DPLogger("PeriodicBatchTransmitter");
    protected BatchPipelineConfiguration mBatchPipelineConfiguration;
    private ScheduledThreadPoolExecutor mScheduledExecutor;
    private Transmitter mTransmitter;

    public PeriodicBatchTransmitter(ByteArrayQueue byteArrayQueue, MetricsTransport metricsTransport, UploadResultBroadcaster uploadResultBroadcaster, BatchPipelineConfiguration batchPipelineConfiguration, PeriodicMetricReporter periodicMetricReporter, long j, Context context) {
        super(byteArrayQueue, metricsTransport, uploadResultBroadcaster, periodicMetricReporter, context);
        this.mBatchPipelineConfiguration = batchPipelineConfiguration;
        createScheduledThreadPoolExecutor(j);
    }

    private void createScheduledThreadPoolExecutor(long j) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new BackgroundThreadFactory("PeriodicBatchTransmitterThread"));
        this.mScheduledExecutor = scheduledThreadPoolExecutor;
        scheduledThreadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        Transmitter transmitter = new Transmitter();
        this.mTransmitter = transmitter;
        this.mScheduledExecutor.schedule(transmitter, j, TimeUnit.MILLISECONDS);
    }

    protected class Transmitter implements Runnable {
        private final AtomicBoolean mIsActive = new AtomicBoolean(true);

        public Transmitter() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mIsActive.get()) {
                PeriodicBatchTransmitter.log.verbose("Transmitter.run", "Shutdown invoked.", new Object[0]);
                return;
            }
            PeriodicBatchTransmitter.this.transmitBatches(false);
            PeriodicBatchTransmitter.log.verbose("Transmitter.run", "Rescheduling next transmission.", new Object[0]);
            PeriodicBatchTransmitter.this.mScheduledExecutor.schedule(PeriodicBatchTransmitter.this.mTransmitter, PeriodicBatchTransmitter.this.mBatchPipelineConfiguration.getTransmissionPeriodMillis(), TimeUnit.MILLISECONDS);
        }

        public void shutdown() {
            this.mIsActive.set(false);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.transmitter.ThreadPoolBatchTransmitter, com.amazon.client.metrics.thirdparty.batch.transmitter.BatchTransmitter
    public void shutdown() {
        this.mTransmitter.shutdown();
        this.mScheduledExecutor.shutdown();
        try {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.mScheduledExecutor;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            if (!scheduledThreadPoolExecutor.awaitTermination(CoroutineLiveDataKt.DEFAULT_TIMEOUT, timeUnit)) {
                this.mScheduledExecutor.shutdownNow();
                if (!this.mScheduledExecutor.awaitTermination(CoroutineLiveDataKt.DEFAULT_TIMEOUT, timeUnit)) {
                    log.error("shutdown", "Thread pool did not terminate.", new Object[0]);
                }
            }
        } catch (InterruptedException e) {
            log.error("shutdown", "Thread pool interrupted on shutdown.", e);
            Thread.currentThread().interrupt();
        }
        super.shutdown();
    }
}
