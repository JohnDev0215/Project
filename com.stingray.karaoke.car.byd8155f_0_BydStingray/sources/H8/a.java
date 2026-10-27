package H8;

import A7.m;
import E8.l;
import F8.T;
import F8.W;
import Q7.AbstractC0662o;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0658k;
import Q7.N;
import Q7.O;
import T7.C0790i;
import T7.C0792k;
import java.util.List;
import n7.AbstractC1985B;
import n7.u;
import o8.C2061f;
import y8.n;

/* JADX INFO: loaded from: classes.dex */
public final class a extends C0792k {
    /* JADX WARN: Illegal instructions before constructor call */
    public a(C2061f c2061f) {
        i iVar = i.f2895a;
        c cVar = i.f2896b;
        List list = u.l;
        O o = N.c;
        super(cVar, c2061f, 3, 1, list, l.f1859e);
        C0790i c0790i = new C0790i(this, null, R7.g.f7535a, true, 1, o);
        c0790i.q1(list, AbstractC0662o.d);
        String str = c0790i.getName().l;
        m.e(str, "errorConstructor.name.toString()");
        e eVarB = i.b(9, str, "");
        h hVar = h.f2871G;
        c0790i.f8900s = new f(i.d(hVar, new String[0]), eVarB, hVar, list, false, new String[0]);
        o0(eVarB, AbstractC1985B.t(c0790i), c0790i);
    }

    @Override // T7.AbstractC0783b, Q7.P
    public final InterfaceC0658k f(W w10) {
        m.f(w10, "substitutor");
        return this;
    }

    @Override // T7.AbstractC0783b, T7.y
    public final n i(T t10, G8.f fVar) {
        String str = getName().l;
        m.e(str, "name.toString()");
        return i.b(9, str, t10.toString());
    }

    @Override // T7.AbstractC0783b
    /* JADX INFO: renamed from: i0 */
    public final InterfaceC0652e f(W w10) {
        m.f(w10, "substitutor");
        return this;
    }

    @Override // T7.C0792k
    public final String toString() {
        String strB = getName().b();
        m.e(strB, "name.asString()");
        return strB;
    }
}
