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
        for (int i2 = 0; i2 < bArr.length - 1; i2++) {
            str = str + byteToHex(bArr[i2]) + " ";
        }
        return str + byteToHex(bArr[bArr.length - 1]);
    }

    public static String byteToHex(byte b2) {
        String hexString = Integer.toHexString(b2 & 255);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString.toUpperCase();
    }

    public static int byteToInt(byte b2, byte b3, byte b4, byte b5) {
        return ((b2 & 255) << 24) | ((b3 & 255) << 16) | ((b4 & 255) << 8) | (b5 & 255);
    }

    public static byte getBit(byte b2, int i2) {
        int i3 = 1 << i2;
        return (b2 & i3) == i3 ? (byte) 1 : (byte) 0;
    }

    public static byte[] intToByteArray(int i2) throws NullPointerException {
        return intToByteArray(i2, ByteOrder.LITTLE_ENDIAN);
    }

    public static byte[] intToByteArray(int i2, ByteOrder byteOrder) {
        if (byteOrder == null) {
            return null;
        }
        byte[] bArr = new byte[4];
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int i3 = 3; i3 >= 0; i3--) {
                bArr[i3] = (byte) (i2 & 255);
                i2 >>= 8;
            }
        } else {
            for (int i4 = 0; i4 < 4; i4++) {
                bArr[i4] = (byte) (i2 & 255);
                i2 >>= 8;
            }
        }
        return bArr;
    }
}
