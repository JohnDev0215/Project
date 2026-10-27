package c.a.m.e.a;

import c.a.g;

/* JADX INFO: loaded from: classes.dex */
public final class c<T> extends a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.a.l.d<? super c.a.j.b> f1837b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.a.l.a f1838c;

    public c(c.a.c<T> cVar, c.a.l.d<? super c.a.j.b> dVar, c.a.l.a aVar) {
        super(cVar);
        this.f1837b = dVar;
        this.f1838c = aVar;
    }

    @Override // c.a.c
    protected void j(g<? super T> gVar) {
        this.f1834a.a(new c.a.m.d.b(gVar, this.f1837b, this.f1838c));
    }
}
