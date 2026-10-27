package com.amazon.client.metrics.thirdparty.configuration;

/* JADX INFO: loaded from: classes2.dex */
public enum BatchTransmitterType {
    PERIODIC("Periodic"),
    NOS("Nos"),
    URGENT("Urgent"),
    SCHEDULED("Scheduled"),
    CRITICAL("Critical");

    private final String mName;

    BatchTransmitterType(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public static BatchTransmitterType fromString(String str) throws MetricsConfigurationException {
        for (BatchTransmitterType batchTransmitterType : values()) {
            if (batchTransmitterType.getName().equalsIgnoreCase(str)) {
                return batchTransmitterType;
            }
        }
        throw new MetricsConfigurationException(str + " is not a valid BatchTransmitterType");
    }
}
