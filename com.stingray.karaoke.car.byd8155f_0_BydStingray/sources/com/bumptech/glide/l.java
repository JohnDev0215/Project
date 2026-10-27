package com.bumptech.glide;

import A4.q;
import B5.RunnableC0079c;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Looper;
import android.util.Log;
import h8.AbstractC1386c;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import l3.C1764u;
import l5.C1778f;
import l9.n;
import n0.C1886o;
import r4.C2290c;

/* JADX INFO: loaded from: classes.dex */
public final class l implements ComponentCallbacks2, t4.b {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final w4.f f12710t;
    public final b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Context f12711m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C1886o f12712n;
    public final t4.e o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C1886o f12713p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C1764u f12714q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final CopyOnWriteArrayList f12715r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final w4.f f12716s;

    static {
        w4.f fVar = (w4.f) new w4.f().c(Bitmap.class);
        fVar.f23284w = true;
        f12710t = fVar;
        ((w4.f) new w4.f().c(C2290c.class)).f23284w = true;
    }

    public l(b bVar, C1886o c1886o, C1886o c1886o2, Context context) {
        w4.f fVar;
        int i7 = 12;
        t4.e eVar = new t4.e(12);
        n nVar = bVar.f12669q;
        this.f12714q = new C1764u(26);
        RunnableC0079c runnableC0079c = new RunnableC0079c(this, 10);
        this.l = bVar;
        this.f12712n = c1886o;
        this.f12713p = c1886o2;
        this.o = eVar;
        this.f12711m = context;
        Context applicationContext = context.getApplicationContext();
        k kVar = new k(this, eVar);
        nVar.getClass();
        boolean z6 = AbstractC1386c.c(applicationContext, "android.permission.ACCESS_NETWORK_STATE") == 0;
        if (Log.isLoggable("ConnectivityMonitor", 3)) {
            Log.d("ConnectivityMonitor", z6 ? "ACCESS_NETWORK_STATE permission granted, registering connectivity monitor" : "ACCESS_NETWORK_STATE permission missing, cannot register connectivity monitor");
        }
        t4.b c1778f = z6 ? new C1778f(applicationContext, kVar) : new n(i7);
        synchronized (bVar.f12670r) {
            if (bVar.f12670r.contains(this)) {
                throw new IllegalStateException("Cannot register already registered manager");
            }
            bVar.f12670r.add(this);
        }
        char[] cArr = q.f432a;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            c1886o.g(this);
        } else {
            q.f().post(runnableC0079c);
        }
        c1886o.g(c1778f);
        this.f12715r = new CopyOnWriteArrayList(bVar.f12667n.f12678e);
        e eVar2 = bVar.f12667n;
        synchronized (eVar2) {
            try {
                if (eVar2.f12683j == null) {
                    eVar2.d.getClass();
                    w4.f fVar2 = new w4.f();
                    fVar2.f23284w = true;
                    eVar2.f12683j = fVar2;
                }
                fVar = eVar2.f12683j;
            } finally {
            }
        }
        synchronized (this) {
            w4.f fVar3 = (w4.f) fVar.clone();
            if (fVar3.f23284w && !fVar3.f23286y) {
                throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
            }
            fVar3.f23286y = true;
            fVar3.f23284w = true;
            this.f12716s = fVar3;
        }
    }

    @Override // t4.b
    public final synchronized void J() {
        b();
        this.f12714q.J();
    }

    public final void a(x4.c cVar) {
        if (cVar == null) {
            return;
        }
        boolean zC = c(cVar);
        w4.c cVarD = cVar.D();
        if (zC) {
            return;
        }
        b bVar = this.l;
        synchronized (bVar.f12670r) {
            try {
                Iterator it = bVar.f12670r.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((l) it.next()).c(cVar)) {
                        }
                    } else if (cVarD != null) {
                        cVar.n(null);
                        cVarD.clear();
                    }
                }
            } finally {
            }
        }
    }

    public final synchronized void b() {
        t4.e eVar = this.o;
        eVar.f22462m = false;
        for (w4.c cVar : q.e((Set) eVar.f22463n)) {
            if (!cVar.h() && !cVar.isRunning()) {
                cVar.g();
            }
        }
        ((HashSet) eVar.o).clear();
    }

    public final synchronized boolean c(x4.c cVar) {
        w4.c cVarD = cVar.D();
        if (cVarD == null) {
            return true;
        }
        t4.e eVar = this.o;
        boolean z6 = true;
        if (cVarD == null) {
            eVar.getClass();
        } else {
            boolean zRemove = ((Set) eVar.f22463n).remove(cVarD);
            if (!((HashSet) eVar.o).remove(cVarD) && !zRemove) {
                z6 = false;
            }
            if (z6) {
                cVarD.clear();
            }
        }
        if (!z6) {
            return false;
        }
        ((Set) this.f12714q.f18608m).remove(cVar);
        cVar.n(null);
        return true;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i7) {
    }

    public final synchronized String toString() {
        return super.toString() + "{tracker=" + this.o + ", treeNode=" + this.f12713p + "}";
    }
}
