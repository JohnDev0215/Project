package c8;

import A7.m;
import E8.j;
import N0.q;
import N7.n;
import P8.f;
import P8.g;
import P8.k;
import P8.r;
import R7.h;
import S0.p;
import W7.C0840d;
import f8.InterfaceC1266b;
import java.util.Iterator;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import o8.C2058c;
import o8.C2061f;

/* JADX INFO: renamed from: c8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1038c implements h {
    public final q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC1266b f12432m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f12433n;
    public final j o;

    public C1038c(q qVar, InterfaceC1266b interfaceC1266b, boolean z6) {
        m.f(qVar, "c");
        m.f(interfaceC1266b, "annotationOwner");
        this.l = qVar;
        this.f12432m = interfaceC1266b;
        this.f12433n = z6;
        this.o = ((C1036a) qVar.f4399m).f12411a.c(new p(this, 9));
    }

    @Override // R7.h
    public final R7.b e(C2058c c2058c) {
        R7.b bVar;
        m.f(c2058c, "fqName");
        InterfaceC1266b interfaceC1266b = this.f12432m;
        C0840d c0840dA = interfaceC1266b.a(c2058c);
        if (c0840dA != null && (bVar = (R7.b) this.o.b(c0840dA)) != null) {
            return bVar;
        }
        C2061f c2061f = a8.c.f11205a;
        return a8.c.a(c2058c, interfaceC1266b, this.l);
    }

    @Override // R7.h
    public final boolean isEmpty() {
        return this.f12432m.k().isEmpty();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        InterfaceC1266b interfaceC1266b = this.f12432m;
        r rVarG0 = P8.m.g0(AbstractC1998m.S(interfaceC1266b.k()), this.o);
        C2061f c2061f = a8.c.f11205a;
        return new f(new g(P8.m.c0(AbstractC1996k.y(new k[]{rVarG0, AbstractC1996k.y(new Object[]{a8.c.a(n.f4855m, interfaceC1266b, this.l)})})), false, new A3.c(18)));
    }

    @Override // R7.h
    public final boolean k(C2058c c2058c) {
        return P8.n.A(this, c2058c);
    }
}
