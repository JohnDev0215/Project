package e8;

import A7.m;
import F8.A;
import F8.AbstractC0282q;
import F8.AbstractC0287w;
import F8.H;
import F8.O;
import F8.a0;
import G8.f;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q8.h;
import g1.AbstractC1274a;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.List;
import m7.i;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import q8.C2202f;
import q8.C2203g;
import y8.n;

/* JADX INFO: renamed from: e8.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1240e extends AbstractC0282q {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1240e(A a10, A a11) {
        super(a10, a11);
        m.f(a10, "lowerBound");
        m.f(a11, "upperBound");
        G8.d.f2722a.b(a10, a11);
    }

    public static final ArrayList Q0(C2203g c2203g, AbstractC0287w abstractC0287w) {
        List<O> listO0 = abstractC0287w.o0();
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(listO0, 10));
        for (O o : listO0) {
            c2203g.getClass();
            m.f(o, "typeProjection");
            StringBuilder sb = new StringBuilder();
            AbstractC1998m.d0(AbstractC1387d.j(o), sb, ", ", (124 & 4) != 0 ? "" : null, (124 & 8) != 0 ? "" : null, -1, "...", (124 & 64) != 0 ? null : new C2202f(c2203g, 0));
            String string = sb.toString();
            m.e(string, "StringBuilder().apply(builderAction).toString()");
            arrayList.add(string);
        }
        return arrayList;
    }

    public static final String R0(String str, String str2) {
        if (!h.D0(str, '<')) {
            return str;
        }
        return h.c1(str, '<') + '<' + str2 + '>' + h.a1('>', str, str);
    }

    @Override // F8.AbstractC0287w
    public final AbstractC0287w I0(f fVar) {
        m.f(fVar, "kotlinTypeRefiner");
        A a10 = this.f2477m;
        m.f(a10, "type");
        A a11 = this.f2478n;
        m.f(a11, "type");
        return new C1240e(a10, a11);
    }

    @Override // F8.a0
    public final a0 L0(boolean z6) {
        return new C1240e(this.f2477m.L0(z6), this.f2478n.L0(z6));
    }

    @Override // F8.a0
    /* JADX INFO: renamed from: M0 */
    public final a0 I0(f fVar) {
        m.f(fVar, "kotlinTypeRefiner");
        A a10 = this.f2477m;
        m.f(a10, "type");
        A a11 = this.f2478n;
        m.f(a11, "type");
        return new C1240e(a10, a11);
    }

    @Override // F8.a0
    public final a0 N0(H h10) {
        m.f(h10, "newAttributes");
        return new C1240e(this.f2477m.N0(h10), this.f2478n.N0(h10));
    }

    @Override // F8.AbstractC0282q
    public final A O0() {
        return this.f2477m;
    }

    @Override // F8.AbstractC0282q
    public final String P0(C2203g c2203g, C2203g c2203g2) {
        m.f(c2203g, "renderer");
        A a10 = this.f2477m;
        String strY = c2203g.Y(a10);
        A a11 = this.f2478n;
        String strY2 = c2203g.Y(a11);
        if (c2203g2.f21281a.n()) {
            return "raw (" + strY + ".." + strY2 + ')';
        }
        if (a11.o0().isEmpty()) {
            return c2203g.F(strY, strY2, AbstractC1274a.L(this));
        }
        ArrayList arrayListQ0 = Q0(c2203g, a10);
        ArrayList arrayListQ02 = Q0(c2203g, a11);
        String strF0 = AbstractC1998m.f0(arrayListQ0, ", ", null, null, C1239d.f14489m, 30);
        ArrayList<i> arrayListF0 = AbstractC1998m.F0(arrayListQ0, arrayListQ02);
        if (arrayListF0.isEmpty()) {
            strY2 = R0(strY2, strF0);
        } else {
            for (i iVar : arrayListF0) {
                String str = (String) iVar.l;
                String str2 = (String) iVar.f19259m;
                if (!m.a(str, h.S0(str2, "out ")) && !str2.equals("*")) {
                    break;
                }
            }
            strY2 = R0(strY2, strF0);
        }
        String strR0 = R0(strY, strF0);
        return m.a(strR0, strY2) ? strR0 : c2203g.F(strR0, strY2, AbstractC1274a.L(this));
    }

    @Override // F8.AbstractC0282q, F8.AbstractC0287w
    public final n z0() {
        InterfaceC0654g interfaceC0654gN = B0().n();
        InterfaceC0652e interfaceC0652e = interfaceC0654gN instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gN : null;
        if (interfaceC0652e != null) {
            n nVarC0 = interfaceC0652e.C0(new C1238c());
            m.e(nVarC0, "classDescriptor.getMemberScope(RawSubstitution())");
            return nVarC0;
        }
        throw new IllegalStateException(("Incorrect classifier: " + B0().n()).toString());
    }
}
