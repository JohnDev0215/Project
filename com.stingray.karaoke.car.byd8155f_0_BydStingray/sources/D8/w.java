package D8;

import A.I0;
import B8.D;
import Q7.InterfaceC0657j;
import Q7.O;
import T.C0746i0;
import T7.AbstractC0784c;
import a.AbstractC0904a;
import h8.AbstractC1387d;
import j8.Q;
import j8.V;
import j8.W;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import l8.InterfaceC1788f;
import n7.AbstractC2000o;
import o8.C2061f;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public final class w extends AbstractC0784c {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final B8.k f1294w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final W f1295x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final a f1296y;

    /* JADX WARN: Illegal instructions before constructor call */
    public w(B8.k kVar, W w10, int i7) {
        int i10;
        A7.m.f(kVar, "c");
        B8.i iVar = (B8.i) kVar.f779a;
        E8.o oVar = iVar.f762a;
        R7.f fVar = R7.g.f7535a;
        C2061f c2061fF = AbstractC0904a.F((InterfaceC1788f) kVar.f780b, w10.f17232p);
        V v10 = w10.f17234r;
        A7.m.e(v10, "proto.variance");
        int iOrdinal = v10.ordinal();
        if (iOrdinal != 0) {
            i10 = 1;
            if (iOrdinal == 1) {
                i10 = 3;
            } else if (iOrdinal != 2) {
                throw new A9.k();
            }
        } else {
            i10 = 2;
        }
        super(oVar, (InterfaceC0657j) kVar.c, fVar, c2061fF, i10, w10.f17233q, i7, O.f7181n);
        this.f1294w = kVar;
        this.f1295x = w10;
        this.f1296y = new a(iVar.f762a, new I0(this, 7));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Iterable, java.util.List] */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v9 */
    @Override // T7.AbstractC0789h
    public final List d1() {
        B8.k kVar = this.f1294w;
        C0746i0 c0746i0 = (C0746i0) kVar.d;
        W w10 = this.f1295x;
        A7.m.f(w10, "<this>");
        A7.m.f(c0746i0, "typeTable");
        List list = w10.f17235s;
        boolean zIsEmpty = list.isEmpty();
        ?? arrayList = list;
        if (zIsEmpty) {
            arrayList = 0;
        }
        if (arrayList == 0) {
            List<Integer> list2 = w10.f17236t;
            A7.m.e(list2, "upperBoundIdList");
            arrayList = new ArrayList(AbstractC2000o.J(list2, 10));
            for (Integer num : list2) {
                A7.m.e(num, "it");
                arrayList.add(c0746i0.c(num.intValue()));
            }
        }
        if (arrayList.isEmpty()) {
            return AbstractC1387d.j(AbstractC2564d.e(this).n());
        }
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((D) kVar.f784h).g((Q) it.next()));
        }
        return arrayList2;
    }

    @Override // A9.b, R7.a
    public final R7.h k() {
        return this.f1296y;
    }
}
