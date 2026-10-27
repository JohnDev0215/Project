package com.bumptech.glide;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.load.engine.h;
import com.bumptech.glide.m.k;
import com.bumptech.glide.manager.c;
import com.bumptech.glide.manager.i;
import com.bumptech.glide.manager.l;
import com.bumptech.glide.manager.m;
import com.bumptech.glide.manager.o;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: RequestManager.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements ComponentCallbacks2, i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final com.bumptech.glide.request.e f2656b = com.bumptech.glide.request.e.R(Bitmap.class).F();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final com.bumptech.glide.request.e f2657c = com.bumptech.glide.request.e.R(com.bumptech.glide.load.k.g.c.class).F();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final com.bumptech.glide.request.e f2658d = com.bumptech.glide.request.e.S(h.f2786c).H(Priority.LOW).M(true);
    protected final com.bumptech.glide.b e;
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
            f fVar = f.this;
            fVar.g.a(fVar);
        }
    }

    /* JADX INFO: compiled from: RequestManager.java */
    private class b implements c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final m f2660a;

        b(m mVar) {
            this.f2660a = mVar;
        }

        @Override // com.bumptech.glide.manager.c.a
        public void a(boolean z) {
            if (z) {
                synchronized (f.this) {
                    this.f2660a.e();
                }
            }
        }
    }

    public f(com.bumptech.glide.b bVar, com.bumptech.glide.manager.h hVar, l lVar, Context context) {
        this(bVar, hVar, lVar, new m(), bVar.g(), context);
    }

    private void y(com.bumptech.glide.request.h.d<?> dVar) {
        boolean zX = x(dVar);
        com.bumptech.glide.request.c cVarG = dVar.g();
        if (zX || this.e.p(dVar) || cVarG == null) {
            return;
        }
        dVar.j(null);
        cVarG.clear();
    }

    @Override // com.bumptech.glide.manager.i
    public synchronized void a() {
        u();
        this.j.a();
    }

    @Override // com.bumptech.glide.manager.i
    public synchronized void e() {
        t();
        this.j.e();
    }

    @Override // com.bumptech.glide.manager.i
    public synchronized void k() {
        this.j.k();
        Iterator<com.bumptech.glide.request.h.d<?>> it = this.j.m().iterator();
        while (it.hasNext()) {
            n(it.next());
        }
        this.j.l();
        this.h.b();
        this.g.b(this);
        this.g.b(this.m);
        this.l.removeCallbacks(this.k);
        this.e.s(this);
    }

    public <ResourceType> e<ResourceType> l(Class<ResourceType> cls) {
        return new e<>(this.e, this, cls, this.f);
    }

    public e<Bitmap> m() {
        return l(Bitmap.class).a(f2656b);
    }

    public void n(com.bumptech.glide.request.h.d<?> dVar) {
        if (dVar == null) {
            return;
        }
        y(dVar);
    }

    List<com.bumptech.glide.request.d<Object>> o() {
        return this.n;
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
            s();
        }
    }

    synchronized com.bumptech.glide.request.e p() {
        return this.o;
    }

    <T> g<?, T> q(Class<T> cls) {
        return this.e.i().d(cls);
    }

    public synchronized void r() {
        this.h.c();
    }

    public synchronized void s() {
        r();
        Iterator<f> it = this.i.a().iterator();
        while (it.hasNext()) {
            it.next().r();
        }
    }

    public synchronized void t() {
        this.h.d();
    }

    public synchronized String toString() {
        return super.toString() + "{tracker=" + this.h + ", treeNode=" + this.i + "}";
    }

    public synchronized void u() {
        this.h.f();
    }

    protected synchronized void v(com.bumptech.glide.request.e eVar) {
        this.o = eVar.clone().b();
    }

    synchronized void w(com.bumptech.glide.request.h.d<?> dVar, com.bumptech.glide.request.c cVar) {
        this.j.n(dVar);
        this.h.g(cVar);
    }

    synchronized boolean x(com.bumptech.glide.request.h.d<?> dVar) {
        com.bumptech.glide.request.c cVarG = dVar.g();
        if (cVarG == null) {
            return true;
        }
        if (!this.h.a(cVarG)) {
            return false;
        }
        this.j.o(dVar);
        dVar.j(null);
        return true;
    }

    f(com.bumptech.glide.b bVar, com.bumptech.glide.manager.h hVar, l lVar, m mVar, com.bumptech.glide.manager.d dVar, Context context) {
        this.j = new o();
        a aVar = new a();
        this.k = aVar;
        Handler handler = new Handler(Looper.getMainLooper());
        this.l = handler;
        this.e = bVar;
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
        this.n = new CopyOnWriteArrayList<>(bVar.i().b());
        v(bVar.i().c());
        bVar.o(this);
    }
}
