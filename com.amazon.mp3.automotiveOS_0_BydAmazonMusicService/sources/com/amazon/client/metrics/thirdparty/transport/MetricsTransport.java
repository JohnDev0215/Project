package com.amazon.client.metrics.thirdparty.transport;

/* JADX INFO: loaded from: classes2.dex */
public interface MetricsTransport {
    void close();

    void shutdown();

    UploadResult transmit(byte[] bArr);
}
