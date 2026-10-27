package org.apache.commons.compress.compressors.lzw;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.BitInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;

/* JADX INFO: loaded from: classes4.dex */
public abstract class LZWInputStream extends CompressorInputStream implements InputStreamStatistics {
    protected static final int DEFAULT_CODE_SIZE = 9;
    protected static final int UNUSED_PREFIX = -1;
    private byte[] characters;
    protected final BitInputStream in;
    private byte[] outputStack;
    private int outputStackLocation;
    private int[] prefixes;
    private byte previousCodeFirstChar;
    private int tableSize;
    private final byte[] oneByte = new byte[1];
    private int clearCode = -1;
    private int codeSize = 9;
    private int previousCode = -1;

    protected abstract int addEntry(int i, byte b) throws IOException;

    protected abstract int decompressNextSymbol() throws IOException;

    protected LZWInputStream(InputStream inputStream, ByteOrder byteOrder) {
        this.in = new BitInputStream(inputStream, byteOrder);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = read(this.oneByte);
        return i < 0 ? i : this.oneByte[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int fromStack = readFromStack(bArr, i, i2);
        while (true) {
            int i3 = i2 - fromStack;
            if (i3 > 0) {
                int iDecompressNextSymbol = decompressNextSymbol();
                if (iDecompressNextSymbol < 0) {
                    if (fromStack <= 0) {
                        return iDecompressNextSymbol;
                    }
                    count(fromStack);
                    return fromStack;
                }
                fromStack += readFromStack(bArr, i + fromStack, i3);
            } else {
                count(fromStack);
                return fromStack;
            }
        }
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.in.getBytesRead();
    }

    protected void setClearCode(int i) {
        this.clearCode = 1 << (i - 1);
    }

    protected void initializeTables(int i, int i2) throws MemoryLimitException {
        if (i <= 0) {
            throw new IllegalArgumentException("maxCodeSize is " + i + ", must be bigger than 0");
        }
        if (i2 > -1) {
            long j = (((long) (1 << i)) * 6) >> 10;
            if (j > i2) {
                throw new MemoryLimitException(j, i2);
            }
        }
        initializeTables(i);
    }

    protected void initializeTables(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxCodeSize is " + i + ", must be bigger than 0");
        }
        int i2 = 1 << i;
        this.prefixes = new int[i2];
        this.characters = new byte[i2];
        this.outputStack = new byte[i2];
        this.outputStackLocation = i2;
        for (int i3 = 0; i3 < 256; i3++) {
            this.prefixes[i3] = -1;
            this.characters[i3] = (byte) i3;
        }
    }

    protected int readNextCode() throws IOException {
        int i = this.codeSize;
        if (i > 31) {
            throw new IllegalArgumentException("Code size must not be bigger than 31");
        }
        return (int) this.in.readBits(i);
    }

    protected int addEntry(int i, byte b, int i2) {
        int i3 = this.tableSize;
        if (i3 >= i2) {
            return -1;
        }
        this.prefixes[i3] = i;
        this.characters[i3] = b;
        this.tableSize = i3 + 1;
        return i3;
    }

    protected int addRepeatOfPreviousCode() throws IOException {
        int i = this.previousCode;
        if (i == -1) {
            throw new IOException("The first code can't be a reference to its preceding code");
        }
        return addEntry(i, this.previousCodeFirstChar);
    }

    protected int expandCodeToOutputStack(int i, boolean z) throws IOException {
        int i2 = i;
        while (i2 >= 0) {
            byte[] bArr = this.outputStack;
            int i3 = this.outputStackLocation - 1;
            this.outputStackLocation = i3;
            bArr[i3] = this.characters[i2];
            i2 = this.prefixes[i2];
        }
        int i4 = this.previousCode;
        if (i4 != -1 && !z) {
            addEntry(i4, this.outputStack[this.outputStackLocation]);
        }
        this.previousCode = i;
        byte[] bArr2 = this.outputStack;
        int i5 = this.outputStackLocation;
        this.previousCodeFirstChar = bArr2[i5];
        return i5;
    }

    private int readFromStack(byte[] bArr, int i, int i2) {
        int length = this.outputStack.length - this.outputStackLocation;
        if (length <= 0) {
            return 0;
        }
        int iMin = Math.min(length, i2);
        System.arraycopy(this.outputStack, this.outputStackLocation, bArr, i, iMin);
        this.outputStackLocation += iMin;
        return iMin;
    }

    protected int getCodeSize() {
        return this.codeSize;
    }

    protected void resetCodeSize() {
        setCodeSize(9);
    }

    protected void setCodeSize(int i) {
        this.codeSize = i;
    }

    protected void incrementCodeSize() {
        this.codeSize++;
    }

    protected void resetPreviousCode() {
        this.previousCode = -1;
    }

    protected int getPrefix(int i) {
        return this.prefixes[i];
    }

    protected void setPrefix(int i, int i2) {
        this.prefixes[i] = i2;
    }

    protected int getPrefixesLength() {
        return this.prefixes.length;
    }

    protected int getClearCode() {
        return this.clearCode;
    }

    protected int getTableSize() {
        return this.tableSize;
    }

    protected void setTableSize(int i) {
        this.tableSize = i;
    }
}
