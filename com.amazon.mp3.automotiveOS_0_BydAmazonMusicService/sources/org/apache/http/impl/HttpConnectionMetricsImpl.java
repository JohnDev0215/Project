package org.apache.http.impl;

import org.apache.http.io.HttpTransportMetrics;

/* JADX INFO: loaded from: classes7.dex */
public class HttpConnectionMetricsImpl {
    private final HttpTransportMetrics inTransportMetric;
    private final HttpTransportMetrics outTransportMetric;
    private long requestCount = 0;
    private long responseCount = 0;

    public HttpConnectionMetricsImpl(HttpTransportMetrics httpTransportMetrics, HttpTransportMetrics httpTransportMetrics2) {
        this.inTransportMetric = httpTransportMetrics;
        this.outTransportMetric = httpTransportMetrics2;
    }

    public void incrementRequestCount() {
        this.requestCount++;
    }

    public void incrementResponseCount() {
        this.responseCount++;
    }
}
