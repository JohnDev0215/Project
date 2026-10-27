package org.apache.commons.compress.compressors.snappy;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Arrays;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* JADX INFO: loaded from: classes4.dex */
public class FramedSnappyCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    static final int COMPRESSED_CHUNK_TYPE = 0;
    static final long MASK_OFFSET = 2726488792L;
    private static final int MAX_SKIPPABLE_TYPE = 253;
    private static final int MAX_UNSKIPPABLE_TYPE = 127;
    private static final int MIN_UNSKIPPABLE_TYPE = 2;
    private static final int PADDING_CHUNK_TYPE = 254;
    private static final int STREAM_IDENTIFIER_TYPE = 255;
    static final byte[] SZ_SIGNATURE = {-1, 6, 0, 0, 115, 78, 97, 80, 112, ClassDefinitionUtils.OPS_dup};
    private static final int UNCOMPRESSED_CHUNK_TYPE = 1;
    private final int blockSize;
    private final PureJavaCrc32C checksum;
    private final CountingInputStream countingStream;
    private SnappyCompressorInputStream currentCompressedChunk;
    private final FramedSnappyDialect dialect;
    private boolean endReached;
    private long expectedChecksum;
    private boolean inUncompressedChunk;
    private final PushbackInputStream inputStream;
    private final byte[] oneByte;
    private final ByteUtils.ByteSupplier supplier;
    private int uncompressedBytesRemaining;
    private long unreadBytes;

    static long unmask(long j) {
        long j2 = (j - MASK_OFFSET) & 4294967295L;
        return ((j2 << 15) | (j2 >> 17)) & 4294967295L;
    }

    public FramedSnappyCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, FramedSnappyDialect.STANDARD);
    }

    public FramedSnappyCompressorInputStream(InputStream inputStream, FramedSnappyDialect framedSnappyDialect) throws IOException {
        this(inputStream, 32768, framedSnappyDialect);
    }

    public FramedSnappyCompressorInputStream(InputStream inputStream, int i, FramedSnappyDialect framedSnappyDialect) throws IOException {
        this.oneByte = new byte[1];
        this.expectedChecksum = -1L;
        this.checksum = new PureJavaCrc32C();
        this.supplier = new ByteUtils.ByteSupplier() { // from class: org.apache.commons.compress.compressors.snappy.FramedSnappyCompressorInputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.compress.utils.ByteUtils.ByteSupplier
            public final int getAsByte() {
                return this.f$0.readOneByte();
            }
        };
        if (i <= 0) {
            throw new IllegalArgumentException("blockSize must be bigger than 0");
        }
        CountingInputStream countingInputStream = new CountingInputStream(inputStream);
        this.countingStream = countingInputStream;
        this.inputStream = new PushbackInputStream(countingInputStream, 1);
        this.blockSize = i;
        this.dialect = framedSnappyDialect;
        if (framedSnappyDialect.hasStreamIdentifier()) {
            readStreamIdentifier();
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.oneByte, 0, 1) == -1) {
            return -1;
        }
        return this.oneByte[0] & 255;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            SnappyCompressorInputStream snappyCompressorInputStream = this.currentCompressedChunk;
            if (snappyCompressorInputStream != null) {
                snappyCompressorInputStream.close();
                this.currentCompressedChunk = null;
            }
        } finally {
            this.inputStream.close();
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int once = readOnce(bArr, i, i2);
        if (once != -1) {
            return once;
        }
        readNextBlock();
        if (this.endReached) {
            return -1;
        }
        return readOnce(bArr, i, i2);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.inUncompressedChunk) {
            return Math.min(this.uncompressedBytesRemaining, this.inputStream.available());
        }
        SnappyCompressorInputStream snappyCompressorInputStream = this.currentCompressedChunk;
        if (snappyCompressorInputStream != null) {
            return snappyCompressorInputStream.available();
        }
        return 0;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.countingStream.getBytesRead() - this.unreadBytes;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int readOnce(byte[] r5, int r6, int r7) throws java.io.IOException {
        /*
            r4 = this;
            boolean r0 = r4.inUncompressedChunk
            r1 = -1
            if (r0 == 0) goto L1f
            int r0 = r4.uncompressedBytesRemaining
            int r7 = java.lang.Math.min(r0, r7)
            if (r7 != 0) goto Le
            return r1
        Le:
            java.io.PushbackInputStream r0 = r4.inputStream
            int r7 = r0.read(r5, r6, r7)
            if (r7 == r1) goto L42
            int r0 = r4.uncompressedBytesRemaining
            int r0 = r0 - r7
            r4.uncompressedBytesRemaining = r0
            r4.count(r7)
            goto L42
        L1f:
            org.apache.commons.compress.compressors.snappy.SnappyCompressorInputStream r0 = r4.currentCompressedChunk
            if (r0 == 0) goto L43
            long r2 = r0.getBytesRead()
            org.apache.commons.compress.compressors.snappy.SnappyCompressorInputStream r0 = r4.currentCompressedChunk
            int r7 = r0.read(r5, r6, r7)
            if (r7 != r1) goto L38
            org.apache.commons.compress.compressors.snappy.SnappyCompressorInputStream r0 = r4.currentCompressedChunk
            r0.close()
            r0 = 0
            r4.currentCompressedChunk = r0
            goto L42
        L38:
            org.apache.commons.compress.compressors.snappy.SnappyCompressorInputStream r0 = r4.currentCompressedChunk
            long r0 = r0.getBytesRead()
            long r0 = r0 - r2
            r4.count(r0)
        L42:
            r1 = r7
        L43:
            if (r1 <= 0) goto L4a
            org.apache.commons.compress.compressors.snappy.PureJavaCrc32C r7 = r4.checksum
            r7.update(r5, r6, r1)
        L4a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.snappy.FramedSnappyCompressorInputStream.readOnce(byte[], int, int):int");
    }

    private void readNextBlock() throws IOException {
        verifyLastChecksumAndReset();
        this.inUncompressedChunk = false;
        int oneByte = readOneByte();
        if (oneByte == -1) {
            this.endReached = true;
            return;
        }
        if (oneByte == 255) {
            this.inputStream.unread(oneByte);
            this.unreadBytes++;
            pushedBackBytes(1L);
            readStreamIdentifier();
            readNextBlock();
            return;
        }
        if (oneByte == 254 || (oneByte > 127 && oneByte <= 253)) {
            skipBlock();
            readNextBlock();
            return;
        }
        if (oneByte >= 2 && oneByte <= 127) {
            throw new IOException("Unskippable chunk with type " + oneByte + " (hex " + Integer.toHexString(oneByte) + ") detected.");
        }
        if (oneByte == 1) {
            this.inUncompressedChunk = true;
            int size = readSize() - 4;
            this.uncompressedBytesRemaining = size;
            if (size < 0) {
                throw new IOException("Found illegal chunk with negative size");
            }
            this.expectedChecksum = unmask(readCrc());
            return;
        }
        if (oneByte == 0) {
            boolean zUsesChecksumWithCompressedChunks = this.dialect.usesChecksumWithCompressedChunks();
            long size2 = ((long) readSize()) - (zUsesChecksumWithCompressedChunks ? 4L : 0L);
            if (size2 < 0) {
                throw new IOException("Found illegal chunk with negative size");
            }
            if (zUsesChecksumWithCompressedChunks) {
                this.expectedChecksum = unmask(readCrc());
            } else {
                this.expectedChecksum = -1L;
            }
            SnappyCompressorInputStream snappyCompressorInputStream = new SnappyCompressorInputStream(new BoundedInputStream(this.inputStream, size2), this.blockSize);
            this.currentCompressedChunk = snappyCompressorInputStream;
            count(snappyCompressorInputStream.getBytesRead());
            return;
        }
        throw new IOException("Unknown chunk type " + oneByte + " detected.");
    }

    private long readCrc() throws IOException {
        byte[] bArr = new byte[4];
        int fully = IOUtils.readFully(this.inputStream, bArr);
        count(fully);
        if (fully != 4) {
            throw new IOException("Premature end of stream");
        }
        return ByteUtils.fromLittleEndian(bArr);
    }

    private int readSize() throws IOException {
        return (int) ByteUtils.fromLittleEndian(this.supplier, 3);
    }

    private void skipBlock() throws IOException {
        int size = readSize();
        if (size < 0) {
            throw new IOException("Found illegal chunk with negative size");
        }
        long j = size;
        long jSkip = IOUtils.skip(this.inputStream, j);
        count(jSkip);
        if (jSkip != j) {
            throw new IOException("Premature end of stream");
        }
    }

    private void readStreamIdentifier() throws IOException {
        byte[] bArr = new byte[10];
        int fully = IOUtils.readFully(this.inputStream, bArr);
        count(fully);
        if (10 != fully || !matches(bArr, 10)) {
            throw new IOException("Not a framed Snappy stream");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int readOneByte() throws IOException {
        int i = this.inputStream.read();
        if (i == -1) {
            return -1;
        }
        count(1);
        return i & 255;
    }

    private void verifyLastChecksumAndReset() throws IOException {
        long j = this.expectedChecksum;
        if (j >= 0 && j != this.checksum.getValue()) {
            throw new IOException("Checksum verification failed");
        }
        this.expectedChecksum = -1L;
        this.checksum.reset();
    }

    public static boolean matches(byte[] bArr, int i) {
        byte[] bArr2 = SZ_SIGNATURE;
        if (i < bArr2.length) {
            return false;
        }
        if (bArr.length > bArr2.length) {
            byte[] bArr3 = new byte[bArr2.length];
            System.arraycopy(bArr, 0, bArr3, 0, bArr2.length);
            bArr = bArr3;
        }
        return Arrays.equals(bArr, bArr2);
    }
}
