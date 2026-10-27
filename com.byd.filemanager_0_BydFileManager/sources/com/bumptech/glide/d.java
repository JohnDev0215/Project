package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.c;
import com.bumptech.glide.load.engine.x.j;
import com.bumptech.glide.load.engine.y.a;
import com.bumptech.glide.load.engine.y.i;
import com.bumptech.glide.manager.k;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GlideBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.bumptech.glide.load.engine.i f2845b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.bumptech.glide.load.engine.x.e f2846c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.bumptech.glide.load.engine.x.b f2847d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.bumptech.glide.load.engine.y.h f2848e;
    private com.bumptech.glide.load.engine.z.a f;
    private com.bumptech.glide.load.engine.z.a g;
    private a.InterfaceC0065a h;
    private com.bumptech.glide.load.engine.y.i i;
    private com.bumptech.glide.manager.d j;
    private k.b m;
    private com.bumptech.glide.load.engine.z.a n;
    private boolean o;
    private List<com.bumptech.glide.request.d<Object>> p;
    private boolean q;
    private boolean r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<Class<?>, i<?, ?>> f2844a = new b.d.a();
    private int k = 4;
    private c.a l = new a();

    /* JADX INFO: compiled from: GlideBuilder.java */
    class a implements c.a {
        a() {
        }

        @Override // com.bumptech.glide.c.a
        public com.bumptech.glide.request.e a() {
            return new com.bumptech.glide.request.e();
        }
    }

    /* JADX INFO: compiled from: GlideBuilder.java */
    class b implements c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.bumptech.glide.request.e f2850a;

        b(com.bumptech.glide.request.e eVar) {
            this.f2850a = eVar;
        }

        @Override // com.bumptech.glide.c.a
        public com.bumptech.glide.request.e a() {
            com.bumptech.glide.request.e eVar = this.f2850a;
            return eVar != null ? eVar : new com.bumptech.glide.request.e();
        }
    }

    c a(Context context) {
        if (this.f == null) {
            this.f = com.bumptech.glide.load.engine.z.a.g();
        }
        if (this.g == null) {
            this.g = com.bumptech.glide.load.engine.z.a.e();
        }
        if (this.n == null) {
            this.n = com.bumptech.glide.load.engine.z.a.c();
        }
        if (this.i == null) {
            this.i = new i.a(context).a();
        }
        if (this.j == null) {
            this.j = new com.bumptech.glide.manager.f();
        }
        if (this.f2846c == null) {
            int iB = this.i.b();
            if (iB > 0) {
                this.f2846c = new com.bumptech.glide.load.engine.x.k(iB);
            } else {
                this.f2846c = new com.bumptech.glide.load.engine.x.f();
            }
        }
        if (this.f2847d == null) {
            this.f2847d = new j(this.i.a());
        }
        if (this.f2848e == null) {
            this.f2848e = new com.bumptech.glide.load.engine.y.g(this.i.d());
        }
        if (this.h == null) {
            this.h = new com.bumptech.glide.load.engine.y.f(context);
        }
        if (this.f2845b == null) {
            this.f2845b = new com.bumptech.glide.load.engine.i(this.f2848e, this.h, this.g, this.f, com.bumptech.glide.load.engine.z.a.h(), this.n, this.o);
        }
        List<com.bumptech.glide.request.d<Object>> list = this.p;
        if (list == null) {
            this.p = Collections.emptyList();
        } else {
            this.p = Collections.unmodifiableList(list);
        }
        return new c(context, this.f2845b, this.f2848e, this.f2846c, this.f2847d, new k(this.m), this.j, this.k, this.l, this.f2844a, this.p, this.q, this.r);
    }

    public d b(com.bumptech.glide.load.engine.x.e eVar) {
        this.f2846c = eVar;
        return this;
    }

    public d c(c.a aVar) {
        this.l = (c.a) com.bumptech.glide.o.j.d(aVar);
        return this;
    }

    public d d(com.bumptech.glide.request.e eVar) {
        return c(new b(eVar));
    }

    public d e(a.InterfaceC0065a interfaceC0065a) {
        this.h = interfaceC0065a;
        return this;
    }

    public d f(com.bumptech.glide.load.engine.z.a aVar) {
        this.g = aVar;
        return this;
    }

    public d g(int i) {
        if (i < 2 || i > 6) {
            throw new IllegalArgumentException("Log level must be one of Log.VERBOSE, Log.DEBUG, Log.INFO, Log.WARN, or Log.ERROR");
        }
        this.k = i;
        return this;
    }

    public d h(com.bumptech.glide.load.engine.y.h hVar) {
        this.f2848e = hVar;
        return this;
    }

    void i(k.b bVar) {
        this.m = bVar;
    }

    public d j(com.bumptech.glide.load.engine.z.a aVar) {
        this.f = aVar;
        return this;
    }
}
