package org.apache.tools.tar;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TarBuffer {
    public static final int DEFAULT_BLKSIZE = 10240;
    public static final int DEFAULT_RCDSIZE = 512;
    private byte[] blockBuffer;
    private int blockSize;
    private int currBlkIdx;
    private int currRecIdx;
    private boolean debug;
    private InputStream inStream;
    private OutputStream outStream;
    private int recordSize;
    private int recsPerBlock;

    public TarBuffer(InputStream inStream) {
        this(inStream, DEFAULT_BLKSIZE);
    }

    public TarBuffer(InputStream inStream, int blockSize) {
        this(inStream, blockSize, 512);
    }

    public TarBuffer(InputStream inStream, int blockSize, int recordSize) {
        this.inStream = inStream;
        this.outStream = null;
        initialize(blockSize, recordSize);
    }

    public TarBuffer(OutputStream outStream) {
        this(outStream, DEFAULT_BLKSIZE);
    }

    public TarBuffer(OutputStream outStream, int blockSize) {
        this(outStream, blockSize, 512);
    }

    public TarBuffer(OutputStream outStream, int blockSize, int recordSize) {
        this.inStream = null;
        this.outStream = outStream;
        initialize(blockSize, recordSize);
    }

    private void initialize(int blockSize, int recordSize) {
        this.debug = false;
        this.blockSize = blockSize;
        this.recordSize = recordSize;
        int i = blockSize / recordSize;
        this.recsPerBlock = i;
        this.blockBuffer = new byte[blockSize];
        if (this.inStream != null) {
            this.currBlkIdx = -1;
            this.currRecIdx = i;
        } else {
            this.currBlkIdx = 0;
            this.currRecIdx = 0;
        }
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int getRecordSize() {
        return this.recordSize;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isEOFRecord(byte[] record) {
        int sz = getRecordSize();
        for (int i = 0; i < sz; i++) {
            if (record[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public void skipRecord() throws IOException {
        if (this.debug) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("SkipRecord: recIdx = ");
            stringBuffer.append(this.currRecIdx);
            stringBuffer.append(" blkIdx = ");
            stringBuffer.append(this.currBlkIdx);
            printStream.println(stringBuffer.toString());
        }
        if (this.inStream == null) {
            throw new IOException("reading (via skip) from an output buffer");
        }
        if (this.currRecIdx >= this.recsPerBlock && !readBlock()) {
            return;
        }
        this.currRecIdx++;
    }

    public byte[] readRecord() throws IOException {
        if (this.debug) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("ReadRecord: recIdx = ");
            stringBuffer.append(this.currRecIdx);
            stringBuffer.append(" blkIdx = ");
            stringBuffer.append(this.currBlkIdx);
            printStream.println(stringBuffer.toString());
        }
        if (this.inStream == null) {
            throw new IOException("reading from an output buffer");
        }
        if (this.currRecIdx >= this.recsPerBlock && !readBlock()) {
            return null;
        }
        int i = this.recordSize;
        byte[] result = new byte[i];
        System.arraycopy(this.blockBuffer, this.currRecIdx * i, result, 0, i);
        this.currRecIdx++;
        return result;
    }

    private boolean readBlock() throws IOException {
        if (this.debug) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("ReadBlock: blkIdx = ");
            stringBuffer.append(this.currBlkIdx);
            printStream.println(stringBuffer.toString());
        }
        if (this.inStream == null) {
            throw new IOException("reading from an output buffer");
        }
        this.currRecIdx = 0;
        int offset = 0;
        int bytesNeeded = this.blockSize;
        while (true) {
            if (bytesNeeded <= 0) {
                break;
            }
            long numBytes = this.inStream.read(this.blockBuffer, offset, bytesNeeded);
            if (numBytes == -1) {
                Arrays.fill(this.blockBuffer, offset, offset + bytesNeeded, (byte) 0);
                break;
            }
            offset = (int) (((long) offset) + numBytes);
            bytesNeeded = (int) (((long) bytesNeeded) - numBytes);
            if (numBytes != this.blockSize && this.debug) {
                PrintStream printStream2 = System.err;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("ReadBlock: INCOMPLETE READ ");
                stringBuffer2.append(numBytes);
                stringBuffer2.append(" of ");
                stringBuffer2.append(this.blockSize);
                stringBuffer2.append(" bytes read.");
                printStream2.println(stringBuffer2.toString());
            }
        }
        this.currBlkIdx++;
        return true;
    }

    public int getCurrentBlockNum() {
        return this.currBlkIdx;
    }

    public int getCurrentRecordNum() {
        return this.currRecIdx - 1;
    }

    public void writeRecord(byte[] record) throws IOException {
        if (this.debug) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("WriteRecord: recIdx = ");
            stringBuffer.append(this.currRecIdx);
            stringBuffer.append(" blkIdx = ");
            stringBuffer.append(this.currBlkIdx);
            printStream.println(stringBuffer.toString());
        }
        if (this.outStream == null) {
            throw new IOException("writing to an input buffer");
        }
        if (record.length != this.recordSize) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("record to write has length '");
            stringBuffer2.append(record.length);
            stringBuffer2.append("' which is not the record size of '");
            stringBuffer2.append(this.recordSize);
            stringBuffer2.append("'");
            throw new IOException(stringBuffer2.toString());
        }
        if (this.currRecIdx >= this.recsPerBlock) {
            writeBlock();
        }
        byte[] bArr = this.blockBuffer;
        int i = this.currRecIdx;
        int i2 = this.recordSize;
        System.arraycopy(record, 0, bArr, i * i2, i2);
        this.currRecIdx++;
    }

    public void writeRecord(byte[] buf, int offset) throws IOException {
        if (this.debug) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("WriteRecord: recIdx = ");
            stringBuffer.append(this.currRecIdx);
            stringBuffer.append(" blkIdx = ");
            stringBuffer.append(this.currBlkIdx);
            printStream.println(stringBuffer.toString());
        }
        if (this.outStream == null) {
            throw new IOException("writing to an input buffer");
        }
        if (this.recordSize + offset > buf.length) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("record has length '");
            stringBuffer2.append(buf.length);
            stringBuffer2.append("' with offset '");
            stringBuffer2.append(offset);
            stringBuffer2.append("' which is less than the record size of '");
            stringBuffer2.append(this.recordSize);
            stringBuffer2.append("'");
            throw new IOException(stringBuffer2.toString());
        }
        if (this.currRecIdx >= this.recsPerBlock) {
            writeBlock();
        }
        byte[] bArr = this.blockBuffer;
        int i = this.currRecIdx;
        int i2 = this.recordSize;
        System.arraycopy(buf, offset, bArr, i * i2, i2);
        this.currRecIdx++;
    }

    private void writeBlock() throws IOException {
        if (this.debug) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("WriteBlock: blkIdx = ");
            stringBuffer.append(this.currBlkIdx);
            printStream.println(stringBuffer.toString());
        }
        OutputStream outputStream = this.outStream;
        if (outputStream == null) {
            throw new IOException("writing to an input buffer");
        }
        outputStream.write(this.blockBuffer, 0, this.blockSize);
        this.outStream.flush();
        this.currRecIdx = 0;
        this.currBlkIdx++;
    }

    private void flushBlock() throws IOException {
        if (this.debug) {
            System.err.println("TarBuffer.flushBlock() called.");
        }
        if (this.outStream == null) {
            throw new IOException("writing to an input buffer");
        }
        if (this.currRecIdx > 0) {
            writeBlock();
        }
    }

    public void close() throws IOException {
        if (this.debug) {
            System.err.println("TarBuffer.closeBuffer().");
        }
        if (this.outStream != null) {
            flushBlock();
            if (this.outStream != System.out && this.outStream != System.err) {
                this.outStream.close();
                this.outStream = null;
                return;
            }
            return;
        }
        InputStream inputStream = this.inStream;
        if (inputStream != null && inputStream != System.in) {
            this.inStream.close();
            this.inStream = null;
        }
    }
}
