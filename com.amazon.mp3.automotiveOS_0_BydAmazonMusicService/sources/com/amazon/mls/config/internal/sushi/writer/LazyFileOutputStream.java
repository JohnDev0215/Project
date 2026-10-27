package com.amazon.mls.config.internal.sushi.writer;

import com.amazon.mls.config.internal.sushi.SushiFile;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
class LazyFileOutputStream {
    private final boolean append;
    private FileOutputStream fileOutputStream = null;
    private final SushiFile supportFile;

    LazyFileOutputStream(SushiFile sushiFile, boolean z) {
        this.supportFile = sushiFile;
        this.append = z;
    }

    public void write(byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream = this.fileOutputStream;
        if (fileOutputStream == null && this.supportFile != null) {
            this.fileOutputStream = new FileOutputStream(this.supportFile.getSupportFile(), this.append);
        } else if (fileOutputStream == null) {
            throw new FileNotFoundException("LazyFileOutputStream can't open a stream for a null support file.");
        }
        this.fileOutputStream.write(bArr);
    }

    public void close() throws IOException {
        FileOutputStream fileOutputStream = this.fileOutputStream;
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
    }
}
