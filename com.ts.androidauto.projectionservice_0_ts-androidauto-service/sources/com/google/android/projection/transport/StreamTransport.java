package com.google.android.projection.transport;

import com.google.android.projection.logging.Log;
import com.google.android.projection.protocol.Transport;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class StreamTransport extends Transport {
    private static final String TAG = "AHU.TRANSPORT";
    private final InputStream mInputStream;
    private final OutputStream mOutputStream;

    public StreamTransport(InputStream input, OutputStream output) {
        this.mInputStream = input;
        this.mOutputStream = output;
    }

    @Override // com.google.android.projection.protocol.Transport
    public void stopTransport(Transport.StopReason reason) {
        try {
            this.mInputStream.close();
            this.mOutputStream.close();
        } catch (IOException e) {
            Log.w("AHU.TRANSPORT", "Failed to close streams: " + e);
        }
    }

    @Override // com.google.android.projection.protocol.Transport
    public int read(byte[] b, int off, int len) throws IOException {
        return this.mInputStream.read(b, off, len);
    }

    @Override // com.google.android.projection.protocol.Transport
    public void write(byte[] b, int off, int len) throws IOException {
        this.mOutputStream.write(b, off, len);
    }
}
