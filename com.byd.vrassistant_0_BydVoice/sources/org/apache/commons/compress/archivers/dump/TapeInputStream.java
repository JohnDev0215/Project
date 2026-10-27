package org.apache.commons.compress.archivers.dump;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants;
import org.apache.commons.compress.utils.IOUtils;

/* JADX INFO: loaded from: classes4.dex */
class TapeInputStream extends FilterInputStream {
    private static final int RECORD_SIZE = 1024;
    private byte[] blockBuffer;
    private int blockSize;
    private long bytesRead;
    private int currBlkIdx;
    private boolean isCompressed;
    private int readOffset;

    public TapeInputStream(InputStream inputStream) {
        super(inputStream);
        this.blockBuffer = new byte[1024];
        this.currBlkIdx = -1;
        this.blockSize = 1024;
        this.readOffset = 1024;
    }

    public void resetBlockSize(int i, boolean z) throws IOException {
        this.isCompressed = z;
        if (i < 1) {
            throw new IOException("Block with " + i + " records found, must be at least 1");
        }
        int i2 = i * 1024;
        this.blockSize = i2;
        byte[] bArr = this.blockBuffer;
        byte[] bArr2 = new byte[i2];
        this.blockBuffer = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, 1024);
        readFully(this.blockBuffer, 1024, this.blockSize - 1024);
        this.currBlkIdx = 0;
        this.readOffset = 1024;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        int i = this.readOffset;
        int i2 = this.blockSize;
        return i < i2 ? i2 - i : this.in.available();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        throw new IllegalArgumentException("All reads must be multiple of record size (1024 bytes.");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        if (i2 % 1024 != 0) {
            throw new IllegalArgumentException("All reads must be multiple of record size (1024 bytes.");
        }
        while (i3 < i2) {
            if (this.readOffset == this.blockSize) {
                try {
                    readBlock(true);
                } catch (ShortFileException unused) {
                    return -1;
                }
            }
            int i4 = this.readOffset;
            int i5 = i2 - i3;
            int i6 = i4 + i5;
            int i7 = this.blockSize;
            if (i6 > i7) {
                i5 = i7 - i4;
            }
            System.arraycopy(this.blockBuffer, i4, bArr, i, i5);
            this.readOffset += i5;
            i3 += i5;
            i += i5;
        }
        return i3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = 0;
        if (j % 1024 != 0) {
            throw new IllegalArgumentException("All reads must be multiple of record size (1024 bytes.");
        }
        while (j2 < j) {
            int i = this.readOffset;
            int i2 = this.blockSize;
            if (i == i2) {
                try {
                    readBlock(j - j2 < ((long) i2));
                } catch (ShortFileException unused) {
                    return -1L;
                }
            }
            int i3 = this.readOffset;
            long j3 = j - j2;
            long j4 = ((long) i3) + j3;
            int i4 = this.blockSize;
            if (j4 > i4) {
                j3 = ((long) i4) - ((long) i3);
            }
            this.readOffset = (int) (((long) i3) + j3);
            j2 += j3;
        }
        return j2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.in == null || this.in == System.in) {
            return;
        }
        this.in.close();
    }

    public byte[] peek() throws IOException {
        if (this.readOffset == this.blockSize) {
            try {
                readBlock(true);
            } catch (ShortFileException unused) {
                return null;
            }
        }
        byte[] bArr = new byte[1024];
        System.arraycopy(this.blockBuffer, this.readOffset, bArr, 0, 1024);
        return bArr;
    }

    public byte[] readRecord() throws IOException {
        byte[] bArr = new byte[1024];
        if (-1 != read(bArr, 0, 1024)) {
            return bArr;
        }
        throw new ShortFileException();
    }

    private void readBlock(boolean z) throws IOException {
        if (this.in == null) {
            throw new IOException("Input buffer is closed");
        }
        if (!this.isCompressed || this.currBlkIdx == -1) {
            readFully(this.blockBuffer, 0, this.blockSize);
            this.bytesRead += (long) this.blockSize;
        } else {
            readFully(this.blockBuffer, 0, 4);
            this.bytesRead += 4;
            int iConvert32 = DumpArchiveUtil.convert32(this.blockBuffer, 0);
            if (!((iConvert32 & 1) == 1)) {
                readFully(this.blockBuffer, 0, this.blockSize);
                this.bytesRead += (long) this.blockSize;
            } else {
                int i = (iConvert32 >> 1) & 7;
                int i2 = (iConvert32 >> 4) & 268435455;
                byte[] range = readRange(i2);
                this.bytesRead += (long) i2;
                if (!z) {
                    Arrays.fill(this.blockBuffer, (byte) 0);
                } else {
                    int i3 = AnonymousClass1.$SwitchMap$org$apache$commons$compress$archivers$dump$DumpArchiveConstants$COMPRESSION_TYPE[DumpArchiveConstants.COMPRESSION_TYPE.find(i & 3).ordinal()];
                    if (i3 != 1) {
                        if (i3 == 2) {
                            throw new UnsupportedCompressionAlgorithmException("BZLIB2");
                        }
                        if (i3 == 3) {
                            throw new UnsupportedCompressionAlgorithmException("LZO");
                        }
                        throw new UnsupportedCompressionAlgorithmException();
                    }
                    Inflater inflater = new Inflater();
                    try {
                        try {
                            inflater.setInput(range, 0, range.length);
                            if (inflater.inflate(this.blockBuffer) != this.blockSize) {
                                throw new ShortFileException();
                            }
                        } catch (DataFormatException e) {
                            throw new DumpArchiveException("Bad data", e);
                        }
                    } finally {
                        inflater.end();
                    }
                }
            }
        }
        this.currBlkIdx++;
        this.readOffset = 0;
    }

    /* JADX INFO: renamed from: org.apache.commons.compress.archivers.dump.TapeInputStream$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$compress$archivers$dump$DumpArchiveConstants$COMPRESSION_TYPE;

        static {
            int[] iArr = new int[DumpArchiveConstants.COMPRESSION_TYPE.values().length];
            $SwitchMap$org$apache$commons$compress$archivers$dump$DumpArchiveConstants$COMPRESSION_TYPE = iArr;
            try {
                iArr[DumpArchiveConstants.COMPRESSION_TYPE.ZLIB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$dump$DumpArchiveConstants$COMPRESSION_TYPE[DumpArchiveConstants.COMPRESSION_TYPE.BZLIB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$archivers$dump$DumpArchiveConstants$COMPRESSION_TYPE[DumpArchiveConstants.COMPRESSION_TYPE.LZO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void readFully(byte[] bArr, int i, int i2) throws IOException {
        if (IOUtils.readFully(this.in, bArr, i, i2) < i2) {
            throw new ShortFileException();
        }
    }

    private byte[] readRange(int i) throws IOException {
        byte[] range = IOUtils.readRange(this.in, i);
        if (range.length >= i) {
            return range;
        }
        throw new ShortFileException();
    }

    public long getBytesRead() {
        return this.bytesRead;
    }
}
