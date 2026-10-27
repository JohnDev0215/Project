package org.apache.commons.codec.binary;

import org.apache.commons.codec.binary.BaseNCodec;
import org.bouncycastle.math.ec.Tnaf;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class Base32 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 5;
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final int BYTES_PER_UNENCODED_BLOCK = 5;
    private static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, Tnaf.POW_2_WIDTH, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, Tnaf.POW_2_WIDTH, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private static final byte[] ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 50, 51, 52, 53, 54, 55};
    private static final byte[] HEX_DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, Tnaf.POW_2_WIDTH, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, Tnaf.POW_2_WIDTH, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private static final byte[] HEX_ENCODE_TABLE = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86};
    private static final long MASK_1BITS = 1;
    private static final long MASK_2BITS = 3;
    private static final long MASK_3BITS = 7;
    private static final long MASK_4BITS = 15;
    private static final int MASK_5BITS = 31;
    private static final long MASK_6BITS = 63;
    private static final long MASK_7BITS = 127;
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;

    public Base32() {
        this(false);
    }

    private static void validateCharacter(long j, BaseNCodec.Context context) {
        if ((j & context.lbitWorkArea) != 0) {
            throw new IllegalArgumentException("Last encoded character (before the paddings if any) is a valid base 32 alphabet but not a possible value. Expected the discarded bits to be zero.");
        }
    }

    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2, types: [boolean, int] */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    void decode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        byte b2;
        if (context.eof) {
            return;
        }
        ?? r3 = 1;
        if (i2 < 0) {
            context.eof = true;
        }
        int i3 = 0;
        int i4 = i;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            int i5 = i4 + 1;
            byte b3 = bArr[i4];
            if (b3 == this.pad) {
                context.eof = r3;
                break;
            }
            byte[] bArrEnsureBufferSize = ensureBufferSize(this.decodeSize, context);
            if (b3 >= 0) {
                byte[] bArr2 = this.decodeTable;
                if (b3 < bArr2.length && (b2 = bArr2[b3]) >= 0) {
                    int i6 = (context.modulus + r3) % 8;
                    context.modulus = i6;
                    context.lbitWorkArea = (context.lbitWorkArea << 5) + ((long) b2);
                    if (i6 == 0) {
                        int i7 = context.pos;
                        int i8 = i7 + 1;
                        context.pos = i8;
                        bArrEnsureBufferSize[i7] = (byte) ((r14 >> 32) & 255);
                        int i9 = i8 + 1;
                        context.pos = i9;
                        bArrEnsureBufferSize[i8] = (byte) ((r14 >> 24) & 255);
                        int i10 = i9 + 1;
                        context.pos = i10;
                        bArrEnsureBufferSize[i9] = (byte) ((r14 >> 16) & 255);
                        int i11 = i10 + 1;
                        context.pos = i11;
                        bArrEnsureBufferSize[i10] = (byte) ((r14 >> 8) & 255);
                        context.pos = i11 + 1;
                        bArrEnsureBufferSize[i11] = (byte) (r14 & 255);
                    }
                }
            }
            i3++;
            i4 = i5;
            r3 = 1;
        }
        if (!context.eof || context.modulus < 2) {
            return;
        }
        byte[] bArrEnsureBufferSize2 = ensureBufferSize(this.decodeSize, context);
        switch (context.modulus) {
            case 2:
                validateCharacter(MASK_2BITS, context);
                int i12 = context.pos;
                context.pos = i12 + 1;
                bArrEnsureBufferSize2[i12] = (byte) ((context.lbitWorkArea >> 2) & 255);
                return;
            case 3:
                validateCharacter(MASK_7BITS, context);
                int i13 = context.pos;
                context.pos = i13 + 1;
                bArrEnsureBufferSize2[i13] = (byte) ((context.lbitWorkArea >> MASK_3BITS) & 255);
                return;
            case 4:
                validateCharacter(MASK_4BITS, context);
                context.lbitWorkArea = context.lbitWorkArea >> 4;
                int i14 = context.pos;
                int i15 = i14 + 1;
                context.pos = i15;
                bArrEnsureBufferSize2[i14] = (byte) ((r3 >> 8) & 255);
                context.pos = i15 + 1;
                bArrEnsureBufferSize2[i15] = (byte) (r3 & 255);
                return;
            case 5:
                validateCharacter(MASK_1BITS, context);
                context.lbitWorkArea = context.lbitWorkArea >> MASK_1BITS;
                int i16 = context.pos;
                int i17 = i16 + 1;
                context.pos = i17;
                bArrEnsureBufferSize2[i16] = (byte) ((r3 >> 16) & 255);
                int i18 = i17 + 1;
                context.pos = i18;
                bArrEnsureBufferSize2[i17] = (byte) ((r3 >> 8) & 255);
                context.pos = i18 + 1;
                bArrEnsureBufferSize2[i18] = (byte) (r3 & 255);
                return;
            case 6:
                validateCharacter(MASK_6BITS, context);
                context.lbitWorkArea = context.lbitWorkArea >> 6;
                int i19 = context.pos;
                int i20 = i19 + 1;
                context.pos = i20;
                bArrEnsureBufferSize2[i19] = (byte) ((r3 >> 16) & 255);
                int i21 = i20 + 1;
                context.pos = i21;
                bArrEnsureBufferSize2[i20] = (byte) ((r3 >> 8) & 255);
                context.pos = i21 + 1;
                bArrEnsureBufferSize2[i21] = (byte) (r3 & 255);
                return;
            case 7:
                validateCharacter(MASK_3BITS, context);
                context.lbitWorkArea = context.lbitWorkArea >> MASK_2BITS;
                int i22 = context.pos;
                int i23 = i22 + 1;
                context.pos = i23;
                bArrEnsureBufferSize2[i22] = (byte) ((r3 >> 24) & 255);
                int i24 = i23 + 1;
                context.pos = i24;
                bArrEnsureBufferSize2[i23] = (byte) ((r3 >> 16) & 255);
                int i25 = i24 + 1;
                context.pos = i25;
                bArrEnsureBufferSize2[i24] = (byte) ((r3 >> 8) & 255);
                context.pos = i25 + 1;
                bArrEnsureBufferSize2[i25] = (byte) (r3 & 255);
                return;
            default:
                throw new IllegalStateException("Impossible modulus " + context.modulus);
        }
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    void encode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        boolean z;
        if (context.eof) {
            return;
        }
        boolean z2 = false;
        int i3 = 1;
        if (i2 >= 0) {
            int i4 = i;
            int i5 = 0;
            while (i5 < i2) {
                byte[] bArrEnsureBufferSize = ensureBufferSize(this.encodeSize, context);
                context.modulus = (context.modulus + i3) % 5;
                int i6 = i4 + 1;
                int i7 = bArr[i4];
                if (i7 < 0) {
                    i7 += 256;
                }
                long j = (context.lbitWorkArea << 8) + ((long) i7);
                context.lbitWorkArea = j;
                if (context.modulus == 0) {
                    int i8 = context.pos;
                    int i9 = i8 + 1;
                    context.pos = i9;
                    byte[] bArr2 = this.encodeTable;
                    bArrEnsureBufferSize[i8] = bArr2[((int) (j >> 35)) & MASK_5BITS];
                    int i10 = i9 + 1;
                    context.pos = i10;
                    bArrEnsureBufferSize[i9] = bArr2[((int) (j >> 30)) & MASK_5BITS];
                    int i11 = i10 + 1;
                    context.pos = i11;
                    bArrEnsureBufferSize[i10] = bArr2[((int) (j >> 25)) & MASK_5BITS];
                    int i12 = i11 + 1;
                    context.pos = i12;
                    bArrEnsureBufferSize[i11] = bArr2[((int) (j >> 20)) & MASK_5BITS];
                    int i13 = i12 + 1;
                    context.pos = i13;
                    bArrEnsureBufferSize[i12] = bArr2[((int) (j >> MASK_4BITS)) & MASK_5BITS];
                    int i14 = i13 + 1;
                    context.pos = i14;
                    bArrEnsureBufferSize[i13] = bArr2[((int) (j >> 10)) & MASK_5BITS];
                    int i15 = i14 + 1;
                    context.pos = i15;
                    bArrEnsureBufferSize[i14] = bArr2[((int) (j >> 5)) & MASK_5BITS];
                    int i16 = i15 + 1;
                    context.pos = i16;
                    bArrEnsureBufferSize[i15] = bArr2[((int) j) & MASK_5BITS];
                    int i17 = context.currentLinePos + 8;
                    context.currentLinePos = i17;
                    int i18 = this.lineLength;
                    if (i18 <= 0 || i18 > i17) {
                        z = false;
                    } else {
                        byte[] bArr3 = this.lineSeparator;
                        z = false;
                        System.arraycopy(bArr3, 0, bArrEnsureBufferSize, i16, bArr3.length);
                        context.pos += this.lineSeparator.length;
                        context.currentLinePos = 0;
                    }
                } else {
                    z = z2;
                }
                i5++;
                i4 = i6;
                z2 = z;
                i3 = 1;
            }
            return;
        }
        context.eof = true;
        if (context.modulus == 0 && this.lineLength == 0) {
            return;
        }
        byte[] bArrEnsureBufferSize2 = ensureBufferSize(this.encodeSize, context);
        int i19 = context.pos;
        int i20 = context.modulus;
        if (i20 != 0) {
            if (i20 == 1) {
                int i21 = i19 + 1;
                context.pos = i21;
                byte[] bArr4 = this.encodeTable;
                long j2 = context.lbitWorkArea;
                bArrEnsureBufferSize2[i19] = bArr4[((int) (j2 >> MASK_2BITS)) & MASK_5BITS];
                int i22 = i21 + 1;
                context.pos = i22;
                bArrEnsureBufferSize2[i21] = bArr4[((int) (j2 << 2)) & MASK_5BITS];
                int i23 = i22 + 1;
                context.pos = i23;
                byte b2 = this.pad;
                bArrEnsureBufferSize2[i22] = b2;
                int i24 = i23 + 1;
                context.pos = i24;
                bArrEnsureBufferSize2[i23] = b2;
                int i25 = i24 + 1;
                context.pos = i25;
                bArrEnsureBufferSize2[i24] = b2;
                int i26 = i25 + 1;
                context.pos = i26;
                bArrEnsureBufferSize2[i25] = b2;
                int i27 = i26 + 1;
                context.pos = i27;
                bArrEnsureBufferSize2[i26] = b2;
                context.pos = i27 + 1;
                bArrEnsureBufferSize2[i27] = b2;
            } else if (i20 == 2) {
                int i28 = i19 + 1;
                context.pos = i28;
                byte[] bArr5 = this.encodeTable;
                long j3 = context.lbitWorkArea;
                bArrEnsureBufferSize2[i19] = bArr5[((int) (j3 >> 11)) & MASK_5BITS];
                int i29 = i28 + 1;
                context.pos = i29;
                bArrEnsureBufferSize2[i28] = bArr5[((int) (j3 >> 6)) & MASK_5BITS];
                int i30 = i29 + 1;
                context.pos = i30;
                bArrEnsureBufferSize2[i29] = bArr5[((int) (j3 >> MASK_1BITS)) & MASK_5BITS];
                int i31 = i30 + 1;
                context.pos = i31;
                bArrEnsureBufferSize2[i30] = bArr5[((int) (j3 << 4)) & MASK_5BITS];
                int i32 = i31 + 1;
                context.pos = i32;
                byte b3 = this.pad;
                bArrEnsureBufferSize2[i31] = b3;
                int i33 = i32 + 1;
                context.pos = i33;
                bArrEnsureBufferSize2[i32] = b3;
                int i34 = i33 + 1;
                context.pos = i34;
                bArrEnsureBufferSize2[i33] = b3;
                context.pos = i34 + 1;
                bArrEnsureBufferSize2[i34] = b3;
            } else if (i20 == 3) {
                int i35 = i19 + 1;
                context.pos = i35;
                byte[] bArr6 = this.encodeTable;
                long j4 = context.lbitWorkArea;
                bArrEnsureBufferSize2[i19] = bArr6[((int) (j4 >> 19)) & MASK_5BITS];
                int i36 = i35 + 1;
                context.pos = i36;
                bArrEnsureBufferSize2[i35] = bArr6[((int) (j4 >> 14)) & MASK_5BITS];
                int i37 = i36 + 1;
                context.pos = i37;
                bArrEnsureBufferSize2[i36] = bArr6[((int) (j4 >> 9)) & MASK_5BITS];
                int i38 = i37 + 1;
                context.pos = i38;
                bArrEnsureBufferSize2[i37] = bArr6[((int) (j4 >> 4)) & MASK_5BITS];
                int i39 = i38 + 1;
                context.pos = i39;
                bArrEnsureBufferSize2[i38] = bArr6[((int) (j4 << MASK_1BITS)) & MASK_5BITS];
                int i40 = i39 + 1;
                context.pos = i40;
                byte b4 = this.pad;
                bArrEnsureBufferSize2[i39] = b4;
                int i41 = i40 + 1;
                context.pos = i41;
                bArrEnsureBufferSize2[i40] = b4;
                context.pos = i41 + 1;
                bArrEnsureBufferSize2[i41] = b4;
            } else {
                if (i20 != 4) {
                    throw new IllegalStateException("Impossible modulus " + context.modulus);
                }
                int i42 = i19 + 1;
                context.pos = i42;
                byte[] bArr7 = this.encodeTable;
                long j5 = context.lbitWorkArea;
                bArrEnsureBufferSize2[i19] = bArr7[((int) (j5 >> 27)) & MASK_5BITS];
                int i43 = i42 + 1;
                context.pos = i43;
                bArrEnsureBufferSize2[i42] = bArr7[((int) (j5 >> 22)) & MASK_5BITS];
                int i44 = i43 + 1;
                context.pos = i44;
                bArrEnsureBufferSize2[i43] = bArr7[((int) (j5 >> 17)) & MASK_5BITS];
                int i45 = i44 + 1;
                context.pos = i45;
                bArrEnsureBufferSize2[i44] = bArr7[((int) (j5 >> 12)) & MASK_5BITS];
                int i46 = i45 + 1;
                context.pos = i46;
                bArrEnsureBufferSize2[i45] = bArr7[((int) (j5 >> MASK_3BITS)) & MASK_5BITS];
                int i47 = i46 + 1;
                context.pos = i47;
                bArrEnsureBufferSize2[i46] = bArr7[((int) (j5 >> 2)) & MASK_5BITS];
                int i48 = i47 + 1;
                context.pos = i48;
                bArrEnsureBufferSize2[i47] = bArr7[((int) (j5 << MASK_2BITS)) & MASK_5BITS];
                context.pos = i48 + 1;
                bArrEnsureBufferSize2[i48] = this.pad;
            }
        }
        int i49 = context.currentLinePos;
        int i50 = context.pos;
        int i51 = i49 + (i50 - i19);
        context.currentLinePos = i51;
        if (this.lineLength <= 0 || i51 <= 0) {
            return;
        }
        byte[] bArr8 = this.lineSeparator;
        System.arraycopy(bArr8, 0, bArrEnsureBufferSize2, i50, bArr8.length);
        context.pos += this.lineSeparator.length;
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public boolean isInAlphabet(byte b2) {
        if (b2 >= 0) {
            byte[] bArr = this.decodeTable;
            if (b2 < bArr.length && bArr[b2] != -1) {
                return true;
            }
        }
        return false;
    }

    public Base32(byte b2) {
        this(false, b2);
    }

    public Base32(boolean z) {
        this(0, null, z, (byte) 61);
    }

    public Base32(boolean z, byte b2) {
        this(0, null, z, b2);
    }

    public Base32(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public Base32(int i, byte[] bArr) {
        this(i, bArr, false, (byte) 61);
    }

    public Base32(int i, byte[] bArr, boolean z) {
        this(i, bArr, z, (byte) 61);
    }

    public Base32(int i, byte[] bArr, boolean z, byte b2) {
        super(5, 8, i, bArr == null ? 0 : bArr.length, b2);
        if (z) {
            this.encodeTable = HEX_ENCODE_TABLE;
            this.decodeTable = HEX_DECODE_TABLE;
        } else {
            this.encodeTable = ENCODE_TABLE;
            this.decodeTable = DECODE_TABLE;
        }
        if (i <= 0) {
            this.encodeSize = 8;
            this.lineSeparator = null;
        } else if (bArr != null) {
            if (!containsAlphabetOrPad(bArr)) {
                this.encodeSize = bArr.length + 8;
                byte[] bArr2 = new byte[bArr.length];
                this.lineSeparator = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            } else {
                throw new IllegalArgumentException("lineSeparator must not contain Base32 characters: [" + StringUtils.newStringUtf8(bArr) + "]");
            }
        } else {
            throw new IllegalArgumentException("lineLength " + i + " > 0, but lineSeparator is null");
        }
        this.decodeSize = this.encodeSize - 1;
        if (isInAlphabet(b2) || BaseNCodec.isWhiteSpace(b2)) {
            throw new IllegalArgumentException("pad must not be in alphabet or whitespace");
        }
    }
}
