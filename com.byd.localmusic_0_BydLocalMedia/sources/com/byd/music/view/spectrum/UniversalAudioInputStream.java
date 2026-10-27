package com.byd.music.view.spectrum;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public class UniversalAudioInputStream implements TarsosDSPAudioInputStream {
    private final TarsosDSPAudioFormat format;
    private final InputStream underlyingStream;

    @Override // com.byd.music.view.spectrum.TarsosDSPAudioInputStream
    public long getFrameLength() {
        return -1L;
    }

    public UniversalAudioInputStream(InputStream inputStream, TarsosDSPAudioFormat tarsosDSPAudioFormat) {
        this.underlyingStream = inputStream;
        this.format = tarsosDSPAudioFormat;
    }

    @Override // com.byd.music.view.spectrum.TarsosDSPAudioInputStream
    public long skip(long j) throws IOException {
        int i = 0;
        for (int i2 = 0; i2 < j; i2++) {
            if (this.underlyingStream.read() != -1) {
                i++;
            }
        }
        return i;
    }

    @Override // com.byd.music.view.spectrum.TarsosDSPAudioInputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.underlyingStream.read(bArr, i, i2);
    }

    @Override // com.byd.music.view.spectrum.TarsosDSPAudioInputStream
    public void close() throws IOException {
        this.underlyingStream.close();
    }

    @Override // com.byd.music.view.spectrum.TarsosDSPAudioInputStream
    public TarsosDSPAudioFormat getFormat() {
        return this.format;
    }
}
