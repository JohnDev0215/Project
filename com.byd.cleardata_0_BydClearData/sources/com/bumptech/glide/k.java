package com.bumptech.glide;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.o.c;
import com.bumptech.glide.o.m;
import com.bumptech.glide.o.n;
import com.bumptech.glide.o.p;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class k implements ComponentCallbacks2, com.bumptech.glide.o.i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final com.bumptech.glide.r.f f1952b = com.bumptech.glide.r.f.V(Bitmap.class).H();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final com.bumptech.glide.r.f f1953c = com.bumptech.glide.r.f.V(com.bumptech.glide.load.p.h.c.class).H();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final com.bumptech.glide.r.f f1954d = com.bumptech.glide.r.f.W(com.bumptech.glide.load.n.j.f2143c).K(g.LOW).Q(true);
    protected final c e;
    protected final Context f;
    final com.bumptech.glide.o.h g;
    private final n h;
    private final m i;
    private final p j;
    private final Runnable k;
    private final Handler l;
    private final com.bumptech.glide.o.c m;
    private final CopyOnWriteArrayList<com.bumptech.glide.r.e<Object>> n;
    private com.bumptech.glide.r.f o;
    private boolean p;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            k kVar = k.this;
            kVar.g.a(kVar);
        }
    }

    private class b implements c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final n f1956a;

        b(n nVar) {
            this.f1956a = nVar;
        }

        @Override // com.bumptech.glide.o.c.a
        public void a(boolean z) {
            if (z) {
                synchronized (k.this) {
                    this.f1956a.e();
                }
            }
        }
    }

    public k(c cVar, com.bumptech.glide.o.h hVar, m mVar, Context context) {
        this(cVar, hVar, mVar, new n(), cVar.g(), context);
    }

    k(c cVar, com.bumptech.glide.o.h hVar, m mVar, n nVar, com.bumptech.glide.o.d dVar, Context context) {
        this.j = new p();
        a aVar = new a();
        this.k = aVar;
        Handler handler = new Handler(Looper.getMainLooper());
        this.l = handler;
        this.e = cVar;
        this.g = hVar;
        this.i = mVar;
        this.h = nVar;
        this.f = context;
        com.bumptech.glide.o.c cVarA = dVar.a(context.getApplicationContext(), new b(nVar));
        this.m = cVarA;
        if (com.bumptech.glide.t.k.o()) {
            handler.post(aVar);
        } else {
            hVar.a(this);
        }
        hVar.a(cVarA);
        this.n = new CopyOnWriteArrayList<>(cVar.i().b());
        y(cVar.i().c());
        cVar.o(this);
    }

    private void B(com.bumptech.glide.r.j.e<?> eVar) {
        boolean zA = A(eVar);
        com.bumptech.glide.r.c cVarG = eVar.g();
        if (zA || this.e.p(eVar) || cVarG == null) {
            return;
        }
        eVar.j(null);
        cVarG.clear();
    }

    synchronized boolean A(com.bumptech.glide.r.j.e<?> eVar) {
        com.bumptech.glide.r.c cVarG = eVar.g();
        if (cVarG == null) {
            return true;
        }
        if (!this.h.a(cVarG)) {
            return false;
        }
        this.j.o(eVar);
        eVar.j(null);
        return true;
    }

    @Override // com.bumptech.glide.o.i
    public synchronized void a() {
        x();
        this.j.a();
    }

    @Override // com.bumptech.glide.o.i
    public synchronized void f() {
        w();
        this.j.f();
    }

    @Override // com.bumptech.glide.o.i
    public synchronized void k() {
        this.j.k();
        Iterator<com.bumptech.glide.r.j.e<?>> it = this.j.m().iterator();
        while (it.hasNext()) {
            o(it.next());
        }
        this.j.l();
        this.h.b();
        this.g.b(this);
        this.g.b(this.m);
        this.l.removeCallbacks(this.k);
        this.e.s(this);
    }

    public <ResourceType> j<ResourceType> l(Class<ResourceType> cls) {
        return new j<>(this.e, this, cls, this.f);
    }

    public j<Bitmap> m() {
        return l(Bitmap.class).a(f1952b);
    }

    public j<Drawable> n() {
        return l(Drawable.class);
    }

    public void o(com.bumptech.glide.r.j.e<?> eVar) {
        if (eVar == null) {
            return;
        }
        B(eVar);
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        if (i == 60 && this.p) {
            v();
        }
    }

    List<com.bumptech.glide.r.e<Object>> p() {
        return this.n;
    }

    synchronized com.bumptech.glide.r.f q() {
        return this.o;
    }

    <T> l<?, T> r(Class<T> cls) {
        return this.e.i().d(cls);
    }

    public j<Drawable> s(Uri uri) {
        return n().h0(uri);
    }

    public j<Drawable> t(Object obj) {
        return n().i0(obj);
    }

    public synchronized String toString() {
        return super.toString() + "{tracker=" + this.h + ", treeNode=" + this.i + "}";
    }

    public synchronized void u() {
        this.h.c();
    }

    public synchronized void v() {
        u();
        Iterator<k> it = this.i.a().iterator();
        while (it.hasNext()) {
            it.next().u();
        }
    }

    public synchronized void w() {
        this.h.d();
    }

    public synchronized void x() {
        this.h.f();
    }

    protected synchronized void y(com.bumptech.glide.r.f fVar) {
        this.o = fVar.clone().b();
    }

    synchronized void z(com.bumptech.glide.r.j.e<?> eVar, com.bumptech.glide.r.c cVar) {
        this.j.n(eVar);
        this.h.g(cVar);
    }
}
