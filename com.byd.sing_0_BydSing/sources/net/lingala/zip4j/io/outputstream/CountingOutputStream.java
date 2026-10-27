package net.lingala.zip4j.io.outputstream;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class CountingOutputStream extends OutputStream implements OutputStreamWithSplitZipSupport {
    private long numberOfBytesWritten = 0;
    private OutputStream outputStream;

    public CountingOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public boolean checkBuffSizeAndStartNextSplitFile(int i) {
        if (isSplitZipFile()) {
            return ((SplitOutputStream) this.outputStream).checkBufferSizeAndStartNextSplitFile(i);
        }
        return false;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.outputStream.close();
    }

    @Override // net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport
    public int getCurrentSplitFileCounter() {
        if (isSplitZipFile()) {
            return ((SplitOutputStream) this.outputStream).getCurrentSplitFileCounter();
        }
        return 0;
    }

    @Override // net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport
    public long getFilePointer() {
        OutputStream outputStream = this.outputStream;
        return outputStream instanceof SplitOutputStream ? ((SplitOutputStream) outputStream).getFilePointer() : this.numberOfBytesWritten;
    }

    public long getNumberOfBytesWritten() {
        OutputStream outputStream = this.outputStream;
        return outputStream instanceof SplitOutputStream ? ((SplitOutputStream) outputStream).getFilePointer() : this.numberOfBytesWritten;
    }

    public long getOffsetForNextEntry() {
        OutputStream outputStream = this.outputStream;
        return outputStream instanceof SplitOutputStream ? ((SplitOutputStream) outputStream).getFilePointer() : this.numberOfBytesWritten;
    }

    public long getSplitLength() {
        if (isSplitZipFile()) {
            return ((SplitOutputStream) this.outputStream).getSplitLength();
        }
        return 0L;
    }

    public boolean isSplitZipFile() {
        OutputStream outputStream = this.outputStream;
        return (outputStream instanceof SplitOutputStream) && ((SplitOutputStream) outputStream).isSplitZipFile();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.outputStream.write(bArr, i, i2);
        this.numberOfBytesWritten += (long) i2;
    }
}
