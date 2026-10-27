package u;

/* JADX INFO: renamed from: u.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2456D {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long[] f22584a = {-9187201950435737345L, -1};

    static {
        new C2484x(0);
    }

    public static final void a(long[] jArr, int i7) {
        A7.m.f(jArr, "metadata");
        int i10 = (i7 + 7) >> 3;
        for (int i11 = 0; i11 < i10; i11++) {
            long j3 = jArr[i11] & (-9187201950435737472L);
            jArr[i11] = (-72340172838076674L) & ((~j3) + (j3 >>> 7));
        }
        int length = jArr.length;
        int i12 = length - 1;
        int i13 = length - 2;
        jArr[i13] = (jArr[i13] & 72057594037927935L) | (-72057594037927936L);
        jArr[i12] = jArr[0];
    }

    public static final int b(long[] jArr, int i7, int i10) {
        A7.m.f(jArr, "metadata");
        while (i7 < i10) {
            if (((jArr[i7 >> 3] >> ((i7 & 7) << 3)) & 255) == 128) {
                return i7;
            }
            i7++;
        }
        return -1;
    }

    public static final int c(int i7) {
        if (i7 == 7) {
            return 6;
        }
        return i7 - (i7 / 8);
    }

    public static final int d(int i7) {
        if (i7 == 0) {
            return 6;
        }
        return (i7 * 2) + 1;
    }

    public static final int e(int i7) {
        if (i7 > 0) {
            return (-1) >>> Integer.numberOfLeadingZeros(i7);
        }
        return 0;
    }

    public static final int f(int i7) {
        if (i7 == 7) {
            return 8;
        }
        return ((i7 - 1) / 7) + i7;
    }
}
