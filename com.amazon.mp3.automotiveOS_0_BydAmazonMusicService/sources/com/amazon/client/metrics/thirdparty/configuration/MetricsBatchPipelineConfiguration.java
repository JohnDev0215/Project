package com.amazon.client.metrics.thirdparty.configuration;

/* JADX INFO: loaded from: classes2.dex */
public class MetricsBatchPipelineConfiguration implements BatchPipelineConfiguration {
    private BatchQueueType mBatchQueueType;
    private final BatchTransmitterType mBatchTransmitterType;
    private final BoundedNumberEvaluator mBoundedCheckBatchOpenTimeMillis;
    private final BoundedNumberEvaluator mBoundedExpiryTimeMillis;
    private final BoundedNumberEvaluator mBoundedMaxBatchOpenTimeMillis;
    private final BoundedNumberEvaluator mBoundedMaxBatchQueueCapacityBytes;
    private final BoundedNumberEvaluator mBoundedMaxBatchQueueEntries;
    private final BoundedNumberEvaluator mBoundedMaxNumEntriesPerBatch;
    private final BoundedNumberEvaluator mBoundedMaxSizePerBatchBytes;
    private final BoundedNumberEvaluator mBoundedPurgePeriodMillis;
    private final BoundedNumberEvaluator mBoundedTransmissionPeriodMillis;
    private String mDirectoryPrefix;

    public MetricsBatchPipelineConfiguration(BatchQueueType batchQueueType, String str, long j, long j2, int i, int i2, int i3, int i4, long j3, long j4, long j5, BatchTransmitterType batchTransmitterType) {
        this.mBatchQueueType = batchQueueType;
        this.mDirectoryPrefix = str;
        this.mBoundedMaxBatchOpenTimeMillis = new BoundedNumberEvaluator("MaxBatchOpenTimeMillis", 500L, 604800000L, j);
        this.mBoundedCheckBatchOpenTimeMillis = new BoundedNumberEvaluator("CheckBatchOpenTimeMillis", 500L, 86400000L, j2);
        this.mBoundedMaxBatchQueueCapacityBytes = new BoundedNumberEvaluator("MaxBatchQueueCapacityBytes", 1024L, 10485760L, i3);
        this.mBoundedMaxNumEntriesPerBatch = new BoundedNumberEvaluator("MaxBatchEntries", 1L, 1000000L, i);
        this.mBoundedMaxSizePerBatchBytes = new BoundedNumberEvaluator("MaxBatchSizeBytes", 1024L, 1048576L, i2);
        this.mBoundedMaxBatchQueueEntries = new BoundedNumberEvaluator("MaxBatchQueueEntries", 1L, 10000L, i4);
        this.mBoundedExpiryTimeMillis = new BoundedNumberEvaluator("ExpiryTimeMillis", 86400000L, 3888000000L, j3);
        this.mBoundedPurgePeriodMillis = new BoundedNumberEvaluator("PurgePeriodMillis", 3600000L, 259200000L, j4);
        this.mBoundedTransmissionPeriodMillis = new BoundedNumberEvaluator("TransmissionPeriodMillis", 10000L, 86400000L, j5);
        this.mBatchTransmitterType = batchTransmitterType;
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public long getMaxBatchOpenTimeMillis() {
        return this.mBoundedMaxBatchOpenTimeMillis.getValue();
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public long getCheckBatchOpenTimeMillis() {
        return this.mBoundedCheckBatchOpenTimeMillis.getValue();
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public long getMaxBatchQueueCapacityBytes() {
        return this.mBoundedMaxBatchQueueCapacityBytes.getValue();
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public long getMaxNumEntriesPerBatch() {
        return this.mBoundedMaxNumEntriesPerBatch.getValue();
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public long getMaxSizePerBatchBytes() {
        return this.mBoundedMaxSizePerBatchBytes.getValue();
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public long getMaxBatchQueueEntries() {
        return this.mBoundedMaxBatchQueueEntries.getValue();
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public long getExpiryTimeMillis() {
        return this.mBoundedExpiryTimeMillis.getValue();
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public long getPurgePeriodMillis() {
        return this.mBoundedPurgePeriodMillis.getValue();
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public long getTransmissionPeriodMillis() {
        return this.mBoundedTransmissionPeriodMillis.getValue();
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public BatchTransmitterType getBatchTransmitterType() {
        return this.mBatchTransmitterType;
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public BatchQueueType getBatchQueueType() {
        return this.mBatchQueueType;
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public String getDirectoryPrefix() {
        return this.mDirectoryPrefix;
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public void setBatchQueueType(BatchQueueType batchQueueType) {
        if (this.mBatchQueueType == null) {
            this.mBatchQueueType = batchQueueType;
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
    public void setDirectoryPrefix(String str) {
        if (this.mDirectoryPrefix == null) {
            this.mDirectoryPrefix = str;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MetricsBatchPipelineConfiguration metricsBatchPipelineConfiguration = (MetricsBatchPipelineConfiguration) obj;
        return metricsBatchPipelineConfiguration.getBatchQueueType() == getBatchQueueType() && (metricsBatchPipelineConfiguration.getDirectoryPrefix() != null ? metricsBatchPipelineConfiguration.getDirectoryPrefix().equals(getDirectoryPrefix()) : getDirectoryPrefix() == null) && metricsBatchPipelineConfiguration.getCheckBatchOpenTimeMillis() == getCheckBatchOpenTimeMillis() && metricsBatchPipelineConfiguration.getMaxBatchOpenTimeMillis() == getMaxBatchOpenTimeMillis() && metricsBatchPipelineConfiguration.getMaxBatchQueueEntries() == getMaxBatchQueueEntries() && metricsBatchPipelineConfiguration.getMaxBatchQueueCapacityBytes() == getMaxBatchQueueCapacityBytes() && metricsBatchPipelineConfiguration.getMaxNumEntriesPerBatch() == getMaxNumEntriesPerBatch() && metricsBatchPipelineConfiguration.getMaxSizePerBatchBytes() == getMaxSizePerBatchBytes() && metricsBatchPipelineConfiguration.getExpiryTimeMillis() == getExpiryTimeMillis() && metricsBatchPipelineConfiguration.getPurgePeriodMillis() == getPurgePeriodMillis() && metricsBatchPipelineConfiguration.getTransmissionPeriodMillis() == getTransmissionPeriodMillis() && metricsBatchPipelineConfiguration.getBatchTransmitterType() == getBatchTransmitterType();
    }

    public int hashCode() {
        return (getBatchQueueType() == null ? 0 : getBatchQueueType().hashCode()) + (getDirectoryPrefix() != null ? getDirectoryPrefix().hashCode() : 0) + Long.valueOf(getCheckBatchOpenTimeMillis()).hashCode() + Long.valueOf(getMaxBatchOpenTimeMillis()).hashCode() + Long.valueOf(getMaxBatchQueueEntries()).hashCode() + Long.valueOf(getMaxBatchQueueCapacityBytes()).hashCode() + Long.valueOf(getMaxNumEntriesPerBatch()).hashCode() + Long.valueOf(getMaxSizePerBatchBytes()).hashCode() + Long.valueOf(getExpiryTimeMillis()).hashCode() + Long.valueOf(getPurgePeriodMillis()).hashCode() + Long.valueOf(getTransmissionPeriodMillis()).hashCode() + getBatchTransmitterType().hashCode();
    }
}
