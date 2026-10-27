package com.bumptech.glide;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import d2.c;
import d2.m;
import d2.n;
import d2.p;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: RequestManager.java */
/* JADX INFO: loaded from: classes.dex */
public class k implements ComponentCallbacks2, d2.i {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static final g2.f f3998r = g2.f.i0(Bitmap.class).K();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private static final g2.f f3999s = g2.f.i0(b2.c.class).K();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private static final g2.f f4000t = g2.f.j0(q1.j.f9023c).S(g.LOW).Z(true);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected final c f4001e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected final Context f4002g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final d2.h f4003h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final n f4004i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final m f4005j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final p f4006k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final Runnable f4007l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final Handler f4008m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final d2.c f4009n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final CopyOnWriteArrayList<g2.e<Object>> f4010o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private g2.f f4011p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f4012q;

    /* JADX INFO: compiled from: RequestManager.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            k kVar = k.this;
            kVar.f4003h.a(kVar);
        }
    }

    /* JADX INFO: compiled from: RequestManager.java */
    private class b implements c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final n f4014a;

        b(n nVar) {
            this.f4014a = nVar;
        }

        @Override // d2.c.a
        public void a(boolean z6) {
            if (z6) {
                synchronized (k.this) {
                    this.f4014a.e();
                }
            }
        }
    }

    public k(c cVar, d2.h hVar, m mVar, Context context) {
        this(cVar, hVar, mVar, new n(), cVar.g(), context);
    }

    private void A(h2.h<?> hVar) {
        boolean z6 = z(hVar);
        g2.c cVarH = hVar.h();
        if (z6 || this.f4001e.p(hVar) || cVarH == null) {
            return;
        }
        hVar.l(null);
        cVarH.clear();
    }

    @Override // d2.i
    public synchronized void a() {
        w();
        this.f4006k.a();
    }

    public <ResourceType> j<ResourceType> b(Class<ResourceType> cls) {
        return new j<>(this.f4001e, this, cls, this.f4002g);
    }

    public j<Bitmap> c() {
        return b(Bitmap.class).a(f3998r);
    }

    @Override // d2.i
    public synchronized void g() {
        v();
        this.f4006k.g();
    }

    public j<Drawable> m() {
        return b(Drawable.class);
    }

    public j<b2.c> n() {
        return b(b2.c.class).a(f3999s);
    }

    public void o(h2.h<?> hVar) {
        if (hVar == null) {
            return;
        }
        A(hVar);
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // d2.i
    public synchronized void onDestroy() {
        this.f4006k.onDestroy();
        Iterator<h2.h<?>> it = this.f4006k.c().iterator();
        while (it.hasNext()) {
            o(it.next());
        }
        this.f4006k.b();
        this.f4004i.b();
        this.f4003h.b(this);
        this.f4003h.b(this.f4009n);
        this.f4008m.removeCallbacks(this.f4007l);
        this.f4001e.s(this);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i7) {
        if (i7 == 60 && this.f4012q) {
            u();
        }
    }

    List<g2.e<Object>> p() {
        return this.f4010o;
    }

    synchronized g2.f q() {
        return this.f4011p;
    }

    <T> l<?, T> r(Class<T> cls) {
        return this.f4001e.i().e(cls);
    }

    public j<Drawable> s(Uri uri) {
        return m().v0(uri);
    }

    public synchronized void t() {
        this.f4004i.c();
    }

    public synchronized String toString() {
        return super.toString() + "{tracker=" + this.f4004i + ", treeNode=" + this.f4005j + "}";
    }

    public synchronized void u() {
        t();
        Iterator<k> it = this.f4005j.a().iterator();
        while (it.hasNext()) {
            it.next().t();
        }
    }

    public synchronized void v() {
        this.f4004i.d();
    }

    public synchronized void w() {
        this.f4004i.f();
    }

    protected synchronized void x(g2.f fVar) {
        this.f4011p = fVar.clone().b();
    }

    synchronized void y(h2.h<?> hVar, g2.c cVar) {
        this.f4006k.m(hVar);
        this.f4004i.g(cVar);
    }

    synchronized boolean z(h2.h<?> hVar) {
        g2.c cVarH = hVar.h();
        if (cVarH == null) {
            return true;
        }
        if (!this.f4004i.a(cVarH)) {
            return false;
        }
        this.f4006k.n(hVar);
        hVar.l(null);
        return true;
    }

    k(c cVar, d2.h hVar, m mVar, n nVar, d2.d dVar, Context context) {
        this.f4006k = new p();
        a aVar = new a();
        this.f4007l = aVar;
        Handler handler = new Handler(Looper.getMainLooper());
        this.f4008m = handler;
        this.f4001e = cVar;
        this.f4003h = hVar;
        this.f4005j = mVar;
        this.f4004i = nVar;
        this.f4002g = context;
        d2.c cVarA = dVar.a(context.getApplicationContext(), new b(nVar));
        this.f4009n = cVarA;
        if (k2.k.p()) {
            handler.post(aVar);
        } else {
            hVar.a(this);
        }
        hVar.a(cVarA);
        this.f4010o = new CopyOnWriteArrayList<>(cVar.i().c());
        x(cVar.i().d());
        cVar.o(this);
    }
}
