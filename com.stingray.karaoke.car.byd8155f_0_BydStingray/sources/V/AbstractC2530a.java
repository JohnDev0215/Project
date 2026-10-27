package v;

import A7.m;

/* JADX INFO: renamed from: v.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2530a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f22938a = new int[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long[] f22939b = new long[0];
    public static final Object[] c = new Object[0];

    public static final int a(int i7, int i10, int[] iArr) {
        m.f(iArr, "array");
        int i11 = i7 - 1;
        int i12 = 0;
        while (i12 <= i11) {
            int i13 = (i12 + i11) >>> 1;
            int i14 = iArr[i13];
            if (i14 < i10) {
                i12 = i13 + 1;
            } else {
                if (i14 <= i10) {
                    return i13;
                }
                i11 = i13 - 1;
            }
        }
        return ~i12;
    }

    public static final int b(long[] jArr, int i7, long j3) {
        m.f(jArr, "array");
        int i10 = i7 - 1;
        int i11 = 0;
        while (i11 <= i10) {
            int i12 = (i11 + i10) >>> 1;
            long j10 = jArr[i12];
            if (j10 < j3) {
                i11 = i12 + 1;
            } else {
                if (j10 <= j3) {
                    return i12;
                }
                i10 = i12 - 1;
            }
        }
        return ~i11;
    }

    public static final void c(String str) {
        m.f(str, "message");
        throw new IllegalArgumentException(str);
    }
}
