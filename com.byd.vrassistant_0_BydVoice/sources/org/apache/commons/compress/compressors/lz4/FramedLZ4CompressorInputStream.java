package org.apache.commons.compress.compressors.lz4;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.ChecksumCalculatingInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* JADX INFO: loaded from: classes4.dex */
public class FramedLZ4CompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    static final int BLOCK_CHECKSUM_MASK = 16;
    static final int BLOCK_INDEPENDENCE_MASK = 32;
    static final int BLOCK_MAX_SIZE_MASK = 112;
    static final int CONTENT_CHECKSUM_MASK = 4;
    static final int CONTENT_SIZE_MASK = 8;
    private static final byte SKIPPABLE_FRAME_PREFIX_BYTE_MASK = 80;
    static final int SUPPORTED_VERSION = 64;
    static final int UNCOMPRESSED_FLAG_MASK = Integer.MIN_VALUE;
    static final int VERSION_MASK = 192;
    private byte[] blockDependencyBuffer;
    private final XXHash32 blockHash;
    private final XXHash32 contentHash;
    private InputStream currentBlock;
    private final boolean decompressConcatenated;
    private boolean endReached;
    private boolean expectBlockChecksum;
    private boolean expectBlockDependency;
    private boolean expectContentChecksum;
    private boolean expectContentSize;
    private boolean inUncompressed;
    private final CountingInputStream inputStream;
    private final byte[] oneByte;
    private final ByteUtils.ByteSupplier supplier;
    static final byte[] LZ4_SIGNATURE = {4, 34, 77, Ascii.CAN};
    private static final byte[] SKIPPABLE_FRAME_TRAILER = {ClassDefinitionUtils.OPS_aload_0, 77, Ascii.CAN};

    public FramedLZ4CompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    public FramedLZ4CompressorInputStream(InputStream inputStream, boolean z) throws IOException {
        this.oneByte = new byte[1];
        this.supplier = new ByteUtils.ByteSupplier() { // from class: org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorInputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.compress.utils.ByteUtils.ByteSupplier
            public final int getAsByte() {
                return this.f$0.readOneByte();
            }
        };
        this.contentHash = new XXHash32();
        this.blockHash = new XXHash32();
        this.inputStream = new CountingInputStream(inputStream);
        this.decompressConcatenated = z;
        init(true);
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
            InputStream inputStream = this.currentBlock;
            if (inputStream != null) {
                inputStream.close();
                this.currentBlock = null;
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
        if (this.endReached) {
            return -1;
        }
        int once = readOnce(bArr, i, i2);
        if (once == -1) {
            nextBlock();
            if (!this.endReached) {
                once = readOnce(bArr, i, i2);
            }
        }
        if (once != -1) {
            if (this.expectBlockDependency) {
                appendToBlockDependencyBuffer(bArr, i, once);
            }
            if (this.expectContentChecksum) {
                this.contentHash.update(bArr, i, once);
            }
        }
        return once;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.inputStream.getBytesRead();
    }

    private void init(boolean z) throws IOException {
        if (readSignature(z)) {
            readFrameDescriptor();
            nextBlock();
        }
    }

    private boolean readSignature(boolean z) throws IOException {
        String str = z ? "Not a LZ4 frame stream" : "LZ4 frame stream followed by garbage";
        byte[] bArr = new byte[4];
        int fully = IOUtils.readFully(this.inputStream, bArr);
        count(fully);
        if (fully == 0 && !z) {
            this.endReached = true;
            return false;
        }
        if (4 != fully) {
            throw new IOException(str);
        }
        int iSkipSkippableFrame = skipSkippableFrame(bArr);
        if (iSkipSkippableFrame == 0 && !z) {
            this.endReached = true;
            return false;
        }
        if (4 == iSkipSkippableFrame && matches(bArr, 4)) {
            return true;
        }
        throw new IOException(str);
    }

    private void readFrameDescriptor() throws IOException {
        int oneByte = readOneByte();
        if (oneByte == -1) {
            throw new IOException("Premature end of stream while reading frame flags");
        }
        this.contentHash.update(oneByte);
        if ((oneByte & 192) != 64) {
            throw new IOException("Unsupported version " + (oneByte >> 6));
        }
        boolean z = (oneByte & 32) == 0;
        this.expectBlockDependency = z;
        if (z) {
            if (this.blockDependencyBuffer == null) {
                this.blockDependencyBuffer = new byte[65536];
            }
        } else {
            this.blockDependencyBuffer = null;
        }
        this.expectBlockChecksum = (oneByte & 16) != 0;
        this.expectContentSize = (oneByte & 8) != 0;
        this.expectContentChecksum = (oneByte & 4) != 0;
        int oneByte2 = readOneByte();
        if (oneByte2 == -1) {
            throw new IOException("Premature end of stream while reading frame BD byte");
        }
        this.contentHash.update(oneByte2);
        if (this.expectContentSize) {
            byte[] bArr = new byte[8];
            int fully = IOUtils.readFully(this.inputStream, bArr);
            count(fully);
            if (8 != fully) {
                throw new IOException("Premature end of stream while reading content size");
            }
            this.contentHash.update(bArr, 0, 8);
        }
        int oneByte3 = readOneByte();
        if (oneByte3 == -1) {
            throw new IOException("Premature end of stream while reading frame header checksum");
        }
        int value = (int) ((this.contentHash.getValue() >> 8) & 255);
        this.contentHash.reset();
        if (oneByte3 != value) {
            throw new IOException("Frame header checksum mismatch");
        }
    }

    private void nextBlock() throws IOException {
        maybeFinishCurrentBlock();
        long jFromLittleEndian = ByteUtils.fromLittleEndian(this.supplier, 4);
        boolean z = ((-2147483648L) & jFromLittleEndian) != 0;
        int i = (int) (jFromLittleEndian & 2147483647L);
        if (i < 0) {
            throw new IOException("Found illegal block with negative size");
        }
        if (i == 0) {
            verifyContentChecksum();
            if (!this.decompressConcatenated) {
                this.endReached = true;
                return;
            } else {
                init(false);
                return;
            }
        }
        InputStream boundedInputStream = new BoundedInputStream(this.inputStream, i);
        if (this.expectBlockChecksum) {
            boundedInputStream = new ChecksumCalculatingInputStream(this.blockHash, boundedInputStream);
        }
        if (z) {
            this.inUncompressed = true;
            this.currentBlock = boundedInputStream;
            return;
        }
        this.inUncompressed = false;
        BlockLZ4CompressorInputStream blockLZ4CompressorInputStream = new BlockLZ4CompressorInputStream(boundedInputStream);
        if (this.expectBlockDependency) {
            blockLZ4CompressorInputStream.prefill(this.blockDependencyBuffer);
        }
        this.currentBlock = blockLZ4CompressorInputStream;
    }

    private void maybeFinishCurrentBlock() throws IOException {
        InputStream inputStream = this.currentBlock;
        if (inputStream != null) {
            inputStream.close();
            this.currentBlock = null;
            if (this.expectBlockChecksum) {
                verifyChecksum(this.blockHash, "block");
                this.blockHash.reset();
            }
        }
    }

    private void verifyContentChecksum() throws IOException {
        if (this.expectContentChecksum) {
            verifyChecksum(this.contentHash, "content");
        }
        this.contentHash.reset();
    }

    private void verifyChecksum(XXHash32 xXHash32, String str) throws IOException {
        byte[] bArr = new byte[4];
        int fully = IOUtils.readFully(this.inputStream, bArr);
        count(fully);
        if (4 != fully) {
            throw new IOException("Premature end of stream while reading " + str + " checksum");
        }
        if (xXHash32.getValue() != ByteUtils.fromLittleEndian(bArr)) {
            throw new IOException(str + " checksum mismatch.");
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

    private int readOnce(byte[] bArr, int i, int i2) throws IOException {
        if (this.inUncompressed) {
            int i3 = this.currentBlock.read(bArr, i, i2);
            count(i3);
            return i3;
        }
        BlockLZ4CompressorInputStream blockLZ4CompressorInputStream = (BlockLZ4CompressorInputStream) this.currentBlock;
        long bytesRead = blockLZ4CompressorInputStream.getBytesRead();
        int i4 = this.currentBlock.read(bArr, i, i2);
        count(blockLZ4CompressorInputStream.getBytesRead() - bytesRead);
        return i4;
    }

    private static boolean isSkippableFrameSignature(byte[] bArr) {
        if ((bArr[0] & SKIPPABLE_FRAME_PREFIX_BYTE_MASK) != 80) {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            if (bArr[i] != SKIPPABLE_FRAME_TRAILER[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private int skipSkippableFrame(byte[] bArr) throws IOException {
        int fully = 4;
        while (fully == 4 && isSkippableFrameSignature(bArr)) {
            long jFromLittleEndian = ByteUtils.fromLittleEndian(this.supplier, 4);
            if (jFromLittleEndian < 0) {
                throw new IOException("Found illegal skippable frame with negative size");
            }
            long jSkip = IOUtils.skip(this.inputStream, jFromLittleEndian);
            count(jSkip);
            if (jFromLittleEndian != jSkip) {
                throw new IOException("Premature end of stream while skipping frame");
            }
            fully = IOUtils.readFully(this.inputStream, bArr);
            count(fully);
        }
        return fully;
    }

    private void appendToBlockDependencyBuffer(byte[] bArr, int i, int i2) {
        int iMin = Math.min(i2, this.blockDependencyBuffer.length);
        if (iMin > 0) {
            byte[] bArr2 = this.blockDependencyBuffer;
            int length = bArr2.length - iMin;
            if (length > 0) {
                System.arraycopy(bArr2, iMin, bArr2, 0, length);
            }
            System.arraycopy(bArr, i, this.blockDependencyBuffer, length, iMin);
        }
    }

    public static boolean matches(byte[] bArr, int i) {
        byte[] bArr2 = LZ4_SIGNATURE;
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
