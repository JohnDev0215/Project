package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.e;
import com.bumptech.glide.load.i.d;
import com.bumptech.glide.load.j.n;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: SourceGenerator.java */
/* JADX INFO: loaded from: classes.dex */
class w implements e, e.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f<?> f2839b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e.a f2840c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2841d;
    private b e;
    private Object f;
    private volatile n.a<?> g;
    private c h;

    /* JADX INFO: compiled from: SourceGenerator.java */
    class a implements d.a<Object> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ n.a f2842b;

        a(n.a aVar) {
            this.f2842b = aVar;
        }

        @Override // com.bumptech.glide.load.i.d.a
        public void c(Exception exc) {
            if (w.this.g(this.f2842b)) {
                w.this.i(this.f2842b, exc);
            }
        }

        @Override // com.bumptech.glide.load.i.d.a
        public void d(Object obj) {
            if (w.this.g(this.f2842b)) {
                w.this.h(this.f2842b, obj);
            }
        }
    }

    w(f<?> fVar, e.a aVar) {
        this.f2839b = fVar;
        this.f2840c = aVar;
    }

    private void d(Object obj) {
        long jB = com.bumptech.glide.m.f.b();
        try {
            com.bumptech.glide.load.a<X> aVarP = this.f2839b.p(obj);
            d dVar = new d(aVarP, obj, this.f2839b.k());
            this.h = new c(this.g.f2996a, this.f2839b.o());
            this.f2839b.d().a(this.h, dVar);
            if (Log.isLoggable("SourceGenerator", 2)) {
                Log.v("SourceGenerator", "Finished encoding source to cache, key: " + this.h + ", data: " + obj + ", encoder: " + aVarP + ", duration: " + com.bumptech.glide.m.f.a(jB));
            }
            this.g.f2998c.b();
            this.e = new b(Collections.singletonList(this.g.f2996a), this.f2839b, this);
        } catch (Throwable th) {
            this.g.f2998c.b();
            throw th;
        }
    }

    private boolean f() {
        return this.f2841d < this.f2839b.g().size();
    }

    private void j(n.a<?> aVar) {
        this.g.f2998c.f(this.f2839b.l(), new a(aVar));
    }

    @Override // com.bumptech.glide.load.engine.e.a
    public void a() {
        throw new UnsupportedOperationException();
    }

    @Override // com.bumptech.glide.load.engine.e.a
    public void b(com.bumptech.glide.load.c cVar, Exception exc, com.bumptech.glide.load.i.d<?> dVar, DataSource dataSource) {
        this.f2840c.b(cVar, exc, dVar, this.g.f2998c.e());
    }

    @Override // com.bumptech.glide.load.engine.e.a
    public void c(com.bumptech.glide.load.c cVar, Object obj, com.bumptech.glide.load.i.d<?> dVar, DataSource dataSource, com.bumptech.glide.load.c cVar2) {
        this.f2840c.c(cVar, obj, dVar, this.g.f2998c.e(), cVar);
    }

    @Override // com.bumptech.glide.load.engine.e
    public void cancel() {
        n.a<?> aVar = this.g;
        if (aVar != null) {
            aVar.f2998c.cancel();
        }
    }

    @Override // com.bumptech.glide.load.engine.e
    public boolean e() {
        Object obj = this.f;
        if (obj != null) {
            this.f = null;
            d(obj);
        }
        b bVar = this.e;
        if (bVar != null && bVar.e()) {
            return true;
        }
        this.e = null;
        this.g = null;
        boolean z = false;
        while (!z && f()) {
            List<n.a<?>> listG = this.f2839b.g();
            int i = this.f2841d;
            this.f2841d = i + 1;
            this.g = listG.get(i);
            if (this.g != null && (this.f2839b.e().c(this.g.f2998c.e()) || this.f2839b.t(this.g.f2998c.a()))) {
                j(this.g);
                z = true;
            }
        }
        return z;
    }

    boolean g(n.a<?> aVar) {
        n.a<?> aVar2 = this.g;
        return aVar2 != null && aVar2 == aVar;
    }

    void h(n.a<?> aVar, Object obj) {
        h hVarE = this.f2839b.e();
        if (obj != null && hVarE.c(aVar.f2998c.e())) {
            this.f = obj;
            this.f2840c.a();
        } else {
            e.a aVar2 = this.f2840c;
            com.bumptech.glide.load.c cVar = aVar.f2996a;
            com.bumptech.glide.load.i.d<?> dVar = aVar.f2998c;
            aVar2.c(cVar, obj, dVar, dVar.e(), this.h);
        }
    }

    void i(n.a<?> aVar, Exception exc) {
        e.a aVar2 = this.f2840c;
        c cVar = this.h;
        com.bumptech.glide.load.i.d<?> dVar = aVar.f2998c;
        aVar2.b(cVar, exc, dVar, dVar.e());
    }
}
