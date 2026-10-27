package p6;

/* JADX INFO: compiled from: -Util.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final boolean a(byte[] a7, int i7, byte[] b7, int i8, int i9) {
        kotlin.jvm.internal.l.e(a7, "a");
        kotlin.jvm.internal.l.e(b7, "b");
        for (int i10 = 0; i10 < i9; i10++) {
            if (a7[i10 + i7] != b7[i10 + i8]) {
                return false;
            }
        }
        return true;
    }

    public static final void b(long j7, long j8, long j9) {
        if ((j8 | j9) < 0 || j8 > j7 || j7 - j8 < j9) {
            throw new ArrayIndexOutOfBoundsException("size=" + j7 + " offset=" + j8 + " byteCount=" + j9);
        }
    }

    public static final int c(int i7) {
        return ((i7 & 255) << 24) | (((-16777216) & i7) >>> 24) | ((16711680 & i7) >>> 8) | ((65280 & i7) << 8);
    }

    public static final short d(short s6) {
        int i7 = s6 & 65535;
        return (short) (((i7 & 255) << 8) | ((65280 & i7) >>> 8));
    }

    public static final String e(byte b7) {
        return new String(new char[]{q6.b.f()[(b7 >> 4) & 15], q6.b.f()[b7 & 15]});
    }

    public static final String f(int i7) {
        if (i7 == 0) {
            return "0";
        }
        int i8 = 0;
        char[] cArr = {q6.b.f()[(i7 >> 28) & 15], q6.b.f()[(i7 >> 24) & 15], q6.b.f()[(i7 >> 20) & 15], q6.b.f()[(i7 >> 16) & 15], q6.b.f()[(i7 >> 12) & 15], q6.b.f()[(i7 >> 8) & 15], q6.b.f()[(i7 >> 4) & 15], q6.b.f()[i7 & 15]};
        while (i8 < 8 && cArr[i8] == '0') {
            i8++;
        }
        return new String(cArr, i8, 8 - i8);
    }
}
