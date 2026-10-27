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
        for (int i3 = 0; i3 < bArr.length - 1; i3++) {
            str = str + byteToHex(bArr[i3]) + " ";
        }
        return str + byteToHex(bArr[bArr.length - 1]);
    }

    public static String byteToHex(byte b3) {
        String hexString = Integer.toHexString(b3 & 255);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString.toUpperCase();
    }

    public static int byteToInt(byte b3, byte b4, byte b5, byte b6) {
        return ((b3 & 255) << 24) | ((b4 & 255) << 16) | ((b5 & 255) << 8) | (b6 & 255);
    }

    public static byte getBit(byte b3, int i3) {
        int i4 = 1 << i3;
        return (b3 & i4) == i4 ? (byte) 1 : (byte) 0;
    }

    public static byte[] intToByteArray(int i3) throws NullPointerException {
        return intToByteArray(i3, ByteOrder.LITTLE_ENDIAN);
    }

    public static byte[] intToByteArray(int i3, ByteOrder byteOrder) {
        if (byteOrder == null) {
            return null;
        }
        byte[] bArr = new byte[4];
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int i4 = 3; i4 >= 0; i4--) {
                bArr[i4] = (byte) (i3 & 255);
                i3 >>= 8;
            }
        } else {
            for (int i5 = 0; i5 < 4; i5++) {
                bArr[i5] = (byte) (i3 & 255);
                i3 >>= 8;
            }
        }
        return bArr;
    }
}
