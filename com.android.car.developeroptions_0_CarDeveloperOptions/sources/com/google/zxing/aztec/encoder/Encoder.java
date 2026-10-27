package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

/* JADX INFO: loaded from: classes.dex */
public final class Encoder {
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i * 16)) * i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AztecCode encode(byte[] bArr, int i, int i2) {
        BitArray bitArrayStuffBits;
        int i3;
        int i4;
        BitArray bitArrayStuffBits2;
        boolean z;
        int iAbs;
        int i5;
        int i6;
        int[] iArr = WORD_SIZE;
        BitArray bitArrayEncode = new HighLevelEncoder(bArr).encode();
        int size = ((bitArrayEncode.getSize() * i) / 100) + 11;
        int size2 = bitArrayEncode.getSize() + size;
        int i7 = 4;
        int i8 = 0;
        if (i2 != 0) {
            boolean z2 = i2 < 0;
            iAbs = Math.abs(i2);
            if (iAbs > (z2 ? 4 : 32)) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i2)));
            }
            i5 = totalBitsInLayer(iAbs, z2);
            i4 = iArr[iAbs];
            int i9 = i5 - (i5 % i4);
            bitArrayStuffBits2 = stuffBits(bitArrayEncode, i4);
            z = z2;
            if (bitArrayStuffBits2.getSize() + size > i9) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
            if (z2) {
                z = z2;
                if (bitArrayStuffBits2.getSize() > i4 * 64) {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                }
            }
        } else {
            BitArray bitArray = null;
            int i10 = 0;
            int i11 = 0;
            while (i10 <= i) {
                boolean z3 = i10 <= 3 ? 1 : i8;
                int i12 = z3 != 0 ? i10 + 1 : i10;
                int i13 = totalBitsInLayer(i12, z3);
                if (size2 <= i13) {
                    if (i11 != iArr[i12]) {
                        i3 = iArr[i12];
                        bitArrayStuffBits = stuffBits(bitArrayEncode, i3);
                    } else {
                        int i14 = i11;
                        bitArrayStuffBits = bitArray;
                        i3 = i14;
                    }
                    int i15 = i13 - (i13 % i3);
                    if ((z3 == 0 || bitArrayStuffBits.getSize() <= i3 * 64) && bitArrayStuffBits.getSize() + size <= i15) {
                        i4 = i3;
                        bitArrayStuffBits2 = bitArrayStuffBits;
                        z = z3;
                        iAbs = i12;
                        i5 = i13;
                    } else {
                        BitArray bitArray2 = bitArrayStuffBits;
                        i11 = i3;
                        bitArray = bitArray2;
                    }
                }
                i10++;
                i = 32;
                i7 = 4;
                i8 = 0;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray bitArrayGenerateCheckWords = generateCheckWords(bitArrayStuffBits2, i5, i4);
        int size3 = bitArrayStuffBits2.getSize() / i4;
        BitArray bitArrayGenerateModeMessage = generateModeMessage(z, iAbs, size3);
        int i16 = z ? (iAbs * 4) + 11 : (iAbs * 4) + 14;
        int[] iArr2 = new int[i16];
        int i17 = 2;
        if (z) {
            for (int i18 = i8; i18 < i16; i18++) {
                iArr2[i18] = i18;
            }
            i6 = i16;
        } else {
            int i19 = i16 / 2;
            i6 = i16 + 1 + (((i19 - 1) / 15) * 2);
            int i20 = i6 / 2;
            for (int i21 = i8; i21 < i19; i21++) {
                iArr2[(i19 - i21) - 1] = (i20 - r15) - 1;
                iArr2[i19 + i21] = (i21 / 15) + i21 + i20 + 1;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(i6);
        int i22 = i8;
        int i23 = i22;
        while (i22 < iAbs) {
            int i24 = (iAbs - i22) * i7;
            int i25 = z ? i24 + 9 : i24 + 12;
            int i26 = i8;
            while (i26 < i25) {
                int i27 = i26 * 2;
                while (i8 < i17) {
                    if (bitArrayGenerateCheckWords.get(i23 + i27 + i8)) {
                        int i28 = i22 * 2;
                        bitMatrix.set(iArr2[i28 + i8], iArr2[i28 + i26]);
                    }
                    if (bitArrayGenerateCheckWords.get((i25 * 2) + i23 + i27 + i8)) {
                        int i29 = i22 * 2;
                        bitMatrix.set(iArr2[i29 + i26], iArr2[((i16 - 1) - i29) - i8]);
                    }
                    if (bitArrayGenerateCheckWords.get((i25 * 4) + i23 + i27 + i8)) {
                        int i30 = (i16 - 1) - (i22 * 2);
                        bitMatrix.set(iArr2[i30 - i8], iArr2[i30 - i26]);
                    }
                    if (bitArrayGenerateCheckWords.get((i25 * 6) + i23 + i27 + i8)) {
                        int i31 = i22 * 2;
                        bitMatrix.set(iArr2[((i16 - 1) - i31) - i26], iArr2[i31 + i8]);
                    }
                    i8++;
                    i17 = 2;
                }
                i26++;
                i8 = 0;
                i17 = 2;
            }
            i23 += i25 * 8;
            i22++;
            i7 = 4;
            i8 = 0;
            i17 = 2;
        }
        drawModeMessage(bitMatrix, z, i6, bitArrayGenerateModeMessage);
        if (z) {
            drawBullsEye(bitMatrix, i6 / 2, 5);
        } else {
            int i32 = i6 / 2;
            drawBullsEye(bitMatrix, i32, 7);
            int i33 = 0;
            int i34 = 0;
            while (i33 < (i16 / 2) - 1) {
                for (int i35 = i32 & 1; i35 < i6; i35 += 2) {
                    int i36 = i32 - i34;
                    bitMatrix.set(i36, i35);
                    int i37 = i32 + i34;
                    bitMatrix.set(i37, i35);
                    bitMatrix.set(i35, i36);
                    bitMatrix.set(i35, i37);
                }
                i33 += 15;
                i34 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setCompact(z);
        aztecCode.setSize(i6);
        aztecCode.setLayers(iAbs);
        aztecCode.setCodeWords(size3);
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }

    private static void drawBullsEye(BitMatrix bitMatrix, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 <= i6) {
                    bitMatrix.set(i5, i4);
                    bitMatrix.set(i5, i6);
                    bitMatrix.set(i4, i5);
                    bitMatrix.set(i6, i5);
                    i5++;
                }
            }
        }
        int i7 = i - i2;
        bitMatrix.set(i7, i7);
        int i8 = i7 + 1;
        bitMatrix.set(i8, i7);
        bitMatrix.set(i7, i8);
        int i9 = i + i2;
        bitMatrix.set(i9, i7);
        bitMatrix.set(i9, i8);
        bitMatrix.set(i9, i9 - 1);
    }

    static BitArray generateModeMessage(boolean z, int i, int i2) {
        BitArray bitArray = new BitArray();
        if (z) {
            bitArray.appendBits(i - 1, 2);
            bitArray.appendBits(i2 - 1, 6);
            return generateCheckWords(bitArray, 28, 4);
        }
        bitArray.appendBits(i - 1, 5);
        bitArray.appendBits(i2 - 1, 11);
        return generateCheckWords(bitArray, 40, 4);
    }

    private static void drawModeMessage(BitMatrix bitMatrix, boolean z, int i, BitArray bitArray) {
        int i2 = i / 2;
        int i3 = 0;
        if (z) {
            while (i3 < 7) {
                int i4 = (i2 - 3) + i3;
                if (bitArray.get(i3)) {
                    bitMatrix.set(i4, i2 - 5);
                }
                if (bitArray.get(i3 + 7)) {
                    bitMatrix.set(i2 + 5, i4);
                }
                if (bitArray.get(20 - i3)) {
                    bitMatrix.set(i4, i2 + 5);
                }
                if (bitArray.get(27 - i3)) {
                    bitMatrix.set(i2 - 5, i4);
                }
                i3++;
            }
            return;
        }
        while (i3 < 10) {
            int i5 = (i2 - 5) + i3 + (i3 / 5);
            if (bitArray.get(i3)) {
                bitMatrix.set(i5, i2 - 7);
            }
            if (bitArray.get(i3 + 10)) {
                bitMatrix.set(i2 + 7, i5);
            }
            if (bitArray.get(29 - i3)) {
                bitMatrix.set(i5, i2 + 7);
            }
            if (bitArray.get(39 - i3)) {
                bitMatrix.set(i2 - 7, i5);
            }
            i3++;
        }
    }

    private static BitArray generateCheckWords(BitArray bitArray, int i, int i2) {
        int size = bitArray.getSize() / i2;
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(getGF(i2));
        int i3 = i / i2;
        int[] iArrBitsToWords = bitsToWords(bitArray, i2, i3);
        reedSolomonEncoder.encode(iArrBitsToWords, i3 - size);
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(0, i % i2);
        for (int i4 : iArrBitsToWords) {
            bitArray2.appendBits(i4, i2);
        }
        return bitArray2;
    }

    private static int[] bitsToWords(BitArray bitArray, int i, int i2) {
        int[] iArr = new int[i2];
        int size = bitArray.getSize() / i;
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                i4 |= bitArray.get((i3 * i) + i5) ? 1 << ((i - i5) - 1) : 0;
            }
            iArr[i3] = i4;
        }
        return iArr;
    }

    private static GenericGF getGF(int i) {
        if (i == 4) {
            return GenericGF.AZTEC_PARAM;
        }
        if (i == 6) {
            return GenericGF.AZTEC_DATA_6;
        }
        if (i == 8) {
            return GenericGF.AZTEC_DATA_8;
        }
        if (i == 10) {
            return GenericGF.AZTEC_DATA_10;
        }
        if (i != 12) {
            return null;
        }
        return GenericGF.AZTEC_DATA_12;
    }

    static BitArray stuffBits(BitArray bitArray, int i) {
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int i2 = (1 << i) - 2;
        int i3 = 0;
        while (i3 < size) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = i3 + i5;
                if (i6 >= size || bitArray.get(i6)) {
                    i4 |= 1 << ((i - 1) - i5);
                }
            }
            int i7 = i4 & i2;
            if (i7 == i2) {
                bitArray2.appendBits(i7, i);
            } else if (i7 == 0) {
                bitArray2.appendBits(i4 | 1, i);
            } else {
                bitArray2.appendBits(i4, i);
                i3 += i;
            }
            i3--;
            i3 += i;
        }
        return bitArray2;
    }
}
