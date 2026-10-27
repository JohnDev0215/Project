package org.apache.tools.tar;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class TarOutputStream extends FilterOutputStream {
    public static final int LONGFILE_ERROR = 0;
    public static final int LONGFILE_GNU = 2;
    public static final int LONGFILE_TRUNCATE = 1;
    protected byte[] assemBuf;
    protected int assemLen;
    protected TarBuffer buffer;
    protected int currBytes;
    protected int currSize;
    protected boolean debug;
    protected int longFileMode;
    protected byte[] oneBuf;
    protected byte[] recordBuf;

    public TarOutputStream(OutputStream os) {
        this(os, TarBuffer.DEFAULT_BLKSIZE, 512);
    }

    public TarOutputStream(OutputStream os, int blockSize) {
        this(os, blockSize, 512);
    }

    public TarOutputStream(OutputStream os, int blockSize, int recordSize) {
        super(os);
        this.longFileMode = 0;
        this.buffer = new TarBuffer(os, blockSize, recordSize);
        this.debug = false;
        this.assemLen = 0;
        this.assemBuf = new byte[recordSize];
        this.recordBuf = new byte[recordSize];
        this.oneBuf = new byte[1];
    }

    public void setLongFileMode(int longFileMode) {
        this.longFileMode = longFileMode;
    }

    public void setDebug(boolean debugF) {
        this.debug = debugF;
    }

    public void setBufferDebug(boolean debug) {
        this.buffer.setDebug(debug);
    }

    public void finish() throws IOException {
        writeEOFRecord();
        writeEOFRecord();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        finish();
        this.buffer.close();
    }

    public int getRecordSize() {
        return this.buffer.getRecordSize();
    }

    public void putNextEntry(TarEntry entry) throws IOException {
        if (entry.getName().length() >= 100) {
            int i = this.longFileMode;
            if (i == 2) {
                TarEntry longLinkEntry = new TarEntry(TarConstants.GNU_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME);
                longLinkEntry.setSize(entry.getName().length() + 1);
                putNextEntry(longLinkEntry);
                write(entry.getName().getBytes());
                write(0);
                closeEntry();
            } else if (i != 1) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("file name '");
                stringBuffer.append(entry.getName());
                stringBuffer.append("' is too long ( > ");
                stringBuffer.append(100);
                stringBuffer.append(" bytes)");
                throw new RuntimeException(stringBuffer.toString());
            }
        }
        entry.writeEntryHeader(this.recordBuf);
        this.buffer.writeRecord(this.recordBuf);
        this.currBytes = 0;
        if (entry.isDirectory()) {
            this.currSize = 0;
        } else {
            this.currSize = (int) entry.getSize();
        }
    }

    public void closeEntry() throws IOException {
        byte[] bArr;
        if (this.assemLen > 0) {
            int i = this.assemLen;
            while (true) {
                bArr = this.assemBuf;
                if (i >= bArr.length) {
                    break;
                }
                bArr[i] = 0;
                i++;
            }
            this.buffer.writeRecord(bArr);
            this.currBytes += this.assemLen;
            this.assemLen = 0;
        }
        int i2 = this.currBytes;
        if (i2 < this.currSize) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("entry closed at '");
            stringBuffer.append(this.currBytes);
            stringBuffer.append("' before the '");
            stringBuffer.append(this.currSize);
            stringBuffer.append("' bytes specified in the header were written");
            throw new IOException(stringBuffer.toString());
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        byte[] bArr = this.oneBuf;
        bArr[0] = (byte) b;
        write(bArr, 0, 1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] wBuf) throws IOException {
        write(wBuf, 0, wBuf.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] wBuf, int wOffset, int numToWrite) throws IOException {
        if (this.currBytes + numToWrite > this.currSize) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("request to write '");
            stringBuffer.append(numToWrite);
            stringBuffer.append("' bytes exceeds size in header of '");
            stringBuffer.append(this.currSize);
            stringBuffer.append("' bytes");
            throw new IOException(stringBuffer.toString());
        }
        int i = this.assemLen;
        if (i > 0) {
            int i2 = i + numToWrite;
            byte[] bArr = this.recordBuf;
            if (i2 >= bArr.length) {
                int aLen = bArr.length - i;
                System.arraycopy(this.assemBuf, 0, bArr, 0, i);
                System.arraycopy(wBuf, wOffset, this.recordBuf, this.assemLen, aLen);
                this.buffer.writeRecord(this.recordBuf);
                this.currBytes += this.recordBuf.length;
                wOffset += aLen;
                numToWrite -= aLen;
                this.assemLen = 0;
            } else {
                System.arraycopy(wBuf, wOffset, this.assemBuf, i, numToWrite);
                wOffset += numToWrite;
                this.assemLen += numToWrite;
                numToWrite -= numToWrite;
            }
        }
        while (numToWrite > 0) {
            if (numToWrite < this.recordBuf.length) {
                System.arraycopy(wBuf, wOffset, this.assemBuf, this.assemLen, numToWrite);
                this.assemLen += numToWrite;
                return;
            } else {
                this.buffer.writeRecord(wBuf, wOffset);
                int num = this.recordBuf.length;
                this.currBytes += num;
                numToWrite -= num;
                wOffset += num;
            }
        }
    }

    private void writeEOFRecord() throws IOException {
        int i = 0;
        while (true) {
            byte[] bArr = this.recordBuf;
            if (i < bArr.length) {
                bArr[i] = 0;
                i++;
            } else {
                this.buffer.writeRecord(bArr);
                return;
            }
        }
    }
}
