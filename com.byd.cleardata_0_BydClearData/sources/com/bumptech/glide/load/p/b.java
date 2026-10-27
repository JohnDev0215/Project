package com.bumptech.glide.load.p;

import com.bumptech.glide.load.n.v;
import com.bumptech.glide.t.j;

/* JADX INFO: loaded from: classes.dex */
public class b<T> implements v<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final T f2312b;

    public b(T t) {
        this.f2312b = (T) j.d(t);
    }

    @Override // com.bumptech.glide.load.n.v
    public final T b() {
        return this.f2312b;
    }

    @Override // com.bumptech.glide.load.n.v
    public final int c() {
        return 1;
    }

    @Override // com.bumptech.glide.load.n.v
    public Class<T> d() {
        return (Class<T>) this.f2312b.getClass();
    }

    @Override // com.bumptech.glide.load.n.v
    public void f() {
    }
}
