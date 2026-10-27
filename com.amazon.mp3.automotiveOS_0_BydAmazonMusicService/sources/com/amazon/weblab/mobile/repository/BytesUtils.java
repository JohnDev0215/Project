package com.amazon.weblab.mobile.repository;

import java.util.Arrays;
import org.json.JSONException;

/* JADX INFO: loaded from: classes5.dex */
class BytesUtils {
    BytesUtils() {
    }

    String getStringFromRange(byte[] bArr, int[] iArr) throws JSONException {
        if (isInvalidRange(bArr, iArr)) {
            throw new JSONException("Index out of bounds");
        }
        return new String(Arrays.copyOfRange(bArr, iArr[0], iArr[1] + 1));
    }

    int[] getRangeOfStringValue(byte[] bArr, byte[] bArr2, int i) throws JSONException {
        return getRangeOfStringValue(bArr, bArr2, i, bArr.length - 1);
    }

    private int[] getRangeOfStringValue(byte[] bArr, byte[] bArr2, int i, int i2) throws JSONException {
        if (isOutOfBounds(bArr, i, i2)) {
            throw new JSONException("Index out of bounds");
        }
        int iFindEndPositionOfString = findEndPositionOfString(bArr, bArr2, i, i2);
        if (iFindEndPositionOfString == -1) {
            throw new JSONException("Property not found");
        }
        int i3 = iFindEndPositionOfString + 3;
        if (isOutOfBounds(bArr, i3)) {
            throw new JSONException("Malformed JSON");
        }
        int iFindNextDelimiter = findNextDelimiter(bArr, LazyJSONKeys.QUOTE, i3);
        if (iFindNextDelimiter != -1) {
            return new int[]{i3, iFindNextDelimiter - 1};
        }
        throw new JSONException("Value not found");
    }

    String getStringValue(byte[] bArr, byte[] bArr2, int i) throws JSONException {
        return getStringValue(bArr, bArr2, i, bArr.length - 1);
    }

    String getStringValue(byte[] bArr, byte[] bArr2, int i, int i2) throws JSONException {
        int[] rangeOfStringValue = getRangeOfStringValue(bArr, bArr2, i, i2);
        if (rangeOfStringValue == null) {
            throw new JSONException("Value not found");
        }
        return getStringFromRange(bArr, rangeOfStringValue);
    }

    boolean getBooleanValue(byte[] bArr, byte[] bArr2, int i) throws JSONException {
        if (bArr2 == null || isOutOfBounds(bArr, i)) {
            throw new JSONException("Property not found");
        }
        int iFindEndPositionOfString = findEndPositionOfString(bArr, bArr2, i);
        if (iFindEndPositionOfString == -1) {
            throw new JSONException("Property not found");
        }
        int i2 = iFindEndPositionOfString + 2;
        if (isOutOfBounds(bArr, i2)) {
            throw new JSONException("Value not found");
        }
        byte b = bArr[i2];
        if (b == LazyJSONKeys.TRUE_PREFIX) {
            return true;
        }
        if (b == LazyJSONKeys.FALSE_PREFIX) {
            return false;
        }
        throw new JSONException("Value not found");
    }

    long getLongValue(byte[] bArr, byte[] bArr2, int i) throws JSONException {
        if (bArr2 == null || isOutOfBounds(bArr, i)) {
            throw new JSONException("Property not found");
        }
        int iFindEndPositionOfString = findEndPositionOfString(bArr, bArr2, i);
        if (iFindEndPositionOfString == -1) {
            throw new JSONException("Property not found");
        }
        int i2 = iFindEndPositionOfString + 2;
        int iFindEndOfNumeric = findEndOfNumeric(bArr, i2);
        if (isOutOfBounds(bArr, i2, iFindEndOfNumeric)) {
            throw new JSONException("Value not found");
        }
        return getLongFromRange(bArr, i2, iFindEndOfNumeric);
    }

    int findEndPositionOfString(byte[] bArr, byte[] bArr2, int i) {
        return findEndPositionOfString(bArr, bArr2, i, bArr.length - 1);
    }

    private int findEndPositionOfString(byte[] bArr, byte[] bArr2, int i, int i2) {
        int iFindStartPositionOfString;
        if (bArr2 == null || isOutOfBounds(bArr, i, i2) || (iFindStartPositionOfString = findStartPositionOfString(bArr, bArr2, i, i2)) == -1) {
            return -1;
        }
        return (iFindStartPositionOfString + bArr2.length) - 1;
    }

    private int findStartPositionOfString(byte[] bArr, byte[] bArr2, int i, int i2) {
        if (bArr2 != null && !isOutOfBounds(bArr, i, i2)) {
            int length = bArr2.length - 1;
            loop0: while (true) {
                int i3 = 0;
                while (i < i2) {
                    if (bArr2[i3] == bArr[i]) {
                        if (i3 == length) {
                            return i - length;
                        }
                        i++;
                        i3++;
                    }
                }
                i++;
            }
        }
        return -1;
    }

    int findNextDelimiter(byte[] bArr, byte b, int i) {
        if (isOutOfBounds(bArr, i)) {
            return -1;
        }
        while (i < bArr.length) {
            if (bArr[i] == b) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private int findEndOfNumeric(byte[] bArr, int i) {
        if (isOutOfBounds(bArr, i)) {
            return -1;
        }
        while (i <= bArr.length - 1) {
            byte b = bArr[i];
            if (b < LazyJSONKeys.ZERO || b > LazyJSONKeys.NINE) {
                return i - 1;
            }
            i++;
        }
        return i - 1;
    }

    private long getLongFromRange(byte[] bArr, int i, int i2) {
        long jPow = 0;
        if (isOutOfBounds(bArr, i, i2)) {
            return 0L;
        }
        while (i <= i2) {
            jPow = (long) (jPow + (Math.pow(10.0d, i2 - i) * ((double) Character.digit(bArr[i], 10))));
            i++;
        }
        return jPow;
    }

    boolean isOutOfBounds(byte[] bArr, int i) {
        return bArr == null || i < 0 || i >= bArr.length;
    }

    boolean isOutOfBounds(byte[] bArr, int i, int i2) {
        return bArr == null || i < 0 || i2 >= bArr.length || i > i2;
    }

    boolean isInvalidRange(byte[] bArr, int[] iArr) {
        return bArr == null || iArr == null || iArr.length != 2 || isOutOfBounds(bArr, iArr[0], iArr[1]);
    }
}
