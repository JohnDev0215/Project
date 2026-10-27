package com.bumptech.glide.o;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class a implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<i> f2458a = Collections.newSetFromMap(new WeakHashMap());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f2459b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2460c;

    a() {
    }

    @Override // com.bumptech.glide.o.h
    public void a(i iVar) {
        this.f2458a.add(iVar);
        if (this.f2460c) {
            iVar.k();
        } else if (this.f2459b) {
            iVar.a();
        } else {
            iVar.f();
        }
    }

    @Override // com.bumptech.glide.o.h
    public void b(i iVar) {
        this.f2458a.remove(iVar);
    }

    void c() {
        this.f2460c = true;
        Iterator it = com.bumptech.glide.t.k.i(this.f2458a).iterator();
        while (it.hasNext()) {
            ((i) it.next()).k();
        }
    }

    void d() {
        this.f2459b = true;
        Iterator it = com.bumptech.glide.t.k.i(this.f2458a).iterator();
        while (it.hasNext()) {
            ((i) it.next()).a();
        }
    }

    void e() {
        this.f2459b = false;
        Iterator it = com.bumptech.glide.t.k.i(this.f2458a).iterator();
        while (it.hasNext()) {
            ((i) it.next()).f();
        }
    }
}
