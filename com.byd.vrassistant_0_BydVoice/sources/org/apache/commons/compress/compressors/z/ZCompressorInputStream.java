package org.apache.commons.compress.compressors.z;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.compressors.lzw.LZWInputStream;

/* JADX INFO: loaded from: classes4.dex */
public class ZCompressorInputStream extends LZWInputStream {
    private static final int BLOCK_MODE_MASK = 128;
    private static final int MAGIC_1 = 31;
    private static final int MAGIC_2 = 157;
    private static final int MAX_CODE_SIZE_MASK = 31;
    private final boolean blockMode;
    private final int maxCodeSize;
    private long totalCodesRead;

    public ZCompressorInputStream(InputStream inputStream, int i) throws IOException {
        super(inputStream, ByteOrder.LITTLE_ENDIAN);
        int bits = (int) this.in.readBits(8);
        int bits2 = (int) this.in.readBits(8);
        int bits3 = (int) this.in.readBits(8);
        if (bits != 31 || bits2 != 157 || bits3 < 0) {
            throw new IOException("Input is not in .Z format");
        }
        boolean z = (bits3 & 128) != 0;
        this.blockMode = z;
        int i2 = bits3 & 31;
        this.maxCodeSize = i2;
        if (z) {
            setClearCode(9);
        }
        initializeTables(i2, i);
        clearEntries();
    }

    public ZCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, -1);
    }

    private void clearEntries() {
        setTableSize((this.blockMode ? 1 : 0) + 256);
    }

    @Override // org.apache.commons.compress.compressors.lzw.LZWInputStream
    protected int readNextCode() throws IOException {
        int nextCode = super.readNextCode();
        if (nextCode >= 0) {
            this.totalCodesRead++;
        }
        return nextCode;
    }

    private void reAlignReading() throws IOException {
        long j = 8 - (this.totalCodesRead % 8);
        if (j == 8) {
            j = 0;
        }
        for (long j2 = 0; j2 < j; j2++) {
            readNextCode();
        }
        this.in.clearBitCache();
    }

    @Override // org.apache.commons.compress.compressors.lzw.LZWInputStream
    protected int addEntry(int i, byte b) throws IOException {
        int codeSize = 1 << getCodeSize();
        int iAddEntry = addEntry(i, b, codeSize);
        if (getTableSize() == codeSize && getCodeSize() < this.maxCodeSize) {
            reAlignReading();
            incrementCodeSize();
        }
        return iAddEntry;
    }

    @Override // org.apache.commons.compress.compressors.lzw.LZWInputStream
    protected int decompressNextSymbol() throws IOException {
        int nextCode = readNextCode();
        if (nextCode < 0) {
            return -1;
        }
        boolean z = false;
        if (this.blockMode && nextCode == getClearCode()) {
            clearEntries();
            reAlignReading();
            resetCodeSize();
            resetPreviousCode();
            return 0;
        }
        if (nextCode == getTableSize()) {
            addRepeatOfPreviousCode();
            z = true;
        } else if (nextCode > getTableSize()) {
            throw new IOException(String.format("Invalid %d bit code 0x%x", Integer.valueOf(getCodeSize()), Integer.valueOf(nextCode)));
        }
        return expandCodeToOutputStack(nextCode, z);
    }

    public static boolean matches(byte[] bArr, int i) {
        return i > 3 && bArr[0] == 31 && bArr[1] == -99;
    }
}
