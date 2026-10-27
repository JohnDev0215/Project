package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.compressors.lzw.LZWInputStream;

/* JADX INFO: loaded from: classes4.dex */
class UnshrinkingInputStream extends LZWInputStream {
    private static final int MAX_CODE_SIZE = 13;
    private static final int MAX_TABLE_SIZE = 8192;
    private final boolean[] isUsed;

    public UnshrinkingInputStream(InputStream inputStream) throws IOException {
        super(inputStream, ByteOrder.LITTLE_ENDIAN);
        setClearCode(9);
        initializeTables(13);
        this.isUsed = new boolean[getPrefixesLength()];
        for (int i = 0; i < 256; i++) {
            this.isUsed[i] = true;
        }
        setTableSize(getClearCode() + 1);
    }

    @Override // org.apache.commons.compress.compressors.lzw.LZWInputStream
    protected int addEntry(int i, byte b) throws IOException {
        int tableSize = getTableSize();
        while (tableSize < 8192 && this.isUsed[tableSize]) {
            tableSize++;
        }
        setTableSize(tableSize);
        int iAddEntry = addEntry(i, b, 8192);
        if (iAddEntry >= 0) {
            this.isUsed[iAddEntry] = true;
        }
        return iAddEntry;
    }

    private void partialClear() {
        boolean[] zArr = new boolean[8192];
        int i = 0;
        while (true) {
            boolean[] zArr2 = this.isUsed;
            if (i >= zArr2.length) {
                break;
            }
            if (zArr2[i] && getPrefix(i) != -1) {
                zArr[getPrefix(i)] = true;
            }
            i++;
        }
        for (int clearCode = getClearCode() + 1; clearCode < 8192; clearCode++) {
            if (!zArr[clearCode]) {
                this.isUsed[clearCode] = false;
                setPrefix(clearCode, -1);
            }
        }
    }

    @Override // org.apache.commons.compress.compressors.lzw.LZWInputStream
    protected int decompressNextSymbol() throws IOException {
        int nextCode = readNextCode();
        if (nextCode < 0) {
            return -1;
        }
        boolean z = false;
        if (nextCode != getClearCode()) {
            if (!this.isUsed[nextCode]) {
                nextCode = addRepeatOfPreviousCode();
                z = true;
            }
            return expandCodeToOutputStack(nextCode, z);
        }
        int nextCode2 = readNextCode();
        if (nextCode2 < 0) {
            throw new IOException("Unexpected EOF;");
        }
        if (nextCode2 == 1) {
            if (getCodeSize() >= 13) {
                throw new IOException("Attempt to increase code size beyond maximum");
            }
            incrementCodeSize();
        } else if (nextCode2 == 2) {
            partialClear();
            setTableSize(getClearCode() + 1);
        } else {
            throw new IOException("Invalid clear code subcode " + nextCode2);
        }
        return 0;
    }
}
