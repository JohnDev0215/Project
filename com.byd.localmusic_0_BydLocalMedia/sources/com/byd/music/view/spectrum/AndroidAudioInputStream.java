package com.byd.music.view.spectrum;

import android.media.AudioRecord;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public class AndroidAudioInputStream implements TarsosDSPAudioInputStream {
    private final TarsosDSPAudioFormat format;
    private final AudioRecord underlyingStream;

    @Override // com.byd.music.view.spectrum.TarsosDSPAudioInputStream
    public long getFrameLength() {
        return -1L;
    }

    public AndroidAudioInputStream(AudioRecord audioRecord, TarsosDSPAudioFormat tarsosDSPAudioFormat) {
        this.underlyingStream = audioRecord;
        this.format = tarsosDSPAudioFormat;
    }

    @Override // com.byd.music.view.spectrum.TarsosDSPAudioInputStream
    public long skip(long j) throws IOException {
        throw new IOException("Can not skip in audio stream");
    }

    @Override // com.byd.music.view.spectrum.TarsosDSPAudioInputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.underlyingStream.read(bArr, i, i2);
    }

    @Override // com.byd.music.view.spectrum.TarsosDSPAudioInputStream
    public void close() throws IOException {
        this.underlyingStream.stop();
        this.underlyingStream.release();
    }

    @Override // com.byd.music.view.spectrum.TarsosDSPAudioInputStream
    public TarsosDSPAudioFormat getFormat() {
        return this.format;
    }
}
