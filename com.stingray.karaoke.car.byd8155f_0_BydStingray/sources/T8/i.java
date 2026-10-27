package t8;

import F8.A;
import F8.AbstractC0287w;
import Q7.AbstractC0669w;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0671y;
import o8.C2057b;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class i extends g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2057b f22501b;
    public final C2061f c;

    public i(C2057b c2057b, C2061f c2061f) {
        super(new m7.i(c2057b, c2061f));
        this.f22501b = c2057b;
        this.c = c2061f;
    }

    @Override // t8.g
    public final AbstractC0287w a(InterfaceC0671y interfaceC0671y) {
        A7.m.f(interfaceC0671y, "module");
        C2057b c2057b = this.f22501b;
        InterfaceC0652e interfaceC0652eD = AbstractC0669w.d(interfaceC0671y, c2057b);
        A aJ = null;
        if (interfaceC0652eD != null) {
            if (!r8.d.n(interfaceC0652eD, 3)) {
                interfaceC0652eD = null;
            }
            if (interfaceC0652eD != null) {
                aJ = interfaceC0652eD.j();
            }
        }
        if (aJ != null) {
            return aJ;
        }
        H8.h hVar = H8.h.f2876L;
        String string = c2057b.toString();
        A7.m.e(string, "enumClassId.toString()");
        String str = this.c.l;
        A7.m.e(str, "enumEntryName.toString()");
        return H8.i.c(hVar, string, str);
    }

    @Override // t8.g
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f22501b.i());
        sb.append('.');
        sb.append(this.c);
        return sb.toString();
    }
}
