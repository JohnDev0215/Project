package com.bumptech.glide.manager;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ActivityFragmentLifecycle.java */
/* JADX INFO: loaded from: classes.dex */
class a implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<i> f3199a = Collections.newSetFromMap(new WeakHashMap());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3200b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3201c;

    a() {
    }

    @Override // com.bumptech.glide.manager.h
    public void a(i iVar) {
        this.f3199a.add(iVar);
        if (this.f3201c) {
            iVar.k();
        } else if (this.f3200b) {
            iVar.a();
        } else {
            iVar.e();
        }
    }

    @Override // com.bumptech.glide.manager.h
    public void b(i iVar) {
        this.f3199a.remove(iVar);
    }

    void c() {
        this.f3201c = true;
        Iterator it = com.bumptech.glide.m.k.i(this.f3199a).iterator();
        while (it.hasNext()) {
            ((i) it.next()).k();
        }
    }

    void d() {
        this.f3200b = true;
        Iterator it = com.bumptech.glide.m.k.i(this.f3199a).iterator();
        while (it.hasNext()) {
            ((i) it.next()).a();
        }
    }

    void e() {
        this.f3200b = false;
        Iterator it = com.bumptech.glide.m.k.i(this.f3199a).iterator();
        while (it.hasNext()) {
            ((i) it.next()).e();
        }
    }
}
