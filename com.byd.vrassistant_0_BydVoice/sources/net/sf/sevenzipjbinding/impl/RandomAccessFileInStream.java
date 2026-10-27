package net.sf.sevenzipjbinding.impl;

import java.io.IOException;
import java.io.RandomAccessFile;
import net.sf.sevenzipjbinding.IInStream;
import net.sf.sevenzipjbinding.SevenZipException;

/* JADX INFO: loaded from: classes3.dex */
public class RandomAccessFileInStream implements IInStream {
    private final RandomAccessFile randomAccessFile;

    public RandomAccessFileInStream(RandomAccessFile randomAccessFile) {
        this.randomAccessFile = randomAccessFile;
    }

    @Override // net.sf.sevenzipjbinding.ISeekableStream
    public synchronized long seek(long j, int i) throws SevenZipException {
        try {
            if (i == 0) {
                this.randomAccessFile.seek(j);
            } else if (i == 1) {
                RandomAccessFile randomAccessFile = this.randomAccessFile;
                randomAccessFile.seek(randomAccessFile.getFilePointer() + j);
            } else if (i == 2) {
                RandomAccessFile randomAccessFile2 = this.randomAccessFile;
                randomAccessFile2.seek(randomAccessFile2.length() + j);
            } else {
                throw new RuntimeException("Seek: unknown origin: " + i);
            }
        } catch (IOException e) {
            throw new SevenZipException("Error while seek operation", e);
        }
        return this.randomAccessFile.getFilePointer();
    }

    @Override // net.sf.sevenzipjbinding.ISequentialInStream
    public synchronized int read(byte[] bArr) throws SevenZipException {
        try {
            int i = this.randomAccessFile.read(bArr);
            if (i == -1) {
                return 0;
            }
            return i;
        } catch (IOException e) {
            throw new SevenZipException("Error reading random access file", e);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.randomAccessFile.close();
    }
}
