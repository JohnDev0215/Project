package b.c.a.r.i;

import b.c.a.o;
import b.c.a.p;
import java.io.IOException;

/* JADX INFO: compiled from: TreeTypeAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class l<T> extends o<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.c.a.n<T> f2285a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.c.a.h<T> f2286b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b.c.a.d f2287c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b.c.a.s.a<T> f2288d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final p f2289e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l<T>.b f2290f = new b();
    public o<T> g;

    /* JADX INFO: compiled from: TreeTypeAdapter.java */
    public final class b implements b.c.a.m, b.c.a.g {
        public b() {
        }
    }

    public l(b.c.a.n<T> nVar, b.c.a.h<T> hVar, b.c.a.d dVar, b.c.a.s.a<T> aVar, p pVar) {
        this.f2285a = nVar;
        this.f2286b = hVar;
        this.f2287c = dVar;
        this.f2288d = aVar;
        this.f2289e = pVar;
    }

    @Override // b.c.a.o
    public T b(b.c.a.t.a aVar) throws IOException {
        if (this.f2286b == null) {
            return e().b(aVar);
        }
        b.c.a.i iVarA = b.c.a.r.g.a(aVar);
        if (iVarA.e()) {
            return null;
        }
        return this.f2286b.a(iVarA, this.f2288d.f(), this.f2290f);
    }

    @Override // b.c.a.o
    public void d(b.c.a.t.b bVar, T t) throws IOException {
        b.c.a.n<T> nVar = this.f2285a;
        if (nVar == null) {
            e().d(bVar, t);
        } else if (t == null) {
            bVar.s();
        } else {
            b.c.a.r.g.b(nVar.a(t, this.f2288d.f(), this.f2290f), bVar);
        }
    }

    public final o<T> e() {
        o<T> oVar = this.g;
        if (oVar != null) {
            return oVar;
        }
        o<T> oVarM = this.f2287c.m(this.f2289e, this.f2288d);
        this.g = oVarM;
        return oVarM;
    }
}
