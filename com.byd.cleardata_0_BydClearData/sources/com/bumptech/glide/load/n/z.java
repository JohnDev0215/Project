package com.bumptech.glide.load.n;

import android.util.Log;
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.n.f;
import com.bumptech.glide.load.o.n;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class z implements f, f.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g<?> f2201b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final f.a f2202c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2203d;
    private c e;
    private Object f;
    private volatile n.a<?> g;
    private d h;

    class a implements d.a<Object> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ n.a f2204b;

        a(n.a aVar) {
            this.f2204b = aVar;
        }

        @Override // com.bumptech.glide.load.data.d.a
        public void d(Exception exc) {
            if (z.this.g(this.f2204b)) {
                z.this.i(this.f2204b, exc);
            }
        }

        @Override // com.bumptech.glide.load.data.d.a
        public void f(Object obj) {
            if (z.this.g(this.f2204b)) {
                z.this.h(this.f2204b, obj);
            }
        }
    }

    z(g<?> gVar, f.a aVar) {
        this.f2201b = gVar;
        this.f2202c = aVar;
    }

    private void e(Object obj) {
        long jB = com.bumptech.glide.t.f.b();
        try {
            com.bumptech.glide.load.d<X> dVarP = this.f2201b.p(obj);
            e eVar = new e(dVarP, obj, this.f2201b.k());
            this.h = new d(this.g.f2250a, this.f2201b.o());
            this.f2201b.d().a(this.h, eVar);
            if (Log.isLoggable("SourceGenerator", 2)) {
                Log.v("SourceGenerator", "Finished encoding source to cache, key: " + this.h + ", data: " + obj + ", encoder: " + dVarP + ", duration: " + com.bumptech.glide.t.f.a(jB));
            }
            this.g.f2252c.b();
            this.e = new c(Collections.singletonList(this.g.f2250a), this.f2201b, this);
        } catch (Throwable th) {
            this.g.f2252c.b();
            throw th;
        }
    }

    private boolean f() {
        return this.f2203d < this.f2201b.g().size();
    }

    private void j(n.a<?> aVar) {
        this.g.f2252c.e(this.f2201b.l(), new a(aVar));
    }

    @Override // com.bumptech.glide.load.n.f
    public boolean a() {
        Object obj = this.f;
        if (obj != null) {
            this.f = null;
            e(obj);
        }
        c cVar = this.e;
        if (cVar != null && cVar.a()) {
            return true;
        }
        this.e = null;
        this.g = null;
        boolean z = false;
        while (!z && f()) {
            List<n.a<?>> listG = this.f2201b.g();
            int i = this.f2203d;
            this.f2203d = i + 1;
            this.g = listG.get(i);
            if (this.g != null && (this.f2201b.e().c(this.g.f2252c.c()) || this.f2201b.t(this.g.f2252c.a()))) {
                j(this.g);
                z = true;
            }
        }
        return z;
    }

    @Override // com.bumptech.glide.load.n.f.a
    public void b() {
        throw new UnsupportedOperationException();
    }

    @Override // com.bumptech.glide.load.n.f.a
    public void c(com.bumptech.glide.load.g gVar, Object obj, com.bumptech.glide.load.data.d<?> dVar, com.bumptech.glide.load.a aVar, com.bumptech.glide.load.g gVar2) {
        this.f2202c.c(gVar, obj, dVar, this.g.f2252c.c(), gVar);
    }

    @Override // com.bumptech.glide.load.n.f
    public void cancel() {
        n.a<?> aVar = this.g;
        if (aVar != null) {
            aVar.f2252c.cancel();
        }
    }

    @Override // com.bumptech.glide.load.n.f.a
    public void d(com.bumptech.glide.load.g gVar, Exception exc, com.bumptech.glide.load.data.d<?> dVar, com.bumptech.glide.load.a aVar) {
        this.f2202c.d(gVar, exc, dVar, this.g.f2252c.c());
    }

    boolean g(n.a<?> aVar) {
        n.a<?> aVar2 = this.g;
        return aVar2 != null && aVar2 == aVar;
    }

    void h(n.a<?> aVar, Object obj) {
        j jVarE = this.f2201b.e();
        if (obj != null && jVarE.c(aVar.f2252c.c())) {
            this.f = obj;
            this.f2202c.b();
        } else {
            f.a aVar2 = this.f2202c;
            com.bumptech.glide.load.g gVar = aVar.f2250a;
            com.bumptech.glide.load.data.d<?> dVar = aVar.f2252c;
            aVar2.c(gVar, obj, dVar, dVar.c(), this.h);
        }
    }

    void i(n.a<?> aVar, Exception exc) {
        f.a aVar2 = this.f2202c;
        d dVar = this.h;
        com.bumptech.glide.load.data.d<?> dVar2 = aVar.f2252c;
        aVar2.d(dVar, exc, dVar2, dVar2.c());
    }
}
