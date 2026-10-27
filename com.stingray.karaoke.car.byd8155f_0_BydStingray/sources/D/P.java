package D;

import T.C0753m;
import T.C0761q;

/* JADX INFO: loaded from: classes.dex */
public abstract class P {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final S f1043a = new S(AbstractC0127i.f1076a, g0.b.f14669u);

    public static final long a(int i7, int i10, int i11, boolean z6) {
        if (!z6) {
            return io.sentry.config.a.e(i7, i10, 0, i11);
        }
        int iMin = Math.min(i7, 262142);
        int iMin2 = i10 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(i10, 262142);
        int iJ = io.sentry.config.a.J(iMin2 == Integer.MAX_VALUE ? iMin : iMin2);
        return io.sentry.config.a.e(iMin, iMin2, Math.min(iJ, 0), i11 != Integer.MAX_VALUE ? Math.min(iJ, i11) : Integer.MAX_VALUE);
    }

    public static final S b(InterfaceC0123e interfaceC0123e, g0.f fVar, C0761q c0761q, int i7) {
        if (A7.m.a(interfaceC0123e, AbstractC0127i.f1076a) && A7.m.a(fVar, g0.b.f14669u)) {
            c0761q.Q(-849081669);
            c0761q.p(false);
            return f1043a;
        }
        c0761q.Q(-849030798);
        boolean z6 = true;
        boolean z10 = (((i7 & 14) ^ 6) > 4 && c0761q.f(interfaceC0123e)) || (i7 & 6) == 4;
        if ((((i7 & 112) ^ 48) <= 32 || !c0761q.f(fVar)) && (i7 & 48) != 32) {
            z6 = false;
        }
        boolean z11 = z10 | z6;
        Object objG = c0761q.G();
        if (z11 || objG == C0753m.f8270a) {
            objG = new S(interfaceC0123e, fVar);
            c0761q.a0(objG);
        }
        S s6 = (S) objG;
        c0761q.p(false);
        return s6;
    }
}
