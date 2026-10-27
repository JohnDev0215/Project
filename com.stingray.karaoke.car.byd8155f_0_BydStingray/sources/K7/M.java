package K7;

import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class M extends k0 implements InterfaceC2786o {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final N f3738p;

    public M(N n10) {
        A7.m.f(n10, "property");
        this.f3738p = n10;
    }

    @Override // H7.o
    public final H7.w f() {
        return this.f3738p;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, m7.f] */
    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws I7.a {
        ((M) this.f3738p.f3739u.getValue()).h(obj, obj2, obj3);
        return m7.q.f19267a;
    }

    @Override // K7.g0
    public final m0 t() {
        return this.f3738p;
    }
}
