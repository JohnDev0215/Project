package O;

import x3.AbstractC2673a;

/* JADX INFO: renamed from: O.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0479j implements e1.z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0.c f4968a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0480k f4969b;
    public long c = 0;

    public C0479j(g0.c cVar, InterfaceC0480k interfaceC0480k) {
        this.f4968a = cVar;
        this.f4969b = interfaceC0480k;
    }

    @Override // e1.z
    public final long a(a1.i iVar, long j3, a1.k kVar, long j10) {
        long jA = this.f4969b.a();
        if (!h6.e.m(jA)) {
            jA = this.c;
        }
        this.c = jA;
        return a1.h.d(a1.h.d(AbstractC2673a.d(iVar.f11035a, iVar.f11036b), AbstractC2673a.T(jA)), this.f4968a.a(j10, 0L, kVar));
    }
}
