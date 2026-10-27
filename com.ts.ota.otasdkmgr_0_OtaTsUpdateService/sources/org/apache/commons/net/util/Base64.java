package org.apache.commons.net.util;

import com.engine.tbox.data.ReservationCharging;
import com.engine.tbox.utils.AESUtils;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class Base64 {
    static final int CHUNK_SIZE = 76;
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    private static final int MASK_6BITS = 63;
    private static final int MASK_8BITS = 255;
    private byte[] buffer;
    private int currentLinePos;
    private final int decodeSize;
    private final int encodeSize;
    private final byte[] encodeTable;
    private boolean eof;
    private final int lineLength;
    private final byte[] lineSeparator;
    private int modulus;
    private int pos;
    private int readPos;
    private int x;
    private static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] STANDARD_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] URL_SAFE_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte PAD = 61;
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, ReservationCharging.MAX_MIN, 60, PAD, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, ReservationCharging.MAX_HOUR, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    public Base64() {
        this(false);
    }

    public Base64(boolean urlSafe) {
        this(CHUNK_SIZE, CHUNK_SEPARATOR, urlSafe);
    }

    public Base64(int lineLength) {
        this(lineLength, CHUNK_SEPARATOR);
    }

    public Base64(int lineLength, byte[] lineSeparator) {
        this(lineLength, lineSeparator, false);
    }

    public Base64(int lineLength, byte[] lineSeparator, boolean urlSafe) {
        if (lineSeparator == null) {
            lineLength = 0;
            lineSeparator = NetConstants.EMPTY_BTYE_ARRAY;
        }
        this.lineLength = lineLength > 0 ? (lineLength / 4) * 4 : 0;
        byte[] bArr = new byte[lineSeparator.length];
        this.lineSeparator = bArr;
        System.arraycopy(lineSeparator, 0, bArr, 0, lineSeparator.length);
        if (lineLength > 0) {
            this.encodeSize = lineSeparator.length + 4;
        } else {
            this.encodeSize = 4;
        }
        this.decodeSize = this.encodeSize - 1;
        if (containsBase64Byte(lineSeparator)) {
            String sep = newStringUtf8(lineSeparator);
            throw new IllegalArgumentException("lineSeperator must not contain base64 characters: [" + sep + "]");
        }
        this.encodeTable = urlSafe ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }

    public boolean isUrlSafe() {
        return this.encodeTable == URL_SAFE_ENCODE_TABLE;
    }

    boolean hasData() {
        return this.buffer != null;
    }

    int avail() {
        if (this.buffer != null) {
            return this.pos - this.readPos;
        }
        return 0;
    }

    private void resizeBuffer() {
        byte[] bArr = this.buffer;
        if (bArr == null) {
            this.buffer = new byte[DEFAULT_BUFFER_SIZE];
            this.pos = 0;
            this.readPos = 0;
        } else {
            byte[] b = new byte[bArr.length * 2];
            System.arraycopy(bArr, 0, b, 0, bArr.length);
            this.buffer = b;
        }
    }

    int readResults(byte[] b, int bPos, int bAvail) {
        if (this.buffer == null) {
            return this.eof ? -1 : 0;
        }
        int len = Math.min(avail(), bAvail);
        byte[] bArr = this.buffer;
        if (bArr != b) {
            System.arraycopy(bArr, this.readPos, b, bPos, len);
            int i = this.readPos + len;
            this.readPos = i;
            if (i >= this.pos) {
                this.buffer = null;
            }
        } else {
            this.buffer = null;
        }
        return len;
    }

    void setInitialBuffer(byte[] out, int outPos, int outAvail) {
        if (out != null && out.length == outAvail) {
            this.buffer = out;
            this.pos = outPos;
            this.readPos = outPos;
        }
    }

    void encode(byte[] bArr, int inPos, int inAvail) {
        int i;
        if (this.eof) {
            return;
        }
        if (inAvail < 0) {
            this.eof = true;
            byte[] bArr2 = this.buffer;
            if (bArr2 == null || bArr2.length - this.pos < this.encodeSize) {
                resizeBuffer();
            }
            int i2 = this.modulus;
            if (i2 == 1) {
                byte[] bArr3 = this.buffer;
                int i3 = this.pos;
                int i4 = i3 + 1;
                this.pos = i4;
                byte[] bArr4 = this.encodeTable;
                int i5 = this.x;
                bArr3[i3] = bArr4[(i5 >> 2) & MASK_6BITS];
                int i6 = i4 + 1;
                this.pos = i6;
                bArr3[i4] = bArr4[(i5 << 4) & MASK_6BITS];
                if (bArr4 == STANDARD_ENCODE_TABLE) {
                    int i7 = i6 + 1;
                    this.pos = i7;
                    bArr3[i6] = PAD;
                    this.pos = i7 + 1;
                    bArr3[i7] = PAD;
                }
            } else if (i2 == 2) {
                byte[] bArr5 = this.buffer;
                int i8 = this.pos;
                int i9 = i8 + 1;
                this.pos = i9;
                byte[] bArr6 = this.encodeTable;
                int i10 = this.x;
                bArr5[i8] = bArr6[(i10 >> 10) & MASK_6BITS];
                int i11 = i9 + 1;
                this.pos = i11;
                bArr5[i9] = bArr6[(i10 >> 4) & MASK_6BITS];
                int i12 = i11 + 1;
                this.pos = i12;
                bArr5[i11] = bArr6[(i10 << 2) & MASK_6BITS];
                if (bArr6 == STANDARD_ENCODE_TABLE) {
                    this.pos = i12 + 1;
                    bArr5[i12] = PAD;
                }
            }
            if (this.lineLength > 0 && (i = this.pos) > 0) {
                byte[] bArr7 = this.lineSeparator;
                System.arraycopy(bArr7, 0, this.buffer, i, bArr7.length);
                this.pos += this.lineSeparator.length;
                return;
            }
            return;
        }
        int i13 = 0;
        while (i13 < inAvail) {
            byte[] bArr8 = this.buffer;
            if (bArr8 == null || bArr8.length - this.pos < this.encodeSize) {
                resizeBuffer();
            }
            int i14 = this.modulus + 1;
            this.modulus = i14;
            this.modulus = i14 % 3;
            int inPos2 = inPos + 1;
            int i15 = bArr[inPos];
            if (i15 < 0) {
                i15 += AESUtils.ECB_256_LENGTH;
            }
            int i16 = (this.x << 8) + i15;
            this.x = i16;
            if (this.modulus == 0) {
                byte[] bArr9 = this.buffer;
                int i17 = this.pos;
                int i18 = i17 + 1;
                this.pos = i18;
                byte[] bArr10 = this.encodeTable;
                bArr9[i17] = bArr10[(i16 >> 18) & MASK_6BITS];
                int i19 = i18 + 1;
                this.pos = i19;
                bArr9[i18] = bArr10[(i16 >> 12) & MASK_6BITS];
                int i20 = i19 + 1;
                this.pos = i20;
                bArr9[i19] = bArr10[(i16 >> 6) & MASK_6BITS];
                int i21 = i20 + 1;
                this.pos = i21;
                bArr9[i20] = bArr10[i16 & MASK_6BITS];
                int i22 = this.currentLinePos + 4;
                this.currentLinePos = i22;
                int i23 = this.lineLength;
                if (i23 > 0 && i23 <= i22) {
                    byte[] bArr11 = this.lineSeparator;
                    System.arraycopy(bArr11, 0, bArr9, i21, bArr11.length);
                    this.pos += this.lineSeparator.length;
                    this.currentLinePos = 0;
                }
            }
            i13++;
            inPos = inPos2;
        }
    }

    void decode(byte[] in, int inPos, int inAvail) {
        int i;
        int result;
        if (this.eof) {
            return;
        }
        if (inAvail < 0) {
            this.eof = true;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= inAvail) {
                break;
            }
            byte[] bArr = this.buffer;
            if (bArr == null || bArr.length - this.pos < this.decodeSize) {
                resizeBuffer();
            }
            int inPos2 = inPos + 1;
            byte b = in[inPos];
            if (b == 61) {
                this.eof = true;
                break;
            }
            if (b >= 0) {
                byte[] bArr2 = DECODE_TABLE;
                if (b < bArr2.length && (result = bArr2[b]) >= 0) {
                    int i3 = this.modulus + 1;
                    this.modulus = i3;
                    int i4 = i3 % 4;
                    this.modulus = i4;
                    int i5 = (this.x << 6) + result;
                    this.x = i5;
                    if (i4 == 0) {
                        byte[] bArr3 = this.buffer;
                        int i6 = this.pos;
                        int i7 = i6 + 1;
                        this.pos = i7;
                        bArr3[i6] = (byte) ((i5 >> 16) & 255);
                        int i8 = i7 + 1;
                        this.pos = i8;
                        bArr3[i7] = (byte) ((i5 >> 8) & 255);
                        this.pos = i8 + 1;
                        bArr3[i8] = (byte) (i5 & 255);
                    }
                }
            }
            i2++;
            inPos = inPos2;
        }
        if (this.eof && (i = this.modulus) != 0) {
            int i9 = this.x << 6;
            this.x = i9;
            if (i == 2) {
                int i10 = i9 << 6;
                this.x = i10;
                byte[] bArr4 = this.buffer;
                int i11 = this.pos;
                this.pos = i11 + 1;
                bArr4[i11] = (byte) ((i10 >> 16) & 255);
                return;
            }
            if (i == 3) {
                byte[] bArr5 = this.buffer;
                int i12 = this.pos;
                int i13 = i12 + 1;
                this.pos = i13;
                bArr5[i12] = (byte) ((i9 >> 16) & 255);
                this.pos = i13 + 1;
                bArr5[i13] = (byte) ((i9 >> 8) & 255);
            }
        }
    }

    public static boolean isBase64(byte octet) {
        if (octet != 61) {
            if (octet >= 0) {
                byte[] bArr = DECODE_TABLE;
                if (octet >= bArr.length || bArr[octet] == -1) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean isArrayByteBase64(byte[] arrayOctet) {
        for (byte element : arrayOctet) {
            if (!isBase64(element) && !isWhiteSpace(element)) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsBase64Byte(byte[] arrayOctet) {
        for (byte element : arrayOctet) {
            if (isBase64(element)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] encodeBase64(byte[] binaryData) {
        return encodeBase64(binaryData, false);
    }

    public static String encodeBase64String(byte[] binaryData) {
        return newStringUtf8(encodeBase64(binaryData, true));
    }

    public static String encodeBase64StringUnChunked(byte[] binaryData) {
        return newStringUtf8(encodeBase64(binaryData, false));
    }

    public static String encodeBase64String(byte[] binaryData, boolean useChunking) {
        return newStringUtf8(encodeBase64(binaryData, useChunking));
    }

    public static byte[] encodeBase64URLSafe(byte[] binaryData) {
        return encodeBase64(binaryData, false, true);
    }

    public static String encodeBase64URLSafeString(byte[] binaryData) {
        return newStringUtf8(encodeBase64(binaryData, false, true));
    }

    public static byte[] encodeBase64Chunked(byte[] binaryData) {
        return encodeBase64(binaryData, true);
    }

    public byte[] decode(String pArray) {
        return decode(getBytesUtf8(pArray));
    }

    private byte[] getBytesUtf8(String pArray) {
        return pArray.getBytes(StandardCharsets.UTF_8);
    }

    public byte[] decode(byte[] pArray) {
        reset();
        if (pArray == null || pArray.length == 0) {
            return pArray;
        }
        long len = (pArray.length * 3) / 4;
        byte[] buf = new byte[(int) len];
        setInitialBuffer(buf, 0, buf.length);
        decode(pArray, 0, pArray.length);
        decode(pArray, 0, -1);
        byte[] result = new byte[this.pos];
        readResults(result, 0, result.length);
        return result;
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked) {
        return encodeBase64(binaryData, isChunked, false);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked, boolean urlSafe) {
        return encodeBase64(binaryData, isChunked, urlSafe, Integer.MAX_VALUE);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked, boolean urlSafe, int maxResultSize) {
        if (binaryData == null || binaryData.length == 0) {
            return binaryData;
        }
        long len = getEncodeLength(binaryData, isChunked ? CHUNK_SIZE : 0, isChunked ? CHUNK_SEPARATOR : NetConstants.EMPTY_BTYE_ARRAY);
        if (len > maxResultSize) {
            throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + len + ") than the specified maxium size of " + maxResultSize);
        }
        Base64 b64 = isChunked ? new Base64(urlSafe) : new Base64(0, CHUNK_SEPARATOR, urlSafe);
        return b64.encode(binaryData);
    }

    public static byte[] decodeBase64(String base64String) {
        return new Base64().decode(base64String);
    }

    public static byte[] decodeBase64(byte[] base64Data) {
        return new Base64().decode(base64Data);
    }

    private static boolean isWhiteSpace(byte byteToCheck) {
        if (byteToCheck == 9 || byteToCheck == 10 || byteToCheck == 13 || byteToCheck == 32) {
            return true;
        }
        return false;
    }

    public String encodeToString(byte[] pArray) {
        return newStringUtf8(encode(pArray));
    }

    private static String newStringUtf8(byte[] encode) {
        return new String(encode, StandardCharsets.UTF_8);
    }

    public byte[] encode(byte[] pArray) {
        int i;
        reset();
        if (pArray == null || pArray.length == 0) {
            return pArray;
        }
        long len = getEncodeLength(pArray, this.lineLength, this.lineSeparator);
        byte[] buf = new byte[(int) len];
        setInitialBuffer(buf, 0, buf.length);
        encode(pArray, 0, pArray.length);
        encode(pArray, 0, -1);
        if (this.buffer != buf) {
            readResults(buf, 0, buf.length);
        }
        if (isUrlSafe() && (i = this.pos) < buf.length) {
            byte[] smallerBuf = new byte[i];
            System.arraycopy(buf, 0, smallerBuf, 0, i);
            return smallerBuf;
        }
        return buf;
    }

    private static long getEncodeLength(byte[] pArray, int chunkSize, byte[] chunkSeparator) {
        int chunkSize2 = (chunkSize / 4) * 4;
        int chunkSize3 = pArray.length;
        long len = (chunkSize3 * 4) / 3;
        long mod = len % 4;
        if (mod != 0) {
            len += 4 - mod;
        }
        if (chunkSize2 > 0) {
            boolean lenChunksPerfectly = len % ((long) chunkSize2) == 0;
            long len2 = len + ((len / ((long) chunkSize2)) * ((long) chunkSeparator.length));
            if (!lenChunksPerfectly) {
                return len2 + ((long) chunkSeparator.length);
            }
            return len2;
        }
        return len;
    }

    public static BigInteger decodeInteger(byte[] pArray) {
        return new BigInteger(1, decodeBase64(pArray));
    }

    public static byte[] encodeInteger(BigInteger bigInt) {
        if (bigInt == null) {
            throw new NullPointerException("encodeInteger called with null parameter");
        }
        return encodeBase64(toIntegerBytes(bigInt), false);
    }

    static byte[] toIntegerBytes(BigInteger bigInt) {
        int bitlen = ((bigInt.bitLength() + 7) >> 3) << 3;
        byte[] bigBytes = bigInt.toByteArray();
        if (bigInt.bitLength() % 8 != 0 && (bigInt.bitLength() / 8) + 1 == bitlen / 8) {
            return bigBytes;
        }
        int startSrc = 0;
        int len = bigBytes.length;
        if (bigInt.bitLength() % 8 == 0) {
            startSrc = 1;
            len--;
        }
        int startDst = (bitlen / 8) - len;
        byte[] resizedBytes = new byte[bitlen / 8];
        System.arraycopy(bigBytes, startSrc, resizedBytes, startDst, len);
        return resizedBytes;
    }

    private void reset() {
        this.buffer = null;
        this.pos = 0;
        this.readPos = 0;
        this.currentLinePos = 0;
        this.modulus = 0;
        this.eof = false;
    }

    int getLineLength() {
        return this.lineLength;
    }

    byte[] getLineSeparator() {
        return (byte[]) this.lineSeparator.clone();
    }
}
