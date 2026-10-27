package F8;

import A.I0;

/* JADX INFO: loaded from: classes.dex */
public final class F extends O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Q7.Q f2435a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f2436b;

    public F(Q7.Q q4) {
        A7.m.f(q4, "typeParameter");
        this.f2435a = q4;
        this.f2436b = h6.r.n(m7.g.l, new I0(this, 16));
    }

    @Override // F8.O
    public final int a() {
        return 3;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // F8.O
    public final AbstractC0287w b() {
        return (AbstractC0287w) this.f2436b.getValue();
    }

    @Override // F8.O
    public final boolean c() {
        return true;
    }

    @Override // F8.O
    public final O d(G8.f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        return this;
    }
}
