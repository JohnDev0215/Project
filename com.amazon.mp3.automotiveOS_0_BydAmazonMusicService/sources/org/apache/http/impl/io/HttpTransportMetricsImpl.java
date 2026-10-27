package org.apache.http.impl.io;

import org.apache.http.io.HttpTransportMetrics;

/* JADX INFO: loaded from: classes7.dex */
public class HttpTransportMetricsImpl implements HttpTransportMetrics {
    private long bytesTransferred = 0;

    public void incrementBytesTransferred(long j) {
        this.bytesTransferred += j;
    }
}
