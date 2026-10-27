package com.ts.car.iap.util;

import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class IapParcel {
    private static final int BIT_SHIFT_2 = 2;
    private static final int BIT_SHIFT_3 = 3;
    private static final int BYTE_0 = 0;
    private static final int BYTE_1 = 1;
    private static final int BYTE_2 = 2;
    private static final int BYTE_3 = 3;
    private static final int BYTE_4 = 4;
    private static final int BYTE_5 = 5;
    private static final int BYTE_6 = 6;
    private static final int BYTE_7 = 7;
    private static final int BYTE_MASK = 255;
    private static final int BYTE_SHIFT_0 = 0;
    private static final int BYTE_SHIFT_1 = 8;
    private static final int BYTE_SHIFT_2 = 16;
    private static final int BYTE_SHIFT_3 = 24;
    private static final int BYTE_SHIFT_4 = 32;
    private static final int BYTE_SHIFT_5 = 40;
    private static final int BYTE_SHIFT_6 = 48;
    private static final int BYTE_SHIFT_7 = 56;
    private byte[] mData;
    private int mPosition;

    public IapParcel(byte[] data) {
        this.mData = data == null ? null : (byte[]) data.clone();
        this.mPosition = 0;
    }

    public final int dataAvail() {
        byte[] bArr = this.mData;
        if (bArr == null) {
            return 0;
        }
        return bArr.length - this.mPosition;
    }

    public final int readInt() {
        if (this.mData == null) {
            return 0;
        }
        if (dataAvail() < 4) {
            this.mPosition = this.mData.length;
            return 0;
        }
        byte[] bArr = this.mData;
        int i = this.mPosition;
        int value = (bArr[i + 3] & 255) << 24;
        int value2 = (bArr[i + 0] & 255) | value | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 1] & 255) << 8);
        this.mPosition = i + 4;
        return value2;
    }

    public final long readLong() {
        if (this.mData == null) {
            return 0L;
        }
        if (dataAvail() < 8) {
            this.mPosition = this.mData.length;
            return 0L;
        }
        byte[] bArr = this.mData;
        int i = this.mPosition;
        long value = (((long) bArr[i + 7]) & 255) << 56;
        long value2 = value | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 1]) & 255) << 8) | (255 & ((long) bArr[i + 0]));
        this.mPosition = i + 8;
        return value2;
    }

    public final String readString() {
        int len = readInt();
        if (len <= 0) {
            return null;
        }
        String str = new String(this.mData, this.mPosition, len, StandardCharsets.UTF_8);
        this.mPosition += len + 1;
        return str;
    }

    public final int[] createIntArray() {
        int count = readInt();
        if (count > 0 && count <= (dataAvail() >> 2)) {
            int[] val = new int[count];
            for (int i = 0; i < count; i++) {
                val[i] = readInt();
            }
            return val;
        }
        return null;
    }

    public final long[] createLongArray() {
        int count = readInt();
        if (count > 0 && count <= (dataAvail() >> 3)) {
            long[] val = new long[count];
            for (int i = 0; i < count; i++) {
                val[i] = readLong();
            }
            return val;
        }
        return null;
    }

    public final String[] createStringArray() {
        int count = readInt();
        if (count > 0) {
            String[] val = new String[count];
            for (int i = 0; i < count; i++) {
                val[i] = readString();
            }
            return val;
        }
        return null;
    }

    public final byte[] createByteArray() {
        int count = readInt();
        if (count > 0 && count <= dataAvail()) {
            byte[] val = new byte[count];
            System.arraycopy(this.mData, this.mPosition, val, 0, count);
            this.mPosition += count;
            return val;
        }
        return null;
    }
}
