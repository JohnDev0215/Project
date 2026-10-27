package a1;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f11027a;

    public /* synthetic */ a(long j3) {
        this.f11027a = j3;
    }

    public static long a(long j3, int i7, int i10, int i11, int i12, int i13) {
        if ((i13 & 1) != 0) {
            i7 = j(j3);
        }
        if ((i13 & 2) != 0) {
            i10 = h(j3);
        }
        if ((i13 & 4) != 0) {
            i11 = i(j3);
        }
        if ((i13 & 8) != 0) {
            i12 = g(j3);
        }
        if (!(i11 >= 0 && i7 >= 0)) {
            w0.c.e0("minHeight(" + i11 + ") and minWidth(" + i7 + ") must be >= 0");
            throw null;
        }
        if (!(i10 >= i7)) {
            w0.c.e0("maxWidth(" + i10 + ") must be >= minWidth(" + i7 + ')');
            throw null;
        }
        if (i12 >= i11) {
            return io.sentry.config.a.U(i7, i10, i11, i12);
        }
        w0.c.e0("maxHeight(" + i12 + ") must be >= minHeight(" + i11 + ')');
        throw null;
    }

    public static final boolean b(long j3, long j10) {
        return j3 == j10;
    }

    public static final boolean c(long j3) {
        int i7 = (int) (3 & j3);
        int i10 = (((i7 & 2) >> 1) * 3) + ((i7 & 1) << 1);
        return (((int) (j3 >> (i10 + 46))) & ((1 << (18 - i10)) - 1)) != 0;
    }

    public static final boolean d(long j3) {
        int i7 = (int) (3 & j3);
        return (((int) (j3 >> 33)) & ((1 << (((((i7 & 2) >> 1) * 3) + ((i7 & 1) << 1)) + 13)) - 1)) != 0;
    }

    public static final boolean e(long j3) {
        int i7 = (int) (3 & j3);
        int i10 = (((i7 & 2) >> 1) * 3) + ((i7 & 1) << 1);
        int i11 = (1 << (18 - i10)) - 1;
        int i12 = ((int) (j3 >> (i10 + 15))) & i11;
        int i13 = ((int) (j3 >> (i10 + 46))) & i11;
        return i12 == (i13 == 0 ? Integer.MAX_VALUE : i13 - 1);
    }

    public static final boolean f(long j3) {
        int i7 = (int) (3 & j3);
        int i10 = (1 << (((((i7 & 2) >> 1) * 3) + ((i7 & 1) << 1)) + 13)) - 1;
        int i11 = ((int) (j3 >> 2)) & i10;
        int i12 = ((int) (j3 >> 33)) & i10;
        return i11 == (i12 == 0 ? Integer.MAX_VALUE : i12 - 1);
    }

    public static final int g(long j3) {
        int i7 = (int) (3 & j3);
        int i10 = (((i7 & 2) >> 1) * 3) + ((i7 & 1) << 1);
        int i11 = ((int) (j3 >> (i10 + 46))) & ((1 << (18 - i10)) - 1);
        if (i11 == 0) {
            return Integer.MAX_VALUE;
        }
        return i11 - 1;
    }

    public static final int h(long j3) {
        int i7 = (int) (3 & j3);
        int i10 = (int) (j3 >> 33);
        int i11 = i10 & ((1 << (((((i7 & 2) >> 1) * 3) + ((i7 & 1) << 1)) + 13)) - 1);
        if (i11 == 0) {
            return Integer.MAX_VALUE;
        }
        return i11 - 1;
    }

    public static final int i(long j3) {
        int i7 = (int) (3 & j3);
        int i10 = (((i7 & 2) >> 1) * 3) + ((i7 & 1) << 1);
        return ((int) (j3 >> (i10 + 15))) & ((1 << (18 - i10)) - 1);
    }

    public static final int j(long j3) {
        int i7 = (int) (3 & j3);
        return ((int) (j3 >> 2)) & ((1 << (((((i7 & 2) >> 1) * 3) + ((i7 & 1) << 1)) + 13)) - 1);
    }

    public static final boolean k(long j3) {
        int i7 = (int) (3 & j3);
        int i10 = (((i7 & 2) >> 1) * 3) + ((i7 & 1) << 1);
        return (((int) (j3 >> 33)) & ((1 << (i10 + 13)) - 1)) - 1 == 0 || (((int) (j3 >> (i10 + 46))) & ((1 << (18 - i10)) - 1)) - 1 == 0;
    }

    public static String l(long j3) {
        int iH = h(j3);
        String strValueOf = iH == Integer.MAX_VALUE ? "Infinity" : String.valueOf(iH);
        int iG = g(j3);
        String strValueOf2 = iG != Integer.MAX_VALUE ? String.valueOf(iG) : "Infinity";
        StringBuilder sb = new StringBuilder("Constraints(minWidth = ");
        sb.append(j(j3));
        sb.append(", maxWidth = ");
        sb.append(strValueOf);
        sb.append(", minHeight = ");
        sb.append(i(j3));
        sb.append(", maxHeight = ");
        return android.hardware.bydauto.ac.tsimpl.a.k(sb, strValueOf2, ')');
    }

    public final boolean equals(Object obj) {
        if (obj instanceof a) {
            return this.f11027a == ((a) obj).f11027a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f11027a);
    }

    public final String toString() {
        return l(this.f11027a);
    }
}
