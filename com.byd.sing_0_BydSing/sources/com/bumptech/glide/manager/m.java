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
    private final Set<com.bumptech.glide.request.c> f3209a = Collections.newSetFromMap(new WeakHashMap());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<com.bumptech.glide.request.c> f3210b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3211c;

    public boolean a(com.bumptech.glide.request.c cVar) {
        boolean z = true;
        if (cVar == null) {
            return true;
        }
        boolean zRemove = this.f3209a.remove(cVar);
        if (!this.f3210b.remove(cVar) && !zRemove) {
            z = false;
        }
        if (z) {
            cVar.clear();
        }
        return z;
    }

    public void b() {
        Iterator it = com.bumptech.glide.m.k.i(this.f3209a).iterator();
        while (it.hasNext()) {
            a((com.bumptech.glide.request.c) it.next());
        }
        this.f3210b.clear();
    }

    public void c() {
        this.f3211c = true;
        for (com.bumptech.glide.request.c cVar : com.bumptech.glide.m.k.i(this.f3209a)) {
            if (cVar.isRunning() || cVar.j()) {
                cVar.clear();
                this.f3210b.add(cVar);
            }
        }
    }

    public void d() {
        this.f3211c = true;
        for (com.bumptech.glide.request.c cVar : com.bumptech.glide.m.k.i(this.f3209a)) {
            if (cVar.isRunning()) {
                cVar.pause();
                this.f3210b.add(cVar);
            }
        }
    }

    public void e() {
        for (com.bumptech.glide.request.c cVar : com.bumptech.glide.m.k.i(this.f3209a)) {
            if (!cVar.j() && !cVar.e()) {
                cVar.clear();
                if (this.f3211c) {
                    this.f3210b.add(cVar);
                } else {
                    cVar.h();
                }
            }
        }
    }

    public void f() {
        this.f3211c = false;
        for (com.bumptech.glide.request.c cVar : com.bumptech.glide.m.k.i(this.f3209a)) {
            if (!cVar.j() && !cVar.isRunning()) {
                cVar.h();
            }
        }
        this.f3210b.clear();
    }

    public void g(com.bumptech.glide.request.c cVar) {
        this.f3209a.add(cVar);
        if (!this.f3211c) {
            cVar.h();
            return;
        }
        cVar.clear();
        if (Log.isLoggable("RequestTracker", 2)) {
            Log.v("RequestTracker", "Paused, delaying request");
        }
        this.f3210b.add(cVar);
    }

    public String toString() {
        return super.toString() + "{numRequests=" + this.f3209a.size() + ", isPaused=" + this.f3211c + "}";
    }
}
