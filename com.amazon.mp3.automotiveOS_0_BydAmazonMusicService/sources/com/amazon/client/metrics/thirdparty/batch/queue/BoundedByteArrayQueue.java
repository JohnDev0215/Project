package com.amazon.client.metrics.thirdparty.batch.queue;

import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import com.amazon.device.utils.thirdparty.BackgroundThreadFactory;
import com.amazon.dp.logger.DPLogger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BoundedByteArrayQueue implements ByteArrayQueue {
    private static final DPLogger log = new DPLogger("Metrics:BoundedByteArrayQueue");
    protected final BatchPipelineConfiguration mBatchPipelineConfiguration;
    protected final PeriodicMetricReporter mPeriodicMetricReporter;
    private String mQueueName;
    private ScheduledThreadPoolExecutor mQueuePurgerExecutor;
    protected final Set<ByteArrayQueueListener> mListeners = new HashSet(1);
    private final QueuePurger mQueuePurger = new QueuePurger();
    protected long mBytesUsed = 0;
    protected long mNumEntries = 0;

    protected abstract void purgeExpiredBatches();

    public BoundedByteArrayQueue(BatchPipelineConfiguration batchPipelineConfiguration, PeriodicMetricReporter periodicMetricReporter) throws IllegalArgumentException {
        if (batchPipelineConfiguration.getMaxBatchQueueCapacityBytes() <= 0) {
            throw new IllegalArgumentException("Capacity of queue must be greater than 0 bytes.");
        }
        if (batchPipelineConfiguration.getExpiryTimeMillis() < 0) {
            throw new IllegalArgumentException("ExpiryTimeMillis must not be negative.");
        }
        if (batchPipelineConfiguration.getPurgePeriodMillis() < 0) {
            throw new IllegalArgumentException("PurgePeriodMillis must not be negative.");
        }
        if (periodicMetricReporter == null) {
            throw new IllegalArgumentException("Periodic metric reporter must not be null.");
        }
        this.mPeriodicMetricReporter = periodicMetricReporter;
        this.mBatchPipelineConfiguration = batchPipelineConfiguration;
        initializeQueuePurger();
    }

    private void initializeQueuePurger() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new BackgroundThreadFactory("BatchQueuePurgerThread"));
        this.mQueuePurgerExecutor = scheduledThreadPoolExecutor;
        scheduledThreadPoolExecutor.scheduleAtFixedRate(this.mQueuePurger, this.mBatchPipelineConfiguration.getPurgePeriodMillis(), this.mBatchPipelineConfiguration.getPurgePeriodMillis(), TimeUnit.MILLISECONDS);
        this.mQueuePurgerExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void shutdown() {
        this.mQueuePurger.shutdown();
        this.mQueuePurgerExecutor.shutdown();
        try {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.mQueuePurgerExecutor;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            if (!scheduledThreadPoolExecutor.awaitTermination(CoroutineLiveDataKt.DEFAULT_TIMEOUT, timeUnit)) {
                this.mQueuePurgerExecutor.shutdownNow();
                if (!this.mQueuePurgerExecutor.awaitTermination(CoroutineLiveDataKt.DEFAULT_TIMEOUT, timeUnit)) {
                    log.error("shutdown", "Thread pool did not terminate.", new Object[0]);
                }
            }
        } catch (InterruptedException e) {
            this.mQueuePurgerExecutor.shutdownNow();
            log.error("shutdown", "Thread pool interrupted on shutdown.", e);
            Thread.currentThread().interrupt();
        }
    }

    private class QueuePurger implements Runnable {
        private final AtomicBoolean mIsActive = new AtomicBoolean(true);

        public QueuePurger() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.mIsActive.get()) {
                BoundedByteArrayQueue.log.verbose("QueuePurger.run", "Shutdown invoked.", new Object[0]);
            } else {
                BoundedByteArrayQueue.log.verbose("QueuePurger.run", "Purging expired batches.", new Object[0]);
                BoundedByteArrayQueue.this.purgeExpiredBatches();
            }
        }

        public void shutdown() {
            this.mIsActive.set(false);
        }
    }

    protected synchronized void notifyListeners() {
        Iterator<ByteArrayQueueListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onInsert(this.mNumEntries, this.mBytesUsed);
        }
    }

    protected void validateInput(SerializedBatch serializedBatch) throws IllegalArgumentException {
        if (serializedBatch == null || serializedBatch.getSerializedBytes() == null) {
            throw new IllegalArgumentException("Serialized batch cannot be null.");
        }
        if (serializedBatch.getLength() <= 0) {
            throw new IllegalArgumentException("Serialized batch cannot be empty.");
        }
        if (serializedBatch.getLength() > this.mBatchPipelineConfiguration.getMaxBatchQueueCapacityBytes()) {
            throw new IllegalArgumentException("Serialized object size is larger than the maximum capacity.");
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void addListener(ByteArrayQueueListener byteArrayQueueListener) {
        if (byteArrayQueueListener == null) {
            throw new IllegalArgumentException("listener cannot be null");
        }
        this.mListeners.add(byteArrayQueueListener);
    }

    public synchronized long getNumEntriesInQueue() {
        return this.mNumEntries;
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public void setQueueName(String str) {
        this.mQueueName = str;
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public String getQueueName() {
        return this.mQueueName;
    }
}
