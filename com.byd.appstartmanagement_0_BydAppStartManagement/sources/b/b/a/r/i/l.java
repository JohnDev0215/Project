package b.b.a.r.i;

import b.b.a.o;
import b.b.a.p;
import java.io.IOException;

/* JADX INFO: compiled from: TreeTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l<T> extends o<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.a.n<T> f466a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.b.a.h<T> f467b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b.b.a.d f468c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b.b.a.s.a<T> f469d;
    public final p e;
    public final l<T>.b f = new b();
    public o<T> g;

    /* JADX INFO: compiled from: TreeTypeAdapter.java */
    public final class b implements b.b.a.m, b.b.a.g {
        public b() {
        }
    }

    public l(b.b.a.n<T> nVar, b.b.a.h<T> hVar, b.b.a.d dVar, b.b.a.s.a<T> aVar, p pVar) {
        this.f466a = nVar;
        this.f467b = hVar;
        this.f468c = dVar;
        this.f469d = aVar;
        this.e = pVar;
    }

    @Override // b.b.a.o
    public T b(b.b.a.t.a aVar) throws IOException {
        if (this.f467b == null) {
            return e().b(aVar);
        }
        b.b.a.i iVarA = b.b.a.r.g.a(aVar);
        if (iVarA.e()) {
            return null;
        }
        return this.f467b.a(iVarA, this.f469d.f(), this.f);
    }

    @Override // b.b.a.o
    public void d(b.b.a.t.b bVar, T t) throws IOException {
        b.b.a.n<T> nVar = this.f466a;
        if (nVar == null) {
            e().d(bVar, t);
        } else if (t == null) {
            bVar.n();
        } else {
            b.b.a.r.g.b(nVar.a(t, this.f469d.f(), this.f), bVar);
        }
    }

    public final o<T> e() {
        o<T> oVar = this.g;
        if (oVar != null) {
            return oVar;
        }
        o<T> oVarM = this.f468c.m(this.e, this.f469d);
        this.g = oVarM;
        return oVarM;
    }
}
