package com.amazon.alexa.voice.core;

import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface AudioSink extends Closeable {
    void flush() throws IOException;

    void write(byte[] bArr, int i, int i2) throws IOException;
}
