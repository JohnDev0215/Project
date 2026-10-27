package y;

import n0.AbstractC1887p;
import n0.C1855E;
import n0.C1880i;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: y.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2713s extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23995m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f23996n;
    public final /* synthetic */ Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2713s(Object obj, int i7, Object obj2) {
        super(1);
        this.f23995m = i7;
        this.o = obj;
        this.f23996n = obj2;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f23995m) {
            case 0:
                E0.H h10 = (E0.H) obj;
                h10.a();
                p0.d.m(h10, ((C1855E) this.o).f19375a, (AbstractC1887p) this.f23996n, 0.0f, null, 60);
                break;
            case 1:
                E0.H h11 = (E0.H) obj;
                h11.a();
                p0.d.m(h11, (C1880i) this.o, (AbstractC1887p) this.f23996n, 0.0f, null, 60);
                break;
            default:
                ((C.k) this.o).b((C.j) this.f23996n);
                break;
        }
        return m7.q.f19267a;
    }
}
