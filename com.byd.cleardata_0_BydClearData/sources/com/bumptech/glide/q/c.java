package com.bumptech.glide.q;

import com.bumptech.glide.load.n.i;
import com.bumptech.glide.load.n.t;
import com.bumptech.glide.load.p.i.g;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final t<?, ?, ?> f2482a = new t<>(Object.class, Object.class, Object.class, Collections.singletonList(new i(Object.class, Object.class, Object.class, Collections.emptyList(), new g(), null)), null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a.d.a<com.bumptech.glide.t.i, t<?, ?, ?>> f2483b = new a.d.a<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final AtomicReference<com.bumptech.glide.t.i> f2484c = new AtomicReference<>();

    private com.bumptech.glide.t.i b(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        com.bumptech.glide.t.i andSet = this.f2484c.getAndSet(null);
        if (andSet == null) {
            andSet = new com.bumptech.glide.t.i();
        }
        andSet.a(cls, cls2, cls3);
        return andSet;
    }

    public <Data, TResource, Transcode> t<Data, TResource, Transcode> a(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        t<Data, TResource, Transcode> tVar;
        com.bumptech.glide.t.i iVarB = b(cls, cls2, cls3);
        synchronized (this.f2483b) {
            tVar = (t) this.f2483b.get(iVarB);
        }
        this.f2484c.set(iVarB);
        return tVar;
    }

    public boolean c(t<?, ?, ?> tVar) {
        return f2482a.equals(tVar);
    }

    public void d(Class<?> cls, Class<?> cls2, Class<?> cls3, t<?, ?, ?> tVar) {
        synchronized (this.f2483b) {
            a.d.a<com.bumptech.glide.t.i, t<?, ?, ?>> aVar = this.f2483b;
            com.bumptech.glide.t.i iVar = new com.bumptech.glide.t.i(cls, cls2, cls3);
            if (tVar == null) {
                tVar = f2482a;
            }
            aVar.put(iVar, tVar);
        }
    }
}
