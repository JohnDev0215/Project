package D8;

import A7.A;
import A7.B;
import a.AbstractC0904a;
import j8.C1579y;
import j8.G;
import j8.T;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import l8.InterfaceC1788f;
import n7.AbstractC2000o;
import n7.z;
import o8.C2061f;
import p8.AbstractC2126a;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f1256j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f1257a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f1258b;
    public final LinkedHashMap c;
    public final E8.e d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final E8.e f1259e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final E8.j f1260f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final E8.i f1261g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final E8.i f1262h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r f1263i;

    static {
        B b10 = A.f443a;
        f1256j = new H7.w[]{b10.h(new A7.s(b10.b(p.class), "functionNames", "getFunctionNames()Ljava/util/Set;")), b10.h(new A7.s(b10.b(p.class), "variableNames", "getVariableNames()Ljava/util/Set;"))};
    }

    public p(r rVar, List list, List list2, List list3) {
        A7.m.f(list, "functionList");
        A7.m.f(list2, "propertyList");
        A7.m.f(list3, "typeAliasList");
        this.f1263i = rVar;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : list) {
            C2061f c2061fF = AbstractC0904a.F((InterfaceC1788f) rVar.f1267b.f780b, ((C1579y) ((AbstractC2126a) obj)).f17491q);
            Object arrayList = linkedHashMap.get(c2061fF);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(c2061fF, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        this.f1257a = c(linkedHashMap);
        r rVar2 = this.f1263i;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object obj2 : list2) {
            C2061f c2061fF2 = AbstractC0904a.F((InterfaceC1788f) rVar2.f1267b.f780b, ((G) ((AbstractC2126a) obj2)).f17120q);
            Object arrayList2 = linkedHashMap2.get(c2061fF2);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap2.put(c2061fF2, arrayList2);
            }
            ((List) arrayList2).add(obj2);
        }
        this.f1258b = c(linkedHashMap2);
        ((B8.i) this.f1263i.f1267b.f779a).c.getClass();
        r rVar3 = this.f1263i;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        for (Object obj3 : list3) {
            C2061f c2061fF3 = AbstractC0904a.F((InterfaceC1788f) rVar3.f1267b.f780b, ((T) ((AbstractC2126a) obj3)).f17209p);
            Object arrayList3 = linkedHashMap3.get(c2061fF3);
            if (arrayList3 == null) {
                arrayList3 = new ArrayList();
                linkedHashMap3.put(c2061fF3, arrayList3);
            }
            ((List) arrayList3).add(obj3);
        }
        this.c = c(linkedHashMap3);
        this.d = ((E8.l) ((B8.i) this.f1263i.f1267b.f779a).f762a).b(new o(this, 0));
        this.f1259e = ((E8.l) ((B8.i) this.f1263i.f1267b.f779a).f762a).b(new o(this, 1));
        this.f1260f = ((E8.l) ((B8.i) this.f1263i.f1267b.f779a).f762a).c(new o(this, 2));
        r rVar4 = this.f1263i;
        E8.o oVar = ((B8.i) rVar4.f1267b.f779a).f762a;
        n nVar = new n(this, rVar4, 0);
        E8.l lVar = (E8.l) oVar;
        lVar.getClass();
        this.f1261g = new E8.i(lVar, nVar);
        r rVar5 = this.f1263i;
        E8.o oVar2 = ((B8.i) rVar5.f1267b.f779a).f762a;
        n nVar2 = new n(this, rVar5, 1);
        E8.l lVar2 = (E8.l) oVar2;
        lVar2.getClass();
        this.f1262h = new E8.i(lVar2, nVar2);
    }

    public static LinkedHashMap c(LinkedHashMap linkedHashMap) throws IOException {
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(z.q(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Object key = entry.getKey();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Iterable<AbstractC2126a> iterable = (Iterable) entry.getValue();
            ArrayList arrayList = new ArrayList(AbstractC2000o.J(iterable, 10));
            for (AbstractC2126a abstractC2126a : iterable) {
                int iC = abstractC2126a.c();
                int iJ = A2.g.j(iC) + iC;
                if (iJ > 4096) {
                    iJ = 4096;
                }
                A2.g gVarT = A2.g.t(byteArrayOutputStream, iJ);
                gVarT.L(iC);
                abstractC2126a.f(gVarT);
                gVarT.n();
                arrayList.add(m7.q.f19267a);
            }
            linkedHashMap2.put(key, byteArrayOutputStream.toByteArray());
        }
        return linkedHashMap2;
    }

    public final Collection a(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        return !((Set) C7.a.N(this.f1261g, f1256j[0])).contains(c2061f) ? n7.u.l : (Collection) this.d.b(c2061f);
    }

    public final Collection b(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        return !((Set) C7.a.N(this.f1262h, f1256j[1])).contains(c2061f) ? n7.u.l : (Collection) this.f1259e.b(c2061f);
    }
}
