package Q7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import o8.C2058c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class G implements H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f7176a;

    public G(ArrayList arrayList) {
        this.f7176a = arrayList;
    }

    @Override // Q7.H
    public final boolean a(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        ArrayList arrayList = this.f7176a;
        if (arrayList.isEmpty()) {
            return true;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (A7.m.a(((T7.B) ((D) it.next())).f8767q, c2058c)) {
                return false;
            }
        }
        return true;
    }

    @Override // Q7.H
    public final void b(C2058c c2058c, ArrayList arrayList) {
        A7.m.f(c2058c, "fqName");
        for (Object obj : this.f7176a) {
            if (A7.m.a(((T7.B) ((D) obj)).f8767q, c2058c)) {
                arrayList.add(obj);
            }
        }
    }

    @Override // Q7.E
    public final List c(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        ArrayList arrayList = this.f7176a;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (A7.m.a(((T7.B) ((D) obj)).f8767q, c2058c)) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    @Override // Q7.E
    public final Collection r(C2058c c2058c, InterfaceC2782k interfaceC2782k) {
        A7.m.f(c2058c, "fqName");
        A7.m.f(interfaceC2782k, "nameFilter");
        return P8.m.i0(new P8.g(P8.m.g0(AbstractC1998m.S(this.f7176a), r.o), true, new F(c2058c, 0)));
    }
}
