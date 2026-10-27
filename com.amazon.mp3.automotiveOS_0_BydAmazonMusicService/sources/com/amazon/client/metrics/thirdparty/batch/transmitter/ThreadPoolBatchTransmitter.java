package com.amazon.client.metrics.thirdparty.batch.transmitter;

import android.content.Context;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue;
import com.amazon.client.metrics.thirdparty.transport.MetricsTransport;
import com.amazon.device.utils.thirdparty.BackgroundThreadFactory;
import com.amazon.dp.logger.DPLogger;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ThreadPoolBatchTransmitter extends BatchTransmitter {
    private static final DPLogger log = new DPLogger("Metrics:ThreadPoolBatchTransmitter");
    protected ThreadPoolExecutor mThreadPoolExecutor;

    public ThreadPoolBatchTransmitter(ByteArrayQueue byteArrayQueue, MetricsTransport metricsTransport, UploadResultBroadcaster uploadResultBroadcaster, PeriodicMetricReporter periodicMetricReporter, Context context) {
        super(byteArrayQueue, metricsTransport, uploadResultBroadcaster, periodicMetricReporter, context);
        createDefaultThreadPoolExecutor();
    }

    private void createDefaultThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(2), new BackgroundThreadFactory("BatchTransmitterThreadName"));
        this.mThreadPoolExecutor = threadPoolExecutor;
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.transmitter.BatchTransmitter
    public void shutdown() {
        super.shutdown();
        this.mThreadPoolExecutor.shutdown();
        try {
            ThreadPoolExecutor threadPoolExecutor = this.mThreadPoolExecutor;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            if (threadPoolExecutor.awaitTermination(CoroutineLiveDataKt.DEFAULT_TIMEOUT, timeUnit)) {
                return;
            }
            this.mThreadPoolExecutor.shutdownNow();
            if (this.mThreadPoolExecutor.awaitTermination(CoroutineLiveDataKt.DEFAULT_TIMEOUT, timeUnit)) {
                return;
            }
            log.error("shutdown", "Thread pool did not terminate.", new Object[0]);
        } catch (InterruptedException e) {
            log.error("shutdown", "Thread pool interrupted on shutdown.", e);
            Thread.currentThread().interrupt();
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.transmitter.BatchTransmitter
    public synchronized void transmitBatches(boolean z) {
        if (z) {
            try {
                log.verbose("transmitBatches", "Enabling broadcast result for next run.", new Object[0]);
                this.mQueuePusher.enableBroadcastResultForNextRun();
            } catch (RejectedExecutionException e) {
                log.error("transmitBatches", "Unexpected rejected execution exception while executing QueuePusher", e);
            }
        }
        this.mThreadPoolExecutor.execute(this.mQueuePusher);
    }
}
