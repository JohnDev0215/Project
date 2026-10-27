package com.bumptech.glide.m;

import com.bumptech.glide.o.i;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ModelToResourceClassCache.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicReference<i> f3411a = new AtomicReference<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.d.a<i, List<Class<?>>> f3412b = new b.d.a<>();

    public List<Class<?>> a(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        List<Class<?>> list;
        i andSet = this.f3411a.getAndSet(null);
        if (andSet == null) {
            andSet = new i(cls, cls2, cls3);
        } else {
            andSet.a(cls, cls2, cls3);
        }
        synchronized (this.f3412b) {
            list = this.f3412b.get(andSet);
        }
        this.f3411a.set(andSet);
        return list;
    }

    public void b(Class<?> cls, Class<?> cls2, Class<?> cls3, List<Class<?>> list) {
        synchronized (this.f3412b) {
            this.f3412b.put(new i(cls, cls2, cls3), list);
        }
    }
}
