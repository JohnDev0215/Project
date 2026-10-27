package com.amazon.alexa.voice.core.internal.audio;

import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AudioBase implements Closeable {
    private volatile boolean closed;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
    }

    public final boolean isClosed() {
        return this.closed;
    }

    protected final void checkClosed() throws IOException {
        if (this.closed) {
            throw new IOException("Audio is closed");
        }
    }

    protected final int checkResult(int i) throws IOException {
        if (i >= 0) {
            return i;
        }
        throw new IOException("Failed to read audio data");
    }
}
