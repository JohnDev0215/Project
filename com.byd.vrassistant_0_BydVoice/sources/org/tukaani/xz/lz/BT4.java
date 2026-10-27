package org.tukaani.xz.lz;

import org.tukaani.xz.ArrayCache;

/* JADX INFO: loaded from: classes4.dex */
final class BT4 extends LZEncoder {
    private int cyclicPos;
    private final int cyclicSize;
    private final int depthLimit;
    private final Hash234 hash;
    private int lzPos;
    private final Matches matches;
    private final int[] tree;

    BT4(int i, int i2, int i3, int i4, int i5, int i6, ArrayCache arrayCache) {
        super(i, i2, i3, i4, i5, arrayCache);
        this.cyclicPos = -1;
        int i7 = i + 1;
        this.cyclicSize = i7;
        this.lzPos = i7;
        this.hash = new Hash234(i, arrayCache);
        this.tree = arrayCache.getIntArray(i7 * 2, false);
        this.matches = new Matches(i4 - 1);
        this.depthLimit = i6 <= 0 ? (i4 / 2) + 16 : i6;
    }

    static int getMemoryUsage(int i) {
        return Hash234.getMemoryUsage(i) + (i / 128) + 10;
    }

    private int movePos() {
        int iMovePos = movePos(this.niceLen, 4);
        if (iMovePos != 0) {
            int i = this.lzPos + 1;
            this.lzPos = i;
            if (i == Integer.MAX_VALUE) {
                int i2 = Integer.MAX_VALUE - this.cyclicSize;
                this.hash.normalize(i2);
                normalize(this.tree, this.cyclicSize * 2, i2);
                this.lzPos -= i2;
            }
            int i3 = this.cyclicPos + 1;
            this.cyclicPos = i3;
            if (i3 == this.cyclicSize) {
                this.cyclicPos = 0;
            }
        }
        return iMovePos;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0086, code lost:
    
        r13 = r12.tree;
        r13[r2] = 0;
        r13[r1] = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008c, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void skip(int r13, int r14) {
        /*
            r12 = this;
            int r0 = r12.depthLimit
            int r1 = r12.cyclicPos
            int r2 = r1 << 1
            int r2 = r2 + 1
            int r1 = r1 << 1
            r3 = 0
            r4 = r3
            r5 = r4
        Ld:
            int r6 = r12.lzPos
            int r6 = r6 - r14
            int r7 = r0 + (-1)
            if (r0 == 0) goto L86
            int r0 = r12.cyclicSize
            if (r6 < r0) goto L1a
            goto L86
        L1a:
            int r8 = r12.cyclicPos
            int r9 = r8 - r6
            if (r6 <= r8) goto L21
            goto L22
        L21:
            r0 = r3
        L22:
            int r9 = r9 + r0
            int r0 = r9 << 1
            int r8 = java.lang.Math.min(r4, r5)
            byte[] r9 = r12.buf
            int r10 = r12.readPos
            int r10 = r10 + r8
            int r10 = r10 - r6
            r9 = r9[r10]
            byte[] r10 = r12.buf
            int r11 = r12.readPos
            int r11 = r11 + r8
            r10 = r10[r11]
            if (r9 != r10) goto L5c
        L3a:
            int r8 = r8 + 1
            if (r8 != r13) goto L4b
            int[] r13 = r12.tree
            r14 = r13[r0]
            r13[r1] = r14
            int r0 = r0 + 1
            r14 = r13[r0]
            r13[r2] = r14
            return
        L4b:
            byte[] r9 = r12.buf
            int r10 = r12.readPos
            int r10 = r10 + r8
            int r10 = r10 - r6
            r9 = r9[r10]
            byte[] r10 = r12.buf
            int r11 = r12.readPos
            int r11 = r11 + r8
            r10 = r10[r11]
            if (r9 == r10) goto L3a
        L5c:
            byte[] r9 = r12.buf
            int r10 = r12.readPos
            int r10 = r10 + r8
            int r10 = r10 - r6
            r6 = r9[r10]
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte[] r9 = r12.buf
            int r10 = r12.readPos
            int r10 = r10 + r8
            r9 = r9[r10]
            r9 = r9 & 255(0xff, float:3.57E-43)
            if (r6 >= r9) goto L7c
            int[] r5 = r12.tree
            r5[r1] = r14
            int r0 = r0 + 1
            r14 = r5[r0]
            r1 = r0
            r5 = r8
            goto L84
        L7c:
            int[] r4 = r12.tree
            r4[r2] = r14
            r14 = r4[r0]
            r2 = r0
            r4 = r8
        L84:
            r0 = r7
            goto Ld
        L86:
            int[] r13 = r12.tree
            r13[r2] = r3
            r13[r1] = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.lz.BT4.skip(int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0172, code lost:
    
        r1 = r16.tree;
        r1[r9] = 0;
        r1[r5] = 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c2 A[PHI: r9
  0x00c2: PHI (r9v3 int) = (r9v2 int), (r9v9 int) binds: [B:24:0x0097, B:30:0x00ba] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0143 A[PHI: r14
  0x0143: PHI (r14v2 int) = (r14v1 int), (r14v5 int) binds: [B:48:0x00fb, B:53:0x0112] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // org.tukaani.xz.lz.LZEncoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.tukaani.xz.lz.Matches getMatches() {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.lz.BT4.getMatches():org.tukaani.xz.lz.Matches");
    }

    @Override // org.tukaani.xz.lz.LZEncoder
    public void putArraysToCache(ArrayCache arrayCache) {
        arrayCache.putArray(this.tree);
        this.hash.putArraysToCache(arrayCache);
        super.putArraysToCache(arrayCache);
    }

    @Override // org.tukaani.xz.lz.LZEncoder
    public void skip(int i) {
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return;
            }
            int i3 = this.niceLen;
            int iMovePos = movePos();
            if (iMovePos >= i3) {
                this.hash.calcHashes(this.buf, this.readPos);
                int hash4Pos = this.hash.getHash4Pos();
                this.hash.updateTables(this.lzPos);
                skip(i3, hash4Pos);
            } else if (iMovePos != 0) {
                i3 = iMovePos;
                this.hash.calcHashes(this.buf, this.readPos);
                int hash4Pos2 = this.hash.getHash4Pos();
                this.hash.updateTables(this.lzPos);
                skip(i3, hash4Pos2);
            }
            i = i2;
        }
    }
}
