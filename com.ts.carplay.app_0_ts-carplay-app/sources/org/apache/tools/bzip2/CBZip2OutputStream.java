package org.apache.tools.bzip2;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class CBZip2OutputStream extends OutputStream implements BZip2Constants {
    protected static final int CLEARMASK = -2097153;
    protected static final int DEPTH_THRESH = 10;
    protected static final int GREATER_ICOST = 15;
    protected static final int LESSER_ICOST = 0;
    protected static final int QSORT_STACK_SIZE = 1000;
    protected static final int SETMASK = 2097152;
    protected static final int SMALL_THRESH = 20;
    private int allowableBlockSize;
    private char[] block;
    private int blockCRC;
    boolean blockRandomised;
    int blockSize100k;
    int bsBuff;
    int bsLive;
    private OutputStream bsStream;
    int bytesOut;
    boolean closed;
    private int combinedCRC;
    private int currentChar;
    private boolean firstAttempt;
    private int[] ftab;
    private boolean[] inUse;
    private int[] incs;
    int last;
    CRC mCrc;
    private int[] mtfFreq;
    private int nBlocksRandomised;
    private int nInUse;
    private int nMTF;
    int origPtr;
    private int[] quadrant;
    private int runLength;
    private char[] selector;
    private char[] selectorMtf;
    private char[] seqToUnseq;
    private short[] szptr;
    private char[] unseqToSeq;
    private int workDone;
    private int workFactor;
    private int workLimit;
    private int[] zptr;

    /* JADX INFO: renamed from: org.apache.tools.bzip2.CBZip2OutputStream$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    private static void panic() {
        System.out.println("panic");
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

    protected static void hbMakeCodeLengths(char[] len, int[] freq, int alphaSize, int maxLen) {
        int i;
        int i2 = 260;
        int[] heap = new int[260];
        int nHeap = 516;
        int[] weight = new int[516];
        int[] parent = new int[516];
        int i3 = 0;
        while (true) {
            i = 1;
            if (i3 >= alphaSize) {
                break;
            }
            weight[i3 + 1] = (freq[i3] == 0 ? 1 : freq[i3]) << 8;
            i3++;
            i2 = 260;
            nHeap = 516;
        }
        int i4 = 0;
        while (true) {
            int nNodes = alphaSize;
            heap[i4] = i4;
            weight[i4] = i4;
            parent[i4] = -2;
            int nHeap2 = 1;
            int i5 = 0;
            while (nHeap2 <= alphaSize) {
                parent[nHeap2] = -1;
                int nHeap3 = i5 + 1;
                heap[nHeap3] = nHeap2;
                int zz = nHeap3;
                int tmp = heap[zz];
                while (weight[tmp] < weight[heap[zz >> 1]]) {
                    heap[zz] = heap[zz >> 1];
                    zz >>= 1;
                }
                heap[zz] = tmp;
                nHeap2++;
                i5 = nHeap3;
                i2 = 260;
                nHeap = 516;
                i = 1;
                i4 = 0;
            }
            if (i5 >= i2) {
                panic();
            }
            while (i5 > i) {
                int n1 = heap[i];
                heap[i] = heap[i5];
                int nHeap4 = i5 - 1;
                int zz2 = 1;
                int tmp2 = heap[1];
                while (true) {
                    int yy = zz2 << 1;
                    if (yy > nHeap4) {
                        break;
                    }
                    if (yy < nHeap4 && weight[heap[yy + 1]] < weight[heap[yy]]) {
                        yy++;
                    }
                    if (weight[tmp2] < weight[heap[yy]]) {
                        break;
                    }
                    heap[zz2] = heap[yy];
                    zz2 = yy;
                }
                heap[zz2] = tmp2;
                int n2 = heap[i];
                heap[i] = heap[nHeap4];
                int nHeap5 = nHeap4 - 1;
                int zz3 = 1;
                int tmp3 = heap[1];
                while (true) {
                    int yy2 = zz3 << 1;
                    if (yy2 > nHeap5) {
                        break;
                    }
                    if (yy2 < nHeap5 && weight[heap[yy2 + 1]] < weight[heap[yy2]]) {
                        yy2++;
                    }
                    if (weight[tmp3] < weight[heap[yy2]]) {
                        break;
                    }
                    int nNodes2 = heap[yy2];
                    heap[zz3] = nNodes2;
                    zz3 = yy2;
                }
                heap[zz3] = tmp3;
                int nNodes3 = nNodes + 1;
                parent[n2] = nNodes3;
                parent[n1] = nNodes3;
                weight[nNodes3] = ((weight[n1] & (-256)) + (weight[n2] & (-256))) | ((((weight[n1] & 255) > (weight[n2] & 255) ? weight[n1] : weight[n2]) & 255) + i);
                parent[nNodes3] = -1;
                int nHeap6 = nHeap5 + 1;
                heap[nHeap6] = nNodes3;
                int zz4 = nHeap6;
                int tmp4 = heap[zz4];
                while (weight[tmp4] < weight[heap[zz4 >> 1]]) {
                    heap[zz4] = heap[zz4 >> 1];
                    zz4 >>= 1;
                }
                heap[zz4] = tmp4;
                nNodes = nNodes3;
                i5 = nHeap6;
                i2 = 260;
                i4 = 0;
                nHeap = 516;
            }
            if (nNodes >= nHeap) {
                panic();
            }
            int i6 = 1;
            boolean tooLong = false;
            while (i6 <= alphaSize) {
                int j = 0;
                int k = i6;
                while (parent[k] >= 0) {
                    k = parent[k];
                    j++;
                }
                len[i6 - 1] = (char) j;
                if (j > maxLen) {
                    tooLong = true;
                }
                i6++;
                i2 = 260;
            }
            if (tooLong) {
                for (int i7 = 1; i7 < alphaSize; i7++) {
                    int j2 = weight[i7] >> 8;
                    weight[i7] = ((j2 / 2) + 1) << 8;
                }
            } else {
                return;
            }
        }
    }

    public CBZip2OutputStream(OutputStream inStream) throws IOException {
        this(inStream, 9);
    }

    public CBZip2OutputStream(OutputStream inStream, int inBlockSize) throws IOException {
        this.mCrc = new CRC();
        this.inUse = new boolean[256];
        this.seqToUnseq = new char[256];
        this.unseqToSeq = new char[256];
        this.selector = new char[BZip2Constants.MAX_SELECTORS];
        this.selectorMtf = new char[BZip2Constants.MAX_SELECTORS];
        this.mtfFreq = new int[258];
        this.currentChar = -1;
        this.runLength = 0;
        this.closed = false;
        this.incs = new int[]{1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
        this.block = null;
        this.quadrant = null;
        this.zptr = null;
        this.ftab = null;
        bsSetStream(inStream);
        this.workFactor = 50;
        inBlockSize = inBlockSize > 9 ? 9 : inBlockSize;
        this.blockSize100k = inBlockSize < 1 ? 1 : inBlockSize;
        allocateCompressStructures();
        initialize();
        initBlock();
    }

    @Override // java.io.OutputStream
    public void write(int bv) throws IOException {
        int b = (bv + 256) % 256;
        int i = this.currentChar;
        if (i != -1) {
            if (i == b) {
                int i2 = this.runLength + 1;
                this.runLength = i2;
                if (i2 > 254) {
                    writeRun();
                    this.currentChar = -1;
                    this.runLength = 0;
                    return;
                }
                return;
            }
            writeRun();
            this.runLength = 1;
            this.currentChar = b;
            return;
        }
        this.currentChar = b;
        this.runLength++;
    }

    private void writeRun() throws IOException {
        int i;
        if (this.last < this.allowableBlockSize) {
            this.inUse[this.currentChar] = true;
            int i2 = 0;
            while (true) {
                i = this.runLength;
                if (i2 >= i) {
                    break;
                }
                this.mCrc.updateCRC((char) this.currentChar);
                i2++;
            }
            if (i == 1) {
                int i3 = this.last + 1;
                this.last = i3;
                this.block[i3 + 1] = (char) this.currentChar;
                return;
            }
            if (i == 2) {
                int i4 = this.last + 1;
                this.last = i4;
                char[] cArr = this.block;
                int i5 = this.currentChar;
                cArr[i4 + 1] = (char) i5;
                int i6 = i4 + 1;
                this.last = i6;
                cArr[i6 + 1] = (char) i5;
                return;
            }
            if (i == 3) {
                int i7 = this.last + 1;
                this.last = i7;
                char[] cArr2 = this.block;
                int i8 = this.currentChar;
                cArr2[i7 + 1] = (char) i8;
                int i9 = i7 + 1;
                this.last = i9;
                cArr2[i9 + 1] = (char) i8;
                int i10 = i9 + 1;
                this.last = i10;
                cArr2[i10 + 1] = (char) i8;
                return;
            }
            this.inUse[i - 4] = true;
            int i11 = this.last + 1;
            this.last = i11;
            char[] cArr3 = this.block;
            int i12 = this.currentChar;
            cArr3[i11 + 1] = (char) i12;
            int i13 = i11 + 1;
            this.last = i13;
            cArr3[i13 + 1] = (char) i12;
            int i14 = i13 + 1;
            this.last = i14;
            cArr3[i14 + 1] = (char) i12;
            int i15 = i14 + 1;
            this.last = i15;
            cArr3[i15 + 1] = (char) i12;
            int i16 = i15 + 1;
            this.last = i16;
            cArr3[i16 + 1] = (char) (i - 4);
            return;
        }
        endBlock();
        initBlock();
        writeRun();
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        if (this.runLength > 0) {
            writeRun();
        }
        this.currentChar = -1;
        endBlock();
        endCompression();
        this.closed = true;
        super.close();
        this.bsStream.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        super.flush();
        this.bsStream.flush();
    }

    private void initialize() throws IOException {
        this.bytesOut = 0;
        this.nBlocksRandomised = 0;
        bsPutUChar(104);
        bsPutUChar(this.blockSize100k + 48);
        this.combinedCRC = 0;
    }

    private void initBlock() {
        this.mCrc.initialiseCRC();
        this.last = -1;
        for (int i = 0; i < 256; i++) {
            this.inUse[i] = false;
        }
        this.allowableBlockSize = (this.blockSize100k * BZip2Constants.baseBlockSize) - 20;
    }

    private void endBlock() throws IOException {
        int finalCRC = this.mCrc.getFinalCRC();
        this.blockCRC = finalCRC;
        int i = this.combinedCRC;
        int i2 = (i >>> 31) | (i << 1);
        this.combinedCRC = i2;
        this.combinedCRC = finalCRC ^ i2;
        doReversibleTransformation();
        bsPutUChar(49);
        bsPutUChar(65);
        bsPutUChar(89);
        bsPutUChar(38);
        bsPutUChar(83);
        bsPutUChar(89);
        bsPutint(this.blockCRC);
        if (this.blockRandomised) {
            bsW(1, 1);
            this.nBlocksRandomised++;
        } else {
            bsW(1, 0);
        }
        moveToFrontCodeAndSend();
    }

    private void endCompression() throws IOException {
        bsPutUChar(23);
        bsPutUChar(114);
        bsPutUChar(69);
        bsPutUChar(56);
        bsPutUChar(80);
        bsPutUChar(144);
        bsPutint(this.combinedCRC);
        bsFinishedWithStream();
    }

    private void hbAssignCodes(int[] code, char[] length, int minLen, int maxLen, int alphaSize) {
        int vec = 0;
        for (int n = minLen; n <= maxLen; n++) {
            for (int i = 0; i < alphaSize; i++) {
                if (length[i] == n) {
                    code[i] = vec;
                    vec++;
                }
            }
            vec <<= 1;
        }
    }

    private void bsSetStream(OutputStream f) {
        this.bsStream = f;
        this.bsLive = 0;
        this.bsBuff = 0;
        this.bytesOut = 0;
    }

    private void bsFinishedWithStream() throws IOException {
        while (this.bsLive > 0) {
            int ch = this.bsBuff >> 24;
            try {
                this.bsStream.write(ch);
                this.bsBuff <<= 8;
                this.bsLive -= 8;
                this.bytesOut++;
            } catch (IOException e) {
                throw e;
            }
        }
    }

    private void bsW(int n, int v) throws IOException {
        while (true) {
            int i = this.bsLive;
            if (i >= 8) {
                int ch = this.bsBuff >> 24;
                try {
                    this.bsStream.write(ch);
                    this.bsBuff <<= 8;
                    this.bsLive -= 8;
                    this.bytesOut++;
                } catch (IOException e) {
                    throw e;
                }
            } else {
                this.bsBuff |= v << ((32 - i) - n);
                this.bsLive = i + n;
                return;
            }
        }
    }

    private void bsPutUChar(int c) throws IOException {
        bsW(8, c);
    }

    private void bsPutint(int u) throws IOException {
        bsW(8, (u >> 24) & 255);
        bsW(8, (u >> 16) & 255);
        bsW(8, (u >> 8) & 255);
        bsW(8, u & 255);
    }

    private void bsPutIntVS(int numBits, int c) throws IOException {
        bsW(numBits, c);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0459  */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v42 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void sendMTFValues() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1186
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.bzip2.CBZip2OutputStream.sendMTFValues():void");
    }

    private void moveToFrontCodeAndSend() throws IOException {
        bsPutIntVS(24, this.origPtr);
        generateMTFValues();
        sendMTFValues();
    }

    private void simpleSort(int lo, int hi, int d) {
        int bigN = (hi - lo) + 1;
        if (bigN < 2) {
            return;
        }
        int hp = 0;
        while (this.incs[hp] < bigN) {
            hp++;
        }
        for (int hp2 = hp - 1; hp2 >= 0; hp2--) {
            int h = this.incs[hp2];
            int i = lo + h;
            while (i <= hi) {
                int v = this.zptr[i];
                int j = i;
                while (fullGtU(this.zptr[j - h] + d, v + d)) {
                    int[] iArr = this.zptr;
                    iArr[j] = iArr[j - h];
                    j -= h;
                    if (j <= (lo + h) - 1) {
                        break;
                    }
                }
                int[] iArr2 = this.zptr;
                iArr2[j] = v;
                int i2 = i + 1;
                if (i2 > hi) {
                    break;
                }
                int v2 = iArr2[i2];
                int j2 = i2;
                while (fullGtU(this.zptr[j2 - h] + d, v2 + d)) {
                    int[] iArr3 = this.zptr;
                    iArr3[j2] = iArr3[j2 - h];
                    j2 -= h;
                    if (j2 <= (lo + h) - 1) {
                        break;
                    }
                }
                int[] iArr4 = this.zptr;
                iArr4[j2] = v2;
                int i3 = i2 + 1;
                if (i3 > hi) {
                    break;
                }
                int v3 = iArr4[i3];
                int j3 = i3;
                while (fullGtU(this.zptr[j3 - h] + d, v3 + d)) {
                    int[] iArr5 = this.zptr;
                    iArr5[j3] = iArr5[j3 - h];
                    j3 -= h;
                    if (j3 <= (lo + h) - 1) {
                        break;
                    }
                }
                this.zptr[j3] = v3;
                int i4 = i3 + 1;
                if (this.workDone <= this.workLimit || !this.firstAttempt) {
                    i = i4;
                } else {
                    return;
                }
            }
        }
    }

    private void vswap(int p1, int p2, int n) {
        while (n > 0) {
            int[] iArr = this.zptr;
            int temp = iArr[p1];
            iArr[p1] = iArr[p2];
            iArr[p2] = temp;
            p1++;
            p2++;
            n--;
        }
    }

    private char med3(char a, char b, char c) {
        if (a > b) {
            a = b;
            b = a;
        }
        if (b > c) {
            b = c;
        }
        if (a > b) {
            return a;
        }
        return b;
    }

    private static class StackElem {
        int dd;
        int hh;
        int ll;

        private StackElem() {
        }

        /* synthetic */ StackElem(AnonymousClass1 x0) {
            this();
        }
    }

    private void qSort3(int loSt, int hiSt, int dSt) {
        int n;
        int unHi;
        int gtHi;
        int med = 1000;
        StackElem[] stack = new StackElem[1000];
        int count = 0;
        while (count < med) {
            stack[count] = new StackElem(null);
            count++;
            med = 1000;
        }
        stack[0].ll = loSt;
        stack[0].hh = hiSt;
        stack[0].dd = dSt;
        int sp = 0 + 1;
        int n2 = 0;
        while (sp > 0) {
            if (sp >= med) {
                panic();
            }
            sp--;
            int lo = stack[sp].ll;
            int hi = stack[sp].hh;
            int d = stack[sp].dd;
            if (hi - lo < 20 || d > 10) {
                simpleSort(lo, hi, d);
                if (this.workDone > this.workLimit && this.firstAttempt) {
                    return;
                } else {
                    med = 1000;
                }
            } else {
                char[] cArr = this.block;
                int[] iArr = this.zptr;
                int med2 = med3(cArr[iArr[lo] + d + 1], cArr[iArr[hi] + d + 1], cArr[iArr[(lo + hi) >> 1] + d + 1]);
                int gtHi2 = lo;
                int ltLo = hi;
                int unHi2 = lo;
                int unLo = hi;
                while (true) {
                    if (unHi2 > unLo) {
                        n = n2;
                        int i = unLo;
                        unHi = ltLo;
                        gtHi = i;
                    } else {
                        char[] cArr2 = this.block;
                        int[] iArr2 = this.zptr;
                        n = cArr2[(iArr2[unHi2] + d) + 1] - med2;
                        if (n == 0) {
                            int temp = iArr2[unHi2];
                            iArr2[unHi2] = iArr2[gtHi2];
                            iArr2[gtHi2] = temp;
                            gtHi2++;
                            unHi2++;
                            n2 = n;
                        } else if (n > 0) {
                            int i2 = unLo;
                            unHi = ltLo;
                            gtHi = i2;
                        } else {
                            unHi2++;
                            n2 = n;
                        }
                    }
                    while (true) {
                        if (unHi2 > gtHi) {
                            n2 = n;
                            break;
                        }
                        char[] cArr3 = this.block;
                        int[] iArr3 = this.zptr;
                        n = cArr3[(iArr3[gtHi] + d) + 1] - med2;
                        if (n == 0) {
                            int temp2 = iArr3[gtHi];
                            iArr3[gtHi] = iArr3[unHi];
                            iArr3[unHi] = temp2;
                            unHi--;
                            gtHi--;
                        } else {
                            if (n < 0) {
                                n2 = n;
                                break;
                            }
                            gtHi--;
                        }
                    }
                    if (unHi2 > gtHi) {
                        break;
                    }
                    int med3 = med2;
                    int[] iArr4 = this.zptr;
                    int temp3 = iArr4[unHi2];
                    iArr4[unHi2] = iArr4[gtHi];
                    iArr4[gtHi] = temp3;
                    unHi2++;
                    int temp4 = gtHi - 1;
                    ltLo = unHi;
                    unLo = temp4;
                    med2 = med3;
                }
                if (unHi < gtHi2) {
                    stack[sp].ll = lo;
                    stack[sp].hh = hi;
                    stack[sp].dd = d + 1;
                    sp++;
                    med = 1000;
                } else {
                    int n3 = gtHi2 - lo < unHi2 - gtHi2 ? gtHi2 - lo : unHi2 - gtHi2;
                    vswap(lo, unHi2 - n3, n3);
                    int m = hi - unHi < unHi - gtHi ? hi - unHi : unHi - gtHi;
                    vswap(unHi2, (hi - m) + 1, m);
                    n2 = ((lo + unHi2) - gtHi2) - 1;
                    int n4 = unHi - gtHi;
                    int m2 = (hi - n4) + 1;
                    stack[sp].ll = lo;
                    stack[sp].hh = n2;
                    stack[sp].dd = d;
                    int sp2 = sp + 1;
                    int med4 = n2 + 1;
                    stack[sp2].ll = med4;
                    stack[sp2].hh = m2 - 1;
                    stack[sp2].dd = d + 1;
                    int sp3 = sp2 + 1;
                    stack[sp3].ll = m2;
                    stack[sp3].hh = hi;
                    stack[sp3].dd = d;
                    sp = sp3 + 1;
                    med = 1000;
                }
            }
        }
    }

    private void mainSort() {
        int vv;
        int i;
        int i2;
        int i3;
        int[] runningOrder = new int[256];
        int[] copy = new int[256];
        boolean[] bigDone = new boolean[256];
        int i4 = 0;
        while (true) {
            vv = 20;
            if (i4 >= 20) {
                break;
            }
            char[] cArr = this.block;
            int i5 = this.last;
            cArr[i5 + i4 + 2] = cArr[(i4 % (i5 + 1)) + 1];
            i4++;
        }
        int i6 = 0;
        while (true) {
            i = this.last;
            if (i6 > i + 20) {
                break;
            }
            this.quadrant[i6] = 0;
            i6++;
            vv = 20;
        }
        char[] cArr2 = this.block;
        cArr2[0] = cArr2[i + 1];
        if (i < 4000) {
            int i7 = 0;
            while (true) {
                int i8 = this.last;
                if (i7 <= i8) {
                    this.zptr[i7] = i7;
                    i7++;
                } else {
                    this.firstAttempt = false;
                    this.workLimit = 0;
                    this.workDone = 0;
                    simpleSort(0, i8, 0);
                    return;
                }
            }
        } else {
            int numQSorted = 0;
            int i9 = 0;
            while (true) {
                i2 = 255;
                if (i9 > 255) {
                    break;
                }
                bigDone[i9] = false;
                i9++;
                vv = 20;
            }
            int i10 = 0;
            while (i10 <= 65536) {
                this.ftab[i10] = 0;
                i10++;
                vv = 20;
            }
            char c = this.block[0];
            int i11 = 0;
            while (i11 <= this.last) {
                char c2 = this.block[i11 + 1];
                int[] iArr = this.ftab;
                int i12 = (c << '\b') + c2;
                iArr[i12] = iArr[i12] + 1;
                c = c2;
                i11++;
                vv = 20;
            }
            int i13 = 1;
            while (i13 <= 65536) {
                int[] iArr2 = this.ftab;
                iArr2[i13] = iArr2[i13] + iArr2[i13 - 1];
                i13++;
                vv = 20;
            }
            char c3 = this.block[1];
            int i14 = 0;
            while (true) {
                i3 = this.last;
                if (i14 >= i3) {
                    break;
                }
                char c4 = this.block[i14 + 2];
                int j = (c3 << '\b') + c4;
                c3 = c4;
                int[] iArr3 = this.ftab;
                iArr3[j] = iArr3[j] - 1;
                this.zptr[iArr3[j]] = i14;
                i14++;
                vv = 20;
            }
            char[] cArr3 = this.block;
            int j2 = (cArr3[i3 + 1] << '\b') + cArr3[1];
            int[] iArr4 = this.ftab;
            iArr4[j2] = iArr4[j2] - 1;
            this.zptr[iArr4[j2]] = i3;
            int i15 = 0;
            while (i15 <= 255) {
                runningOrder[i15] = i15;
                i15++;
                vv = 20;
            }
            int ss = 1;
            while (true) {
                ss = (ss * 3) + 1;
                if (ss > 256) {
                    break;
                } else {
                    vv = 20;
                }
            }
            while (true) {
                int h = ss / 3;
                int i16 = h;
                while (i16 <= 255) {
                    int vv2 = runningOrder[i16];
                    int j3 = i16;
                    do {
                        int[] iArr5 = this.ftab;
                        if (iArr5[(runningOrder[j3 - h] + 1) << 8] - iArr5[runningOrder[j3 - h] << 8] <= iArr5[(vv2 + 1) << 8] - iArr5[vv2 << 8]) {
                            break;
                        }
                        runningOrder[j3] = runningOrder[j3 - h];
                        j3 -= h;
                        runningOrder[j3] = vv2;
                        i16++;
                        vv = 20;
                    } while (j3 > h - 1);
                    runningOrder[j3] = vv2;
                    i16++;
                    vv = 20;
                }
                if (h == 1) {
                    break;
                }
                ss = h;
                vv = 20;
            }
            int sb = 0;
            while (sb <= i2) {
                int ss2 = runningOrder[sb];
                int j4 = 0;
                while (j4 <= i2) {
                    int sb2 = ss2 << 8;
                    int sb3 = sb2 + j4;
                    int[] iArr6 = this.ftab;
                    if ((iArr6[sb3] & 2097152) != 2097152) {
                        int lo = iArr6[sb3] & CLEARMASK;
                        int hi = (iArr6[sb3 + 1] & CLEARMASK) - 1;
                        if (hi > lo) {
                            qSort3(lo, hi, 2);
                            numQSorted += (hi - lo) + 1;
                            if (this.workDone > this.workLimit && this.firstAttempt) {
                                return;
                            }
                        }
                        int[] iArr7 = this.ftab;
                        iArr7[sb3] = iArr7[sb3] | 2097152;
                    }
                    j4++;
                    vv = 20;
                }
                bigDone[ss2] = true;
                if (sb < i2) {
                    int[] iArr8 = this.ftab;
                    int bbStart = iArr8[ss2 << 8] & CLEARMASK;
                    int bbSize = (iArr8[(ss2 + 1) << 8] & CLEARMASK) - bbStart;
                    int shifts = 0;
                    while ((bbSize >> shifts) > 65534) {
                        shifts++;
                        vv = 20;
                    }
                    int j5 = 0;
                    while (j5 < bbSize) {
                        int a2update = this.zptr[bbStart + j5];
                        int qVal = j5 >> shifts;
                        int[] iArr9 = this.quadrant;
                        iArr9[a2update] = qVal;
                        if (a2update < vv) {
                            iArr9[this.last + a2update + 1] = qVal;
                        }
                        j5++;
                        vv = 20;
                    }
                    if (((bbSize - 1) >> shifts) > 65535) {
                        panic();
                    }
                }
                for (int j6 = 0; j6 <= 255; j6++) {
                    copy[j6] = this.ftab[(j6 << 8) + ss2] & CLEARMASK;
                }
                int i17 = this.ftab[ss2 << 8];
                int j7 = i17 & CLEARMASK;
                for (int i18 = CLEARMASK; j7 < (this.ftab[(ss2 + 1) << 8] & i18); i18 = CLEARMASK) {
                    char[] cArr4 = this.block;
                    int[] iArr10 = this.zptr;
                    char c5 = cArr4[iArr10[j7]];
                    if (!bigDone[c5]) {
                        iArr10[copy[c5]] = iArr10[j7] == 0 ? this.last : iArr10[j7] - 1;
                        copy[c5] = copy[c5] + 1;
                    }
                    j7++;
                }
                int j8 = 0;
                while (true) {
                    i2 = 255;
                    if (j8 > 255) {
                        break;
                    }
                    int[] iArr11 = this.ftab;
                    int i19 = (j8 << 8) + ss2;
                    iArr11[i19] = iArr11[i19] | 2097152;
                    j8++;
                }
                sb++;
                vv = 20;
            }
        }
    }

    private void randomiseBlock() {
        int rNToGo = 0;
        int rTPos = 0;
        for (int i = 0; i < 256; i++) {
            this.inUse[i] = false;
        }
        for (int i2 = 0; i2 <= this.last; i2++) {
            if (rNToGo == 0) {
                rNToGo = (char) BZip2Constants.rNums[rTPos];
                rTPos++;
                if (rTPos == 512) {
                    rTPos = 0;
                }
            }
            rNToGo--;
            char[] cArr = this.block;
            int i3 = i2 + 1;
            cArr[i3] = (char) (cArr[i3] ^ (rNToGo == 1 ? (char) 1 : (char) 0));
            char[] cArr2 = this.block;
            int i4 = i2 + 1;
            cArr2[i4] = (char) (cArr2[i4] & 255);
            this.inUse[cArr2[i2 + 1]] = true;
        }
    }

    private void doReversibleTransformation() {
        this.workLimit = this.workFactor * this.last;
        this.workDone = 0;
        this.blockRandomised = false;
        this.firstAttempt = true;
        mainSort();
        if (this.workDone > this.workLimit && this.firstAttempt) {
            randomiseBlock();
            this.workDone = 0;
            this.workLimit = 0;
            this.blockRandomised = true;
            this.firstAttempt = false;
            mainSort();
        }
        this.origPtr = -1;
        int i = 0;
        while (true) {
            if (i > this.last) {
                break;
            }
            if (this.zptr[i] != 0) {
                i++;
            } else {
                this.origPtr = i;
                break;
            }
        }
        if (this.origPtr == -1) {
            panic();
        }
    }

    private boolean fullGtU(int i1, int i2) {
        char[] cArr = this.block;
        char c1 = cArr[i1 + 1];
        char c2 = cArr[i2 + 1];
        if (c1 != c2) {
            return c1 > c2;
        }
        int i12 = i1 + 1;
        int i22 = i2 + 1;
        char c12 = cArr[i12 + 1];
        char c22 = cArr[i22 + 1];
        if (c12 != c22) {
            return c12 > c22;
        }
        int i13 = i12 + 1;
        int i23 = i22 + 1;
        char c13 = cArr[i13 + 1];
        char c23 = cArr[i23 + 1];
        if (c13 != c23) {
            return c13 > c23;
        }
        int i14 = i13 + 1;
        int i24 = i23 + 1;
        char c14 = cArr[i14 + 1];
        char c24 = cArr[i24 + 1];
        if (c14 != c24) {
            return c14 > c24;
        }
        int i15 = i14 + 1;
        int i25 = i24 + 1;
        char c15 = cArr[i15 + 1];
        char c25 = cArr[i25 + 1];
        if (c15 != c25) {
            return c15 > c25;
        }
        int i16 = i15 + 1;
        int i26 = i25 + 1;
        char c16 = cArr[i16 + 1];
        char c26 = cArr[i26 + 1];
        if (c16 != c26) {
            return c16 > c26;
        }
        int i17 = i16 + 1;
        int i27 = i26 + 1;
        int k = this.last + 1;
        do {
            char[] cArr2 = this.block;
            char c17 = cArr2[i17 + 1];
            char c27 = cArr2[i27 + 1];
            if (c17 != c27) {
                return c17 > c27;
            }
            int[] iArr = this.quadrant;
            int s1 = iArr[i17];
            int s2 = iArr[i27];
            if (s1 != s2) {
                return s1 > s2;
            }
            int i18 = i17 + 1;
            int i28 = i27 + 1;
            char c18 = cArr2[i18 + 1];
            char c28 = cArr2[i28 + 1];
            if (c18 != c28) {
                return c18 > c28;
            }
            int s12 = iArr[i18];
            int s22 = iArr[i28];
            if (s12 != s22) {
                return s12 > s22;
            }
            int i19 = i18 + 1;
            int i29 = i28 + 1;
            char c19 = cArr2[i19 + 1];
            char c29 = cArr2[i29 + 1];
            if (c19 != c29) {
                return c19 > c29;
            }
            int s13 = iArr[i19];
            int s23 = iArr[i29];
            if (s13 != s23) {
                return s13 > s23;
            }
            int i110 = i19 + 1;
            int i210 = i29 + 1;
            char c110 = cArr2[i110 + 1];
            char c210 = cArr2[i210 + 1];
            if (c110 != c210) {
                return c110 > c210;
            }
            int s14 = iArr[i110];
            int s24 = iArr[i210];
            if (s14 != s24) {
                return s14 > s24;
            }
            i17 = i110 + 1;
            i27 = i210 + 1;
            int i = this.last;
            if (i17 > i) {
                i17 = (i17 - i) - 1;
            }
            int i3 = this.last;
            if (i27 > i3) {
                i27 = (i27 - i3) - 1;
            }
            k -= 4;
            this.workDone++;
        } while (k >= 0);
        return false;
    }

    private void allocateCompressStructures() {
        int n = this.blockSize100k * BZip2Constants.baseBlockSize;
        char[] cArr = new char[n + 1 + 20];
        this.block = cArr;
        int[] iArr = new int[n + 20];
        this.quadrant = iArr;
        this.zptr = new int[n];
        this.ftab = new int[65537];
        if (cArr == null || iArr != null) {
        }
        this.szptr = new short[n * 2];
    }

    /* JADX WARN: Incorrect condition in loop: B:10:0x0022 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void generateMTFValues() {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.bzip2.CBZip2OutputStream.generateMTFValues():void");
    }
}
