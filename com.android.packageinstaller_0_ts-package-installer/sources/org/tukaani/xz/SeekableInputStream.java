package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SeekableInputStream extends InputStream {
    public abstract long length() throws IOException;

    public abstract long position() throws IOException;

    public abstract void seek(long j) throws IOException;

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        if (n <= 0) {
            return 0L;
        }
        long size = length();
        long pos = position();
        if (pos >= size) {
            return 0L;
        }
        if (size - pos < n) {
            n = size - pos;
        }
        seek(pos + n);
        return n;
    }
}
