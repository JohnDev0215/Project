package O7;

import A7.k;
import A7.m;
import F8.AbstractC0287w;
import F8.W;
import L8.t;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import Q7.N;
import R7.h;
import T7.C0799s;
import T7.K;
import T7.Q;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import m7.i;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public final class g extends K {
    public g(InterfaceC0657j interfaceC0657j, g gVar, int i7, boolean z6) {
        super(interfaceC0657j, gVar, R7.g.f7535a, t.f4099g, i7, N.c);
        this.f8906y = true;
        this.f8889G = z6;
        this.f8890H = false;
    }

    @Override // T7.K, T7.t
    public final T7.t e1(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, N n10, h hVar, C2061f c2061f) {
        m.f(interfaceC0657j, "newOwner");
        k.p(i7, "kind");
        m.f(hVar, "annotations");
        return new g(interfaceC0657j, (g) interfaceC0666t, i7, this.f8889G);
    }

    @Override // T7.t
    public final T7.t f1(C0799s c0799s) {
        C2061f c2061f;
        m.f(c0799s, "configuration");
        g gVar = (g) super.f1(c0799s);
        if (gVar == null) {
            return null;
        }
        List listD0 = gVar.D0();
        m.e(listD0, "substituted.valueParameters");
        if (listD0.isEmpty()) {
            return gVar;
        }
        Iterator it = listD0.iterator();
        while (it.hasNext()) {
            AbstractC0287w abstractC0287wB = ((Q) it.next()).b();
            m.e(abstractC0287wB, "it.type");
            if (C7.a.G(abstractC0287wB) != null) {
                List listD02 = gVar.D0();
                m.e(listD02, "substituted.valueParameters");
                ArrayList arrayList = new ArrayList(AbstractC2000o.J(listD02, 10));
                Iterator it2 = listD02.iterator();
                while (it2.hasNext()) {
                    AbstractC0287w abstractC0287wB2 = ((Q) it2.next()).b();
                    m.e(abstractC0287wB2, "it.type");
                    arrayList.add(C7.a.G(abstractC0287wB2));
                }
                int size = gVar.D0().size() - arrayList.size();
                boolean z6 = true;
                if (size == 0) {
                    List listD03 = gVar.D0();
                    m.e(listD03, "valueParameters");
                    ArrayList<i> arrayListF0 = AbstractC1998m.F0(arrayList, listD03);
                    if (arrayListF0.isEmpty()) {
                        return gVar;
                    }
                    for (i iVar : arrayListF0) {
                        if (!m.a((C2061f) iVar.l, ((Q) iVar.f19259m).getName())) {
                        }
                    }
                    return gVar;
                }
                List<Q> listD04 = gVar.D0();
                m.e(listD04, "valueParameters");
                ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(listD04, 10));
                for (Q q4 : listD04) {
                    C2061f name = q4.getName();
                    m.e(name, "it.name");
                    int i7 = q4.f8822r;
                    int i10 = i7 - size;
                    if (i10 >= 0 && (c2061f = (C2061f) arrayList.get(i10)) != null) {
                        name = c2061f;
                    }
                    arrayList2.add(q4.c1(gVar, name, i7));
                }
                C0799s c0799sI1 = gVar.i1(W.f2454b);
                if (arrayList.isEmpty()) {
                    z6 = false;
                } else {
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        if (((C2061f) it3.next()) == null) {
                            break;
                        }
                    }
                    z6 = false;
                }
                c0799sI1.f8868G = Boolean.valueOf(z6);
                c0799sI1.f8875r = arrayList2;
                c0799sI1.f8873p = gVar.b1();
                T7.t tVarF1 = super.f1(c0799sI1);
                m.c(tVarF1);
                return tVarF1;
            }
        }
        return gVar;
    }

    @Override // T7.t, Q7.InterfaceC0666t
    public final boolean j0() {
        return false;
    }

    @Override // T7.t, Q7.InterfaceC0666t
    public final boolean n() {
        return false;
    }

    @Override // T7.t, Q7.InterfaceC0670x
    public final boolean z() {
        return false;
    }
}
