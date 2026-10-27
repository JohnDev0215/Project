package com.bumptech.glide.o;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<com.bumptech.glide.r.c> f2472a = Collections.newSetFromMap(new WeakHashMap());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<com.bumptech.glide.r.c> f2473b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2474c;

    public boolean a(com.bumptech.glide.r.c cVar) {
        boolean z = true;
        if (cVar == null) {
            return true;
        }
        boolean zRemove = this.f2472a.remove(cVar);
        if (!this.f2473b.remove(cVar) && !zRemove) {
            z = false;
        }
        if (z) {
            cVar.clear();
        }
        return z;
    }

    public void b() {
        Iterator it = com.bumptech.glide.t.k.i(this.f2472a).iterator();
        while (it.hasNext()) {
            a((com.bumptech.glide.r.c) it.next());
        }
        this.f2473b.clear();
    }

    public void c() {
        this.f2474c = true;
        for (com.bumptech.glide.r.c cVar : com.bumptech.glide.t.k.i(this.f2472a)) {
            if (cVar.isRunning() || cVar.i()) {
                cVar.clear();
                this.f2473b.add(cVar);
            }
        }
    }

    public void d() {
        this.f2474c = true;
        for (com.bumptech.glide.r.c cVar : com.bumptech.glide.t.k.i(this.f2472a)) {
            if (cVar.isRunning()) {
                cVar.e();
                this.f2473b.add(cVar);
            }
        }
    }

    public void e() {
        for (com.bumptech.glide.r.c cVar : com.bumptech.glide.t.k.i(this.f2472a)) {
            if (!cVar.i() && !cVar.j()) {
                cVar.clear();
                if (this.f2474c) {
                    this.f2473b.add(cVar);
                } else {
                    cVar.f();
                }
            }
        }
    }

    public void f() {
        this.f2474c = false;
        for (com.bumptech.glide.r.c cVar : com.bumptech.glide.t.k.i(this.f2472a)) {
            if (!cVar.i() && !cVar.isRunning()) {
                cVar.f();
            }
        }
        this.f2473b.clear();
    }

    public void g(com.bumptech.glide.r.c cVar) {
        this.f2472a.add(cVar);
        if (!this.f2474c) {
            cVar.f();
            return;
        }
        cVar.clear();
        if (Log.isLoggable("RequestTracker", 2)) {
            Log.v("RequestTracker", "Paused, delaying request");
        }
        this.f2473b.add(cVar);
    }

    public String toString() {
        return super.toString() + "{numRequests=" + this.f2472a.size() + ", isPaused=" + this.f2474c + "}";
    }
}
