package y8;

import A7.A;
import A7.B;
import H7.w;
import Q7.InterfaceC0654g;
import Q7.L;
import T7.K;
import java.util.Collection;
import java.util.List;
import n7.AbstractC1998m;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class r extends o {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ w[] f24217e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final D8.k f24218b;
    public final E8.i c;
    public final E8.i d;

    static {
        B b10 = A.f443a;
        f24217e = new w[]{b10.h(new A7.s(b10.b(r.class), "functions", "getFunctions()Ljava/util/List;")), b10.h(new A7.s(b10.b(r.class), "properties", "getProperties()Ljava/util/List;"))};
    }

    public r(E8.o oVar, D8.k kVar) {
        A7.m.f(oVar, "storageManager");
        this.f24218b = kVar;
        E8.l lVar = (E8.l) oVar;
        this.c = new E8.i(lVar, new q(this, 0));
        this.d = new E8.i(lVar, new q(this, 1));
    }

    @Override // y8.o, y8.n
    public final Collection a(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        List list = (List) C7.a.N(this.c, f24217e[0]);
        O8.g gVar = new O8.g();
        for (Object obj : list) {
            if (A7.m.a(((K) obj).getName(), c2061f)) {
                gVar.add(obj);
            }
        }
        return gVar;
    }

    @Override // y8.o, y8.n
    public final Collection b(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        List list = (List) C7.a.N(this.d, f24217e[1]);
        O8.g gVar = new O8.g();
        for (Object obj : list) {
            if (A7.m.a(((L) obj).getName(), c2061f)) {
                gVar.add(obj);
            }
        }
        return gVar;
    }

    @Override // y8.o, y8.p
    public final InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        A7.m.f(bVar, "location");
        return null;
    }

    @Override // y8.o, y8.p
    public final Collection d(f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        E8.i iVar = this.c;
        w[] wVarArr = f24217e;
        return AbstractC1998m.n0((List) C7.a.N(iVar, wVarArr[0]), (List) C7.a.N(this.d, wVarArr[1]));
    }
}
