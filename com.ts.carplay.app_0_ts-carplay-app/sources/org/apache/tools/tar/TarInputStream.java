package org.apache.tools.tar;

import com.ts.lib.car.VehicleVendorProperty;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.apache.tools.zip.UnixStat;

/* JADX INFO: loaded from: classes.dex */
public class TarInputStream extends FilterInputStream {
    protected TarBuffer buffer;
    protected TarEntry currEntry;
    protected boolean debug;
    protected int entryOffset;
    protected int entrySize;
    protected boolean hasHitEOF;
    protected byte[] oneBuf;
    protected byte[] readBuf;
    private boolean v7Format;

    public TarInputStream(InputStream is) {
        this(is, TarBuffer.DEFAULT_BLKSIZE, 512);
    }

    public TarInputStream(InputStream is, int blockSize) {
        this(is, blockSize, 512);
    }

    public TarInputStream(InputStream is, int blockSize, int recordSize) {
        super(is);
        this.buffer = new TarBuffer(is, blockSize, recordSize);
        this.readBuf = null;
        this.oneBuf = new byte[1];
        this.debug = false;
        this.hasHitEOF = false;
        this.v7Format = false;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
        this.buffer.setDebug(debug);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.buffer.close();
    }

    public int getRecordSize() {
        return this.buffer.getRecordSize();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return this.entrySize - this.entryOffset;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long numToSkip) throws IOException {
        byte[] skipBuf = new byte[VehicleVendorProperty.Health.OIL_HINT];
        long skip = numToSkip;
        while (skip > 0) {
            int realSkip = (int) (skip > ((long) skipBuf.length) ? skipBuf.length : skip);
            int numRead = read(skipBuf, 0, realSkip);
            if (numRead == -1) {
                break;
            }
            skip -= (long) numRead;
        }
        return numToSkip - skip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int markLimit) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() {
    }

    public TarEntry getNextEntry() throws IOException {
        if (this.hasHitEOF) {
            return null;
        }
        if (this.currEntry != null) {
            int numToSkip = this.entrySize - this.entryOffset;
            if (this.debug) {
                PrintStream printStream = System.err;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("TarInputStream: SKIP currENTRY '");
                stringBuffer.append(this.currEntry.getName());
                stringBuffer.append("' SZ ");
                stringBuffer.append(this.entrySize);
                stringBuffer.append(" OFF ");
                stringBuffer.append(this.entryOffset);
                stringBuffer.append("  skipping ");
                stringBuffer.append(numToSkip);
                stringBuffer.append(" bytes");
                printStream.println(stringBuffer.toString());
            }
            if (numToSkip > 0) {
                skip(numToSkip);
            }
            this.readBuf = null;
        }
        byte[] headerBuf = this.buffer.readRecord();
        if (headerBuf == null) {
            if (this.debug) {
                System.err.println("READ NULL RECORD");
            }
            this.hasHitEOF = true;
        } else if (this.buffer.isEOFRecord(headerBuf)) {
            if (this.debug) {
                System.err.println("READ EOF RECORD");
            }
            this.hasHitEOF = true;
        }
        if (this.hasHitEOF) {
            this.currEntry = null;
        } else {
            this.currEntry = new TarEntry(headerBuf);
            if (headerBuf[257] != 117 || headerBuf[258] != 115 || headerBuf[259] != 116 || headerBuf[260] != 97 || headerBuf[261] != 114) {
                this.v7Format = true;
            }
            if (this.debug) {
                PrintStream printStream2 = System.err;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("TarInputStream: SET CURRENTRY '");
                stringBuffer2.append(this.currEntry.getName());
                stringBuffer2.append("' size = ");
                stringBuffer2.append(this.currEntry.getSize());
                printStream2.println(stringBuffer2.toString());
            }
            this.entryOffset = 0;
            this.entrySize = (int) this.currEntry.getSize();
        }
        TarEntry tarEntry = this.currEntry;
        if (tarEntry != null && tarEntry.isGNULongNameEntry()) {
            StringBuffer longName = new StringBuffer();
            byte[] buf = new byte[256];
            while (true) {
                int length = read(buf);
                if (length < 0) {
                    break;
                }
                longName.append(new String(buf, 0, length));
            }
            getNextEntry();
            if (longName.length() > 0 && longName.charAt(longName.length() - 1) == 0) {
                longName.deleteCharAt(longName.length() - 1);
            }
            this.currEntry.setName(longName.toString());
        }
        return this.currEntry;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int num = read(this.oneBuf, 0, 1);
        if (num == -1) {
            return -1;
        }
        return this.oneBuf[0] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buf, int offset, int numToRead) throws IOException {
        int totalRead = 0;
        int i = this.entryOffset;
        int i2 = this.entrySize;
        if (i >= i2) {
            return -1;
        }
        if (numToRead + i > i2) {
            numToRead = i2 - i;
        }
        byte[] bArr = this.readBuf;
        if (bArr != null) {
            int sz = numToRead > bArr.length ? bArr.length : numToRead;
            System.arraycopy(this.readBuf, 0, buf, offset, sz);
            byte[] bArr2 = this.readBuf;
            if (sz >= bArr2.length) {
                this.readBuf = null;
            } else {
                int newLen = bArr2.length - sz;
                byte[] newBuf = new byte[newLen];
                System.arraycopy(bArr2, sz, newBuf, 0, newLen);
                this.readBuf = newBuf;
            }
            totalRead = 0 + sz;
            numToRead -= sz;
            offset += sz;
        }
        while (numToRead > 0) {
            byte[] rec = this.buffer.readRecord();
            if (rec == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("unexpected EOF with ");
                stringBuffer.append(numToRead);
                stringBuffer.append(" bytes unread");
                throw new IOException(stringBuffer.toString());
            }
            int sz2 = numToRead;
            int recLen = rec.length;
            if (recLen > sz2) {
                System.arraycopy(rec, 0, buf, offset, sz2);
                byte[] bArr3 = new byte[recLen - sz2];
                this.readBuf = bArr3;
                System.arraycopy(rec, sz2, bArr3, 0, recLen - sz2);
            } else {
                sz2 = recLen;
                System.arraycopy(rec, 0, buf, offset, recLen);
            }
            totalRead += sz2;
            numToRead -= sz2;
            offset += sz2;
        }
        this.entryOffset += totalRead;
        return totalRead;
    }

    public void copyEntryContents(OutputStream out) throws IOException {
        byte[] buf = new byte[UnixStat.FILE_FLAG];
        while (true) {
            int numRead = read(buf, 0, buf.length);
            if (numRead != -1) {
                out.write(buf, 0, numRead);
            } else {
                return;
            }
        }
    }
}
