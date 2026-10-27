package net.lingala.zip4j.crypto.PBKDF2;

import com.android.volley.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class BinTools {
    public static final String hex = "0123456789ABCDEF";

    public static String bin2hex(byte[] bArr) {
        if (bArr == null) {
            return BuildConfig.FLAVOR;
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b2 : bArr) {
            int i = (b2 + 256) % 256;
            stringBuffer.append(hex.charAt((i / 16) & 15));
            stringBuffer.append(hex.charAt((i % 16) & 15));
        }
        return stringBuffer.toString();
    }

    public static byte[] hex2bin(String str) {
        if (str == null) {
            str = BuildConfig.FLAVOR;
        } else if (str.length() % 2 != 0) {
            str = "0" + str;
        }
        byte[] bArr = new byte[str.length() / 2];
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            int i3 = i + 1;
            bArr[i2] = (byte) ((hex2bin(str.charAt(i)) * 16) + hex2bin(str.charAt(i3)));
            i2++;
            i = i3 + 1;
        }
        return bArr;
    }

    public static int hex2bin(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'A';
        if (c2 < 'A' || c2 > 'F') {
            c3 = 'a';
            if (c2 < 'a' || c2 > 'f') {
                throw new IllegalArgumentException("Input string may only contain hex digits, but found '" + c2 + "'");
            }
        }
        return (c2 - c3) + 10;
    }
}
