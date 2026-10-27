package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.engine.e;
import com.bumptech.glide.load.i.n;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: SourceGenerator.java */
/* JADX INFO: loaded from: classes.dex */
class w implements e, e.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f<?> f3085b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e.a f3086c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3087d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b f3088e;
    private Object f;
    private volatile n.a<?> g;
    private c h;

    /* JADX INFO: compiled from: SourceGenerator.java */
    class a implements d.a<Object> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ n.a f3089b;

        a(n.a aVar) {
            this.f3089b = aVar;
        }

        @Override // com.bumptech.glide.load.data.d.a
        public void d(Exception exc) {
            if (w.this.g(this.f3089b)) {
                w.this.i(this.f3089b, exc);
            }
        }

        @Override // com.bumptech.glide.load.data.d.a
        public void f(Object obj) {
            if (w.this.g(this.f3089b)) {
                w.this.h(this.f3089b, obj);
            }
        }
    }

    w(f<?> fVar, e.a aVar) {
        this.f3085b = fVar;
        this.f3086c = aVar;
    }

    private void e(Object obj) {
        long jB = com.bumptech.glide.o.f.b();
        try {
            com.bumptech.glide.load.a<X> aVarP = this.f3085b.p(obj);
            d dVar = new d(aVarP, obj, this.f3085b.k());
            this.h = new c(this.g.f3221a, this.f3085b.o());
            this.f3085b.d().a(this.h, dVar);
            if (Log.isLoggable("SourceGenerator", 2)) {
                Log.v("SourceGenerator", "Finished encoding source to cache, key: " + this.h + ", data: " + obj + ", encoder: " + aVarP + ", duration: " + com.bumptech.glide.o.f.a(jB));
            }
            this.g.f3223c.b();
            this.f3088e = new b(Collections.singletonList(this.g.f3221a), this.f3085b, this);
        } catch (Throwable th) {
            this.g.f3223c.b();
            throw th;
        }
    }

    private boolean f() {
        return this.f3087d < this.f3085b.g().size();
    }

    private void j(n.a<?> aVar) {
        this.g.f3223c.e(this.f3085b.l(), new a(aVar));
    }

    @Override // com.bumptech.glide.load.engine.e
    public boolean a() {
        Object obj = this.f;
        if (obj != null) {
            this.f = null;
            e(obj);
        }
        b bVar = this.f3088e;
        if (bVar != null && bVar.a()) {
            return true;
        }
        this.f3088e = null;
        this.g = null;
        boolean z = false;
        while (!z && f()) {
            List<n.a<?>> listG = this.f3085b.g();
            int i = this.f3087d;
            this.f3087d = i + 1;
            this.g = listG.get(i);
            if (this.g != null && (this.f3085b.e().c(this.g.f3223c.c()) || this.f3085b.t(this.g.f3223c.a()))) {
                j(this.g);
                z = true;
            }
        }
        return z;
    }

    @Override // com.bumptech.glide.load.engine.e.a
    public void b() {
        throw new UnsupportedOperationException();
    }

    @Override // com.bumptech.glide.load.engine.e.a
    public void c(com.bumptech.glide.load.c cVar, Object obj, com.bumptech.glide.load.data.d<?> dVar, DataSource dataSource, com.bumptech.glide.load.c cVar2) {
        this.f3086c.c(cVar, obj, dVar, this.g.f3223c.c(), cVar);
    }

    @Override // com.bumptech.glide.load.engine.e
    public void cancel() {
        n.a<?> aVar = this.g;
        if (aVar != null) {
            aVar.f3223c.cancel();
        }
    }

    @Override // com.bumptech.glide.load.engine.e.a
    public void d(com.bumptech.glide.load.c cVar, Exception exc, com.bumptech.glide.load.data.d<?> dVar, DataSource dataSource) {
        this.f3086c.d(cVar, exc, dVar, this.g.f3223c.c());
    }

    boolean g(n.a<?> aVar) {
        n.a<?> aVar2 = this.g;
        return aVar2 != null && aVar2 == aVar;
    }

    void h(n.a<?> aVar, Object obj) {
        h hVarE = this.f3085b.e();
        if (obj != null && hVarE.c(aVar.f3223c.c())) {
            this.f = obj;
            this.f3086c.b();
        } else {
            e.a aVar2 = this.f3086c;
            com.bumptech.glide.load.c cVar = aVar.f3221a;
            com.bumptech.glide.load.data.d<?> dVar = aVar.f3223c;
            aVar2.c(cVar, obj, dVar, dVar.c(), this.h);
        }
    }

    void i(n.a<?> aVar, Exception exc) {
        e.a aVar2 = this.f3086c;
        c cVar = this.h;
        com.bumptech.glide.load.data.d<?> dVar = aVar.f3223c;
        aVar2.d(cVar, exc, dVar, dVar.c());
    }
}
