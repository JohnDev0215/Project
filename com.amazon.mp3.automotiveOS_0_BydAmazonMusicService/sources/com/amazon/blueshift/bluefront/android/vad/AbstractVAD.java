package com.amazon.blueshift.bluefront.android.vad;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class AbstractVAD implements Closeable {
    private ByteBuffer vadBuffer = createVAD(true);

    private native ByteBuffer createVAD(boolean z);

    private native int destroyVAD(ByteBuffer byteBuffer);

    private native int isSpeech(ByteBuffer byteBuffer, short[] sArr, int i);

    private native int setDNNThreshold(ByteBuffer byteBuffer, float f);

    public final void setThreshold(float f) {
        setDNNThreshold(this.vadBuffer, f);
    }

    public final boolean isSpeech(short[] sArr, int i) {
        return isSpeech(this.vadBuffer, sArr, i) == 1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        ByteBuffer byteBuffer = this.vadBuffer;
        if (byteBuffer != null) {
            destroyVAD(byteBuffer);
            this.vadBuffer = null;
        }
    }

    static {
        System.loadLibrary("blueshift-audioprocessing");
    }
}
