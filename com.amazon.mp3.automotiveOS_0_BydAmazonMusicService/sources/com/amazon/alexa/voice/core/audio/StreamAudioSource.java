package com.amazon.alexa.voice.core.audio;

import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.internal.audio.AudioBase;
import com.amazon.alexa.voice.core.internal.util.Functions;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
public final class StreamAudioSource extends AudioBase implements AudioSource {
    private final Callable<InputStream> callable;
    private InputStream inputStream;

    public StreamAudioSource(Callable<InputStream> callable) {
        Preconditions.notNull(callable, "callable == null");
        this.callable = callable;
    }

    public StreamAudioSource(InputStream inputStream) {
        this((Callable<InputStream>) Functions.justCallable(inputStream));
        Preconditions.notNull(inputStream, "inputStream == null");
    }

    @Override // com.amazon.alexa.voice.core.AudioSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        checkClosed();
        if (this.inputStream == null) {
            try {
                this.inputStream = this.callable.call();
            } catch (Exception e) {
                throw new IOException(e);
            }
        }
        return this.inputStream.read(bArr, i, i2);
    }

    @Override // com.amazon.alexa.voice.core.internal.audio.AudioBase, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        InputStream inputStream = this.inputStream;
        if (inputStream != null) {
            inputStream.close();
            this.inputStream = null;
        }
    }
}
