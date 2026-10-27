package N7;

import Q7.E;
import T.C0746i0;
import T7.C0793l;
import T7.w;
import T7.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC2000o;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class l extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4813m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ z f4814n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(z zVar, int i7) {
        super(0);
        this.f4813m = i7;
        this.f4814n = zVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f4813m) {
            case 0:
                return ((w) this.f4814n.n0(o.f4873h)).f8916s;
            case 1:
                return new P7.h(this.f4814n);
            default:
                z zVar = this.f4814n;
                C0746i0 c0746i0 = zVar.f8924s;
                if (c0746i0 == null) {
                    StringBuilder sb = new StringBuilder("Dependencies of module ");
                    String str = zVar.getName().l;
                    A7.m.e(str, "name.toString()");
                    sb.append(str);
                    sb.append(" were not set before querying module content");
                    throw new AssertionError(sb.toString());
                }
                zVar.b1();
                List list = c0746i0.l;
                list.contains(zVar);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((z) it.next()).getClass();
                }
                ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    E e10 = ((z) it2.next()).f8925t;
                    A7.m.c(e10);
                    arrayList.add(e10);
                }
                return new C0793l("CompositeProvider@ModuleDescriptor for " + zVar.getName(), arrayList);
        }
    }
}
