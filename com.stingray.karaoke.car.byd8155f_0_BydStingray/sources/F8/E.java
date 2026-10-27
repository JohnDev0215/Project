package F8;

/* JADX INFO: loaded from: classes.dex */
public final class E extends O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A f2434a;

    public E(N7.i iVar) {
        A7.m.f(iVar, "kotlinBuiltIns");
        this.f2434a = iVar.n();
    }

    @Override // F8.O
    public final int a() {
        return 3;
    }

    @Override // F8.O
    public final AbstractC0287w b() {
        return this.f2434a;
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
