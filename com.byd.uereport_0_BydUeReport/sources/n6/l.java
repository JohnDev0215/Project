package n6;

import java.io.IOException;
import k6.o;
import k6.p;

/* JADX INFO: compiled from: TreeTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l<T> extends o<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k6.n<T> f6602a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k6.h<T> f6603b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final k6.d f6604c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final p6.a<T> f6605d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final p f6606e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l<T>.b f6607f = new b();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public o<T> f6608g;

    /* JADX INFO: compiled from: TreeTypeAdapter.java */
    public final class b implements k6.m, k6.g {
        public b() {
        }
    }

    public l(k6.n<T> nVar, k6.h<T> hVar, k6.d dVar, p6.a<T> aVar, p pVar) {
        this.f6602a = nVar;
        this.f6603b = hVar;
        this.f6604c = dVar;
        this.f6605d = aVar;
        this.f6606e = pVar;
    }

    @Override // k6.o
    public T b(q6.a aVar) throws IOException {
        if (this.f6603b == null) {
            return e().b(aVar);
        }
        k6.i iVarA = m6.g.a(aVar);
        if (iVarA.e()) {
            return null;
        }
        return this.f6603b.a(iVarA, this.f6605d.f(), this.f6607f);
    }

    @Override // k6.o
    public void d(q6.b bVar, T t7) throws IOException {
        k6.n<T> nVar = this.f6602a;
        if (nVar == null) {
            e().d(bVar, t7);
        } else if (t7 == null) {
            bVar.B();
        } else {
            m6.g.b(nVar.a(t7, this.f6605d.f(), this.f6607f), bVar);
        }
    }

    public final o<T> e() {
        o<T> oVar = this.f6608g;
        if (oVar != null) {
            return oVar;
        }
        o<T> oVarM = this.f6604c.m(this.f6606e, this.f6605d);
        this.f6608g = oVarM;
        return oVarM;
    }
}
