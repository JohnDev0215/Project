package d8;

import F8.AbstractC0287w;
import F8.C0269d;
import F8.Y;
import Q7.InterfaceC0658k;
import T7.AbstractC0784c;
import Z7.EnumC0898a;
import a.AbstractC0904a;
import c8.C1036a;
import c8.C1038c;
import g8.C1314d;
import g8.C1323m;
import g8.C1325o;
import h8.AbstractC1387d;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import l3.P;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2061f;

/* JADX INFO: renamed from: d8.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1113E extends AbstractC0784c {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final N0.q f13559w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final W7.B f13560x;

    /* JADX WARN: Illegal instructions before constructor call */
    public C1113E(N0.q qVar, W7.B b10, int i7, InterfaceC0658k interfaceC0658k) {
        A7.m.f(b10, "javaTypeParameter");
        C1036a c1036a = (C1036a) qVar.f4399m;
        super(c1036a.f12411a, interfaceC0658k, new C1038c(qVar, b10, false), C2061f.e(b10.f9868a.getName()), 1, false, i7, c1036a.f12420m);
        this.f13559w = qVar;
        this.f13560x = b10;
    }

    @Override // T7.AbstractC0789h
    public final List c1(List list) {
        N0.q qVar = this.f13559w;
        C1314d c1314d = ((C1036a) qVar.f4399m).f12424r;
        c1314d.getClass();
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AbstractC0287w abstractC0287wB = (AbstractC0287w) it.next();
            C1323m c1323m = C1323m.f15019q;
            A7.m.f(abstractC0287wB, "<this>");
            if (!Y.c(abstractC0287wB, c1323m, null) && (abstractC0287wB = c1314d.b(new C1325o(this, false, qVar, EnumC0898a.TYPE_PARAMETER_BOUNDS, false), abstractC0287wB, n7.u.l, null, false)) == null) {
                abstractC0287wB = abstractC0287wB;
            }
            arrayList.add(abstractC0287wB);
        }
        return arrayList;
    }

    @Override // T7.AbstractC0789h
    public final List d1() {
        Type[] bounds = this.f13560x.f9868a.getBounds();
        A7.m.e(bounds, "typeVariable.bounds");
        ArrayList arrayList = new ArrayList(bounds.length);
        for (Type type : bounds) {
            arrayList.add(new W7.p(type));
        }
        W7.p pVar = (W7.p) AbstractC1998m.t0(arrayList);
        Collection collection = arrayList;
        if (A7.m.a(pVar != null ? pVar.f9893a : null, Object.class)) {
            collection = n7.u.l;
        }
        boolean zIsEmpty = collection.isEmpty();
        N0.q qVar = this.f13559w;
        if (zIsEmpty) {
            return AbstractC1387d.j(C0269d.j(((C1036a) qVar.f4399m).o.f8921p.e(), ((C1036a) qVar.f4399m).o.f8921p.n()));
        }
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(collection, 10));
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList2.add(((P) qVar.l).G((W7.p) it.next(), AbstractC0904a.S(2, false, false, this, 3)));
        }
        return arrayList2;
    }
}
