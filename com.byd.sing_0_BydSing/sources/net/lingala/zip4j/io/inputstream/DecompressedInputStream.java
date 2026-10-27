package net.lingala.zip4j.io.inputstream;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class DecompressedInputStream extends InputStream {
    private CipherInputStream cipherInputStream;
    public byte[] oneByteBuffer = new byte[1];

    public DecompressedInputStream(CipherInputStream cipherInputStream) {
        this.cipherInputStream = cipherInputStream;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.cipherInputStream.close();
    }

    public void endOfEntryReached(InputStream inputStream) {
        this.cipherInputStream.endOfEntryReached(inputStream);
    }

    public byte[] getLastReadRawDataCache() {
        return this.cipherInputStream.getLastReadRawDataCache();
    }

    public void pushBackInputStreamIfNecessary(PushbackInputStream pushbackInputStream) {
    }

    @Override // java.io.InputStream
    public int read() {
        if (read(this.oneByteBuffer) == -1) {
            return -1;
        }
        return this.oneByteBuffer[0];
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        return this.cipherInputStream.read(bArr, i, i2);
    }
}
