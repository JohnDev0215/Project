package F8;

/* JADX INFO: renamed from: F8.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0281p extends T {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final T f2476b;
    public final T c;

    public C0281p(T t10, T t11) {
        this.f2476b = t10;
        this.c = t11;
    }

    @Override // F8.T
    public final boolean a() {
        return this.f2476b.a() || this.c.a();
    }

    @Override // F8.T
    public final boolean b() {
        return this.f2476b.b() || this.c.b();
    }

    @Override // F8.T
    public final R7.h c(R7.h hVar) {
        A7.m.f(hVar, "annotations");
        return this.c.c(this.f2476b.c(hVar));
    }

    @Override // F8.T
    public final O d(AbstractC0287w abstractC0287w) {
        O oD = this.f2476b.d(abstractC0287w);
        return oD == null ? this.c.d(abstractC0287w) : oD;
    }

    @Override // F8.T
    public final AbstractC0287w f(int i7, AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "topLevelType");
        A7.k.p(i7, "position");
        return this.c.f(i7, this.f2476b.f(i7, abstractC0287w));
    }
}
