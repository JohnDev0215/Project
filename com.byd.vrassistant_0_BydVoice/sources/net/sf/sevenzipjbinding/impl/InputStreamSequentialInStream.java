package net.sf.sevenzipjbinding.impl;

import java.io.IOException;
import java.io.InputStream;
import net.sf.sevenzipjbinding.ISequentialInStream;
import net.sf.sevenzipjbinding.SevenZipException;

/* JADX INFO: loaded from: classes3.dex */
public class InputStreamSequentialInStream implements ISequentialInStream {
    private final InputStream inputStream;

    public InputStreamSequentialInStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override // net.sf.sevenzipjbinding.ISequentialInStream
    public int read(byte[] bArr) throws SevenZipException {
        if (bArr.length == 0) {
            return 0;
        }
        try {
            int i = this.inputStream.read(bArr);
            if (i < 0) {
                return 0;
            }
            return i;
        } catch (IOException e) {
            throw new SevenZipException("Error reading " + bArr.length + " bytes out of InputStream", e);
        }
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.inputStream.close();
    }
}
