package com.bumptech.glide.o;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class p implements i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Set<com.bumptech.glide.r.j.e<?>> f2476b = Collections.newSetFromMap(new WeakHashMap());

    @Override // com.bumptech.glide.o.i
    public void a() {
        Iterator it = com.bumptech.glide.t.k.i(this.f2476b).iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.r.j.e) it.next()).a();
        }
    }

    @Override // com.bumptech.glide.o.i
    public void f() {
        Iterator it = com.bumptech.glide.t.k.i(this.f2476b).iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.r.j.e) it.next()).f();
        }
    }

    @Override // com.bumptech.glide.o.i
    public void k() {
        Iterator it = com.bumptech.glide.t.k.i(this.f2476b).iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.r.j.e) it.next()).k();
        }
    }

    public void l() {
        this.f2476b.clear();
    }

    public List<com.bumptech.glide.r.j.e<?>> m() {
        return com.bumptech.glide.t.k.i(this.f2476b);
    }

    public void n(com.bumptech.glide.r.j.e<?> eVar) {
        this.f2476b.add(eVar);
    }

    public void o(com.bumptech.glide.r.j.e<?> eVar) {
        this.f2476b.remove(eVar);
    }
}
