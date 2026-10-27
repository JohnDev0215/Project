package com.amazon.client.metrics.thirdparty.configuration;

/* JADX INFO: loaded from: classes2.dex */
public enum HttpRequestSignerType {
    DCP("Dcp"),
    DCP_OAUTH("DcpOAuth"),
    OAUTH("OAuth");

    private final String mName;

    HttpRequestSignerType(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public static HttpRequestSignerType fromString(String str) throws MetricsConfigurationException {
        for (HttpRequestSignerType httpRequestSignerType : values()) {
            if (httpRequestSignerType.getName().equalsIgnoreCase(str)) {
                return httpRequestSignerType;
            }
        }
        throw new MetricsConfigurationException(str + " is not a valid RequestSignerType");
    }
}
