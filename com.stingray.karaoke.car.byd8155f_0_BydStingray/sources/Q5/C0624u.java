package Q5;

import D.AbstractC0133o;
import Q.C0523o0;
import T.C0761q;
import n0.AbstractC1861K;
import n0.C1891t;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q5.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0624u implements InterfaceC2786o {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f7075m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f7076n;
    public final /* synthetic */ Object o;

    public C0624u(String str, boolean z6, boolean z10) {
        this.o = str;
        this.f7075m = z6;
        this.f7076n = z10;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        switch (this.l) {
            case 0:
                ((Number) obj3).intValue();
                A7.m.f((w.q) obj, "$this$AnimatedVisibility");
                l9.d.p(g0.l.f14684a, (String) this.o, this.f7075m, this.f7076n, (C0761q) obj2, 6);
                break;
            default:
                C0761q c0761q = (C0761q) obj2;
                int iIntValue = ((Number) obj3).intValue();
                A7.m.f((C0523o0) obj, "it");
                if ((iIntValue & 17) == 16 && c0761q.x()) {
                    c0761q.K();
                } else {
                    c0761q.Q(2117943619);
                    long j3 = (this.f7075m && this.f7076n) ? l6.d.c(c0761q).f18702f : C1891t.f19451j;
                    c0761q.p(false);
                    c0761q.Q(2117947146);
                    boolean zBooleanValue = ((Boolean) ((T.X) this.o).getValue()).booleanValue();
                    l9.n nVar = AbstractC1861K.f19381a;
                    g0.l lVar = g0.l.f14684a;
                    if (zBooleanValue) {
                        AbstractC0133o.a(androidx.compose.foundation.a.b(h6.q.m(h6.r.r(androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, 1.0f), 1.0f), 1.8f, 1.8f), J.e.f3041a), C1891t.b(0.3f, j3), nVar), c0761q, 0);
                    }
                    c0761q.p(false);
                    AbstractC0133o.a(androidx.compose.foundation.a.b(h6.q.m(androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.b(lVar, 1.0f), 1.0f), J.e.f3041a), j3, nVar), c0761q, 0);
                }
                break;
        }
        return m7.q.f19267a;
    }

    public C0624u(boolean z6, boolean z10, T.X x8) {
        this.f7075m = z6;
        this.f7076n = z10;
        this.o = x8;
    }
}
