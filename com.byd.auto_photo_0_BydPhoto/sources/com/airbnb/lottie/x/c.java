package com.airbnb.lottie.x;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
public class c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b<T> f981a = new b<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private com.airbnb.lottie.r.c.a<?, ?> f982b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    protected T f983c;

    public c(@Nullable T t) {
        this.f983c = null;
        this.f983c = t;
    }

    @Nullable
    public T a(b<T> bVar) {
        return this.f983c;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final T b(float f, float f2, T t, T t2, float f3, float f4, float f5) {
        return a(this.f981a.a(f, f2, t, t2, f3, f4, f5));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final void c(@Nullable com.airbnb.lottie.r.c.a<?, ?> aVar) {
        this.f982b = aVar;
    }
}
