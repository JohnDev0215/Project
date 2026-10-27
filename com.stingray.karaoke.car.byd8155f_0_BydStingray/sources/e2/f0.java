package e2;

import android.util.Pair;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public abstract class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b0 f14097a = new b0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f14098b;
    public static final String c;
    public static final String d;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14098b = Integer.toString(0, 36);
        c = Integer.toString(1, 36);
        d = Integer.toString(2, 36);
    }

    public int a(boolean z6) {
        return p() ? -1 : 0;
    }

    public abstract int b(Object obj);

    public int c(boolean z6) {
        if (p()) {
            return -1;
        }
        return o() - 1;
    }

    public final int d(int i7, c0 c0Var, e0 e0Var, int i10, boolean z6) {
        int i11 = f(i7, c0Var, false).c;
        if (m(i11, e0Var, 0L).o != i7) {
            return i7 + 1;
        }
        int iE = e(i11, i10, z6);
        if (iE == -1) {
            return -1;
        }
        return m(iE, e0Var, 0L).f14095n;
    }

    public int e(int i7, int i10, boolean z6) {
        if (i10 == 0) {
            if (i7 == c(z6)) {
                return -1;
            }
            return i7 + 1;
        }
        if (i10 == 1) {
            return i7;
        }
        if (i10 == 2) {
            return i7 == c(z6) ? a(z6) : i7 + 1;
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        int iC;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f0)) {
            return false;
        }
        f0 f0Var = (f0) obj;
        if (f0Var.o() != o() || f0Var.h() != h()) {
            return false;
        }
        e0 e0Var = new e0();
        c0 c0Var = new c0();
        e0 e0Var2 = new e0();
        c0 c0Var2 = new c0();
        for (int i7 = 0; i7 < o(); i7++) {
            if (!m(i7, e0Var, 0L).equals(f0Var.m(i7, e0Var2, 0L))) {
                return false;
            }
        }
        for (int i10 = 0; i10 < h(); i10++) {
            if (!f(i10, c0Var, true).equals(f0Var.f(i10, c0Var2, true))) {
                return false;
            }
        }
        int iA = a(true);
        if (iA != f0Var.a(true) || (iC = c(true)) != f0Var.c(true)) {
            return false;
        }
        while (iA != iC) {
            int iE = e(iA, 0, true);
            if (iE != f0Var.e(iA, 0, true)) {
                return false;
            }
            iA = iE;
        }
        return true;
    }

    public abstract c0 f(int i7, c0 c0Var, boolean z6);

    public c0 g(Object obj, c0 c0Var) {
        return f(b(obj), c0Var, true);
    }

    public abstract int h();

    public int hashCode() {
        e0 e0Var = new e0();
        c0 c0Var = new c0();
        int iO = o() + 217;
        for (int i7 = 0; i7 < o(); i7++) {
            iO = (iO * 31) + m(i7, e0Var, 0L).hashCode();
        }
        int iH = h() + (iO * 31);
        for (int i10 = 0; i10 < h(); i10++) {
            iH = (iH * 31) + f(i10, c0Var, true).hashCode();
        }
        int iA = a(true);
        while (iA != -1) {
            iH = (iH * 31) + iA;
            iA = e(iA, 0, true);
        }
        return iH;
    }

    public final Pair i(e0 e0Var, c0 c0Var, int i7, long j3) {
        Pair pairJ = j(e0Var, c0Var, i7, j3, 0L);
        pairJ.getClass();
        return pairJ;
    }

    public final Pair j(e0 e0Var, c0 c0Var, int i7, long j3, long j10) {
        AbstractC1346c.f(i7, o());
        m(i7, e0Var, j10);
        if (j3 == -9223372036854775807L) {
            j3 = e0Var.l;
            if (j3 == -9223372036854775807L) {
                return null;
            }
        }
        int i10 = e0Var.f14095n;
        f(i10, c0Var, false);
        while (i10 < e0Var.o && c0Var.f14061e != j3) {
            int i11 = i10 + 1;
            if (f(i11, c0Var, false).f14061e > j3) {
                break;
            }
            i10 = i11;
        }
        f(i10, c0Var, true);
        long jMin = j3 - c0Var.f14061e;
        long j11 = c0Var.d;
        if (j11 != -9223372036854775807L) {
            jMin = Math.min(jMin, j11 - 1);
        }
        long jMax = Math.max(0L, jMin);
        Object obj = c0Var.f14060b;
        obj.getClass();
        return Pair.create(obj, Long.valueOf(jMax));
    }

    public int k(int i7, int i10, boolean z6) {
        if (i10 == 0) {
            if (i7 == a(z6)) {
                return -1;
            }
            return i7 - 1;
        }
        if (i10 == 1) {
            return i7;
        }
        if (i10 == 2) {
            return i7 == a(z6) ? c(z6) : i7 - 1;
        }
        throw new IllegalStateException();
    }

    public abstract Object l(int i7);

    public abstract e0 m(int i7, e0 e0Var, long j3);

    public final void n(int i7, e0 e0Var) {
        m(i7, e0Var, 0L);
    }

    public abstract int o();

    public final boolean p() {
        return o() == 0;
    }
}
