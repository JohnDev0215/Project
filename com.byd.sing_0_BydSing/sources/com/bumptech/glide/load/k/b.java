package com.bumptech.glide.load.k;

import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.m.j;

/* JADX INFO: compiled from: SimpleResource.java */
/* JADX INFO: loaded from: classes.dex */
public class b<T> implements s<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final T f3058b;

    public b(T t) {
        this.f3058b = (T) j.d(t);
    }

    @Override // com.bumptech.glide.load.engine.s
    public void a() {
    }

    @Override // com.bumptech.glide.load.engine.s
    public final int b() {
        return 1;
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<T> c() {
        return (Class<T>) this.f3058b.getClass();
    }

    @Override // com.bumptech.glide.load.engine.s
    public final T get() {
        return this.f3058b;
    }
}
