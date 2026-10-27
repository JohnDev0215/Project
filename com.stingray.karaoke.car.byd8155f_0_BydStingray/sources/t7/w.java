package T7;

import Q7.InterfaceC0657j;
import Q7.InterfaceC0659l;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class w extends AbstractC0794m implements Q7.I {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f8912t;
    public final z o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C2058c f8913p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final E8.i f8914q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final E8.i f8915r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final y8.j f8916s;

    static {
        A7.B b10 = A7.A.f443a;
        f8912t = new H7.w[]{b10.h(new A7.s(b10.b(w.class), "fragments", "getFragments()Ljava/util/List;")), b10.h(new A7.s(b10.b(w.class), "empty", "getEmpty()Z"))};
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(z zVar, C2058c c2058c, E8.l lVar) {
        super(R7.g.f7535a, c2058c.g());
        A7.m.f(zVar, "module");
        A7.m.f(c2058c, "fqName");
        A7.m.f(lVar, "storageManager");
        this.o = zVar;
        this.f8913p = c2058c;
        this.f8914q = new E8.i(lVar, new v(this, 1));
        this.f8915r = new E8.i(lVar, new v(this, 0));
        this.f8916s = new y8.j(lVar, new v(this, 2));
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.q(this, obj);
    }

    public final boolean equals(Object obj) {
        Q7.I i7 = obj instanceof Q7.I ? (Q7.I) obj : null;
        if (i7 == null) {
            return false;
        }
        w wVar = (w) i7;
        return A7.m.a(this.f8913p, wVar.f8913p) && A7.m.a(this.o, wVar.o);
    }

    public final int hashCode() {
        return this.f8913p.hashCode() + (this.o.hashCode() * 31);
    }

    @Override // Q7.InterfaceC0657j
    public final InterfaceC0657j o() {
        C2058c c2058c = this.f8913p;
        if (c2058c.d()) {
            return null;
        }
        C2058c c2058cE = c2058c.e();
        A7.m.e(c2058cE, "fqName.parent()");
        return this.o.n0(c2058cE);
    }
}
