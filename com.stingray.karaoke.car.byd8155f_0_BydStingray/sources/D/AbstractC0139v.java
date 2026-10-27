package D;

import T.C0753m;
import T.C0761q;

/* JADX INFO: renamed from: D.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0139v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0141x f1112a = new C0141x(AbstractC0127i.c, g0.b.f14672x);

    public static final C0141x a(InterfaceC0125g interfaceC0125g, g0.e eVar, C0761q c0761q, int i7) {
        if (A7.m.a(interfaceC0125g, AbstractC0127i.c) && eVar.equals(g0.b.f14672x)) {
            c0761q.Q(345962472);
            c0761q.p(false);
            return f1112a;
        }
        c0761q.Q(346016319);
        boolean z6 = true;
        boolean z10 = (((i7 & 14) ^ 6) > 4 && c0761q.f(interfaceC0125g)) || (i7 & 6) == 4;
        if ((((i7 & 112) ^ 48) <= 32 || !c0761q.f(eVar)) && (i7 & 48) != 32) {
            z6 = false;
        }
        boolean z11 = z10 | z6;
        Object objG = c0761q.G();
        if (z11 || objG == C0753m.f8270a) {
            objG = new C0141x(interfaceC0125g, eVar);
            c0761q.a0(objG);
        }
        C0141x c0141x = (C0141x) objG;
        c0761q.p(false);
        return c0141x;
    }

    public static final long b(int i7, int i10, int i11, boolean z6) {
        if (!z6) {
            return io.sentry.config.a.e(0, i11, i7, i10);
        }
        int iMin = Math.min(i7, 262142);
        int iMin2 = i10 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(i10, 262142);
        int iJ = io.sentry.config.a.J(iMin2 == Integer.MAX_VALUE ? iMin : iMin2);
        return io.sentry.config.a.e(Math.min(iJ, 0), i11 != Integer.MAX_VALUE ? Math.min(iJ, i11) : Integer.MAX_VALUE, iMin, iMin2);
    }
}
