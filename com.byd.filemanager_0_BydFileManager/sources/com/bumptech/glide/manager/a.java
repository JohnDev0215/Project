package com.bumptech.glide.manager;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ActivityFragmentLifecycle.java */
/* JADX INFO: loaded from: classes.dex */
class a implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<i> f3426a = Collections.newSetFromMap(new WeakHashMap());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3427b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3428c;

    a() {
    }

    @Override // com.bumptech.glide.manager.h
    public void a(i iVar) {
        this.f3426a.add(iVar);
        if (this.f3428c) {
            iVar.k();
        } else if (this.f3427b) {
            iVar.a();
        } else {
            iVar.f();
        }
    }

    @Override // com.bumptech.glide.manager.h
    public void b(i iVar) {
        this.f3426a.remove(iVar);
    }

    void c() {
        this.f3428c = true;
        Iterator it = com.bumptech.glide.o.k.i(this.f3426a).iterator();
        while (it.hasNext()) {
            ((i) it.next()).k();
        }
    }

    void d() {
        this.f3427b = true;
        Iterator it = com.bumptech.glide.o.k.i(this.f3426a).iterator();
        while (it.hasNext()) {
            ((i) it.next()).a();
        }
    }

    void e() {
        this.f3427b = false;
        Iterator it = com.bumptech.glide.o.k.i(this.f3426a).iterator();
        while (it.hasNext()) {
            ((i) it.next()).f();
        }
    }
}
