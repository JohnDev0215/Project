package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.zip.Checksum;

/* JADX INFO: loaded from: classes4.dex */
public class ChecksumCalculatingInputStream extends InputStream {
    private final Checksum checksum;
    private final InputStream in;

    public ChecksumCalculatingInputStream(Checksum checksum, InputStream inputStream) {
        Objects.requireNonNull(checksum, "checksum");
        Objects.requireNonNull(inputStream, "in");
        this.checksum = checksum;
        this.in = inputStream;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = this.in.read();
        if (i >= 0) {
            this.checksum.update(i);
        }
        return i;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.in.read(bArr, i, i2);
        if (i3 >= 0) {
            this.checksum.update(bArr, i, i3);
        }
        return i3;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return read() >= 0 ? 1L : 0L;
    }

    public long getValue() {
        return this.checksum.getValue();
    }
}
