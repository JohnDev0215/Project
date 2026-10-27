package Z7;

import F8.AbstractC0287w;
import F8.W;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0652e;
import T7.K;
import b8.C1011f;
import e8.C1238c;
import e8.C1240e;
import java.util.List;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class j implements r8.g {
    @Override // r8.g
    public r8.f a(InterfaceC0649b interfaceC0649b, InterfaceC0649b interfaceC0649b2, InterfaceC0652e interfaceC0652e) {
        A7.m.f(interfaceC0649b, "superDescriptor");
        A7.m.f(interfaceC0649b2, "subDescriptor");
        boolean z6 = interfaceC0649b2 instanceof C1011f;
        r8.f fVar = r8.f.f21884n;
        if (!z6) {
            return fVar;
        }
        C1011f c1011f = (C1011f) interfaceC0649b2;
        if (!c1011f.t().isEmpty()) {
            return fVar;
        }
        r8.k kVarI = r8.l.i(interfaceC0649b, interfaceC0649b2);
        if ((kVarI != null ? kVarI.c() : 0) != 0) {
            return fVar;
        }
        List listD0 = c1011f.D0();
        A7.m.e(listD0, "subDescriptor.valueParameters");
        P8.r rVarG0 = P8.m.g0(AbstractC1998m.S(listD0), C0902e.f10936q);
        AbstractC0287w abstractC0287w = c1011f.f8900s;
        A7.m.c(abstractC0287w);
        P8.h hVarC0 = P8.m.c0(AbstractC1996k.y(new P8.k[]{rVarG0, AbstractC1996k.y(new Object[]{abstractC0287w})}));
        T7.u uVar = c1011f.f8902u;
        P8.f fVar2 = new P8.f(P8.m.c0(AbstractC1996k.y(new P8.k[]{hVarC0, AbstractC1998m.S(AbstractC1999n.E(uVar != null ? uVar.b() : null))})));
        while (fVar2.hasNext()) {
            AbstractC0287w abstractC0287w2 = (AbstractC0287w) fVar2.next();
            if (!abstractC0287w2.o0().isEmpty() && !(abstractC0287w2.K0() instanceof C1240e)) {
                return fVar;
            }
        }
        InterfaceC0649b interfaceC0649bMo0a = (InterfaceC0649b) interfaceC0649b.f(new W(new C1238c()));
        if (interfaceC0649bMo0a == null) {
            return fVar;
        }
        if (interfaceC0649bMo0a instanceof K) {
            K k10 = (K) interfaceC0649bMo0a;
            if (!k10.t().isEmpty()) {
                interfaceC0649bMo0a = k10.v0().p().mo0a();
                A7.m.c(interfaceC0649bMo0a);
            }
        }
        int iC = r8.l.c.n(interfaceC0649bMo0a, interfaceC0649b2, false).c();
        A7.k.s(iC, "DEFAULT.isOverridableByW…Descriptor, false).result");
        return i.f10943a[AbstractC2641j.c(iC)] == 1 ? r8.f.l : fVar;
    }

    @Override // r8.g
    public r8.e b() {
        return r8.e.f21881m;
    }
}
