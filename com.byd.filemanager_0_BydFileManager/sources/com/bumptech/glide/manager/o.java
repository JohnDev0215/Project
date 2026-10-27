package com.bumptech.glide.manager;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: TargetTracker.java */
/* JADX INFO: loaded from: classes.dex */
public final class o implements i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Set<com.bumptech.glide.request.h.e<?>> f3442b = Collections.newSetFromMap(new WeakHashMap());

    @Override // com.bumptech.glide.manager.i
    public void a() {
        Iterator it = com.bumptech.glide.o.k.i(this.f3442b).iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.request.h.e) it.next()).a();
        }
    }

    @Override // com.bumptech.glide.manager.i
    public void f() {
        Iterator it = com.bumptech.glide.o.k.i(this.f3442b).iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.request.h.e) it.next()).f();
        }
    }

    @Override // com.bumptech.glide.manager.i
    public void k() {
        Iterator it = com.bumptech.glide.o.k.i(this.f3442b).iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.request.h.e) it.next()).k();
        }
    }

    public void l() {
        this.f3442b.clear();
    }

    public List<com.bumptech.glide.request.h.e<?>> m() {
        return com.bumptech.glide.o.k.i(this.f3442b);
    }

    public void n(com.bumptech.glide.request.h.e<?> eVar) {
        this.f3442b.add(eVar);
    }

    public void o(com.bumptech.glide.request.h.e<?> eVar) {
        this.f3442b.remove(eVar);
    }
}
