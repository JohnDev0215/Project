package com.bumptech.glide.k;

import com.bumptech.glide.m.i;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ModelToResourceClassCache.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicReference<i> f2709a = new AtomicReference<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.d.a<i, List<Class<?>>> f2710b = new c.d.a<>();

    public List<Class<?>> a(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        List<Class<?>> list;
        i andSet = this.f2709a.getAndSet(null);
        if (andSet == null) {
            andSet = new i(cls, cls2, cls3);
        } else {
            andSet.a(cls, cls2, cls3);
        }
        synchronized (this.f2710b) {
            list = this.f2710b.get(andSet);
        }
        this.f2709a.set(andSet);
        return list;
    }

    public void b(Class<?> cls, Class<?> cls2, Class<?> cls3, List<Class<?>> list) {
        synchronized (this.f2710b) {
            this.f2710b.put(new i(cls, cls2, cls3), list);
        }
    }
}
