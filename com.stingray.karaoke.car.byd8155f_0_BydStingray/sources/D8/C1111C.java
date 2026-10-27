package d8;

import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import Q7.L;
import c8.C1036a;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n7.AbstractC2004s;
import o8.C2061f;
import w8.C2606a;

/* JADX INFO: renamed from: d8.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1111C extends AbstractC1112D {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ int f13557p = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final W7.n f13558n;
    public final C1122i o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1111C(N0.q qVar, W7.n nVar, C1122i c1122i) {
        super(qVar, null);
        A7.m.f(nVar, "jClass");
        this.f13558n = nVar;
        this.o = c1122i;
    }

    public static L v(L l) {
        if (l.N() != 2) {
            return l;
        }
        Collection collectionQ = l.q();
        A7.m.e(collectionQ, "this.overriddenDescriptors");
        Collection<L> collection = collectionQ;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(collection, 10));
        for (L l10 : collection) {
            A7.m.e(l10, "it");
            arrayList.add(v(l10));
        }
        return (L) AbstractC1998m.r0(AbstractC1998m.U(arrayList));
    }

    @Override // y8.o, y8.p
    public final InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        return null;
    }

    @Override // d8.z
    public final Set h(y8.f fVar, y8.k kVar) {
        A7.m.f(fVar, "kindFilter");
        return n7.w.l;
    }

    @Override // d8.z
    public final Set i(y8.f fVar, y8.k kVar) {
        A7.m.f(fVar, "kindFilter");
        Set setC0 = AbstractC1998m.C0(((InterfaceC1116c) this.f13636e.a()).b());
        C1122i c1122i = this.o;
        C1111C c1111cQ = l9.l.Q(c1122i);
        Set setE = c1111cQ != null ? c1111cQ.e() : null;
        if (setE == null) {
            setE = n7.w.l;
        }
        setC0.addAll(setE);
        if (this.f13558n.f9891a.isEnum()) {
            setC0.addAll(AbstractC1999n.D(N7.o.c, N7.o.f4868a));
        }
        N0.q qVar = this.f13635b;
        ((C2606a) ((C1036a) qVar.f4399m).f12430x).getClass();
        A7.m.f(qVar, "_context_receiver_0");
        A7.m.f(c1122i, "thisDescriptor");
        setC0.addAll(new ArrayList());
        return setC0;
    }

    @Override // d8.z
    public final void j(ArrayList arrayList, C2061f c2061f) {
        A7.m.f(c2061f, "name");
        N0.q qVar = this.f13635b;
        C2606a c2606a = (C2606a) ((C1036a) qVar.f4399m).f12430x;
        C1122i c1122i = this.o;
        c2606a.getClass();
        A7.m.f(qVar, "_context_receiver_0");
        A7.m.f(c1122i, "thisDescriptor");
        A7.m.f(c2061f, "name");
    }

    @Override // d8.z
    public final InterfaceC1116c k() {
        return new C1114a(this.f13558n, C1123j.f13595p);
    }

    @Override // d8.z
    public final void m(LinkedHashSet linkedHashSet, C2061f c2061f) {
        A7.m.f(c2061f, "name");
        C1122i c1122i = this.o;
        C1111C c1111cQ = l9.l.Q(c1122i);
        Collection collectionD0 = c1111cQ == null ? n7.w.l : AbstractC1998m.D0(c1111cQ.a(c2061f, Y7.b.f10800p));
        C1036a c1036a = (C1036a) this.f13635b.f4399m;
        linkedHashSet.addAll(l9.d.d0(c2061f, collectionD0, linkedHashSet, this.o, c1036a.f12414f, c1036a.f12427u.d));
        if (this.f13558n.f9891a.isEnum()) {
            if (c2061f.equals(N7.o.c)) {
                linkedHashSet.add(r8.m.i(c1122i));
            } else if (c2061f.equals(N7.o.f4868a)) {
                linkedHashSet.add(r8.m.j(c1122i));
            }
        }
    }

    @Override // d8.AbstractC1112D, d8.z
    public final void n(ArrayList arrayList, C2061f c2061f) {
        A7.m.f(c2061f, "name");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        P7.m mVar = new P7.m(c2061f, 1);
        C1122i c1122i = this.o;
        O8.l.e(AbstractC1387d.j(c1122i), C1109A.l, new C1110B(c1122i, linkedHashSet, mVar));
        boolean zIsEmpty = arrayList.isEmpty();
        N0.q qVar = this.f13635b;
        if (zIsEmpty) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : linkedHashSet) {
                L lV = v((L) obj);
                Object arrayList2 = linkedHashMap.get(lV);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                    linkedHashMap.put(lV, arrayList2);
                }
                ((List) arrayList2).add(obj);
            }
            ArrayList arrayList3 = new ArrayList();
            Iterator it = linkedHashMap.entrySet().iterator();
            while (it.hasNext()) {
                Collection collection = (Collection) ((Map.Entry) it.next()).getValue();
                C1036a c1036a = (C1036a) qVar.f4399m;
                AbstractC2004s.L(arrayList3, l9.d.d0(c2061f, collection, arrayList, this.o, c1036a.f12414f, c1036a.f12427u.d));
            }
            arrayList.addAll(arrayList3);
        } else {
            C1036a c1036a2 = (C1036a) qVar.f4399m;
            arrayList.addAll(l9.d.d0(c2061f, linkedHashSet, arrayList, this.o, c1036a2.f12414f, c1036a2.f12427u.d));
        }
        if (this.f13558n.f9891a.isEnum() && c2061f.equals(N7.o.f4869b)) {
            O8.l.a(arrayList, r8.m.h(c1122i));
        }
    }

    @Override // d8.z
    public final Set o(y8.f fVar) {
        A7.m.f(fVar, "kindFilter");
        Set setC0 = AbstractC1998m.C0(((InterfaceC1116c) this.f13636e.a()).e());
        C1123j c1123j = C1123j.f13596q;
        C1122i c1122i = this.o;
        O8.l.e(AbstractC1387d.j(c1122i), C1109A.l, new C1110B(c1122i, setC0, c1123j));
        if (this.f13558n.f9891a.isEnum()) {
            setC0.add(N7.o.f4869b);
        }
        return setC0;
    }

    @Override // d8.z
    public final InterfaceC0657j q() {
        return this.o;
    }
}
