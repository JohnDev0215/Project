package y0;

import androidx.compose.ui.input.pointer.PointerHoverIconModifierElement;

/* JADX INFO: renamed from: y0.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2737o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2723a f24074a = new C2723a(1000);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2723a f24075b;
    public static final StackTraceElement[] c;

    static {
        new C2723a(1007);
        new C2723a(1008);
        f24075b = new C2723a(1002);
        c = new StackTraceElement[0];
    }

    public static final boolean a(q qVar) {
        return !qVar.f24082h && qVar.d;
    }

    public static final boolean b(q qVar) {
        return (qVar.b() || !qVar.f24082h || qVar.d) ? false : true;
    }

    public static final boolean c(q qVar) {
        return qVar.f24082h && !qVar.d;
    }

    public static final boolean d(int i7, int i10) {
        return i7 == i10;
    }

    public static final boolean e(int i7, int i10) {
        return i7 == i10;
    }

    public static final boolean f(q qVar, long j3) {
        long j10 = qVar.c;
        float fD = m0.c.d(j10);
        float fE = m0.c.e(j10);
        return fD < 0.0f || fD > ((float) ((int) (j3 >> 32))) || fE < 0.0f || fE > ((float) ((int) (j3 & 4294967295L)));
    }

    public static final boolean g(q qVar, long j3, long j10) {
        if (!e(qVar.f24083i, 1)) {
            return f(qVar, j3);
        }
        long j11 = qVar.c;
        float fD = m0.c.d(j11);
        float fE = m0.c.e(j11);
        return fD < (-m0.f.d(j10)) || fD > m0.f.d(j10) + ((float) ((int) (j3 >> 32))) || fE < (-m0.f.b(j10)) || fE > m0.f.b(j10) + ((float) ((int) (j3 & 4294967295L)));
    }

    public static g0.o h(g0.o oVar, C2723a c2723a) {
        return oVar.k(new PointerHoverIconModifierElement(c2723a, false));
    }

    public static final long i(q qVar, boolean z6) {
        long jG = m0.c.g(qVar.c, qVar.f24081g);
        if (z6 || !qVar.b()) {
            return jG;
        }
        return 0L;
    }
}
