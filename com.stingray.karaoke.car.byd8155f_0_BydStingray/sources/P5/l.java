package P5;

import java.util.List;
import n7.AbstractC1998m;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ p f5373m;

    public /* synthetic */ l(p pVar, int i7) {
        this.l = i7;
        this.f5373m = pVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                m0.c cVar = (m0.c) obj2;
                ((y0.q) obj).a();
                this.f5373m.r((int) m0.c.d(cVar.f18880a), (int) m0.c.e(cVar.f18880a), true);
                return m7.q.f19267a;
            default:
                List list = (List) obj;
                List list2 = (List) obj2;
                A7.m.f(list, "old");
                A7.m.f(list2, "new");
                Object objA0 = AbstractC1998m.a0(list);
                p pVar = this.f5373m;
                Integer numValueOf = objA0 != null ? Integer.valueOf(pVar.k(objA0)) : null;
                Object objA02 = AbstractC1998m.a0(list2);
                return Boolean.valueOf(A7.m.a(numValueOf, objA02 != null ? Integer.valueOf(pVar.k(objA02)) : null) && list.size() == list2.size());
        }
    }
}
