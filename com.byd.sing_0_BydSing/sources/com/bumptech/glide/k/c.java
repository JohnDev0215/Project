package com.bumptech.glide.k;

import com.bumptech.glide.load.engine.g;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.m.i;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: LoadPathCache.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final q<?, ?, ?> f2706a = new q<>(Object.class, Object.class, Object.class, Collections.singletonList(new g(Object.class, Object.class, Object.class, Collections.emptyList(), new com.bumptech.glide.load.k.h.g(), null)), null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.d.a<i, q<?, ?, ?>> f2707b = new c.d.a<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final AtomicReference<i> f2708c = new AtomicReference<>();

    private i b(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        i andSet = this.f2708c.getAndSet(null);
        if (andSet == null) {
            andSet = new i();
        }
        andSet.a(cls, cls2, cls3);
        return andSet;
    }

    public <Data, TResource, Transcode> q<Data, TResource, Transcode> a(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        q<Data, TResource, Transcode> qVar;
        i iVarB = b(cls, cls2, cls3);
        synchronized (this.f2707b) {
            qVar = (q) this.f2707b.get(iVarB);
        }
        this.f2708c.set(iVarB);
        return qVar;
    }

    public boolean c(q<?, ?, ?> qVar) {
        return f2706a.equals(qVar);
    }

    public void d(Class<?> cls, Class<?> cls2, Class<?> cls3, q<?, ?, ?> qVar) {
        synchronized (this.f2707b) {
            c.d.a<i, q<?, ?, ?>> aVar = this.f2707b;
            i iVar = new i(cls, cls2, cls3);
            if (qVar == null) {
                qVar = f2706a;
            }
            aVar.put(iVar, qVar);
        }
    }
}
