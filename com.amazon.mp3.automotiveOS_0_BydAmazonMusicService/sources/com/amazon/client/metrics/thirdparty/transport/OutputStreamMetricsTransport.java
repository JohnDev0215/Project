package com.amazon.client.metrics.thirdparty.transport;

import android.util.Log;
import com.amazon.client.metrics.thirdparty.transport.TransportStateNotifier;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class OutputStreamMetricsTransport implements MetricsTransport, TransportStateNotifier {
    private final OutputStream mOutputStream;

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public void close() {
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.TransportStateNotifier
    public void listenForTransportWarmed(TransportStateNotifier.TransportWarmedListener transportWarmedListener) {
    }

    public OutputStreamMetricsTransport(OutputStream outputStream) {
        if (outputStream == null) {
            throw new NullPointerException("The OutputStream may not be null.");
        }
        this.mOutputStream = outputStream;
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public UploadResult transmit(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return new UploadResult(8);
        }
        try {
            this.mOutputStream.write(bArr);
            this.mOutputStream.flush();
            return new UploadResult(1);
        } catch (IOException e) {
            Log.e("Metrics:OutputStreamMetricsTransport", "Unable to transmit.", e);
            return new UploadResult(10, e);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public void shutdown() {
        try {
            this.mOutputStream.close();
        } catch (IOException e) {
            Log.e("Metrics:OutputStreamMetricsTransport", "Unable to shutdown transport.", e);
        }
    }
}
