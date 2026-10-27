package net.lingala.zip4j.io.inputstream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;

/* JADX INFO: loaded from: classes.dex */
public class NumberedSplitFileInputStream extends SplitFileInputStream {
    private RandomAccessFile randomAccessFile;

    public NumberedSplitFileInputStream(File file) {
        this.randomAccessFile = new NumberedSplitRandomAccessFile(file, RandomAccessFileMode.READ.getValue());
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        RandomAccessFile randomAccessFile = this.randomAccessFile;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
    }

    @Override // net.lingala.zip4j.io.inputstream.SplitFileInputStream
    public void prepareExtractionForFileHeader(FileHeader fileHeader) throws IOException {
        this.randomAccessFile.seek(fileHeader.getOffsetLocalHeader());
    }

    @Override // java.io.InputStream
    public int read() {
        return this.randomAccessFile.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        return this.randomAccessFile.read(bArr, i, i2);
    }
}
