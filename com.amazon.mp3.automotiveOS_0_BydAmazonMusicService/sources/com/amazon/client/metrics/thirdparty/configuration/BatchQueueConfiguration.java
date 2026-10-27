package com.amazon.client.metrics.thirdparty.configuration;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class BatchQueueConfiguration {
    private BatchQueueType mBatchQueueType;
    private String mDirectoryPrefix;

    public BatchQueueConfiguration(BatchQueueType batchQueueType, String str) throws MetricsConfigurationException {
        if (batchQueueType == null) {
            throw new MetricsConfigurationException("batchQueueType is null in configuration");
        }
        if (str == null) {
            throw new MetricsConfigurationException("directoryPrefix is null in configuration");
        }
        this.mBatchQueueType = batchQueueType;
        this.mDirectoryPrefix = str;
    }

    public BatchQueueType getBatchQueueType() {
        return this.mBatchQueueType;
    }

    public String getDirectoryPrefix() {
        return this.mDirectoryPrefix;
    }
}
