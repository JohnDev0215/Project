package org.apache.commons.compress.compressors.bzip2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.BitInputStream;
import org.apache.commons.compress.utils.CloseShieldFilterInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;

/* JADX INFO: loaded from: classes4.dex */
public class BZip2CompressorInputStream extends CompressorInputStream implements BZip2Constants, InputStreamStatistics {
    private static final int EOF = 0;
    private static final int NO_RAND_PART_A_STATE = 5;
    private static final int NO_RAND_PART_B_STATE = 6;
    private static final int NO_RAND_PART_C_STATE = 7;
    private static final int RAND_PART_A_STATE = 2;
    private static final int RAND_PART_B_STATE = 3;
    private static final int RAND_PART_C_STATE = 4;
    private static final int START_BLOCK_STATE = 1;
    private BitInputStream bin;
    private boolean blockRandomised;
    private int blockSize100k;
    private int computedBlockCRC;
    private int computedCombinedCRC;
    private final CRC crc;
    private int currentState;
    private Data data;
    private final boolean decompressConcatenated;
    private int last;
    private int nInUse;
    private int origPtr;
    private int storedBlockCRC;
    private int storedCombinedCRC;
    private int su_ch2;
    private int su_chPrev;
    private int su_count;
    private int su_i2;
    private int su_j2;
    private int su_rNToGo;
    private int su_rTPos;
    private int su_tPos;
    private char su_z;

    public BZip2CompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    public BZip2CompressorInputStream(InputStream inputStream, boolean z) throws IOException {
        this.crc = new CRC();
        this.currentState = 1;
        this.bin = new BitInputStream(inputStream == System.in ? new CloseShieldFilterInputStream(inputStream) : inputStream, ByteOrder.BIG_ENDIAN);
        this.decompressConcatenated = z;
        init(true);
        initBlock();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.bin != null) {
            int i = read0();
            count(i < 0 ? -1 : 1);
            return i;
        }
        throw new IOException("Stream closed");
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            throw new IndexOutOfBoundsException("offs(" + i + ") < 0.");
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("len(" + i2 + ") < 0.");
        }
        int i3 = i + i2;
        if (i3 > bArr.length) {
            throw new IndexOutOfBoundsException("offs(" + i + ") + len(" + i2 + ") > dest.length(" + bArr.length + ").");
        }
        if (this.bin == null) {
            throw new IOException("Stream closed");
        }
        if (i2 == 0) {
            return 0;
        }
        int i4 = i;
        while (i4 < i3) {
            int i5 = read0();
            if (i5 < 0) {
                break;
            }
            bArr[i4] = (byte) i5;
            count(1);
            i4++;
        }
        if (i4 == i) {
            return -1;
        }
        return i4 - i;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.bin.getBytesRead();
    }

    private void makeMaps() {
        boolean[] zArr = this.data.inUse;
        byte[] bArr = this.data.seqToUnseq;
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            if (zArr[i2]) {
                bArr[i] = (byte) i2;
                i++;
            }
        }
        this.nInUse = i;
    }

    private int read0() throws IOException {
        switch (this.currentState) {
            case 0:
                return -1;
            case 1:
                return setupBlock();
            case 2:
                throw new IllegalStateException();
            case 3:
                return setupRandPartB();
            case 4:
                return setupRandPartC();
            case 5:
                throw new IllegalStateException();
            case 6:
                return setupNoRandPartB();
            case 7:
                return setupNoRandPartC();
            default:
                throw new IllegalStateException();
        }
    }

    private int readNextByte(BitInputStream bitInputStream) throws IOException {
        return (int) bitInputStream.readBits(8);
    }

    private boolean init(boolean z) throws IOException {
        BitInputStream bitInputStream = this.bin;
        if (bitInputStream == null) {
            throw new IOException("No InputStream");
        }
        if (!z) {
            bitInputStream.clearBitCache();
        }
        int nextByte = readNextByte(this.bin);
        if (nextByte == -1 && !z) {
            return false;
        }
        int nextByte2 = readNextByte(this.bin);
        int nextByte3 = readNextByte(this.bin);
        if (nextByte != 66 || nextByte2 != 90 || nextByte3 != 104) {
            throw new IOException(z ? "Stream is not in the BZip2 format" : "Garbage after a valid BZip2 stream");
        }
        int nextByte4 = readNextByte(this.bin);
        if (nextByte4 < 49 || nextByte4 > 57) {
            throw new IOException("BZip2 block size is invalid");
        }
        this.blockSize100k = nextByte4 - 48;
        this.computedCombinedCRC = 0;
        return true;
    }

    private void initBlock() throws IOException {
        BitInputStream bitInputStream = this.bin;
        do {
            char cBsGetUByte = bsGetUByte(bitInputStream);
            char cBsGetUByte2 = bsGetUByte(bitInputStream);
            char cBsGetUByte3 = bsGetUByte(bitInputStream);
            char cBsGetUByte4 = bsGetUByte(bitInputStream);
            char cBsGetUByte5 = bsGetUByte(bitInputStream);
            char cBsGetUByte6 = bsGetUByte(bitInputStream);
            if (cBsGetUByte != 23 || cBsGetUByte2 != 'r' || cBsGetUByte3 != 'E' || cBsGetUByte4 != '8' || cBsGetUByte5 != 'P' || cBsGetUByte6 != 144) {
                if (cBsGetUByte != '1' || cBsGetUByte2 != 'A' || cBsGetUByte3 != 'Y' || cBsGetUByte4 != '&' || cBsGetUByte5 != 'S' || cBsGetUByte6 != 'Y') {
                    this.currentState = 0;
                    throw new IOException("Bad block header");
                }
                this.storedBlockCRC = bsGetInt(bitInputStream);
                this.blockRandomised = bsR(bitInputStream, 1) == 1;
                if (this.data == null) {
                    this.data = new Data(this.blockSize100k);
                }
                getAndMoveToFrontDecode();
                this.crc.initializeCRC();
                this.currentState = 1;
                return;
            }
        } while (!complete());
    }

    private void endBlock() throws IOException {
        int finalCRC = this.crc.getFinalCRC();
        this.computedBlockCRC = finalCRC;
        int i = this.storedBlockCRC;
        if (i != finalCRC) {
            int i2 = this.storedCombinedCRC;
            this.computedCombinedCRC = ((i2 >>> 31) | (i2 << 1)) ^ i;
            throw new IOException("BZip2 CRC error");
        }
        int i3 = this.computedCombinedCRC;
        this.computedCombinedCRC = finalCRC ^ ((i3 >>> 31) | (i3 << 1));
    }

    private boolean complete() throws IOException {
        int iBsGetInt = bsGetInt(this.bin);
        this.storedCombinedCRC = iBsGetInt;
        this.currentState = 0;
        this.data = null;
        if (iBsGetInt == this.computedCombinedCRC) {
            return (this.decompressConcatenated && init(false)) ? false : true;
        }
        throw new IOException("BZip2 CRC error");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        BitInputStream bitInputStream = this.bin;
        if (bitInputStream != null) {
            try {
                bitInputStream.close();
            } finally {
                this.data = null;
                this.bin = null;
            }
        }
    }

    private static int bsR(BitInputStream bitInputStream, int i) throws IOException {
        long bits = bitInputStream.readBits(i);
        if (bits >= 0) {
            return (int) bits;
        }
        throw new IOException("Unexpected end of stream");
    }

    private static boolean bsGetBit(BitInputStream bitInputStream) throws IOException {
        return bsR(bitInputStream, 1) != 0;
    }

    private static char bsGetUByte(BitInputStream bitInputStream) throws IOException {
        return (char) bsR(bitInputStream, 8);
    }

    private static int bsGetInt(BitInputStream bitInputStream) throws IOException {
        return bsR(bitInputStream, 32);
    }

    private static void checkBounds(int i, int i2, String str) throws IOException {
        if (i < 0) {
            throw new IOException("Corrupted input, " + str + " value negative");
        }
        if (i >= i2) {
            throw new IOException("Corrupted input, " + str + " value too big");
        }
    }

    private static void hbCreateDecodeTables(int[] iArr, int[] iArr2, int[] iArr3, char[] cArr, int i, int i2, int i3) throws IOException {
        int i4 = 0;
        int i5 = 0;
        for (int i6 = i; i6 <= i2; i6++) {
            for (int i7 = 0; i7 < i3; i7++) {
                if (cArr[i7] == i6) {
                    iArr3[i5] = i7;
                    i5++;
                }
            }
        }
        int i8 = 23;
        while (true) {
            i8--;
            if (i8 <= 0) {
                break;
            }
            iArr2[i8] = 0;
            iArr[i8] = 0;
        }
        for (int i9 = 0; i9 < i3; i9++) {
            char c = cArr[i9];
            checkBounds(c, 258, "length");
            int i10 = c + 1;
            iArr2[i10] = iArr2[i10] + 1;
        }
        int i11 = iArr2[0];
        for (int i12 = 1; i12 < 23; i12++) {
            i11 += iArr2[i12];
            iArr2[i12] = i11;
        }
        int i13 = iArr2[i];
        int i14 = i;
        while (i14 <= i2) {
            int i15 = i14 + 1;
            int i16 = iArr2[i15];
            int i17 = i4 + (i16 - i13);
            iArr[i14] = i17 - 1;
            i4 = i17 << 1;
            i14 = i15;
            i13 = i16;
        }
        for (int i18 = i + 1; i18 <= i2; i18++) {
            iArr2[i18] = ((iArr[i18 - 1] + 1) << 1) - iArr2[i18];
        }
    }

    private void recvDecodingTables() throws IOException {
        BitInputStream bitInputStream = this.bin;
        Data data = this.data;
        boolean[] zArr = data.inUse;
        byte[] bArr = data.recvDecodingTables_pos;
        byte[] bArr2 = data.selector;
        byte[] bArr3 = data.selectorMtf;
        int i = 0;
        for (int i2 = 0; i2 < 16; i2++) {
            if (bsGetBit(bitInputStream)) {
                i |= 1 << i2;
            }
        }
        Arrays.fill(zArr, false);
        for (int i3 = 0; i3 < 16; i3++) {
            if (((1 << i3) & i) != 0) {
                int i4 = i3 << 4;
                for (int i5 = 0; i5 < 16; i5++) {
                    if (bsGetBit(bitInputStream)) {
                        zArr[i4 + i5] = true;
                    }
                }
            }
        }
        makeMaps();
        int i6 = this.nInUse + 2;
        int iBsR = bsR(bitInputStream, 3);
        int iBsR2 = bsR(bitInputStream, 15);
        if (iBsR2 < 0) {
            throw new IOException("Corrupted input, nSelectors value negative");
        }
        checkBounds(i6, 259, "alphaSize");
        checkBounds(iBsR, 7, "nGroups");
        for (int i7 = 0; i7 < iBsR2; i7++) {
            int i8 = 0;
            while (bsGetBit(bitInputStream)) {
                i8++;
            }
            if (i7 < 18002) {
                bArr3[i7] = (byte) i8;
            }
        }
        if (iBsR2 > 18002) {
            iBsR2 = 18002;
        }
        int i9 = iBsR;
        while (true) {
            i9--;
            if (i9 < 0) {
                break;
            } else {
                bArr[i9] = (byte) i9;
            }
        }
        for (int i10 = 0; i10 < iBsR2; i10++) {
            int i11 = bArr3[i10] & 255;
            checkBounds(i11, 6, "selectorMtf");
            byte b = bArr[i11];
            while (i11 > 0) {
                bArr[i11] = bArr[i11 - 1];
                i11--;
            }
            bArr[0] = b;
            bArr2[i10] = b;
        }
        char[][] cArr = data.temp_charArray2d;
        for (int i12 = 0; i12 < iBsR; i12++) {
            int iBsR3 = bsR(bitInputStream, 5);
            char[] cArr2 = cArr[i12];
            for (int i13 = 0; i13 < i6; i13++) {
                while (bsGetBit(bitInputStream)) {
                    iBsR3 += bsGetBit(bitInputStream) ? -1 : 1;
                }
                cArr2[i13] = (char) iBsR3;
            }
        }
        createHuffmanDecodingTables(i6, iBsR);
    }

    private void createHuffmanDecodingTables(int i, int i2) throws IOException {
        Data data = this.data;
        char[][] cArr = data.temp_charArray2d;
        int[] iArr = data.minLens;
        int[][] iArr2 = data.limit;
        int[][] iArr3 = data.base;
        int[][] iArr4 = data.perm;
        for (int i3 = 0; i3 < i2; i3++) {
            char[] cArr2 = cArr[i3];
            char c = ' ';
            int i4 = i;
            char c2 = 0;
            while (true) {
                i4--;
                if (i4 >= 0) {
                    char c3 = cArr2[i4];
                    if (c3 > c2) {
                        c2 = c3;
                    }
                    if (c3 < c) {
                        c = c3;
                    }
                }
            }
            hbCreateDecodeTables(iArr2[i3], iArr3[i3], iArr4[i3], cArr[i3], c, c2, i);
            iArr[i3] = c;
        }
    }

    private void getAndMoveToFrontDecode() throws IOException {
        int i;
        String str;
        char c;
        int i2;
        BZip2CompressorInputStream bZip2CompressorInputStream = this;
        BitInputStream bitInputStream = bZip2CompressorInputStream.bin;
        bZip2CompressorInputStream.origPtr = bsR(bitInputStream, 24);
        recvDecodingTables();
        Data data = bZip2CompressorInputStream.data;
        byte[] bArr = data.ll8;
        int[] iArr = data.unzftab;
        byte[] bArr2 = data.selector;
        byte[] bArr3 = data.seqToUnseq;
        char[] cArr = data.getAndMoveToFrontDecode_yy;
        int[] iArr2 = data.minLens;
        int[][] iArr3 = data.limit;
        int[][] iArr4 = data.base;
        int[][] iArr5 = data.perm;
        int i3 = bZip2CompressorInputStream.blockSize100k * BZip2Constants.BASEBLOCKSIZE;
        int i4 = 256;
        while (true) {
            i4--;
            if (i4 < 0) {
                break;
            }
            cArr[i4] = (char) i4;
            iArr[i4] = 0;
        }
        int i5 = bZip2CompressorInputStream.nInUse + 1;
        int andMoveToFrontDecode0 = getAndMoveToFrontDecode0();
        int i6 = bArr2[0] & 255;
        checkBounds(i6, 6, "zt");
        int[] iArr6 = iArr4[i6];
        int[] iArr7 = iArr3[i6];
        int[] iArr8 = iArr5[i6];
        int i7 = iArr2[i6];
        int i8 = andMoveToFrontDecode0;
        int i9 = 49;
        int i10 = -1;
        int i11 = 0;
        while (i8 != i5) {
            int i12 = i5;
            String str2 = "groupNo";
            BitInputStream bitInputStream2 = bitInputStream;
            if (i8 == 0 || i8 == 1) {
                int[] iArr9 = iArr2;
                int i13 = i8;
                int i14 = i3;
                byte[] bArr4 = bArr;
                i8 = i13;
                int i15 = -1;
                int i16 = i9;
                int i17 = i11;
                int i18 = i7;
                int[] iArr10 = iArr8;
                int[] iArr11 = iArr7;
                int[] iArr12 = iArr6;
                int i19 = 1;
                while (true) {
                    if (i8 != 0) {
                        i = i10;
                        if (i8 != 1) {
                            break;
                        } else {
                            i15 += i19 << 1;
                        }
                    } else {
                        i15 += i19;
                        i = i10;
                    }
                    if (i16 == 0) {
                        int i20 = i17 + 1;
                        checkBounds(i20, BZip2Constants.MAX_SELECTORS, str2);
                        int i21 = bArr2[i20] & 255;
                        str = str2;
                        checkBounds(i21, 6, "zt");
                        iArr12 = iArr4[i21];
                        iArr11 = iArr3[i21];
                        iArr10 = iArr5[i21];
                        i18 = iArr9[i21];
                        i17 = i20;
                        i16 = 49;
                    } else {
                        str = str2;
                        i16--;
                    }
                    int i22 = i18;
                    checkBounds(i22, 258, "zn");
                    int iBsR = bsR(bitInputStream2, i22);
                    int i23 = i22;
                    while (iBsR > iArr11[i23]) {
                        int i24 = i23 + 1;
                        checkBounds(i24, 258, "zn");
                        iBsR = (iBsR << 1) | bsR(bitInputStream2, 1);
                        i23 = i24;
                        iArr5 = iArr5;
                    }
                    int i25 = iBsR - iArr12[i23];
                    checkBounds(i25, 258, "zvec");
                    i19 <<= 1;
                    i8 = iArr10[i25];
                    i18 = i22;
                    i10 = i;
                    str2 = str;
                    iArr5 = iArr5;
                }
                bZip2CompressorInputStream = this;
                int[][] iArr13 = iArr5;
                checkBounds(i15, bZip2CompressorInputStream.data.ll8.length, "s");
                char c2 = cArr[0];
                checkBounds(c2, 256, "yy");
                byte b = bArr3[c2];
                int i26 = b & 255;
                iArr[i26] = iArr[i26] + i15 + 1;
                int i27 = i + 1;
                int i28 = i15 + i27;
                checkBounds(i28, bZip2CompressorInputStream.data.ll8.length, "lastShadow");
                Arrays.fill(bArr4, i27, i28 + 1, b);
                if (i28 >= i14) {
                    throw new IOException("Block overrun while expanding RLE in MTF, " + i28 + " exceeds " + i14);
                }
                i10 = i28;
                iArr6 = iArr12;
                iArr7 = iArr11;
                iArr8 = iArr10;
                i7 = i18;
                i11 = i17;
                i5 = i12;
                i9 = i16;
                iArr2 = iArr9;
                bArr = bArr4;
                iArr5 = iArr13;
                i3 = i14;
                bitInputStream = bitInputStream2;
            } else {
                i10++;
                if (i10 >= i3) {
                    throw new IOException("Block overrun in MTF, " + i10 + " exceeds " + i3);
                }
                int i29 = i3;
                checkBounds(i8, 257, "nextSym");
                int i30 = i8 - 1;
                char c3 = cArr[i30];
                int[] iArr14 = iArr2;
                checkBounds(c3, 256, "yy");
                byte b2 = bArr3[c3];
                int i31 = b2 & 255;
                iArr[i31] = iArr[i31] + 1;
                bArr[i10] = b2;
                if (i8 <= 16) {
                    while (i30 > 0) {
                        int i32 = i30 - 1;
                        cArr[i30] = cArr[i32];
                        i30 = i32;
                    }
                    c = 0;
                } else {
                    c = 0;
                    System.arraycopy(cArr, 0, cArr, 1, i30);
                }
                cArr[c] = c3;
                if (i9 == 0) {
                    int i33 = i11 + 1;
                    checkBounds(i33, BZip2Constants.MAX_SELECTORS, "groupNo");
                    int i34 = bArr2[i33] & 255;
                    checkBounds(i34, 6, "zt");
                    int[] iArr15 = iArr4[i34];
                    int[] iArr16 = iArr3[i34];
                    int[] iArr17 = iArr5[i34];
                    i2 = iArr14[i34];
                    i11 = i33;
                    iArr6 = iArr15;
                    iArr7 = iArr16;
                    iArr8 = iArr17;
                    i9 = 49;
                } else {
                    i9--;
                    i2 = i7;
                }
                checkBounds(i2, 258, "zn");
                int iBsR2 = bsR(bitInputStream2, i2);
                int i35 = i2;
                while (iBsR2 > iArr7[i35]) {
                    i35++;
                    checkBounds(i35, 258, "zn");
                    iBsR2 = (iBsR2 << 1) | bsR(bitInputStream2, 1);
                }
                int i36 = iBsR2 - iArr6[i35];
                checkBounds(i36, 258, "zvec");
                i8 = iArr8[i36];
                i7 = i2;
                bitInputStream = bitInputStream2;
                i5 = i12;
                i3 = i29;
                iArr2 = iArr14;
                bZip2CompressorInputStream = this;
            }
        }
        bZip2CompressorInputStream.last = i10;
    }

    private int getAndMoveToFrontDecode0() throws IOException {
        Data data = this.data;
        int i = data.selector[0] & 255;
        checkBounds(i, 6, "zt");
        int[] iArr = data.limit[i];
        int i2 = data.minLens[i];
        checkBounds(i2, 258, "zn");
        int iBsR = bsR(this.bin, i2);
        while (iBsR > iArr[i2]) {
            i2++;
            checkBounds(i2, 258, "zn");
            iBsR = (iBsR << 1) | bsR(this.bin, 1);
        }
        int i3 = iBsR - data.base[i][i2];
        checkBounds(i3, 258, "zvec");
        return data.perm[i][i3];
    }

    private int setupBlock() throws IOException {
        Data data;
        if (this.currentState == 0 || (data = this.data) == null) {
            return -1;
        }
        int[] iArr = data.cftab;
        int i = this.last + 1;
        int[] iArrInitTT = this.data.initTT(i);
        byte[] bArr = this.data.ll8;
        iArr[0] = 0;
        System.arraycopy(this.data.unzftab, 0, iArr, 1, 256);
        int i2 = iArr[0];
        for (int i3 = 1; i3 <= 256; i3++) {
            i2 += iArr[i3];
            iArr[i3] = i2;
        }
        int i4 = this.last;
        for (int i5 = 0; i5 <= i4; i5++) {
            int i6 = bArr[i5] & 255;
            int i7 = iArr[i6];
            iArr[i6] = i7 + 1;
            checkBounds(i7, i, "tt index");
            iArrInitTT[i7] = i5;
        }
        int i8 = this.origPtr;
        if (i8 < 0 || i8 >= iArrInitTT.length) {
            throw new IOException("Stream corrupted");
        }
        this.su_tPos = iArrInitTT[i8];
        this.su_count = 0;
        this.su_i2 = 0;
        this.su_ch2 = 256;
        if (this.blockRandomised) {
            this.su_rNToGo = 0;
            this.su_rTPos = 0;
            return setupRandPartA();
        }
        return setupNoRandPartA();
    }

    private int setupRandPartA() throws IOException {
        if (this.su_i2 <= this.last) {
            this.su_chPrev = this.su_ch2;
            byte[] bArr = this.data.ll8;
            int i = this.su_tPos;
            int i2 = bArr[i] & 255;
            checkBounds(i, this.data.tt.length, "su_tPos");
            this.su_tPos = this.data.tt[this.su_tPos];
            int i3 = this.su_rNToGo;
            if (i3 == 0) {
                this.su_rNToGo = Rand.rNums(this.su_rTPos) - 1;
                int i4 = this.su_rTPos + 1;
                this.su_rTPos = i4;
                if (i4 == 512) {
                    this.su_rTPos = 0;
                }
            } else {
                this.su_rNToGo = i3 - 1;
            }
            int i5 = i2 ^ (this.su_rNToGo == 1 ? 1 : 0);
            this.su_ch2 = i5;
            this.su_i2++;
            this.currentState = 3;
            this.crc.updateCRC(i5);
            return i5;
        }
        endBlock();
        initBlock();
        return setupBlock();
    }

    private int setupNoRandPartA() throws IOException {
        if (this.su_i2 <= this.last) {
            this.su_chPrev = this.su_ch2;
            byte[] bArr = this.data.ll8;
            int i = this.su_tPos;
            int i2 = bArr[i] & 255;
            this.su_ch2 = i2;
            checkBounds(i, this.data.tt.length, "su_tPos");
            this.su_tPos = this.data.tt[this.su_tPos];
            this.su_i2++;
            this.currentState = 6;
            this.crc.updateCRC(i2);
            return i2;
        }
        this.currentState = 5;
        endBlock();
        initBlock();
        return setupBlock();
    }

    private int setupRandPartB() throws IOException {
        if (this.su_ch2 != this.su_chPrev) {
            this.currentState = 2;
            this.su_count = 1;
            return setupRandPartA();
        }
        int i = this.su_count + 1;
        this.su_count = i;
        if (i < 4) {
            this.currentState = 2;
            return setupRandPartA();
        }
        byte[] bArr = this.data.ll8;
        int i2 = this.su_tPos;
        this.su_z = (char) (bArr[i2] & 255);
        checkBounds(i2, this.data.tt.length, "su_tPos");
        this.su_tPos = this.data.tt[this.su_tPos];
        int i3 = this.su_rNToGo;
        if (i3 == 0) {
            this.su_rNToGo = Rand.rNums(this.su_rTPos) - 1;
            int i4 = this.su_rTPos + 1;
            this.su_rTPos = i4;
            if (i4 == 512) {
                this.su_rTPos = 0;
            }
        } else {
            this.su_rNToGo = i3 - 1;
        }
        this.su_j2 = 0;
        this.currentState = 4;
        if (this.su_rNToGo == 1) {
            this.su_z = (char) (this.su_z ^ 1);
        }
        return setupRandPartC();
    }

    private int setupRandPartC() throws IOException {
        if (this.su_j2 < this.su_z) {
            this.crc.updateCRC(this.su_ch2);
            this.su_j2++;
            return this.su_ch2;
        }
        this.currentState = 2;
        this.su_i2++;
        this.su_count = 0;
        return setupRandPartA();
    }

    private int setupNoRandPartB() throws IOException {
        if (this.su_ch2 != this.su_chPrev) {
            this.su_count = 1;
            return setupNoRandPartA();
        }
        int i = this.su_count + 1;
        this.su_count = i;
        if (i >= 4) {
            checkBounds(this.su_tPos, this.data.ll8.length, "su_tPos");
            this.su_z = (char) (this.data.ll8[this.su_tPos] & 255);
            this.su_tPos = this.data.tt[this.su_tPos];
            this.su_j2 = 0;
            return setupNoRandPartC();
        }
        return setupNoRandPartA();
    }

    private int setupNoRandPartC() throws IOException {
        if (this.su_j2 < this.su_z) {
            int i = this.su_ch2;
            this.crc.updateCRC(i);
            this.su_j2++;
            this.currentState = 7;
            return i;
        }
        this.su_i2++;
        this.su_count = 0;
        return setupNoRandPartA();
    }

    private static final class Data {
        final byte[] ll8;
        int[] tt;
        final boolean[] inUse = new boolean[256];
        final byte[] seqToUnseq = new byte[256];
        final byte[] selector = new byte[BZip2Constants.MAX_SELECTORS];
        final byte[] selectorMtf = new byte[BZip2Constants.MAX_SELECTORS];
        final int[] unzftab = new int[256];
        final int[][] limit = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 6, 258);
        final int[][] base = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 6, 258);
        final int[][] perm = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 6, 258);
        final int[] minLens = new int[6];
        final int[] cftab = new int[257];
        final char[] getAndMoveToFrontDecode_yy = new char[256];
        final char[][] temp_charArray2d = (char[][]) Array.newInstance((Class<?>) Character.TYPE, 6, 258);
        final byte[] recvDecodingTables_pos = new byte[6];

        Data(int i) {
            this.ll8 = new byte[i * BZip2Constants.BASEBLOCKSIZE];
        }

        int[] initTT(int i) {
            int[] iArr = this.tt;
            if (iArr != null && iArr.length >= i) {
                return iArr;
            }
            int[] iArr2 = new int[i];
            this.tt = iArr2;
            return iArr2;
        }
    }

    public static boolean matches(byte[] bArr, int i) {
        return i >= 3 && bArr[0] == 66 && bArr[1] == 90 && bArr[2] == 104;
    }
}
