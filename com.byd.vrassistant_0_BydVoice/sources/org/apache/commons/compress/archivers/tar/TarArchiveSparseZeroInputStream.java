package org.apache.commons.compress.archivers.tar;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
class TarArchiveSparseZeroInputStream extends InputStream {
    @Override // java.io.InputStream
    public int read() throws IOException {
        return 0;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        return j;
    }

    TarArchiveSparseZeroInputStream() {
    }
}
