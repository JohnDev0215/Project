package Z7;

import Q7.InterfaceC0649b;
import Q7.InterfaceC0652e;
import Q7.L;

/* JADX INFO: loaded from: classes.dex */
public final class l implements r8.g {
    @Override // r8.g
    public r8.f a(InterfaceC0649b interfaceC0649b, InterfaceC0649b interfaceC0649b2, InterfaceC0652e interfaceC0652e) {
        A7.m.f(interfaceC0649b, "superDescriptor");
        A7.m.f(interfaceC0649b2, "subDescriptor");
        boolean z6 = interfaceC0649b2 instanceof L;
        r8.f fVar = r8.f.f21884n;
        if (!z6 || !(interfaceC0649b instanceof L)) {
            return fVar;
        }
        L l = (L) interfaceC0649b2;
        L l10 = (L) interfaceC0649b;
        return !A7.m.a(l.getName(), l10.getName()) ? fVar : (P8.n.F(l) && P8.n.F(l10)) ? r8.f.l : (P8.n.F(l) || P8.n.F(l10)) ? r8.f.f21883m : fVar;
    }

    @Override // r8.g
    public r8.e b() {
        return r8.e.f21882n;
    }
}
