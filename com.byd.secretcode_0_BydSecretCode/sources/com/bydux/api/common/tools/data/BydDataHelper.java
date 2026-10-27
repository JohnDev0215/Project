package com.bydux.api.common.tools.data;

/* JADX INFO: loaded from: classes.dex */
public class BydDataHelper {
    private static final String TAG = "BydDataHelper";

    public enum ByteOrder {
        BIG_ENDIAN,
        LITTLE_ENDIAN
    }

    private BydDataHelper() {
    }

    public static String byteArrayToHex(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        String str = "";
        for (int i = 0; i < bArr.length - 1; i++) {
            str = str + byteToHex(bArr[i]) + " ";
        }
        return str + byteToHex(bArr[bArr.length - 1]);
    }

    public static String byteToHex(byte b) {
        String hexString = Integer.toHexString(b & 255);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString.toUpperCase();
    }

    public static int byteToInt(byte b, byte b2, byte b3, byte b4) {
        return ((b & 255) << 24) | ((b2 & 255) << 16) | ((b3 & 255) << 8) | (b4 & 255);
    }

    public static byte getBit(byte b, int i) {
        int i2 = 1 << i;
        return (b & i2) == i2 ? (byte) 1 : (byte) 0;
    }

    public static byte[] intToByteArray(int i) throws NullPointerException {
        return intToByteArray(i, ByteOrder.LITTLE_ENDIAN);
    }

    public static byte[] intToByteArray(int i, ByteOrder byteOrder) {
        if (byteOrder == null) {
            return null;
        }
        byte[] bArr = new byte[4];
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int i2 = 3; i2 >= 0; i2--) {
                bArr[i2] = (byte) (i & 255);
                i >>= 8;
            }
        } else {
            for (int i3 = 0; i3 < 4; i3++) {
                bArr[i3] = (byte) (i & 255);
                i >>= 8;
            }
        }
        return bArr;
    }
}
