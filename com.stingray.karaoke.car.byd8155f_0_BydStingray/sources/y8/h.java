package y8;

import A7.A;
import A7.B;
import H7.w;
import Q7.L;
import T7.AbstractC0783b;
import T7.K;
import e0.C1183w;
import java.util.Collection;
import java.util.List;
import n7.u;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends o {
    public static final /* synthetic */ w[] d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AbstractC0783b f24205b;
    public final E8.i c;

    static {
        B b10 = A.f443a;
        d = new w[]{b10.h(new A7.s(b10.b(h.class), "allDescriptors", "getAllDescriptors()Ljava/util/List;"))};
    }

    public h(E8.l lVar, AbstractC0783b abstractC0783b) {
        this.f24205b = abstractC0783b;
        this.c = new E8.i(lVar, new C1183w(this, 19));
    }

    @Override // y8.o, y8.n
    public final Collection a(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        List list = (List) C7.a.N(this.c, d[0]);
        O8.g gVar = new O8.g();
        for (Object obj : list) {
            if ((obj instanceof K) && A7.m.a(((K) obj).getName(), c2061f)) {
                gVar.add(obj);
            }
        }
        return gVar;
    }

    @Override // y8.o, y8.n
    public final Collection b(C2061f c2061f, Y7.b bVar) {
        A7.m.f(c2061f, "name");
        List list = (List) C7.a.N(this.c, d[0]);
        O8.g gVar = new O8.g();
        for (Object obj : list) {
            if ((obj instanceof L) && A7.m.a(((L) obj).getName(), c2061f)) {
                gVar.add(obj);
            }
        }
        return gVar;
    }

    @Override // y8.o, y8.p
    public final Collection d(f fVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(fVar, "kindFilter");
        A7.m.f(interfaceC2782k, "nameFilter");
        return !fVar.a(f.f24197n.f24203b) ? u.l : (List) C7.a.N(this.c, d[0]);
    }

    public abstract List h();
}
