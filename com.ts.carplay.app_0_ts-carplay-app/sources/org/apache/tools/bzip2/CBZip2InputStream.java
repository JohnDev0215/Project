package org.apache.tools.bzip2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class CBZip2InputStream extends InputStream implements BZip2Constants {
    private static final int NO_RAND_PART_A_STATE = 5;
    private static final int NO_RAND_PART_B_STATE = 6;
    private static final int NO_RAND_PART_C_STATE = 7;
    private static final int RAND_PART_A_STATE = 2;
    private static final int RAND_PART_B_STATE = 3;
    private static final int RAND_PART_C_STATE = 4;
    private static final int START_BLOCK_STATE = 1;
    private boolean blockRandomised;
    private int blockSize100k;
    private int bsBuff;
    private int bsLive;
    private InputStream bsStream;
    int ch2;
    int chPrev;
    private int computedBlockCRC;
    private int computedCombinedCRC;
    int count;
    int i;
    int i2;
    int j2;
    private int last;
    private int nInUse;
    private int origPtr;
    private int storedBlockCRC;
    private int storedCombinedCRC;
    int tPos;
    char z;
    private CRC mCrc = new CRC();
    private boolean[] inUse = new boolean[256];
    private char[] seqToUnseq = new char[256];
    private char[] unseqToSeq = new char[256];
    private char[] selector = new char[BZip2Constants.MAX_SELECTORS];
    private char[] selectorMtf = new char[BZip2Constants.MAX_SELECTORS];
    private int[] unzftab = new int[256];
    private int[][] limit = (int[][]) Array.newInstance((Class<?>) int.class, 6, 258);
    private int[][] base = (int[][]) Array.newInstance((Class<?>) int.class, 6, 258);
    private int[][] perm = (int[][]) Array.newInstance((Class<?>) int.class, 6, 258);
    private int[] minLens = new int[6];
    private boolean streamEnd = false;
    private int currentChar = -1;
    private int currentState = 1;
    int rNToGo = 0;
    int rTPos = 0;
    private char[] ll8 = null;
    private int[] tt = null;

    private static void cadvise() {
        System.out.println("CRC Error");
    }

    private static void badBGLengths() {
        cadvise();
    }

    private static void bitStreamEOF() {
        cadvise();
    }

    private static void compressedStreamEOF() {
        cadvise();
    }

    private void makeMaps() {
        this.nInUse = 0;
        for (int i = 0; i < 256; i++) {
            if (this.inUse[i]) {
                char[] cArr = this.seqToUnseq;
                int i2 = this.nInUse;
                cArr[i2] = (char) i;
                this.unseqToSeq[i] = (char) i2;
                this.nInUse = i2 + 1;
            }
        }
    }

    public CBZip2InputStream(InputStream zStream) {
        bsSetStream(zStream);
        initialize();
        initBlock();
        setupBlock();
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.streamEnd) {
            return -1;
        }
        int retChar = this.currentChar;
        int i = this.currentState;
        if (i == 3) {
            setupRandPartB();
        } else if (i == 4) {
            setupRandPartC();
        } else if (i == 6) {
            setupNoRandPartB();
        } else if (i == 7) {
            setupNoRandPartC();
        }
        return retChar;
    }

    private void initialize() {
        char magic3 = bsGetUChar();
        char magic4 = bsGetUChar();
        if (magic3 != 'h' || magic4 < '1' || magic4 > '9') {
            bsFinishedWithStream();
            this.streamEnd = true;
        } else {
            setDecompressStructureSizes(magic4 - '0');
            this.computedCombinedCRC = 0;
        }
    }

    private void initBlock() {
        char magic1 = bsGetUChar();
        char magic2 = bsGetUChar();
        char magic3 = bsGetUChar();
        char magic4 = bsGetUChar();
        char magic5 = bsGetUChar();
        char magic6 = bsGetUChar();
        if (magic1 == 23 && magic2 == 'r' && magic3 == 'E' && magic4 == '8' && magic5 == 'P' && magic6 == 144) {
            complete();
            return;
        }
        if (magic1 != '1' || magic2 != 'A' || magic3 != 'Y' || magic4 != '&' || magic5 != 'S' || magic6 != 'Y') {
            badBlockHeader();
            this.streamEnd = true;
            return;
        }
        this.storedBlockCRC = bsGetInt32();
        if (bsR(1) == 1) {
            this.blockRandomised = true;
        } else {
            this.blockRandomised = false;
        }
        getAndMoveToFrontDecode();
        this.mCrc.initialiseCRC();
        this.currentState = 1;
    }

    private void endBlock() {
        int finalCRC = this.mCrc.getFinalCRC();
        this.computedBlockCRC = finalCRC;
        if (this.storedBlockCRC != finalCRC) {
            crcError();
        }
        int i = this.computedCombinedCRC;
        int i2 = (i >>> 31) | (i << 1);
        this.computedCombinedCRC = i2;
        this.computedCombinedCRC = i2 ^ this.computedBlockCRC;
    }

    private void complete() {
        int iBsGetInt32 = bsGetInt32();
        this.storedCombinedCRC = iBsGetInt32;
        if (iBsGetInt32 != this.computedCombinedCRC) {
            crcError();
        }
        bsFinishedWithStream();
        this.streamEnd = true;
    }

    private static void blockOverrun() {
        cadvise();
    }

    private static void badBlockHeader() {
        cadvise();
    }

    private static void crcError() {
        cadvise();
    }

    private void bsFinishedWithStream() {
        try {
            if (this.bsStream != null && this.bsStream != System.in) {
                this.bsStream.close();
                this.bsStream = null;
            }
        } catch (IOException e) {
        }
    }

    private void bsSetStream(InputStream f) {
        this.bsStream = f;
        this.bsLive = 0;
        this.bsBuff = 0;
    }

    private int bsR(int n) {
        while (true) {
            int i = this.bsLive;
            if (i < n) {
                char thech = 0;
                try {
                    thech = (char) this.bsStream.read();
                } catch (IOException e) {
                    compressedStreamEOF();
                }
                if (thech == 65535) {
                    compressedStreamEOF();
                }
                char zzi = thech;
                this.bsBuff = (this.bsBuff << 8) | (zzi & 255);
                this.bsLive += 8;
            } else {
                int v = (this.bsBuff >> (i - n)) & ((1 << n) - 1);
                this.bsLive = i - n;
                return v;
            }
        }
    }

    private char bsGetUChar() {
        return (char) bsR(8);
    }

    private int bsGetint() {
        int u = (0 << 8) | bsR(8);
        return (((((u << 8) | bsR(8)) << 8) | bsR(8)) << 8) | bsR(8);
    }

    private int bsGetIntVS(int numBits) {
        return bsR(numBits);
    }

    private int bsGetInt32() {
        return bsGetint();
    }

    private void hbCreateDecodeTables(int[] limit, int[] base, int[] perm, char[] length, int minLen, int maxLen, int alphaSize) {
        int pp = 0;
        for (int i = minLen; i <= maxLen; i++) {
            for (int j = 0; j < alphaSize; j++) {
                if (length[j] == i) {
                    perm[pp] = j;
                    pp++;
                }
            }
        }
        for (int i2 = 0; i2 < 23; i2++) {
            base[i2] = 0;
        }
        for (int i3 = 0; i3 < alphaSize; i3++) {
            int i4 = length[i3] + 1;
            base[i4] = base[i4] + 1;
        }
        for (int i5 = 1; i5 < 23; i5++) {
            base[i5] = base[i5] + base[i5 - 1];
        }
        for (int i6 = 0; i6 < 23; i6++) {
            limit[i6] = 0;
        }
        int vec = 0;
        for (int i7 = minLen; i7 <= maxLen; i7++) {
            int vec2 = vec + (base[i7 + 1] - base[i7]);
            limit[i7] = vec2 - 1;
            vec = vec2 << 1;
        }
        for (int i8 = minLen + 1; i8 <= maxLen; i8++) {
            base[i8] = ((limit[i8 - 1] + 1) << 1) - base[i8];
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:50:0x0106 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void recvDecodingTables() {
        /*
            Method dump skipped, instruction units count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.bzip2.CBZip2InputStream.recvDecodingTables():void");
    }

    private void getAndMoveToFrontDecode() {
        int i;
        int i2;
        int i3;
        int i4;
        char c;
        int i5;
        int i6;
        char c2;
        int i7;
        char c3;
        char[] cArr = new char[256];
        int i8 = BZip2Constants.baseBlockSize * this.blockSize100k;
        this.origPtr = bsGetIntVS(24);
        recvDecodingTables();
        int i9 = this.nInUse + 1;
        for (int i10 = 0; i10 <= 255; i10++) {
            this.unzftab[i10] = 0;
        }
        for (int i11 = 0; i11 <= 255; i11++) {
            cArr[i11] = (char) i11;
        }
        this.last = -1;
        int i12 = 49;
        char c4 = this.selector[0];
        int i13 = this.minLens[c4];
        int iBsR = bsR(i13);
        while (iBsR > this.limit[c4][i13]) {
            int i14 = i13 + 1;
            while (true) {
                i7 = this.bsLive;
                if (i7 >= 1) {
                    break;
                }
                try {
                    c3 = (char) this.bsStream.read();
                } catch (IOException e) {
                    compressedStreamEOF();
                    c3 = 0;
                }
                if (c3 == 65535) {
                    compressedStreamEOF();
                }
                this.bsBuff = (c3 & 255) | (this.bsBuff << 8);
                this.bsLive += 8;
            }
            int i15 = (this.bsBuff >> (i7 - 1)) & 1;
            this.bsLive = i7 - 1;
            iBsR = (iBsR << 1) | i15;
            i13 = i14;
        }
        int i16 = this.perm[c4][iBsR - this.base[c4][i13]];
        int i17 = 0;
        while (i16 != i9) {
            if (i16 == 0 || i16 == 1) {
                int i18 = 1;
                int i19 = -1;
                while (true) {
                    if (i16 == 0) {
                        i = i19 + (i18 * 1);
                    } else if (i16 != 1) {
                        i = i19;
                    } else {
                        i = i19 + (i18 * 2);
                    }
                    i18 *= 2;
                    if (i12 != 0) {
                        i2 = i17;
                    } else {
                        i2 = i17 + 1;
                        i12 = 50;
                    }
                    i3 = i12 - 1;
                    char c5 = this.selector[i2];
                    int i20 = this.minLens[c5];
                    int iBsR2 = bsR(i20);
                    while (iBsR2 > this.limit[c5][i20]) {
                        int i21 = i20 + 1;
                        while (true) {
                            i4 = this.bsLive;
                            if (i4 >= 1) {
                                break;
                            }
                            try {
                                c = (char) this.bsStream.read();
                            } catch (IOException e2) {
                                compressedStreamEOF();
                                c = 0;
                            }
                            if (c == 65535) {
                                compressedStreamEOF();
                            }
                            this.bsBuff = (c & 255) | (this.bsBuff << 8);
                            this.bsLive += 8;
                        }
                        int i22 = (this.bsBuff >> (i4 - 1)) & 1;
                        this.bsLive = i4 - 1;
                        iBsR2 = (iBsR2 << 1) | i22;
                        i20 = i21;
                    }
                    i16 = this.perm[c5][iBsR2 - this.base[c5][i20]];
                    if (i16 != 0 && i16 != 1) {
                        break;
                    }
                    i19 = i;
                    i17 = i2;
                    i12 = i3;
                }
                int i23 = i + 1;
                char c6 = this.seqToUnseq[cArr[0]];
                int[] iArr = this.unzftab;
                iArr[c6] = iArr[c6] + i23;
                while (i23 > 0) {
                    int i24 = this.last + 1;
                    this.last = i24;
                    this.ll8[i24] = c6;
                    i23--;
                }
                if (this.last >= i8) {
                    blockOverrun();
                }
                i17 = i2;
                i12 = i3;
            } else {
                int i25 = this.last + 1;
                this.last = i25;
                if (i25 >= i8) {
                    blockOverrun();
                }
                int i26 = i16 - 1;
                char c7 = cArr[i26];
                int[] iArr2 = this.unzftab;
                char[] cArr2 = this.seqToUnseq;
                char c8 = cArr2[c7];
                iArr2[c8] = iArr2[c8] + 1;
                this.ll8[this.last] = cArr2[c7];
                while (i26 > 3) {
                    int i27 = i26 - 1;
                    cArr[i26] = cArr[i27];
                    int i28 = i26 - 2;
                    cArr[i27] = cArr[i28];
                    int i29 = i26 - 3;
                    cArr[i28] = cArr[i29];
                    cArr[i29] = cArr[i26 - 4];
                    i26 -= 4;
                }
                while (i26 > 0) {
                    cArr[i26] = cArr[i26 - 1];
                    i26--;
                }
                cArr[0] = c7;
                if (i12 != 0) {
                    i5 = i17;
                } else {
                    i5 = i17 + 1;
                    i12 = 50;
                }
                int i30 = i12 - 1;
                char c9 = this.selector[i5];
                int i31 = this.minLens[c9];
                int iBsR3 = bsR(i31);
                while (iBsR3 > this.limit[c9][i31]) {
                    int i32 = i31 + 1;
                    while (true) {
                        i6 = this.bsLive;
                        if (i6 >= 1) {
                            break;
                        }
                        try {
                            c2 = (char) this.bsStream.read();
                        } catch (IOException e3) {
                            compressedStreamEOF();
                            c2 = 0;
                        }
                        this.bsBuff = (c2 & 255) | (this.bsBuff << 8);
                        this.bsLive += 8;
                    }
                    int i33 = (this.bsBuff >> (i6 - 1)) & 1;
                    this.bsLive = i6 - 1;
                    iBsR3 = (iBsR3 << 1) | i33;
                    i31 = i32;
                }
                i16 = this.perm[c9][iBsR3 - this.base[c9][i31]];
                i17 = i5;
                i12 = i30;
            }
        }
    }

    private void setupBlock() {
        int[] cftab = new int[257];
        cftab[0] = 0;
        this.i = 1;
        while (true) {
            int i = this.i;
            if (i > 256) {
                break;
            }
            cftab[i] = this.unzftab[i - 1];
            this.i = i + 1;
        }
        this.i = 1;
        while (true) {
            int i2 = this.i;
            if (i2 > 256) {
                break;
            }
            cftab[i2] = cftab[i2] + cftab[i2 - 1];
            this.i = i2 + 1;
        }
        this.i = 0;
        while (true) {
            int i3 = this.i;
            if (i3 > this.last) {
                break;
            }
            char ch = this.ll8[i3];
            this.tt[cftab[ch]] = i3;
            cftab[ch] = cftab[ch] + 1;
            this.i = i3 + 1;
        }
        this.tPos = this.tt[this.origPtr];
        this.count = 0;
        this.i2 = 0;
        this.ch2 = 256;
        if (this.blockRandomised) {
            this.rNToGo = 0;
            this.rTPos = 0;
            setupRandPartA();
            return;
        }
        setupNoRandPartA();
    }

    private void setupRandPartA() {
        if (this.i2 <= this.last) {
            this.chPrev = this.ch2;
            char[] cArr = this.ll8;
            int i = this.tPos;
            this.ch2 = cArr[i];
            this.tPos = this.tt[i];
            if (this.rNToGo == 0) {
                int[] iArr = BZip2Constants.rNums;
                int i2 = this.rTPos;
                this.rNToGo = iArr[i2];
                int i3 = i2 + 1;
                this.rTPos = i3;
                if (i3 == 512) {
                    this.rTPos = 0;
                }
            }
            int i4 = this.rNToGo - 1;
            this.rNToGo = i4;
            int i5 = this.ch2 ^ (i4 == 1 ? 1 : 0);
            this.ch2 = i5;
            this.i2++;
            this.currentChar = i5;
            this.currentState = 3;
            this.mCrc.updateCRC(i5);
            return;
        }
        endBlock();
        initBlock();
        setupBlock();
    }

    private void setupNoRandPartA() {
        int i = this.i2;
        if (i <= this.last) {
            this.chPrev = this.ch2;
            char[] cArr = this.ll8;
            int i2 = this.tPos;
            char c = cArr[i2];
            this.ch2 = c;
            this.tPos = this.tt[i2];
            this.i2 = i + 1;
            this.currentChar = c;
            this.currentState = 6;
            this.mCrc.updateCRC(c);
            return;
        }
        endBlock();
        initBlock();
        setupBlock();
    }

    private void setupRandPartB() {
        if (this.ch2 != this.chPrev) {
            this.currentState = 2;
            this.count = 1;
            setupRandPartA();
            return;
        }
        int i = this.count + 1;
        this.count = i;
        if (i >= 4) {
            char[] cArr = this.ll8;
            int i2 = this.tPos;
            this.z = cArr[i2];
            this.tPos = this.tt[i2];
            if (this.rNToGo == 0) {
                int[] iArr = BZip2Constants.rNums;
                int i3 = this.rTPos;
                this.rNToGo = iArr[i3];
                int i4 = i3 + 1;
                this.rTPos = i4;
                if (i4 == 512) {
                    this.rTPos = 0;
                }
            }
            int i5 = this.rNToGo - 1;
            this.rNToGo = i5;
            this.z = (char) (this.z ^ (i5 != 1 ? (char) 0 : (char) 1));
            this.j2 = 0;
            this.currentState = 4;
            setupRandPartC();
            return;
        }
        this.currentState = 2;
        setupRandPartA();
    }

    private void setupRandPartC() {
        if (this.j2 < this.z) {
            int i = this.ch2;
            this.currentChar = i;
            this.mCrc.updateCRC(i);
            this.j2++;
            return;
        }
        this.currentState = 2;
        this.i2++;
        this.count = 0;
        setupRandPartA();
    }

    private void setupNoRandPartB() {
        if (this.ch2 != this.chPrev) {
            this.currentState = 5;
            this.count = 1;
            setupNoRandPartA();
            return;
        }
        int i = this.count + 1;
        this.count = i;
        if (i >= 4) {
            char[] cArr = this.ll8;
            int i2 = this.tPos;
            this.z = cArr[i2];
            this.tPos = this.tt[i2];
            this.currentState = 7;
            this.j2 = 0;
            setupNoRandPartC();
            return;
        }
        this.currentState = 5;
        setupNoRandPartA();
    }

    private void setupNoRandPartC() {
        if (this.j2 < this.z) {
            int i = this.ch2;
            this.currentChar = i;
            this.mCrc.updateCRC(i);
            this.j2++;
            return;
        }
        this.currentState = 5;
        this.i2++;
        this.count = 0;
        setupNoRandPartA();
    }

    private void setDecompressStructureSizes(int newSize100k) {
        if (newSize100k >= 0 && newSize100k <= 9) {
            int i = this.blockSize100k;
        }
        this.blockSize100k = newSize100k;
        if (newSize100k == 0) {
            return;
        }
        int n = BZip2Constants.baseBlockSize * newSize100k;
        this.ll8 = new char[n];
        this.tt = new int[n];
    }
}
