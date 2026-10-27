package com.bumptech.glide.load.j;

import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.o.j;

/* JADX INFO: compiled from: SimpleResource.java */
/* JADX INFO: loaded from: classes.dex */
public class b<T> implements s<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final T f3287b;

    public b(T t) {
        this.f3287b = (T) j.d(t);
    }

    @Override // com.bumptech.glide.load.engine.s
    public void a() {
    }

    @Override // com.bumptech.glide.load.engine.s
    public final int c() {
        return 1;
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<T> d() {
        return (Class<T>) this.f3287b.getClass();
    }

    @Override // com.bumptech.glide.load.engine.s
    public final T get() {
        return this.f3287b;
    }
}
