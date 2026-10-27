package K;

import A.C0004b;
import e1.C1190c;
import java.util.ArrayList;
import java.util.List;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class K0 implements C0.K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f3177b;
    public final Object c;

    public /* synthetic */ K0(Object obj, int i7, Object obj2) {
        this.f3176a = i7;
        this.f3177b = obj;
        this.c = obj2;
    }

    @Override // C0.K
    public final C0.L h(C0.M m10, List list, long j3) {
        switch (this.f3176a) {
            case 0:
                ArrayList arrayList = new ArrayList(list.size());
                int size = list.size();
                for (int i7 = 0; i7 < size; i7++) {
                    Object obj = list.get(i7);
                    if (!(((C0.J) obj).i() instanceof M0)) {
                        arrayList.add(obj);
                    }
                }
                List list2 = (List) ((InterfaceC2772a) this.c).a();
                ArrayList arrayList2 = null;
                if (list2 != null) {
                    ArrayList arrayList3 = new ArrayList(list2.size());
                    int size2 = list2.size();
                    for (int i10 = 0; i10 < size2; i10++) {
                        m0.d dVar = (m0.d) list2.get(i10);
                        m7.i iVar = dVar != null ? new m7.i(((C0.J) arrayList.get(i10)).b(io.sentry.config.a.f((int) Math.floor(dVar.c()), (int) Math.floor(dVar.b()), 5)), new a1.h(AbstractC2673a.d(Math.round(dVar.f18882a), Math.round(dVar.f18883b)))) : null;
                        if (iVar != null) {
                            arrayList3.add(iVar);
                        }
                    }
                    arrayList2 = arrayList3;
                }
                ArrayList arrayList4 = new ArrayList(list.size());
                int size3 = list.size();
                for (int i11 = 0; i11 < size3; i11++) {
                    Object obj2 = list.get(i11);
                    if (((C0.J) obj2).i() instanceof M0) {
                        arrayList4.add(obj2);
                    }
                }
                return m10.m0(a1.a.h(j3), a1.a.g(j3), n7.v.l, new C0004b(arrayList2, 20, X.l(arrayList4, (InterfaceC2772a) this.f3177b)));
            default:
                ((e1.x) this.f3177b).setParentLayoutDirection((a1.k) this.c);
                return m10.m0(0, 0, n7.v.l, C1190c.f13767p);
        }
    }
}
