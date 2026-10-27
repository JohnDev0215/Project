package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.c;
import com.bumptech.glide.load.n.b0.a;
import com.bumptech.glide.load.n.b0.i;
import com.bumptech.glide.o.l;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.bumptech.glide.load.n.k f1929b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.bumptech.glide.load.n.a0.e f1930c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.bumptech.glide.load.n.a0.b f1931d;
    private com.bumptech.glide.load.n.b0.h e;
    private com.bumptech.glide.load.n.c0.a f;
    private com.bumptech.glide.load.n.c0.a g;
    private a.InterfaceC0067a h;
    private com.bumptech.glide.load.n.b0.i i;
    private com.bumptech.glide.o.d j;
    private l.b m;
    private com.bumptech.glide.load.n.c0.a n;
    private boolean o;
    private List<com.bumptech.glide.r.e<Object>> p;
    private boolean q;
    private boolean r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<Class<?>, l<?, ?>> f1928a = new a.d.a();
    private int k = 4;
    private c.a l = new a();

    class a implements c.a {
        a() {
        }

        @Override // com.bumptech.glide.c.a
        public com.bumptech.glide.r.f a() {
            return new com.bumptech.glide.r.f();
        }
    }

    class b implements c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.bumptech.glide.r.f f1933a;

        b(com.bumptech.glide.r.f fVar) {
            this.f1933a = fVar;
        }

        @Override // com.bumptech.glide.c.a
        public com.bumptech.glide.r.f a() {
            com.bumptech.glide.r.f fVar = this.f1933a;
            return fVar != null ? fVar : new com.bumptech.glide.r.f();
        }
    }

    c a(Context context) {
        if (this.f == null) {
            this.f = com.bumptech.glide.load.n.c0.a.g();
        }
        if (this.g == null) {
            this.g = com.bumptech.glide.load.n.c0.a.e();
        }
        if (this.n == null) {
            this.n = com.bumptech.glide.load.n.c0.a.c();
        }
        if (this.i == null) {
            this.i = new i.a(context).a();
        }
        if (this.j == null) {
            this.j = new com.bumptech.glide.o.f();
        }
        if (this.f1930c == null) {
            int iB = this.i.b();
            if (iB > 0) {
                this.f1930c = new com.bumptech.glide.load.n.a0.k(iB);
            } else {
                this.f1930c = new com.bumptech.glide.load.n.a0.f();
            }
        }
        if (this.f1931d == null) {
            this.f1931d = new com.bumptech.glide.load.n.a0.j(this.i.a());
        }
        if (this.e == null) {
            this.e = new com.bumptech.glide.load.n.b0.g(this.i.d());
        }
        if (this.h == null) {
            this.h = new com.bumptech.glide.load.n.b0.f(context);
        }
        if (this.f1929b == null) {
            this.f1929b = new com.bumptech.glide.load.n.k(this.e, this.h, this.g, this.f, com.bumptech.glide.load.n.c0.a.h(), this.n, this.o);
        }
        List<com.bumptech.glide.r.e<Object>> list = this.p;
        this.p = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        return new c(context, this.f1929b, this.e, this.f1930c, this.f1931d, new com.bumptech.glide.o.l(this.m), this.j, this.k, this.l, this.f1928a, this.p, this.q, this.r);
    }

    public d b(com.bumptech.glide.load.n.a0.e eVar) {
        this.f1930c = eVar;
        return this;
    }

    public d c(c.a aVar) {
        this.l = (c.a) com.bumptech.glide.t.j.d(aVar);
        return this;
    }

    public d d(com.bumptech.glide.r.f fVar) {
        return c(new b(fVar));
    }

    public d e(a.InterfaceC0067a interfaceC0067a) {
        this.h = interfaceC0067a;
        return this;
    }

    public d f(com.bumptech.glide.load.n.c0.a aVar) {
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

    public d h(com.bumptech.glide.load.n.b0.h hVar) {
        this.e = hVar;
        return this;
    }

    void i(l.b bVar) {
        this.m = bVar;
    }

    public d j(com.bumptech.glide.load.n.c0.a aVar) {
        this.f = aVar;
        return this;
    }
}
