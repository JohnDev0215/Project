package com.amazon.client.metrics.thirdparty.configuration;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class HttpConfiguration {
    public static final long DEFAULT_WAKE_LOCK_TIMEOUT_MILLIS = TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES);
    private final int mConnectTimeout;
    private final HttpRequestSignerType mHttpRequestSignerType;
    private final int mReadTimeout;
    private final String mStaticCredentialUrlEndpoint;
    private final String mUrlEndpoint;
    private final long mWakeLockTimeout;

    public HttpConfiguration(HttpRequestSignerType httpRequestSignerType, String str, String str2) throws MetricsConfigurationException {
        this(httpRequestSignerType, str, str2, 10000, 10000, DEFAULT_WAKE_LOCK_TIMEOUT_MILLIS);
    }

    public HttpConfiguration(HttpRequestSignerType httpRequestSignerType, String str, String str2, int i, int i2, long j) throws MetricsConfigurationException {
        if (httpRequestSignerType == null) {
            throw new MetricsConfigurationException("HttpRequestSignerType is null in configuration");
        }
        if (str == null) {
            throw new MetricsConfigurationException("UrlEndpoint is null in configuration");
        }
        if (str2 == null) {
            throw new MetricsConfigurationException("UrlEndpoint is null in configuration");
        }
        if (i < 0 || i2 < 0 || j < 0) {
            throw new MetricsConfigurationException("Negative timeout in configuration");
        }
        this.mHttpRequestSignerType = httpRequestSignerType;
        this.mUrlEndpoint = str;
        this.mStaticCredentialUrlEndpoint = str2;
        this.mConnectTimeout = i;
        this.mReadTimeout = i2;
        this.mWakeLockTimeout = j;
    }

    public HttpRequestSignerType getHttpRequestSignerType() {
        return this.mHttpRequestSignerType;
    }

    public String getUrlEndpoint() {
        return this.mUrlEndpoint;
    }

    public String getStaticCredentialUrlEndpoint() {
        return this.mStaticCredentialUrlEndpoint;
    }

    public int getConnectTimeout() {
        return this.mConnectTimeout;
    }

    public int getReadTimeout() {
        return this.mReadTimeout;
    }

    public long getWakeLockTimeout() {
        return this.mWakeLockTimeout;
    }
}
