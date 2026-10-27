package c.b.b.r.i;

import c.b.b.o;
import c.b.b.p;
import java.io.IOException;

/* JADX INFO: compiled from: TreeTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class l<T> extends o<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.b.b.n<T> f4260a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.b.h<T> f4261b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.b.b.d f4262c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c.b.b.s.a<T> f4263d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final p f4264e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l<T>.b f4265f = new b();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public o<T> f4266g;

    /* JADX INFO: compiled from: TreeTypeAdapter.java */
    public final class b implements c.b.b.m, c.b.b.g {
        public b() {
        }
    }

    public l(c.b.b.n<T> nVar, c.b.b.h<T> hVar, c.b.b.d dVar, c.b.b.s.a<T> aVar, p pVar) {
        this.f4260a = nVar;
        this.f4261b = hVar;
        this.f4262c = dVar;
        this.f4263d = aVar;
        this.f4264e = pVar;
    }

    @Override // c.b.b.o
    public T b(c.b.b.t.a aVar) throws IOException {
        if (this.f4261b == null) {
            return e().b(aVar);
        }
        c.b.b.i iVarA = c.b.b.r.g.a(aVar);
        if (iVarA.e()) {
            return null;
        }
        return this.f4261b.a(iVarA, this.f4263d.f(), this.f4265f);
    }

    @Override // c.b.b.o
    public void d(c.b.b.t.b bVar, T t) throws IOException {
        c.b.b.n<T> nVar = this.f4260a;
        if (nVar == null) {
            e().d(bVar, t);
        } else if (t == null) {
            bVar.Z();
        } else {
            c.b.b.r.g.b(nVar.a(t, this.f4263d.f(), this.f4265f), bVar);
        }
    }

    public final o<T> e() {
        o<T> oVar = this.f4266g;
        if (oVar != null) {
            return oVar;
        }
        o<T> oVarM = this.f4262c.m(this.f4264e, this.f4263d);
        this.f4266g = oVarM;
        return oVarM;
    }
}
