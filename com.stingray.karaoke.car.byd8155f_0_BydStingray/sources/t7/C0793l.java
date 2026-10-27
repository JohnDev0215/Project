package T7;

import Q7.AbstractC0669w;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import o8.C2058c;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: T7.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0793l implements Q7.H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f8852a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f8853b;

    public C0793l(String str, List list) {
        A7.m.f(str, "debugName");
        this.f8852a = list;
        this.f8853b = str;
        list.size();
        AbstractC1998m.D0(list).size();
    }

    @Override // Q7.H
    public final boolean a(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        List list = this.f8852a;
        if (list != null && list.isEmpty()) {
            return true;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (!AbstractC0669w.h((Q7.E) it.next(), c2058c)) {
                return false;
            }
        }
        return true;
    }

    @Override // Q7.H
    public final void b(C2058c c2058c, ArrayList arrayList) {
        A7.m.f(c2058c, "fqName");
        Iterator it = this.f8852a.iterator();
        while (it.hasNext()) {
            AbstractC0669w.b((Q7.E) it.next(), c2058c, arrayList);
        }
    }

    @Override // Q7.E
    public final List c(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f8852a.iterator();
        while (it.hasNext()) {
            AbstractC0669w.b((Q7.E) it.next(), c2058c, arrayList);
        }
        return AbstractC1998m.z0(arrayList);
    }

    @Override // Q7.E
    public final Collection r(C2058c c2058c, InterfaceC2782k interfaceC2782k) {
        A7.m.f(c2058c, "fqName");
        A7.m.f(interfaceC2782k, "nameFilter");
        HashSet hashSet = new HashSet();
        Iterator it = this.f8852a.iterator();
        while (it.hasNext()) {
            hashSet.addAll(((Q7.E) it.next()).r(c2058c, interfaceC2782k));
        }
        return hashSet;
    }

    public final String toString() {
        return this.f8853b;
    }
}
