package com.amazon.alexa.voice.core.audio;

import com.amazon.alexa.voice.core.AudioSink;
import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.Logger;
import com.amazon.alexa.voice.core.internal.audio.AudioBase;
import com.amazon.alexa.voice.core.internal.util.IOUtils;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioPipe extends AudioBase implements AudioSource, AudioSink {
    private final byte[] buffer;
    private final int capacity;
    private volatile int size;
    private final long timeoutMillis;

    public AudioPipe(int i, long j) {
        this.capacity = i;
        this.timeoutMillis = j;
        this.buffer = new byte[i];
    }

    @Override // com.amazon.alexa.voice.core.AudioSink
    public void write(byte[] bArr, int i, int i2) throws IOException {
        synchronized (this.buffer) {
            int i3 = 0;
            while (true) {
                int i4 = i2 - i3;
                if (i4 > 0) {
                    checkClosed();
                    int i5 = this.capacity - this.size;
                    if (i5 == 0) {
                        Logger.debug("Audio Pipe: buffer is full, cannot write, waiting...");
                        IOUtils.waitUntilNotified(this.buffer, this.timeoutMillis);
                    } else {
                        int iMin = Math.min(i5, i4);
                        System.arraycopy(bArr, i + i3, this.buffer, this.size, iMin);
                        i3 += iMin;
                        this.size += iMin;
                        this.buffer.notifyAll();
                    }
                }
            }
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioSink
    public void flush() throws IOException {
        synchronized (this.buffer) {
            while (this.size > 0) {
                checkClosed();
                Logger.debug("Audio Pipe: flushing, %d bytes remains, waiting...", Integer.valueOf(this.size));
                IOUtils.waitUntilNotified(this.buffer, this.timeoutMillis);
            }
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        synchronized (this.buffer) {
            while (this.size == 0) {
                if (isClosed()) {
                    return -1;
                }
                IOUtils.waitUntilNotified(this.buffer, this.timeoutMillis);
            }
            int iMin = Math.min(i2, this.size);
            System.arraycopy(this.buffer, 0, bArr, i, iMin);
            if (this.size > iMin) {
                byte[] bArr2 = this.buffer;
                System.arraycopy(bArr2, iMin, bArr2, 0, this.size - iMin);
            }
            this.size -= iMin;
            this.buffer.notifyAll();
            return iMin;
        }
    }

    @Override // com.amazon.alexa.voice.core.internal.audio.AudioBase, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (isClosed()) {
            return;
        }
        Logger.debug("Audio Pipe: closing");
        try {
            super.close();
            synchronized (this.buffer) {
                this.buffer.notifyAll();
            }
            Logger.debug("Audio Pipe: closed");
        } catch (Throwable th) {
            synchronized (this.buffer) {
                this.buffer.notifyAll();
                Logger.debug("Audio Pipe: closed");
                throw th;
            }
        }
    }
}
