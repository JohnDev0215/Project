package com.amazon.alexa.voice.core.audio;

import com.amazon.alexa.voice.core.AudioSink;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioOutputStream extends OutputStream {
    private final byte[] single = new byte[1];
    private AudioSink sink;

    public AudioOutputStream(AudioSink audioSink) {
        this.sink = audioSink;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.single;
        bArr[0] = (byte) i;
        this.sink.write(bArr, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.sink.write(bArr, i, i2);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        AudioSink audioSink = this.sink;
        if (audioSink != null) {
            audioSink.close();
            this.sink = null;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.sink.flush();
    }
}
