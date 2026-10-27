package a8;

import A7.m;
import A7.o;
import F8.AbstractC0287w;
import N7.n;
import Q7.InterfaceC0671y;
import T7.Q;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class d extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final d f11207m = new d(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        InterfaceC0671y interfaceC0671y = (InterfaceC0671y) obj;
        m.f(interfaceC0671y, "module");
        Q qV = l9.d.V(c.f11206b, interfaceC0671y.l().i(n.f4861t));
        AbstractC0287w abstractC0287wB = qV != null ? qV.b() : null;
        return abstractC0287wB == null ? H8.i.c(H8.h.f2878N, new String[0]) : abstractC0287wB;
    }
}
