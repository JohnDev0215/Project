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
    private final Set<com.bumptech.glide.request.h.d<?>> f3213b = Collections.newSetFromMap(new WeakHashMap());

    @Override // com.bumptech.glide.manager.i
    public void a() {
        Iterator it = com.bumptech.glide.m.k.i(this.f3213b).iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.request.h.d) it.next()).a();
        }
    }

    @Override // com.bumptech.glide.manager.i
    public void e() {
        Iterator it = com.bumptech.glide.m.k.i(this.f3213b).iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.request.h.d) it.next()).e();
        }
    }

    @Override // com.bumptech.glide.manager.i
    public void k() {
        Iterator it = com.bumptech.glide.m.k.i(this.f3213b).iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.request.h.d) it.next()).k();
        }
    }

    public void l() {
        this.f3213b.clear();
    }

    public List<com.bumptech.glide.request.h.d<?>> m() {
        return com.bumptech.glide.m.k.i(this.f3213b);
    }

    public void n(com.bumptech.glide.request.h.d<?> dVar) {
        this.f3213b.add(dVar);
    }

    public void o(com.bumptech.glide.request.h.d<?> dVar) {
        this.f3213b.remove(dVar);
    }
}
