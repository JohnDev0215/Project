package Q;

import T.C0761q;
import T.C0773y;
import n0.C1891t;

/* JADX INFO: loaded from: classes.dex */
public abstract class M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final T.R0 f5575a = new T.R0(C0518m.f5989u);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0773y f5576b = new C0773y(T.S.f8224q, C0518m.f5988t);
    public static final androidx.compose.material3.c c;
    public static final androidx.compose.material3.c d;

    static {
        long j3 = C1891t.f19452k;
        c = new androidx.compose.material3.c(true, Float.NaN, j3);
        d = new androidx.compose.material3.c(false, Float.NaN, j3);
    }

    public static final androidx.compose.material3.c a(boolean z6, float f10, long j3) {
        return (a1.e.a(f10, Float.NaN) && C1891t.c(j3, C1891t.f19452k)) ? z6 ? c : d : new androidx.compose.material3.c(z6, f10, j3);
    }

    public static androidx.compose.material3.c b(int i7) {
        return a((i7 & 1) != 0, Float.NaN, C1891t.f19452k);
    }

    public static final y.Z c(boolean z6, float f10, C0761q c0761q, int i7, int i10) {
        if ((i10 & 1) != 0) {
            z6 = true;
        }
        boolean z10 = z6;
        if ((i10 & 2) != 0) {
            f10 = Float.NaN;
        }
        float f11 = f10;
        long j3 = C1891t.f19452k;
        c0761q.Q(-1280632857);
        y.Z zA = ((Boolean) c0761q.k(f5575a)).booleanValue() ? P.u.a(z10, f11, j3, c0761q, i7 & 1022) : a(z10, f11, j3);
        c0761q.p(false);
        return zA;
    }
}
