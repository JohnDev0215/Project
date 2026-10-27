package c.b.a.t.i;

import c.b.a.o;
import c.b.a.p;
import c.b.a.q;
import c.b.a.r;
import java.io.IOException;

/* JADX INFO: compiled from: TreeTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class l<T> extends q<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p<T> f2995a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.a.j<T> f2996b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.b.a.e f2997c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c.b.a.u.a<T> f2998d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final r f2999e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l<T>.b f3000f = new b();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public q<T> f3001g;

    /* JADX INFO: compiled from: TreeTypeAdapter.java */
    public final class b implements o, c.b.a.i {
        public b() {
        }
    }

    /* JADX INFO: compiled from: TreeTypeAdapter.java */
    public static final class c implements r {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c.b.a.u.a<?> f3003c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f3004d;
        public final Class<?> s;
        public final p<?> x;
        public final c.b.a.j<?> y;

        public c(Object obj, c.b.a.u.a<?> aVar, boolean z, Class<?> cls) {
            p<?> pVar = obj instanceof p ? (p) obj : null;
            this.x = pVar;
            c.b.a.j<?> jVar = obj instanceof c.b.a.j ? (c.b.a.j) obj : null;
            this.y = jVar;
            c.b.a.t.a.a((pVar == null && jVar == null) ? false : true);
            this.f3003c = aVar;
            this.f3004d = z;
            this.s = cls;
        }

        @Override // c.b.a.r
        public <T> q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
            c.b.a.u.a<?> aVar2 = this.f3003c;
            if (aVar2 != null ? aVar2.equals(aVar) || (this.f3004d && this.f3003c.e() == aVar.c()) : this.s.isAssignableFrom(aVar.c())) {
                return new l(this.x, this.y, eVar, aVar, this);
            }
            return null;
        }
    }

    public l(p<T> pVar, c.b.a.j<T> jVar, c.b.a.e eVar, c.b.a.u.a<T> aVar, r rVar) {
        this.f2995a = pVar;
        this.f2996b = jVar;
        this.f2997c = eVar;
        this.f2998d = aVar;
        this.f2999e = rVar;
    }

    public static r f(c.b.a.u.a<?> aVar, Object obj) {
        return new c(obj, aVar, false, null);
    }

    @Override // c.b.a.q
    public T b(c.b.a.v.a aVar) throws IOException {
        if (this.f2996b == null) {
            return e().b(aVar);
        }
        c.b.a.k kVarA = c.b.a.t.g.a(aVar);
        if (kVarA.f()) {
            return null;
        }
        return this.f2996b.a(kVarA, this.f2998d.e(), this.f3000f);
    }

    @Override // c.b.a.q
    public void d(c.b.a.v.b bVar, T t) throws IOException {
        p<T> pVar = this.f2995a;
        if (pVar == null) {
            e().d(bVar, t);
        } else if (t == null) {
            bVar.P();
        } else {
            c.b.a.t.g.b(pVar.b(t, this.f2998d.e(), this.f3000f), bVar);
        }
    }

    public final q<T> e() {
        q<T> qVar = this.f3001g;
        if (qVar != null) {
            return qVar;
        }
        q<T> qVarO = this.f2997c.o(this.f2999e, this.f2998d);
        this.f3001g = qVarO;
        return qVarO;
    }
}
