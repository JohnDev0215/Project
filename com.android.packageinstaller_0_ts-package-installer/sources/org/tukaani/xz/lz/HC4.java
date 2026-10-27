package org.tukaani.xz.lz;

import org.tukaani.xz.ArrayCache;

/* JADX INFO: loaded from: classes2.dex */
final class HC4 extends LZEncoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final int[] chain;
    private int cyclicPos;
    private final int cyclicSize;
    private final int depthLimit;
    private final Hash234 hash;
    private int lzPos;
    private final Matches matches;

    static int getMemoryUsage(int dictSize) {
        return Hash234.getMemoryUsage(dictSize) + (dictSize / 256) + 10;
    }

    HC4(int dictSize, int beforeSizeMin, int readAheadMax, int niceLen, int matchLenMax, int depthLimit, ArrayCache arrayCache) {
        super(dictSize, beforeSizeMin, readAheadMax, niceLen, matchLenMax, arrayCache);
        this.cyclicPos = -1;
        this.hash = new Hash234(dictSize, arrayCache);
        int i = dictSize + 1;
        this.cyclicSize = i;
        this.chain = arrayCache.getIntArray(i, false);
        this.lzPos = this.cyclicSize;
        this.matches = new Matches(niceLen - 1);
        this.depthLimit = depthLimit > 0 ? depthLimit : (niceLen / 4) + 4;
    }

    @Override // org.tukaani.xz.lz.LZEncoder
    public void putArraysToCache(ArrayCache arrayCache) {
        arrayCache.putArray(this.chain);
        this.hash.putArraysToCache(arrayCache);
        super.putArraysToCache(arrayCache);
    }

    private int movePos() {
        int avail = movePos(4, 4);
        if (avail != 0) {
            int i = this.lzPos + 1;
            this.lzPos = i;
            if (i == Integer.MAX_VALUE) {
                int normalizationOffset = Integer.MAX_VALUE - this.cyclicSize;
                this.hash.normalize(normalizationOffset);
                normalize(this.chain, this.cyclicSize, normalizationOffset);
                this.lzPos -= normalizationOffset;
            }
            int i2 = this.cyclicPos + 1;
            this.cyclicPos = i2;
            if (i2 == this.cyclicSize) {
                this.cyclicPos = 0;
            }
        }
        return avail;
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x013f, code lost:
    
        return r15.matches;
     */
    @Override // org.tukaani.xz.lz.LZEncoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.tukaani.xz.lz.Matches getMatches() {
        /*
            Method dump skipped, instruction units count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.lz.HC4.getMatches():org.tukaani.xz.lz.Matches");
    }

    @Override // org.tukaani.xz.lz.LZEncoder
    public void skip(int len) {
        while (true) {
            int len2 = len - 1;
            if (len > 0) {
                if (movePos() != 0) {
                    this.hash.calcHashes(this.buf, this.readPos);
                    this.chain[this.cyclicPos] = this.hash.getHash4Pos();
                    this.hash.updateTables(this.lzPos);
                }
                len = len2;
            } else {
                return;
            }
        }
    }
}
