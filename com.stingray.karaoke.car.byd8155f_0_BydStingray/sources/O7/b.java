package O7;

import A9.k;
import F8.AbstractC0267b;
import F8.C0269d;
import F8.H;
import F8.P;
import N7.o;
import Q7.AbstractC0669w;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0671y;
import Q7.O;
import Q7.Q;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import o8.C2057b;

/* JADX INFO: loaded from: classes.dex */
public final class b extends AbstractC0267b {
    public final /* synthetic */ c c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar) {
        super(cVar.f5172p);
        this.c = cVar;
    }

    @Override // F8.AbstractC0272g
    public final Collection b() {
        List<C2057b> listJ;
        c cVar = this.c;
        int iOrdinal = cVar.f5174r.ordinal();
        if (iOrdinal == 0 || iOrdinal == 1) {
            listJ = AbstractC1387d.j(c.f5170w);
        } else {
            int i7 = cVar.f5175s;
            if (iOrdinal == 2) {
                listJ = AbstractC1999n.D(c.f5171x, new C2057b(o.f4875j, e.o.a(i7)));
            } else {
                if (iOrdinal != 3) {
                    throw new k();
                }
                listJ = AbstractC1999n.D(c.f5171x, new C2057b(o.f4870e, e.f5182p.a(i7)));
            }
        }
        InterfaceC0671y interfaceC0671yO = cVar.f5173q.o();
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(listJ, 10));
        for (C2057b c2057b : listJ) {
            InterfaceC0652e interfaceC0652eD = AbstractC0669w.d(interfaceC0671yO, c2057b);
            if (interfaceC0652eD == null) {
                throw new IllegalStateException(("Built-in class " + c2057b + " not found").toString());
            }
            List listW0 = AbstractC1998m.w0(interfaceC0652eD.C().p().size(), cVar.f5178v);
            ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(listW0, 10));
            Iterator it = listW0.iterator();
            while (it.hasNext()) {
                arrayList2.add(new P(((Q) it.next()).j()));
            }
            H.f2437m.getClass();
            arrayList.add(C0269d.q(H.f2438n, interfaceC0652eD, arrayList2));
        }
        return AbstractC1998m.z0(arrayList);
    }

    @Override // F8.AbstractC0272g
    public final O d() {
        return O.f7181n;
    }

    @Override // F8.AbstractC0267b
    /* JADX INFO: renamed from: i */
    public final InterfaceC0652e n() {
        return this.c;
    }

    @Override // F8.K
    public final boolean m() {
        return true;
    }

    @Override // F8.AbstractC0267b, F8.K
    public final InterfaceC0654g n() {
        return this.c;
    }

    @Override // F8.K
    public final List p() {
        return this.c.f5178v;
    }

    public final String toString() {
        return this.c.toString();
    }
}
