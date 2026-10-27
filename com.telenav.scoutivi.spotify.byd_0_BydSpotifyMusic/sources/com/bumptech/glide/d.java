package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.c;
import d2.l;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import s1.a;
import s1.i;

/* JADX INFO: compiled from: GlideBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private q1.k f3949b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private r1.d f3950c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private r1.b f3951d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private s1.h f3952e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private t1.a f3953f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private t1.a f3954g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private a.InterfaceC0144a f3955h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private s1.i f3956i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private d2.d f3957j;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private l.b f3960m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private t1.a f3961n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f3962o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private List<g2.e<Object>> f3963p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f3964q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f3965r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<Class<?>, l<?, ?>> f3948a = new n.a();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f3958k = 4;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private c.a f3959l = new a();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f3966s = 700;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f3967t = 128;

    /* JADX INFO: compiled from: GlideBuilder.java */
    class a implements c.a {
        a() {
        }

        @Override // com.bumptech.glide.c.a
        public g2.f a() {
            return new g2.f();
        }
    }

    c a(Context context) {
        if (this.f3953f == null) {
            this.f3953f = t1.a.f();
        }
        if (this.f3954g == null) {
            this.f3954g = t1.a.d();
        }
        if (this.f3961n == null) {
            this.f3961n = t1.a.b();
        }
        if (this.f3956i == null) {
            this.f3956i = new i.a(context).a();
        }
        if (this.f3957j == null) {
            this.f3957j = new d2.f();
        }
        if (this.f3950c == null) {
            int iB = this.f3956i.b();
            if (iB > 0) {
                this.f3950c = new r1.j(iB);
            } else {
                this.f3950c = new r1.e();
            }
        }
        if (this.f3951d == null) {
            this.f3951d = new r1.i(this.f3956i.a());
        }
        if (this.f3952e == null) {
            this.f3952e = new s1.g(this.f3956i.d());
        }
        if (this.f3955h == null) {
            this.f3955h = new s1.f(context);
        }
        if (this.f3949b == null) {
            this.f3949b = new q1.k(this.f3952e, this.f3955h, this.f3954g, this.f3953f, t1.a.h(), this.f3961n, this.f3962o);
        }
        List<g2.e<Object>> list = this.f3963p;
        if (list == null) {
            this.f3963p = Collections.emptyList();
        } else {
            this.f3963p = Collections.unmodifiableList(list);
        }
        return new c(context, this.f3949b, this.f3952e, this.f3950c, this.f3951d, new d2.l(this.f3960m), this.f3957j, this.f3958k, this.f3959l, this.f3948a, this.f3963p, this.f3964q, this.f3965r, this.f3966s, this.f3967t);
    }

    public d b(r1.d dVar) {
        this.f3950c = dVar;
        return this;
    }

    public d c(s1.h hVar) {
        this.f3952e = hVar;
        return this;
    }

    void d(l.b bVar) {
        this.f3960m = bVar;
    }
}
