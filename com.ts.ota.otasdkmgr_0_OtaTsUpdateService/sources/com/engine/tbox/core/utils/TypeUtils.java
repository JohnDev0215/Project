package com.engine.tbox.core.utils;

import com.engine.tbox.data.ReservationCharging;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class TypeUtils {
    private static final int BYTE1 = 255;
    private static final int BYTE2 = 65280;
    private static final int POS16 = 16;
    private static final int POS24 = 24;
    private static final int POS3 = 3;
    private static final int POS8 = 8;

    public static short byteArrayToShort(byte[] array) {
        return (short) ((array[1] & ReservationCharging.DEFAULT_ELECTROVALENCE) | ((array[0] << 8) & BYTE2));
    }

    public static int byteArrayPosToShort(byte[] array, int pos) {
        return (array[pos + 1] & ReservationCharging.DEFAULT_ELECTROVALENCE) | ((array[pos] << 8) & BYTE2);
    }

    public static int byteArrayToShortInt(byte[] array) {
        return (array[1] & ReservationCharging.DEFAULT_ELECTROVALENCE) | ((array[0] << 8) & BYTE2);
    }

    public static int byteArrayToInt(byte[] array) {
        return (array[0] & ReservationCharging.DEFAULT_ELECTROVALENCE) | ((array[1] & ReservationCharging.DEFAULT_ELECTROVALENCE) << 8) | ((array[2] & ReservationCharging.DEFAULT_ELECTROVALENCE) << 16) | ((array[3] & ReservationCharging.DEFAULT_ELECTROVALENCE) << 24);
    }

    public static int byteToInt(byte data) {
        return data & ReservationCharging.DEFAULT_ELECTROVALENCE;
    }

    public static int byteArrayPosToShortInt(byte[] array, int pos) {
        return (array[pos + 1] & ReservationCharging.DEFAULT_ELECTROVALENCE) | ((array[pos] << 8) & BYTE2);
    }

    public static byte[] longToBytes(long primitive) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(primitive);
        return buffer.array();
    }

    public static boolean putShortInArray(byte[] array, int pos, short value) {
        if (array == null || pos + 2 > array.length) {
            return false;
        }
        array[pos + 1] = (byte) value;
        array[pos] = (byte) (value >>> 8);
        return true;
    }
}
