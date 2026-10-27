package G;

import A.C0004b;
import C0.C0114v;
import T.C0735d;
import T.C0738e0;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.R0;
import d0.InterfaceC1054c;
import java.util.LinkedHashSet;
import java.util.Map;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class e0 implements d0.k, InterfaceC1054c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d0.l f2554a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0738e0 f2555b;
    public final LinkedHashSet c;

    public e0(d0.k kVar, Map map) {
        c0 c0Var = new c0(kVar, 0);
        R0 r02 = d0.m.f13274a;
        this.f2554a = new d0.l(map, c0Var);
        this.f2555b = C0735d.O(null, T.S.f8224q);
        this.c = new LinkedHashSet();
    }

    @Override // d0.k
    public final d0.j a(String str, InterfaceC2772a interfaceC2772a) {
        return this.f2554a.a(str, interfaceC2772a);
    }

    @Override // d0.k
    public final boolean b(Object obj) {
        return this.f2554a.b(obj);
    }

    @Override // d0.k
    public final Object c(String str) {
        return this.f2554a.c(str);
    }

    @Override // d0.InterfaceC1054c
    public final void d(Object obj) {
        InterfaceC1054c interfaceC1054c = (InterfaceC1054c) this.f2555b.getValue();
        if (interfaceC1054c == null) {
            throw new IllegalArgumentException("null wrappedHolder");
        }
        interfaceC1054c.d(obj);
    }

    @Override // d0.InterfaceC1054c
    public final void e(Object obj, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-697180401);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(obj) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(bVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(this) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            InterfaceC1054c interfaceC1054c = (InterfaceC1054c) this.f2555b.getValue();
            if (interfaceC1054c == null) {
                throw new IllegalArgumentException("null wrappedHolder");
            }
            interfaceC1054c.e(obj, bVar, c0761q, i10 & 126);
            boolean zH = c0761q.h(this) | c0761q.h(obj);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                objG = new C0004b(this, 15, obj);
                c0761q.a0(objG);
            }
            C0735d.d(obj, (InterfaceC2782k) objG, c0761q);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0114v(this, obj, bVar, i7, 3);
        }
    }
}
