package com.amazon.alexa.voice.core.internal.audio;

import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.alexa.voice.core.internal.util.SimpleBuffer;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class BufferedAudioSource implements AudioSource {
    private final SimpleBuffer buffer = new SimpleBuffer();
    private final AudioSource source;
    private volatile boolean useBuffer;

    public BufferedAudioSource(AudioSource audioSource) {
        this.source = audioSource;
    }

    @Override // com.amazon.alexa.voice.core.AudioSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.useBuffer) {
            return this.buffer.read(bArr, i, i2);
        }
        int i3 = this.source.read(bArr, i, i2);
        if (i3 > 0) {
            this.buffer.write(bArr, i, i3);
        }
        return i3;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.buffer.reset();
        if (this.useBuffer) {
            return;
        }
        this.useBuffer = true;
        this.source.close();
    }
}
