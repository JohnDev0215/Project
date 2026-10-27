package net.sf.sevenzipjbinding.impl;

import java.io.IOException;
import java.io.RandomAccessFile;
import net.sf.sevenzipjbinding.IOutStream;
import net.sf.sevenzipjbinding.SevenZipException;

/* JADX INFO: loaded from: classes3.dex */
public class RandomAccessFileOutStream implements IOutStream {
    private final RandomAccessFile randomAccessFile;

    public RandomAccessFileOutStream(RandomAccessFile randomAccessFile) {
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

    @Override // net.sf.sevenzipjbinding.IOutStream
    public synchronized void setSize(long j) throws SevenZipException {
        try {
            this.randomAccessFile.setLength(j);
        } catch (IOException e) {
            throw new SevenZipException("Error setting new length of the file", e);
        }
    }

    @Override // net.sf.sevenzipjbinding.ISequentialOutStream
    public synchronized int write(byte[] bArr) throws SevenZipException {
        try {
            this.randomAccessFile.write(bArr);
        } catch (IOException e) {
            throw new SevenZipException("Error reading random access file", e);
        }
        return bArr.length;
    }

    public void close() throws IOException {
        this.randomAccessFile.close();
    }
}
