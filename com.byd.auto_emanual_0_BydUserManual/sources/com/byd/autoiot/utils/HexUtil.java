package com.byd.autoiot.utils;

import com.byd.auto_emanual.fragment.ZipInfo;

/* JADX INFO: loaded from: classes.dex */
public class HexUtil {
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static byte[] hex2byte(String str) {
        if (str == null || str.length() == 0) {
            return new byte[0];
        }
        if (str.length() % 2 == 1) {
            str = ZipInfo.NO_UPLOAD_TIME + str;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length(); i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) | Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String byte2Hex(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = DIGITS_UPPER;
            cArr[i2] = cArr2[(b & 240) >>> 4];
            cArr[i2 + 1] = cArr2[b & 15];
        }
        return new String(cArr);
    }
}
