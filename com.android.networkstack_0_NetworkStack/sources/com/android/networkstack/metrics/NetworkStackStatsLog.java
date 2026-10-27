package com.android.networkstack.metrics;

import android.os.Build;
import android.os.SystemClock;
import android.util.StatsEvent;
import android.util.StatsLog;

/* JADX INFO: loaded from: classes.dex */
public class NetworkStackStatsLog {
    public static void write(int i, int i2, byte[] bArr, int i3, int i4, int i5, int i6) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builderNewBuilder = StatsEvent.newBuilder();
            builderNewBuilder.setAtomId(i);
            builderNewBuilder.writeInt(i2);
            if (bArr == null) {
                bArr = new byte[0];
            }
            builderNewBuilder.writeByteArray(bArr);
            builderNewBuilder.writeInt(i3);
            builderNewBuilder.writeInt(i4);
            builderNewBuilder.writeInt(i5);
            builderNewBuilder.writeInt(i6);
            builderNewBuilder.usePooledBuffer();
            StatsLog.write(builderNewBuilder.build());
            return;
        }
        QLogger.write(i, i2, bArr, i3, i4, i5, i6);
    }

    public static void write(int i, int i2, int i3, int i4, byte[] bArr, byte[] bArr2, byte[] bArr3, int i5, int i6) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builderNewBuilder = StatsEvent.newBuilder();
            builderNewBuilder.setAtomId(i);
            builderNewBuilder.writeInt(i2);
            builderNewBuilder.writeInt(i3);
            builderNewBuilder.writeInt(i4);
            if (bArr == null) {
                bArr = new byte[0];
            }
            builderNewBuilder.writeByteArray(bArr);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            builderNewBuilder.writeByteArray(bArr2);
            if (bArr3 == null) {
                bArr3 = new byte[0];
            }
            builderNewBuilder.writeByteArray(bArr3);
            builderNewBuilder.writeInt(i5);
            builderNewBuilder.writeInt(i6);
            builderNewBuilder.usePooledBuffer();
            StatsLog.write(builderNewBuilder.build());
            return;
        }
        QLogger.write(i, i2, i3, i4, bArr, bArr2, bArr3, i5, i6);
    }

    public static void write(int i, int i2, int i3, int i4, long j, int i5, byte[] bArr, int i6) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builderNewBuilder = StatsEvent.newBuilder();
            builderNewBuilder.setAtomId(i);
            builderNewBuilder.writeInt(i2);
            builderNewBuilder.writeInt(i3);
            builderNewBuilder.writeInt(i4);
            builderNewBuilder.writeLong(j);
            builderNewBuilder.writeInt(i5);
            if (bArr == null) {
                bArr = new byte[0];
            }
            builderNewBuilder.writeByteArray(bArr);
            builderNewBuilder.writeInt(i6);
            builderNewBuilder.usePooledBuffer();
            StatsLog.write(builderNewBuilder.build());
            return;
        }
        QLogger.write(i, i2, i3, i4, j, i5, bArr, i6);
    }

    private static class QLogger {
        public static void write(int i, int i2, byte[] bArr, int i3, int i4, int i5, int i6) {
            if (bArr == null) {
                bArr = new byte[0];
            }
            int length = bArr.length + 5 + 21 + 5 + 5 + 5 + 5;
            if (length > 4064) {
                return;
            }
            byte[] bArr2 = new byte[length];
            bArr2[0] = 3;
            bArr2[1] = 8;
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            bArr2[2] = 1;
            copyLong(bArr2, 3, jElapsedRealtimeNanos);
            bArr2[11] = 0;
            copyInt(bArr2, 12, i);
            bArr2[16] = 0;
            copyInt(bArr2, 17, i2);
            bArr2[21] = 2;
            copyInt(bArr2, 22, bArr.length);
            System.arraycopy(bArr, 0, bArr2, 26, bArr.length);
            int length2 = 21 + bArr.length + 5;
            bArr2[length2] = 0;
            copyInt(bArr2, length2 + 1, i3);
            int i7 = length2 + 5;
            bArr2[i7] = 0;
            copyInt(bArr2, i7 + 1, i4);
            int i8 = i7 + 5;
            bArr2[i8] = 0;
            copyInt(bArr2, i8 + 1, i5);
            int i9 = i8 + 5;
            bArr2[i9] = 0;
            copyInt(bArr2, i9 + 1, i6);
            StatsLog.writeRaw(bArr2, i9 + 5);
        }

        public static void write(int i, int i2, int i3, int i4, byte[] bArr, byte[] bArr2, byte[] bArr3, int i5, int i6) {
            byte[] bArr4 = bArr == null ? new byte[0] : bArr;
            int length = bArr4.length + 5 + 31;
            byte[] bArr5 = bArr2 == null ? new byte[0] : bArr2;
            int length2 = length + bArr5.length + 5;
            byte[] bArr6 = bArr3 == null ? new byte[0] : bArr3;
            int length3 = length2 + bArr6.length + 5 + 5 + 5;
            if (length3 > 4064) {
                return;
            }
            byte[] bArr7 = new byte[length3];
            bArr7[0] = 3;
            bArr7[1] = 10;
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            bArr7[2] = 1;
            copyLong(bArr7, 3, jElapsedRealtimeNanos);
            bArr7[11] = 0;
            copyInt(bArr7, 12, i);
            bArr7[16] = 0;
            copyInt(bArr7, 17, i2);
            bArr7[21] = 0;
            copyInt(bArr7, 22, i3);
            bArr7[26] = 0;
            copyInt(bArr7, 27, i4);
            bArr7[31] = 2;
            copyInt(bArr7, 32, bArr4.length);
            System.arraycopy(bArr4, 0, bArr7, 36, bArr4.length);
            int length4 = 31 + bArr4.length + 5;
            bArr7[length4] = 2;
            copyInt(bArr7, length4 + 1, bArr5.length);
            System.arraycopy(bArr5, 0, bArr7, length4 + 5, bArr5.length);
            int length5 = length4 + bArr5.length + 5;
            bArr7[length5] = 2;
            copyInt(bArr7, length5 + 1, bArr6.length);
            System.arraycopy(bArr6, 0, bArr7, length5 + 5, bArr6.length);
            int length6 = length5 + bArr6.length + 5;
            bArr7[length6] = 0;
            copyInt(bArr7, length6 + 1, i5);
            int i7 = length6 + 5;
            bArr7[i7] = 0;
            copyInt(bArr7, i7 + 1, i6);
            StatsLog.writeRaw(bArr7, i7 + 5);
        }

        public static void write(int i, int i2, int i3, int i4, long j, int i5, byte[] bArr, int i6) {
            byte[] bArr2 = bArr == null ? new byte[0] : bArr;
            int length = bArr2.length + 5 + 45 + 5;
            if (length > 4064) {
                return;
            }
            byte[] bArr3 = new byte[length];
            bArr3[0] = 3;
            bArr3[1] = 9;
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            bArr3[2] = 1;
            copyLong(bArr3, 3, jElapsedRealtimeNanos);
            bArr3[11] = 0;
            copyInt(bArr3, 12, i);
            bArr3[16] = 0;
            copyInt(bArr3, 17, i2);
            bArr3[21] = 0;
            copyInt(bArr3, 22, i3);
            bArr3[26] = 0;
            copyInt(bArr3, 27, i4);
            bArr3[31] = 1;
            copyLong(bArr3, 32, j);
            bArr3[40] = 0;
            copyInt(bArr3, 41, i5);
            bArr3[45] = 2;
            copyInt(bArr3, 46, bArr2.length);
            System.arraycopy(bArr2, 0, bArr3, 50, bArr2.length);
            int length2 = 45 + bArr2.length + 5;
            bArr3[length2] = 0;
            copyInt(bArr3, length2 + 1, i6);
            StatsLog.writeRaw(bArr3, length2 + 5);
        }

        private static void copyInt(byte[] bArr, int i, int i2) {
            bArr[i] = (byte) i2;
            bArr[i + 1] = (byte) (i2 >> 8);
            bArr[i + 2] = (byte) (i2 >> 16);
            bArr[i + 3] = (byte) (i2 >> 24);
        }

        private static void copyLong(byte[] bArr, int i, long j) {
            bArr[i] = (byte) j;
            bArr[i + 1] = (byte) (j >> 8);
            bArr[i + 2] = (byte) (j >> 16);
            bArr[i + 3] = (byte) (j >> 24);
            bArr[i + 4] = (byte) (j >> 32);
            bArr[i + 5] = (byte) (j >> 40);
            bArr[i + 6] = (byte) (j >> 48);
            bArr[i + 7] = (byte) (j >> 56);
        }
    }
}
