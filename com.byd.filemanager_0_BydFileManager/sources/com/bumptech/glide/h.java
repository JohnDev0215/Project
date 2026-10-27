package com.bumptech.glide;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.manager.c;
import com.bumptech.glide.manager.l;
import com.bumptech.glide.manager.m;
import com.bumptech.glide.manager.o;
import com.bumptech.glide.o.k;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: RequestManager.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements ComponentCallbacks2, com.bumptech.glide.manager.i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final com.bumptech.glide.request.e f2860b = com.bumptech.glide.request.e.a0(Bitmap.class).L();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final com.bumptech.glide.request.e f2861c = com.bumptech.glide.request.e.a0(com.bumptech.glide.load.j.g.c.class).L();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final com.bumptech.glide.request.e f2862d = com.bumptech.glide.request.e.b0(com.bumptech.glide.load.engine.h.f3023c).O(Priority.LOW).U(true);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected final c f2863e;
    protected final Context f;
    final com.bumptech.glide.manager.h g;
    private final m h;
    private final l i;
    private final o j;
    private final Runnable k;
    private final Handler l;
    private final com.bumptech.glide.manager.c m;
    private final CopyOnWriteArrayList<com.bumptech.glide.request.d<Object>> n;
    private com.bumptech.glide.request.e o;
    private boolean p;

    /* JADX INFO: compiled from: RequestManager.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            hVar.g.a(hVar);
        }
    }

    /* JADX INFO: compiled from: RequestManager.java */
    private class b implements c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final m f2865a;

        b(m mVar) {
            this.f2865a = mVar;
        }

        @Override // com.bumptech.glide.manager.c.a
        public void a(boolean z) {
            if (z) {
                synchronized (h.this) {
                    this.f2865a.e();
                }
            }
        }
    }

    public h(c cVar, com.bumptech.glide.manager.h hVar, l lVar, Context context) {
        this(cVar, hVar, lVar, new m(), cVar.g(), context);
    }

    private void B(com.bumptech.glide.request.h.e<?> eVar) {
        boolean zA = A(eVar);
        com.bumptech.glide.request.c cVarG = eVar.g();
        if (zA || this.f2863e.p(eVar) || cVarG == null) {
            return;
        }
        eVar.j(null);
        cVarG.clear();
    }

    synchronized boolean A(com.bumptech.glide.request.h.e<?> eVar) {
        com.bumptech.glide.request.c cVarG = eVar.g();
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

    @Override // com.bumptech.glide.manager.i
    public synchronized void a() {
        x();
        this.j.a();
    }

    @Override // com.bumptech.glide.manager.i
    public synchronized void f() {
        w();
        this.j.f();
    }

    @Override // com.bumptech.glide.manager.i
    public synchronized void k() {
        this.j.k();
        Iterator<com.bumptech.glide.request.h.e<?>> it = this.j.m().iterator();
        while (it.hasNext()) {
            o(it.next());
        }
        this.j.l();
        this.h.b();
        this.g.b(this);
        this.g.b(this.m);
        this.l.removeCallbacks(this.k);
        this.f2863e.s(this);
    }

    public <ResourceType> g<ResourceType> l(Class<ResourceType> cls) {
        return new g<>(this.f2863e, this, cls, this.f);
    }

    public g<Bitmap> m() {
        return l(Bitmap.class).a(f2860b);
    }

    public g<Drawable> n() {
        return l(Drawable.class);
    }

    public void o(com.bumptech.glide.request.h.e<?> eVar) {
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

    List<com.bumptech.glide.request.d<Object>> p() {
        return this.n;
    }

    synchronized com.bumptech.glide.request.e q() {
        return this.o;
    }

    <T> i<?, T> r(Class<T> cls) {
        return this.f2863e.i().d(cls);
    }

    public g<Drawable> s(Uri uri) {
        return n().m0(uri);
    }

    public g<Drawable> t(Object obj) {
        return n().n0(obj);
    }

    public synchronized String toString() {
        return super.toString() + "{tracker=" + this.h + ", treeNode=" + this.i + "}";
    }

    public synchronized void u() {
        this.h.c();
    }

    public synchronized void v() {
        u();
        Iterator<h> it = this.i.a().iterator();
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

    protected synchronized void y(com.bumptech.glide.request.e eVar) {
        this.o = eVar.clone().b();
    }

    synchronized void z(com.bumptech.glide.request.h.e<?> eVar, com.bumptech.glide.request.c cVar) {
        this.j.n(eVar);
        this.h.g(cVar);
    }

    h(c cVar, com.bumptech.glide.manager.h hVar, l lVar, m mVar, com.bumptech.glide.manager.d dVar, Context context) {
        this.j = new o();
        a aVar = new a();
        this.k = aVar;
        Handler handler = new Handler(Looper.getMainLooper());
        this.l = handler;
        this.f2863e = cVar;
        this.g = hVar;
        this.i = lVar;
        this.h = mVar;
        this.f = context;
        com.bumptech.glide.manager.c cVarA = dVar.a(context.getApplicationContext(), new b(mVar));
        this.m = cVarA;
        if (k.o()) {
            handler.post(aVar);
        } else {
            hVar.a(this);
        }
        hVar.a(cVarA);
        this.n = new CopyOnWriteArrayList<>(cVar.i().b());
        y(cVar.i().c());
        cVar.o(this);
    }
}
