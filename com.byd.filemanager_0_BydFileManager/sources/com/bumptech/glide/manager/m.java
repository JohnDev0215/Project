package com.bumptech.glide.manager;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: RequestTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<com.bumptech.glide.request.c> f3438a = Collections.newSetFromMap(new WeakHashMap());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<com.bumptech.glide.request.c> f3439b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3440c;

    public boolean a(com.bumptech.glide.request.c cVar) {
        boolean z = true;
        if (cVar == null) {
            return true;
        }
        boolean zRemove = this.f3438a.remove(cVar);
        if (!this.f3439b.remove(cVar) && !zRemove) {
            z = false;
        }
        if (z) {
            cVar.clear();
        }
        return z;
    }

    public void b() {
        Iterator it = com.bumptech.glide.o.k.i(this.f3438a).iterator();
        while (it.hasNext()) {
            a((com.bumptech.glide.request.c) it.next());
        }
        this.f3439b.clear();
    }

    public void c() {
        this.f3440c = true;
        for (com.bumptech.glide.request.c cVar : com.bumptech.glide.o.k.i(this.f3438a)) {
            if (cVar.isRunning() || cVar.i()) {
                cVar.clear();
                this.f3439b.add(cVar);
            }
        }
    }

    public void d() {
        this.f3440c = true;
        for (com.bumptech.glide.request.c cVar : com.bumptech.glide.o.k.i(this.f3438a)) {
            if (cVar.isRunning()) {
                cVar.e();
                this.f3439b.add(cVar);
            }
        }
    }

    public void e() {
        for (com.bumptech.glide.request.c cVar : com.bumptech.glide.o.k.i(this.f3438a)) {
            if (!cVar.i() && !cVar.j()) {
                cVar.clear();
                if (this.f3440c) {
                    this.f3439b.add(cVar);
                } else {
                    cVar.f();
                }
            }
        }
    }

    public void f() {
        this.f3440c = false;
        for (com.bumptech.glide.request.c cVar : com.bumptech.glide.o.k.i(this.f3438a)) {
            if (!cVar.i() && !cVar.isRunning()) {
                cVar.f();
            }
        }
        this.f3439b.clear();
    }

    public void g(com.bumptech.glide.request.c cVar) {
        this.f3438a.add(cVar);
        if (!this.f3440c) {
            cVar.f();
            return;
        }
        cVar.clear();
        if (Log.isLoggable("RequestTracker", 2)) {
            Log.v("RequestTracker", "Paused, delaying request");
        }
        this.f3439b.add(cVar);
    }

    public String toString() {
        return super.toString() + "{numRequests=" + this.f3438a.size() + ", isPaused=" + this.f3440c + "}";
    }
}
