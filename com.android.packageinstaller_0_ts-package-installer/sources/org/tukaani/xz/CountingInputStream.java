package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
class CountingInputStream extends CloseIgnoringInputStream {
    private long size;

    public CountingInputStream(InputStream in) {
        super(in);
        this.size = 0L;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int ret = this.in.read();
        if (ret != -1) {
            long j = this.size;
            if (j >= 0) {
                this.size = j + 1;
            }
        }
        return ret;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        int ret = this.in.read(b, off, len);
        if (ret > 0) {
            long j = this.size;
            if (j >= 0) {
                this.size = j + ((long) ret);
            }
        }
        return ret;
    }

    public long getSize() {
        return this.size;
    }
}
