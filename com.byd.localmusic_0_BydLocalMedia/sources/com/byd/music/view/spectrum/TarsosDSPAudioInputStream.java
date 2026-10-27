package com.byd.music.view.spectrum;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public interface TarsosDSPAudioInputStream {
    void close() throws IOException;

    TarsosDSPAudioFormat getFormat();

    long getFrameLength();

    int read(byte[] bArr, int i, int i2) throws IOException;

    long skip(long j) throws IOException;
}
