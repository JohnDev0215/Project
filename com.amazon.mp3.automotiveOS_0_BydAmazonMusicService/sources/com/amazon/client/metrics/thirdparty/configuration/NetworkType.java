package com.amazon.client.metrics.thirdparty.configuration;

/* JADX INFO: loaded from: classes2.dex */
public enum NetworkType {
    WIFI("Wifi"),
    ETHERNET("Ethernet"),
    WAN("Wan");

    private final String mName;

    NetworkType(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public static NetworkType fromString(String str) throws MetricsConfigurationException {
        for (NetworkType networkType : values()) {
            if (networkType.getName().equalsIgnoreCase(str)) {
                return networkType;
            }
        }
        throw new MetricsConfigurationException(str + " is not a valid NetworkType");
    }
}
