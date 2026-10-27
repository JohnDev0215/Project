package org.tukaani.xz.lz;

import org.tukaani.xz.ArrayCache;

/* JADX INFO: loaded from: classes2.dex */
final class BT4 extends LZEncoder {
    private int cyclicPos;
    private final int cyclicSize;
    private final int depthLimit;
    private final Hash234 hash;
    private int lzPos;
    private final Matches matches;
    private final int[] tree;

    static int getMemoryUsage(int dictSize) {
        return Hash234.getMemoryUsage(dictSize) + (dictSize / 128) + 10;
    }

    BT4(int dictSize, int beforeSizeMin, int readAheadMax, int niceLen, int matchLenMax, int depthLimit, ArrayCache arrayCache) {
        super(dictSize, beforeSizeMin, readAheadMax, niceLen, matchLenMax, arrayCache);
        this.cyclicPos = -1;
        int i = dictSize + 1;
        this.cyclicSize = i;
        this.lzPos = i;
        this.hash = new Hash234(dictSize, arrayCache);
        this.tree = arrayCache.getIntArray(this.cyclicSize * 2, false);
        this.matches = new Matches(niceLen - 1);
        this.depthLimit = depthLimit > 0 ? depthLimit : (niceLen / 2) + 16;
    }

    @Override // org.tukaani.xz.lz.LZEncoder
    public void putArraysToCache(ArrayCache arrayCache) {
        arrayCache.putArray(this.tree);
        this.hash.putArraysToCache(arrayCache);
        super.putArraysToCache(arrayCache);
    }

    private int movePos() {
        int avail = movePos(this.niceLen, 4);
        if (avail != 0) {
            int i = this.lzPos + 1;
            this.lzPos = i;
            if (i == Integer.MAX_VALUE) {
                int normalizationOffset = Integer.MAX_VALUE - this.cyclicSize;
                this.hash.normalize(normalizationOffset);
                normalize(this.tree, this.cyclicSize * 2, normalizationOffset);
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

    /* JADX WARN: Code restructure failed: missing block: B:64:0x018c, code lost:
    
        r2 = r20.tree;
        r2[r12] = 0;
        r2[r11] = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0195, code lost:
    
        return r20.matches;
     */
    @Override // org.tukaani.xz.lz.LZEncoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.tukaani.xz.lz.Matches getMatches() {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.lz.BT4.getMatches():org.tukaani.xz.lz.Matches");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0084, code lost:
    
        r0 = r11.tree;
        r0[r2] = 0;
        r0[r1] = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008a, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void skip(int r12, int r13) {
        /*
            r11 = this;
            int r0 = r11.depthLimit
            int r1 = r11.cyclicPos
            int r2 = r1 << 1
            int r2 = r2 + 1
            int r1 = r1 << 1
            r3 = 0
            r4 = 0
        Lc:
            int r5 = r11.lzPos
            int r5 = r5 - r13
            int r6 = r0 + (-1)
            r7 = 0
            if (r0 == 0) goto L84
            int r0 = r11.cyclicSize
            if (r5 < r0) goto L1a
            goto L84
        L1a:
            int r8 = r11.cyclicPos
            int r9 = r8 - r5
            if (r5 <= r8) goto L21
            r7 = r0
        L21:
            int r9 = r9 + r7
            int r0 = r9 << 1
            int r7 = java.lang.Math.min(r3, r4)
            byte[] r8 = r11.buf
            int r9 = r11.readPos
            int r9 = r9 + r7
            int r9 = r9 - r5
            r8 = r8[r9]
            byte[] r9 = r11.buf
            int r10 = r11.readPos
            int r10 = r10 + r7
            r9 = r9[r10]
            if (r8 != r9) goto L5b
        L39:
            int r7 = r7 + 1
            if (r7 != r12) goto L4a
            int[] r8 = r11.tree
            r9 = r8[r0]
            r8[r1] = r9
            int r9 = r0 + 1
            r9 = r8[r9]
            r8[r2] = r9
            return
        L4a:
            byte[] r8 = r11.buf
            int r9 = r11.readPos
            int r9 = r9 + r7
            int r9 = r9 - r5
            r8 = r8[r9]
            byte[] r9 = r11.buf
            int r10 = r11.readPos
            int r10 = r10 + r7
            r9 = r9[r10]
            if (r8 == r9) goto L39
        L5b:
            byte[] r8 = r11.buf
            int r9 = r11.readPos
            int r9 = r9 + r7
            int r9 = r9 - r5
            r8 = r8[r9]
            r8 = r8 & 255(0xff, float:3.57E-43)
            byte[] r9 = r11.buf
            int r10 = r11.readPos
            int r10 = r10 + r7
            r9 = r9[r10]
            r9 = r9 & 255(0xff, float:3.57E-43)
            if (r8 >= r9) goto L7a
            int[] r8 = r11.tree
            r8[r1] = r13
            int r1 = r0 + 1
            r13 = r8[r1]
            r4 = r7
            goto L82
        L7a:
            int[] r8 = r11.tree
            r8[r2] = r13
            r2 = r0
            r13 = r8[r2]
            r3 = r7
        L82:
            r0 = r6
            goto Lc
        L84:
            int[] r0 = r11.tree
            r0[r2] = r7
            r0[r1] = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.lz.BT4.skip(int, int):void");
    }

    @Override // org.tukaani.xz.lz.LZEncoder
    public void skip(int len) {
        while (true) {
            int len2 = len - 1;
            if (len > 0) {
                int niceLenLimit = this.niceLen;
                int avail = movePos();
                if (avail < niceLenLimit) {
                    if (avail != 0) {
                        niceLenLimit = avail;
                        this.hash.calcHashes(this.buf, this.readPos);
                        int currentMatch = this.hash.getHash4Pos();
                        this.hash.updateTables(this.lzPos);
                        skip(niceLenLimit, currentMatch);
                    }
                } else {
                    this.hash.calcHashes(this.buf, this.readPos);
                    int currentMatch2 = this.hash.getHash4Pos();
                    this.hash.updateTables(this.lzPos);
                    skip(niceLenLimit, currentMatch2);
                }
                len = len2;
            } else {
                return;
            }
        }
    }
}
