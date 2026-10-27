package com.ts.car.iap.util;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ConvertUtils {
    private static final int BYTE_MASK = 255;
    private static final int BYTE_SHIFT = 8;
    private static final int BYTE_SHIFT_1 = 8;
    private static final int BYTE_SHIFT_2 = 16;
    private static final int BYTE_SHIFT_3 = 24;

    public static byte[] intToBytes(int val) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            int offset = i * 8;
            bytes[i] = (byte) ((val >> offset) & 255);
        }
        return bytes;
    }

    public static byte[] longToBytes(long val) {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            int offset = i * 8;
            bytes[i] = (byte) ((val >> offset) & 255);
        }
        return bytes;
    }

    public static int bytesToInt(byte[] bytes) {
        int index = 3 - 1;
        int value = (bytes[3] & 255) << 24;
        int index2 = index - 1;
        return value | ((bytes[index] & 255) << 16) | ((bytes[index2] & 255) << 8) | (bytes[index2 - 1] & 255);
    }

    public static byte[] concatAll(byte[] first, byte[]... rest) {
        byte[] result;
        int offset = 0;
        int totalLength = 0;
        if (rest == null) {
            return first;
        }
        if (first != null) {
            totalLength = first.length;
        }
        for (byte[] bArr : rest) {
            totalLength += bArr.length;
        }
        if (first != null) {
            result = Arrays.copyOf(first, totalLength);
            offset = first.length;
        } else {
            result = new byte[totalLength];
        }
        for (byte[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
}
