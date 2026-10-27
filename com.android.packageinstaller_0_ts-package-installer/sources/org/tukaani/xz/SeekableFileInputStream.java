package org.tukaani.xz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes2.dex */
public class SeekableFileInputStream extends SeekableInputStream {
    protected RandomAccessFile randomAccessFile;

    public SeekableFileInputStream(File file) throws FileNotFoundException {
        this.randomAccessFile = new RandomAccessFile(file, "r");
    }

    public SeekableFileInputStream(String name) throws FileNotFoundException {
        this.randomAccessFile = new RandomAccessFile(name, "r");
    }

    public SeekableFileInputStream(RandomAccessFile randomAccessFile) {
        this.randomAccessFile = randomAccessFile;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.randomAccessFile.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] buf) throws IOException {
        return this.randomAccessFile.read(buf);
    }

    @Override // java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        return this.randomAccessFile.read(buf, off, len);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.randomAccessFile.close();
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public long length() throws IOException {
        return this.randomAccessFile.length();
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public long position() throws IOException {
        return this.randomAccessFile.getFilePointer();
    }

    @Override // org.tukaani.xz.SeekableInputStream
    public void seek(long pos) throws IOException {
        this.randomAccessFile.seek(pos);
    }
}
