package org.apache.shiro.codec;

/* JADX INFO: loaded from: classes.dex */
public class Base64 {
    private static final int BASELENGTH = 255;
    static final int CHUNK_SIZE = 76;
    private static final int EIGHTBIT = 8;
    private static final int FOURBYTE = 4;
    private static final int LOOKUPLENGTH = 64;
    private static final byte PAD = 61;
    private static final int SIGN = -128;
    private static final int SIXTEENBIT = 16;
    private static final int TWENTYFOURBITGROUP = 24;
    static final byte[] CHUNK_SEPARATOR = "\r\n".getBytes();
    private static final byte[] base64Alphabet = new byte[255];
    private static final byte[] lookUpBase64Alphabet = new byte[64];

    static {
        int i;
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < 255; i4++) {
            base64Alphabet[i4] = -1;
        }
        for (int i5 = 90; i5 >= 65; i5--) {
            base64Alphabet[i5] = (byte) (i5 - 65);
        }
        int i6 = 122;
        while (true) {
            i = 26;
            if (i6 < 97) {
                break;
            }
            base64Alphabet[i6] = (byte) ((i6 - 97) + 26);
            i6--;
        }
        int i7 = 57;
        while (true) {
            i2 = 52;
            if (i7 < 48) {
                break;
            }
            base64Alphabet[i7] = (byte) ((i7 - 48) + 52);
            i7--;
        }
        byte[] bArr = base64Alphabet;
        bArr[43] = 62;
        bArr[47] = 63;
        for (int i8 = 0; i8 <= 25; i8++) {
            lookUpBase64Alphabet[i8] = (byte) (i8 + 65);
        }
        int i9 = 0;
        while (i <= 51) {
            lookUpBase64Alphabet[i] = (byte) (i9 + 97);
            i++;
            i9++;
        }
        while (i2 <= 61) {
            lookUpBase64Alphabet[i2] = (byte) (i3 + 48);
            i2++;
            i3++;
        }
        byte[] bArr2 = lookUpBase64Alphabet;
        bArr2[62] = 43;
        bArr2[63] = 47;
    }

    private static boolean isBase64(byte b) {
        if (b == 61) {
            return true;
        }
        return b >= 0 && base64Alphabet[b] != -1;
    }

    public static boolean isBase64(byte[] bArr) {
        byte[] bArrDiscardWhitespace = discardWhitespace(bArr);
        if (bArrDiscardWhitespace.length == 0) {
            return true;
        }
        for (byte b : bArrDiscardWhitespace) {
            if (!isBase64(b)) {
                return false;
            }
        }
        return true;
    }

    static byte[] discardWhitespace(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (byte b : bArr) {
            if (b != 9 && b != 10 && b != 13 && b != 32) {
                bArr2[i] = b;
                i++;
            }
        }
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr2, 0, bArr3, 0, i);
        return bArr3;
    }

    public static String encodeToString(byte[] bArr) {
        return CodecSupport.toString(encode(bArr));
    }

    public static byte[] encodeChunked(byte[] bArr) {
        return encode(bArr, true);
    }

    public static byte[] encode(byte[] bArr) {
        return encode(bArr, false);
    }

    public static byte[] encode(byte[] bArr, boolean z) {
        int iCeil;
        long j;
        int i;
        long length = ((long) bArr.length) * 8;
        long j2 = length % 24;
        long j3 = length / 24;
        long length2 = j2 != 0 ? (1 + j3) * 4 : j3 * 4;
        if (z) {
            byte[] bArr2 = CHUNK_SEPARATOR;
            iCeil = bArr2.length == 0 ? 0 : (int) Math.ceil(length2 / 76.0f);
            length2 += (long) (bArr2.length * iCeil);
        } else {
            iCeil = 0;
        }
        if (length2 > 2147483647L) {
            throw new IllegalArgumentException("Input array too big, output array would be bigger than Integer.MAX_VALUE=2147483647");
        }
        int i2 = (int) length2;
        byte[] bArr3 = new byte[i2];
        int i3 = 0;
        int length3 = 0;
        int i4 = 0;
        int length4 = 76;
        while (i3 < j3) {
            int i5 = i3 * 3;
            byte b = bArr[i5];
            byte b2 = bArr[i5 + 1];
            byte b3 = bArr[i5 + 2];
            byte b4 = (byte) (b2 & 15);
            byte b5 = (byte) (b & 3);
            int i6 = b & (-128);
            int i7 = b >> 2;
            if (i6 != 0) {
                i7 ^= 192;
            }
            byte b6 = (byte) i7;
            if ((b2 & (-128)) == 0) {
                j = j3;
                i = b2 >> 4;
            } else {
                j = j3;
                i = (b2 >> 4) ^ 240;
            }
            byte b7 = (byte) i;
            int i8 = (b3 & (-128)) == 0 ? b3 >> 6 : (b3 >> 6) ^ 252;
            byte[] bArr4 = lookUpBase64Alphabet;
            bArr3[length3] = bArr4[b6];
            bArr3[length3 + 1] = bArr4[b7 | (b5 << 4)];
            bArr3[length3 + 2] = bArr4[((byte) i8) | (b4 << 2)];
            bArr3[length3 + 3] = bArr4[b3 & 63];
            length3 += 4;
            if (z && length3 == length4) {
                byte[] bArr5 = CHUNK_SEPARATOR;
                System.arraycopy(bArr5, 0, bArr3, length3, bArr5.length);
                i4++;
                length4 = ((i4 + 1) * 76) + (bArr5.length * i4);
                length3 += bArr5.length;
            }
            i3++;
            j3 = j;
        }
        int i9 = i3 * 3;
        if (j2 == 8) {
            byte b8 = bArr[i9];
            byte b9 = (byte) (b8 & 3);
            int i10 = b8 & (-128);
            int i11 = b8 >> 2;
            if (i10 != 0) {
                i11 ^= 192;
            }
            byte[] bArr6 = lookUpBase64Alphabet;
            bArr3[length3] = bArr6[(byte) i11];
            bArr3[length3 + 1] = bArr6[b9 << 4];
            bArr3[length3 + 2] = PAD;
            bArr3[length3 + 3] = PAD;
        } else if (j2 == 16) {
            byte b10 = bArr[i9];
            byte b11 = bArr[i9 + 1];
            byte b12 = (byte) (b11 & 15);
            byte b13 = (byte) (b10 & 3);
            int i12 = b10 & (-128);
            int i13 = b10 >> 2;
            if (i12 != 0) {
                i13 ^= 192;
            }
            byte b14 = (byte) i13;
            int i14 = b11 & (-128);
            int i15 = b11 >> 4;
            if (i14 != 0) {
                i15 ^= 240;
            }
            byte[] bArr7 = lookUpBase64Alphabet;
            bArr3[length3] = bArr7[b14];
            bArr3[length3 + 1] = bArr7[((byte) i15) | (b13 << 4)];
            bArr3[length3 + 2] = bArr7[b12 << 2];
            bArr3[length3 + 3] = PAD;
        }
        if (z && i4 < iCeil) {
            byte[] bArr8 = CHUNK_SEPARATOR;
            System.arraycopy(bArr8, 0, bArr3, i2 - bArr8.length, bArr8.length);
        }
        return bArr3;
    }

    public static String decodeToString(String str) {
        return decodeToString(CodecSupport.toBytes(str));
    }

    public static String decodeToString(byte[] bArr) {
        return CodecSupport.toString(decode(bArr));
    }

    public static byte[] decode(String str) {
        return decode(CodecSupport.toBytes(str));
    }

    public static byte[] decode(byte[] bArr) {
        byte[] bArrDiscardNonBase64 = discardNonBase64(bArr);
        if (bArrDiscardNonBase64.length == 0) {
            return new byte[0];
        }
        int length = bArrDiscardNonBase64.length / 4;
        int length2 = bArrDiscardNonBase64.length;
        while (bArrDiscardNonBase64[length2 - 1] == 61) {
            length2--;
            if (length2 == 0) {
                return new byte[0];
            }
        }
        byte[] bArr2 = new byte[length2 - length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 4;
            byte b = bArrDiscardNonBase64[i3 + 2];
            byte b2 = bArrDiscardNonBase64[i3 + 3];
            byte[] bArr3 = base64Alphabet;
            byte b3 = bArr3[bArrDiscardNonBase64[i3]];
            byte b4 = bArr3[bArrDiscardNonBase64[i3 + 1]];
            if (b != 61 && b2 != 61) {
                byte b5 = bArr3[b];
                byte b6 = bArr3[b2];
                bArr2[i] = (byte) ((b3 << 2) | (b4 >> 4));
                bArr2[i + 1] = (byte) (((b4 & 15) << 4) | ((b5 >> 2) & 15));
                bArr2[i + 2] = (byte) ((b5 << 6) | b6);
            } else if (b == 61) {
                bArr2[i] = (byte) ((b4 >> 4) | (b3 << 2));
            } else {
                byte b7 = bArr3[b];
                bArr2[i] = (byte) ((b3 << 2) | (b4 >> 4));
                bArr2[i + 1] = (byte) (((b4 & 15) << 4) | ((b7 >> 2) & 15));
            }
            i += 3;
        }
        return bArr2;
    }

    static byte[] discardNonBase64(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        for (byte b : bArr) {
            if (isBase64(b)) {
                bArr2[i] = b;
                i++;
            }
        }
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr2, 0, bArr3, 0, i);
        return bArr3;
    }
}
