package com.amazon.minerva.client.thirdparty.storage;

import android.annotation.TargetApi;
import android.util.Log;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class BatchCreator extends AbstractBatchCreatorScheduler {
    private static final String TAG = BatchCreatorManager.class.getSimpleName();
    protected static MetricBatchSerializer sMetricBatchSerializer;
    private final MetricsConfigurationHelper mMetricsConfigurationHelper;
    private final String mRegion;
    List<IonMetricEvent> mRunningBatch;
    private final StorageManager mStorageManager;
    private final int mStoragePriority;
    long mTotalBatchSize;

    public BatchCreator(MetricsConfigurationHelper metricsConfigurationHelper, StorageManager storageManager, String str, int i) {
        super(metricsConfigurationHelper, Integer.toString(i));
        this.mRunningBatch = new LinkedList();
        this.mTotalBatchSize = 0L;
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mRegion = str;
        this.mStorageManager = storageManager;
        this.mStoragePriority = i;
        sMetricBatchSerializer = metricsConfigurationHelper.getUploadConfiguration().createMetricBatchSerializer();
        scheduleBatchOpenTimeWatcher();
    }

    @TargetApi(19)
    public synchronized void addMetricEvent(IonMetricEvent ionMetricEvent) {
        Objects.requireNonNull(ionMetricEvent, "Parameter metricEvent can not be null");
        if (willExceedMaxBatchEntries() || willExceedMaxBatchSize(ionMetricEvent.getSizeInByte())) {
            enqueueBatchForTransmission();
        }
        this.mRunningBatch.add(ionMetricEvent);
        this.mTotalBatchSize += ionMetricEvent.getSizeInByte();
        Log.i(TAG, "addMetricEvent, runningBatch size:" + this.mRunningBatch.size());
    }

    private boolean willExceedMaxBatchEntries() {
        return ((long) (this.mRunningBatch.size() + 1)) > this.mMetricsConfigurationHelper.getStorageConfiguration().getMaxBatchEntries();
    }

    private boolean willExceedMaxBatchSize(long j) {
        return this.mTotalBatchSize + j > this.mMetricsConfigurationHelper.getStorageConfiguration().getMaxBatchSizeBytes();
    }

    synchronized void enqueueBatchForTransmission() {
        if (this.mRunningBatch.size() > 0) {
            Log.i(TAG, "enqueueBatchForTransmission");
            try {
                this.mStorageManager.addBatch(sMetricBatchSerializer.serialize(this.mRunningBatch), this.mRegion, this.mStoragePriority);
            } catch (IOException e) {
                Log.e(TAG, "Running batch failed to add to disk.", e);
            }
            this.mRunningBatch.clear();
            this.mTotalBatchSize = 0L;
            this.mTimeSinceLastPublish.set(System.currentTimeMillis());
        }
    }

    @Override // com.amazon.minerva.client.thirdparty.storage.AbstractBatchCreatorScheduler
    protected synchronized void checkBatchOpenTimeAndEnqueueIfReady() {
        if (maxBatchOpenTimeReached()) {
            enqueueBatchForTransmission();
        }
    }

    @Override // com.amazon.minerva.client.thirdparty.storage.AbstractBatchCreatorScheduler
    public synchronized void shutdown() {
        super.shutdown();
        enqueueBatchForTransmission();
    }
}
