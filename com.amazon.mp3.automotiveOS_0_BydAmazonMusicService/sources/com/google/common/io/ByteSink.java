package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ByteSink {
    public abstract OutputStream openStream() throws IOException;

    protected ByteSink() {
    }

    public void write(byte[] bArr) throws Throwable {
        Preconditions.checkNotNull(bArr);
        try {
            OutputStream outputStream = (OutputStream) Closer.create().register(openStream());
            outputStream.write(bArr);
            outputStream.flush();
        } finally {
        }
    }

    public long writeFrom(InputStream inputStream) throws Throwable {
        Preconditions.checkNotNull(inputStream);
        try {
            OutputStream outputStream = (OutputStream) Closer.create().register(openStream());
            long jCopy = ByteStreams.copy(inputStream, outputStream);
            outputStream.flush();
            return jCopy;
        } finally {
        }
    }
}
