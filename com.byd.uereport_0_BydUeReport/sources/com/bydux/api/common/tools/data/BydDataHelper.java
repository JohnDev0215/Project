package com.bydux.api.common.tools.data;

/* JADX INFO: loaded from: classes2.dex */
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
        for (int i8 = 0; i8 < bArr.length - 1; i8++) {
            str = str + byteToHex(bArr[i8]) + " ";
        }
        return str + byteToHex(bArr[bArr.length - 1]);
    }

    public static String byteToHex(byte b8) {
        String hexString = Integer.toHexString(b8 & 255);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString.toUpperCase();
    }

    public static int byteToInt(byte b8, byte b9, byte b10, byte b11) {
        return ((b8 & 255) << 24) | ((b9 & 255) << 16) | ((b10 & 255) << 8) | (b11 & 255);
    }

    public static byte getBit(byte b8, int i8) {
        int i9 = 1 << i8;
        return (b8 & i9) == i9 ? (byte) 1 : (byte) 0;
    }

    public static byte[] intToByteArray(int i8) throws NullPointerException {
        return intToByteArray(i8, ByteOrder.LITTLE_ENDIAN);
    }

    public static byte[] intToByteArray(int i8, ByteOrder byteOrder) {
        if (byteOrder == null) {
            return null;
        }
        byte[] bArr = new byte[4];
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            for (int i9 = 3; i9 >= 0; i9--) {
                bArr[i9] = (byte) (i8 & 255);
                i8 >>= 8;
            }
        } else {
            for (int i10 = 0; i10 < 4; i10++) {
                bArr[i10] = (byte) (i8 & 255);
                i8 >>= 8;
            }
        }
        return bArr;
    }
}
