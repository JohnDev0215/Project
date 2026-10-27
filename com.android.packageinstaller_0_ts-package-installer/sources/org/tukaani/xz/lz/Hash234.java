package org.tukaani.xz.lz;

import org.tukaani.xz.ArrayCache;

/* JADX INFO: loaded from: classes2.dex */
final class Hash234 extends CRC32Hash {
    private static final int HASH_2_MASK = 1023;
    private static final int HASH_2_SIZE = 1024;
    private static final int HASH_3_MASK = 65535;
    private static final int HASH_3_SIZE = 65536;
    private final int[] hash2Table;
    private final int[] hash3Table;
    private final int hash4Mask;
    private final int hash4Size;
    private final int[] hash4Table;
    private int hash2Value = 0;
    private int hash3Value = 0;
    private int hash4Value = 0;

    static int getHash4Size(int dictSize) {
        int h = dictSize - 1;
        int h2 = h | (h >>> 1);
        int h3 = h2 | (h2 >>> 2);
        int h4 = h3 | (h3 >>> 4);
        int h5 = ((h4 | (h4 >>> 8)) >>> 1) | 65535;
        if (h5 > 16777216) {
            h5 >>>= 1;
        }
        return h5 + 1;
    }

    static int getMemoryUsage(int dictSize) {
        return ((getHash4Size(dictSize) + 66560) / 256) + 4;
    }

    Hash234(int dictSize, ArrayCache arrayCache) {
        this.hash2Table = arrayCache.getIntArray(1024, true);
        this.hash3Table = arrayCache.getIntArray(65536, true);
        int hash4Size = getHash4Size(dictSize);
        this.hash4Size = hash4Size;
        this.hash4Table = arrayCache.getIntArray(hash4Size, true);
        this.hash4Mask = this.hash4Size - 1;
    }

    void putArraysToCache(ArrayCache arrayCache) {
        arrayCache.putArray(this.hash4Table);
        arrayCache.putArray(this.hash3Table);
        arrayCache.putArray(this.hash2Table);
    }

    void calcHashes(byte[] buf, int off) {
        int temp = crcTable[buf[off] & 255] ^ (buf[off + 1] & 255);
        this.hash2Value = temp & HASH_2_MASK;
        int temp2 = temp ^ ((buf[off + 2] & 255) << 8);
        this.hash3Value = 65535 & temp2;
        this.hash4Value = this.hash4Mask & (temp2 ^ (crcTable[buf[off + 3] & 255] << 5));
    }

    int getHash2Pos() {
        return this.hash2Table[this.hash2Value];
    }

    int getHash3Pos() {
        return this.hash3Table[this.hash3Value];
    }

    int getHash4Pos() {
        return this.hash4Table[this.hash4Value];
    }

    void updateTables(int pos) {
        this.hash2Table[this.hash2Value] = pos;
        this.hash3Table[this.hash3Value] = pos;
        this.hash4Table[this.hash4Value] = pos;
    }

    void normalize(int normalizeOffset) {
        LZEncoder.normalize(this.hash2Table, 1024, normalizeOffset);
        LZEncoder.normalize(this.hash3Table, 65536, normalizeOffset);
        LZEncoder.normalize(this.hash4Table, this.hash4Size, normalizeOffset);
    }
}
