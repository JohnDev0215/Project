package t8;

import F8.A;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.H;
import F8.P;
import F8.a0;
import Q7.AbstractC0669w;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0671y;
import g1.AbstractC1274a;
import h8.AbstractC1387d;
import o8.C2057b;

/* JADX INFO: loaded from: classes.dex */
public final class r extends g {
    public r(C2057b c2057b, int i7) {
        super(new p(new f(c2057b, i7)));
    }

    @Override // t8.g
    public final AbstractC0287w a(InterfaceC0671y interfaceC0671y) {
        AbstractC0287w abstractC0287wC;
        A7.m.f(interfaceC0671y, "module");
        H.f2437m.getClass();
        H h10 = H.f2438n;
        N7.i iVarL = interfaceC0671y.l();
        iVarL.getClass();
        InterfaceC0652e interfaceC0652eI = iVarL.i(N7.n.f4832P.g());
        Object obj = this.f22499a;
        q qVar = (q) obj;
        if (qVar instanceof o) {
            abstractC0287wC = ((o) obj).f22506a;
        } else {
            if (!(qVar instanceof p)) {
                throw new A9.k();
            }
            f fVar = ((p) obj).f22507a;
            C2057b c2057b = fVar.f22497a;
            InterfaceC0652e interfaceC0652eD = AbstractC0669w.d(interfaceC0671y, c2057b);
            int i7 = fVar.f22498b;
            if (interfaceC0652eD == null) {
                H8.h hVar = H8.h.o;
                String string = c2057b.toString();
                A7.m.e(string, "classId.toString()");
                abstractC0287wC = H8.i.c(hVar, string, String.valueOf(i7));
            } else {
                A aJ = interfaceC0652eD.j();
                A7.m.e(aJ, "descriptor.defaultType");
                a0 a0VarZ = AbstractC1274a.Z(aJ);
                for (int i10 = 0; i10 < i7; i10++) {
                    a0VarZ = interfaceC0671y.l().h(a0VarZ);
                }
                abstractC0287wC = a0VarZ;
            }
        }
        return C0269d.q(h10, interfaceC0652eI, AbstractC1387d.j(new P(abstractC0287wC)));
    }
}
