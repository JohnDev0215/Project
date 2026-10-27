package com.amazon.avod.fileio;

import com.google.common.base.Preconditions;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class DiskSpaceAwareFileOutputStream extends OutputStream {
    protected final String mFilePath;
    protected final OutputStream mOutputStream;

    public DiskSpaceAwareFileOutputStream(FileOutputStream fileOutputStream, String str) {
        Preconditions.checkNotNull(fileOutputStream, "Null outputStream");
        this.mFilePath = str;
        this.mOutputStream = fileOutputStream;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws DiskIOException {
        try {
            this.mOutputStream.close();
        } catch (DiskIOException e) {
            throw e;
        } catch (IOException e2) {
            throw new DiskIOException(e2.getMessage());
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws DiskIOException {
        try {
            this.mOutputStream.flush();
        } catch (DiskIOException e) {
            throw e;
        } catch (IOException e2) {
            throw new DiskIOException(e2.getMessage());
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws DiskIOException {
        try {
            this.mOutputStream.write(bArr);
        } catch (IOException e) {
            DiskUtils.parseRethrowFileWriteIOException(e, this.mFilePath, bArr.length);
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws DiskIOException {
        try {
            this.mOutputStream.write(bArr, i, i2);
        } catch (IOException e) {
            DiskUtils.parseRethrowFileWriteIOException(e, this.mFilePath, i2);
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws DiskIOException {
        try {
            this.mOutputStream.write(i);
        } catch (IOException e) {
            DiskUtils.parseRethrowFileWriteIOException(e, this.mFilePath, 1L);
        }
    }
}
