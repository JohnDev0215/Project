package com.bydux.api.common.tools.data;

import android.media.AudioSystem;

/* JADX INFO: loaded from: classes5.dex */
public class BydDataHelper {
    private static final String TAG = "BydDataHelper";

    public enum ByteOrder {
        BIG_ENDIAN,
        LITTLE_ENDIAN
    }

    public static int byteToInt(byte hh, byte hl, byte lh, byte ll) {
        return ((hh & 255) << 24) | ((hl & 255) << 16) | ((lh & 255) << 8) | (ll & 255);
    }

    public static byte getBit(byte value, int index) {
        int i = 1 << index;
        return (value & i) == i ? (byte) 1 : (byte) 0;
    }

    private BydDataHelper() {
    }

    public static byte[] intToByteArray(int value) throws NullPointerException {
        return intToByteArray(value, ByteOrder.LITTLE_ENDIAN);
    }

    public static byte[] intToByteArray(int value, ByteOrder order) {
        if (order == null) {
            return null;
        }
        byte[] bArr = new byte[4];
        if (order == ByteOrder.BIG_ENDIAN) {
            for (int i = 3; i >= 0; i--) {
                bArr[i] = (byte) (value & 255);
                value >>= 8;
            }
        } else {
            for (int i2 = 0; i2 < 4; i2++) {
                bArr[i2] = (byte) (value & 255);
                value >>= 8;
            }
        }
        return bArr;
    }

    public static String byteToHex(byte value) {
        String hexString = Integer.toHexString(value & 255);
        if (hexString.length() == 1) {
            hexString = AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS + hexString;
        }
        return hexString.toUpperCase();
    }

    public static String byteArrayToHex(final byte[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        String str = "";
        for (int i = 0; i < array.length - 1; i++) {
            str = str + byteToHex(array[i]) + " ";
        }
        return str + byteToHex(array[array.length - 1]);
    }
}
