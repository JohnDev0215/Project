package q1;

import android.util.Log;
import java.util.Collections;
import java.util.List;
import o1.d;
import q1.f;
import u1.n;

/* JADX INFO: compiled from: SourceGenerator.java */
/* JADX INFO: loaded from: classes.dex */
class z implements f, d.a<Object>, f.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final g<?> f9137e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final f.a f9138g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f9139h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private c f9140i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Object f9141j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private volatile n.a<?> f9142k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private d f9143l;

    z(g<?> gVar, f.a aVar) {
        this.f9137e = gVar;
        this.f9138g = aVar;
    }

    private void g(Object obj) {
        long jB = k2.f.b();
        try {
            n1.d<X> dVarP = this.f9137e.p(obj);
            e eVar = new e(dVarP, obj, this.f9137e.k());
            this.f9143l = new d(this.f9142k.f9613a, this.f9137e.o());
            this.f9137e.d().a(this.f9143l, eVar);
            if (Log.isLoggable("SourceGenerator", 2)) {
                Log.v("SourceGenerator", "Finished encoding source to cache, key: " + this.f9143l + ", data: " + obj + ", encoder: " + dVarP + ", duration: " + k2.f.a(jB));
            }
            this.f9142k.f9615c.b();
            this.f9140i = new c(Collections.singletonList(this.f9142k.f9613a), this.f9137e, this);
        } catch (Throwable th) {
            this.f9142k.f9615c.b();
            throw th;
        }
    }

    private boolean h() {
        return this.f9139h < this.f9137e.g().size();
    }

    @Override // q1.f.a
    public void a(n1.f fVar, Object obj, o1.d<?> dVar, n1.a aVar, n1.f fVar2) {
        this.f9138g.a(fVar, obj, dVar, this.f9142k.f9615c.e(), fVar);
    }

    @Override // q1.f
    public boolean b() {
        Object obj = this.f9141j;
        if (obj != null) {
            this.f9141j = null;
            g(obj);
        }
        c cVar = this.f9140i;
        if (cVar != null && cVar.b()) {
            return true;
        }
        this.f9140i = null;
        this.f9142k = null;
        boolean z6 = false;
        while (!z6 && h()) {
            List<n.a<?>> listG = this.f9137e.g();
            int i7 = this.f9139h;
            this.f9139h = i7 + 1;
            this.f9142k = listG.get(i7);
            if (this.f9142k != null && (this.f9137e.e().c(this.f9142k.f9615c.e()) || this.f9137e.t(this.f9142k.f9615c.a()))) {
                this.f9142k.f9615c.c(this.f9137e.l(), this);
                z6 = true;
            }
        }
        return z6;
    }

    @Override // q1.f.a
    public void c() {
        throw new UnsupportedOperationException();
    }

    @Override // q1.f
    public void cancel() {
        n.a<?> aVar = this.f9142k;
        if (aVar != null) {
            aVar.f9615c.cancel();
        }
    }

    @Override // o1.d.a
    public void d(Exception exc) {
        this.f9138g.e(this.f9143l, exc, this.f9142k.f9615c, this.f9142k.f9615c.e());
    }

    @Override // q1.f.a
    public void e(n1.f fVar, Exception exc, o1.d<?> dVar, n1.a aVar) {
        this.f9138g.e(fVar, exc, dVar, this.f9142k.f9615c.e());
    }

    @Override // o1.d.a
    public void f(Object obj) {
        j jVarE = this.f9137e.e();
        if (obj == null || !jVarE.c(this.f9142k.f9615c.e())) {
            this.f9138g.a(this.f9142k.f9613a, obj, this.f9142k.f9615c, this.f9142k.f9615c.e(), this.f9143l);
        } else {
            this.f9141j = obj;
            this.f9138g.c();
        }
    }
}
