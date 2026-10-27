package com.amazon.client.metrics.thirdparty.configuration;

/* JADX INFO: loaded from: classes2.dex */
public enum TransportType {
    HTTP("Http"),
    OUTPUT_STREAM("OutputStream");

    private final String mName;

    TransportType(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public static TransportType fromString(String str) throws MetricsConfigurationException {
        for (TransportType transportType : values()) {
            if (transportType.getName().equalsIgnoreCase(str)) {
                return transportType;
            }
        }
        throw new MetricsConfigurationException(str + " is not a valid TransportType");
    }
}
