package com.android.bluetooth.util;

import com.google.common.primitives.UnsignedBytes;

/* JADX INFO: loaded from: classes.dex */
public class NumberUtils {
    public static int unsignedByteToInt(byte b) {
        return b & UnsignedBytes.MAX_VALUE;
    }

    public static int littleEndianByteArrayToInt(byte[] bytes) {
        int length = bytes.length;
        if (length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = length - 1; i >= 0; i--) {
            int value = unsignedByteToInt(bytes[i]);
            result += value << (i * 8);
        }
        return result;
    }
}
