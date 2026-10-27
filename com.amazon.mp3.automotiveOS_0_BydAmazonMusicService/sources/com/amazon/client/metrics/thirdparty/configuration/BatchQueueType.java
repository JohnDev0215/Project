package com.amazon.client.metrics.thirdparty.configuration;

/* JADX INFO: loaded from: classes2.dex */
public enum BatchQueueType {
    VOLATILE("Volatile"),
    NON_VOLATILE("NonVolatile"),
    SEMI_VOLATILE("SemiVolatile");

    private final String mName;

    BatchQueueType(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public static BatchQueueType fromString(String str) throws MetricsConfigurationException {
        for (BatchQueueType batchQueueType : values()) {
            if (batchQueueType.getName().equalsIgnoreCase(str)) {
                return batchQueueType;
            }
        }
        throw new MetricsConfigurationException(str + " is not a valid BatchQueueType");
    }
}
