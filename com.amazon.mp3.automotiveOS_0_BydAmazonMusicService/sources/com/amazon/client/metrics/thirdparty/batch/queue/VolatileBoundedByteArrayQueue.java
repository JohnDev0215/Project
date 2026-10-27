package com.amazon.client.metrics.thirdparty.batch.queue;

import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import com.amazon.dp.logger.DPLogger;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class VolatileBoundedByteArrayQueue extends BoundedByteArrayQueue {
    private static final DPLogger log = new DPLogger("Metrics:VolatileBoundedByteArrayQueue");
    protected final Set<ByteArrayQueueListener> mListeners;
    private Deque<SerializedBatch> mQueue;

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public void persistBatches() {
    }

    public VolatileBoundedByteArrayQueue(BatchPipelineConfiguration batchPipelineConfiguration, PeriodicMetricReporter periodicMetricReporter) {
        super(batchPipelineConfiguration, periodicMetricReporter);
        this.mQueue = new LinkedList();
        this.mListeners = new HashSet(1);
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void add(SerializedBatch serializedBatch, boolean z) throws IllegalArgumentException {
        validateInput(serializedBatch);
        ensureCapacity(serializedBatch);
        this.mQueue.add(serializedBatch);
        this.mNumEntries++;
        if (z) {
            notifyListeners();
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized void addFirst(SerializedBatch serializedBatch, boolean z) {
        validateInput(serializedBatch);
        ensureCapacity(serializedBatch);
        this.mQueue.addFirst(serializedBatch);
        this.mNumEntries++;
        if (z) {
            notifyListeners();
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue
    public synchronized SerializedBatch remove() {
        SerializedBatch serializedBatchPoll;
        serializedBatchPoll = this.mQueue.peek() == null ? null : this.mQueue.poll();
        if (serializedBatchPoll != null && serializedBatchPoll.getSerializedBytes() != null) {
            this.mBytesUsed -= serializedBatchPoll.getLength();
            this.mNumEntries--;
        }
        return serializedBatchPoll;
    }

    @Override // com.amazon.client.metrics.thirdparty.batch.queue.BoundedByteArrayQueue
    public synchronized void purgeExpiredBatches() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.mBatchPipelineConfiguration.getExpiryTimeMillis();
        long j = this.mNumEntries;
        while (this.mQueue.peek() != null && this.mQueue.peek().getTimestamp() < jCurrentTimeMillis) {
            remove();
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("expiredBatches", 1.0d);
        }
        log.debug("purgeExpiredBatches", "Number of batches purged: ", Long.valueOf(j - this.mNumEntries));
    }

    private void ensureCapacity(SerializedBatch serializedBatch) {
        this.mBytesUsed += serializedBatch.getLength();
        while (this.mBytesUsed > this.mBatchPipelineConfiguration.getMaxBatchQueueCapacityBytes()) {
            log.debug("add", "Queue is full. Dropping an item from the queue.", new Object[0]);
            this.mPeriodicMetricReporter.getMetricEvent().incrementCounter("droppedBatches", 1.0d);
            if (remove() == null) {
                throw new IllegalArgumentException("All items removed and the queue is still full");
            }
        }
    }
}
