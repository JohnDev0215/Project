package P7;

import A.F;
import Q7.D;
import Q7.H;
import T7.z;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import n7.AbstractC1999n;
import n7.w;
import o8.C2058c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class o implements H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final E8.l f5452a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final z f5453b;
    public B8.i c;
    public final E8.j d;

    public o(E8.l lVar, V7.b bVar, z zVar) {
        this.f5452a = lVar;
        this.f5453b = zVar;
        this.d = lVar.c(new F(this, 3));
    }

    @Override // Q7.H
    public final boolean a(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        E8.j jVar = this.d;
        Object obj = jVar.f1855m.get(c2058c);
        return ((obj == null || obj == E8.k.f1857m) ? d(c2058c) : (D) jVar.b(c2058c)) == null;
    }

    @Override // Q7.H
    public final void b(C2058c c2058c, ArrayList arrayList) {
        A7.m.f(c2058c, "fqName");
        O8.l.a(arrayList, this.d.b(c2058c));
    }

    @Override // Q7.E
    public final List c(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        return AbstractC1999n.E(this.d.b(c2058c));
    }

    public final C8.c d(C2058c c2058c) throws IOException {
        InputStream inputStreamP;
        A7.m.f(c2058c, "fqName");
        if (c2058c.h(N7.o.f4874i)) {
            C8.a.f1016m.getClass();
            inputStreamP = C8.d.p(C8.a.a(c2058c));
        } else {
            inputStreamP = null;
        }
        if (inputStreamP != null) {
            return com.bumptech.glide.d.u(c2058c, this.f5452a, this.f5453b, inputStreamP);
        }
        return null;
    }

    @Override // Q7.E
    public final Collection r(C2058c c2058c, InterfaceC2782k interfaceC2782k) {
        A7.m.f(c2058c, "fqName");
        A7.m.f(interfaceC2782k, "nameFilter");
        return w.l;
    }
}
