package com.amazon.alexa.voice.core.internal.http;

import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.Logger;
import java.io.IOException;
import okio.Buffer;
import okio.Source;
import okio.Timeout;

/* JADX INFO: loaded from: classes2.dex */
public final class StreamableAudioSource implements Source {
    private byte[] buffer;
    private final AudioSource source;
    private final Timeout timeout = new Timeout();

    public StreamableAudioSource(AudioSource audioSource) {
        this.source = audioSource;
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j) throws IOException {
        if (this.buffer == null) {
            this.buffer = new byte[8192];
        }
        int i = this.source.read(this.buffer, 0, (int) Math.min(this.buffer.length, j));
        if (i == -1) {
            return i;
        }
        buffer.write(this.buffer, 0, i);
        return i;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.timeout;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Logger.debug("Closing streamable audio source");
        this.source.close();
        Logger.debug("Streamable audio source closed");
    }
}
