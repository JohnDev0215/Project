package com.amazon.alexa.voice.core.internal.audio;

import com.amazon.alexa.voice.core.AudioSink;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class AudioSinkDelegate implements AudioSink {
    private final AudioSink sink;

    protected void onClose() {
    }

    protected void onFlush() {
    }

    protected void onWrite(byte[] bArr, int i, int i2) {
        throw null;
    }

    public AudioSinkDelegate(AudioSink audioSink) {
        this.sink = audioSink;
    }

    @Override // com.amazon.alexa.voice.core.AudioSink
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        onWrite(bArr, i, i2);
        this.sink.write(bArr, i, i2);
    }

    @Override // com.amazon.alexa.voice.core.AudioSink
    public final void flush() throws IOException {
        onFlush();
        this.sink.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        onClose();
        this.sink.close();
    }
}
