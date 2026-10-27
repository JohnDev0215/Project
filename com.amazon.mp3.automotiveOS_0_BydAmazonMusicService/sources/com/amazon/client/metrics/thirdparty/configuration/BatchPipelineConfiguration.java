package com.amazon.client.metrics.thirdparty.configuration;

/* JADX INFO: loaded from: classes2.dex */
public interface BatchPipelineConfiguration {
    BatchQueueType getBatchQueueType();

    BatchTransmitterType getBatchTransmitterType();

    long getCheckBatchOpenTimeMillis();

    String getDirectoryPrefix();

    long getExpiryTimeMillis();

    long getMaxBatchOpenTimeMillis();

    long getMaxBatchQueueCapacityBytes();

    long getMaxBatchQueueEntries();

    long getMaxNumEntriesPerBatch();

    long getMaxSizePerBatchBytes();

    long getPurgePeriodMillis();

    long getTransmissionPeriodMillis();

    void setBatchQueueType(BatchQueueType batchQueueType);

    void setDirectoryPrefix(String str);
}
