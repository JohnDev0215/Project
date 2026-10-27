package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.b;
import com.bumptech.glide.load.engine.i;
import com.bumptech.glide.load.engine.x.j;
import com.bumptech.glide.load.engine.y.a;
import com.bumptech.glide.load.engine.y.h;
import com.bumptech.glide.load.engine.y.i;
import com.bumptech.glide.manager.k;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GlideBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private i f2646b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.bumptech.glide.load.engine.x.e f2647c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.bumptech.glide.load.engine.x.b f2648d;
    private h e;
    private com.bumptech.glide.load.engine.z.a f;
    private com.bumptech.glide.load.engine.z.a g;
    private a.InterfaceC0089a h;
    private com.bumptech.glide.load.engine.y.i i;
    private com.bumptech.glide.manager.d j;
    private k.b m;
    private com.bumptech.glide.load.engine.z.a n;
    private boolean o;
    private List<com.bumptech.glide.request.d<Object>> p;
    private boolean q;
    private boolean r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<Class<?>, g<?, ?>> f2645a = new c.d.a();
    private int k = 4;
    private b.a l = new a();

    /* JADX INFO: compiled from: GlideBuilder.java */
    class a implements b.a {
        a() {
        }

        @Override // com.bumptech.glide.b.a
        public com.bumptech.glide.request.e a() {
            return new com.bumptech.glide.request.e();
        }
    }

    b a(Context context) {
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
        if (this.f2647c == null) {
            int iB = this.i.b();
            if (iB > 0) {
                this.f2647c = new com.bumptech.glide.load.engine.x.k(iB);
            } else {
                this.f2647c = new com.bumptech.glide.load.engine.x.f();
            }
        }
        if (this.f2648d == null) {
            this.f2648d = new j(this.i.a());
        }
        if (this.e == null) {
            this.e = new com.bumptech.glide.load.engine.y.g(this.i.d());
        }
        if (this.h == null) {
            this.h = new com.bumptech.glide.load.engine.y.f(context);
        }
        if (this.f2646b == null) {
            this.f2646b = new com.bumptech.glide.load.engine.i(this.e, this.h, this.g, this.f, com.bumptech.glide.load.engine.z.a.h(), this.n, this.o);
        }
        List<com.bumptech.glide.request.d<Object>> list = this.p;
        if (list == null) {
            this.p = Collections.emptyList();
        } else {
            this.p = Collections.unmodifiableList(list);
        }
        return new b(context, this.f2646b, this.e, this.f2647c, this.f2648d, new k(this.m), this.j, this.k, this.l, this.f2645a, this.p, this.q, this.r);
    }

    void b(k.b bVar) {
        this.m = bVar;
    }
}
