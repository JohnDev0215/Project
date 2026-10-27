package com.airbnb.lottie.v;

/* JADX INFO: compiled from: LottieValueCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b<T> f3207a = new b<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.airbnb.lottie.r.c.a<?, ?> f3208b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected T f3209c;

    public c(T t) {
        this.f3209c = null;
        this.f3209c = t;
    }

    public T a(b<T> bVar) {
        return this.f3209c;
    }

    public final T b(float f, float f2, T t, T t2, float f3, float f4, float f5) {
        return a(this.f3207a.a(f, f2, t, t2, f3, f4, f5));
    }

    public final void c(com.airbnb.lottie.r.c.a<?, ?> aVar) {
        this.f3208b = aVar;
    }
}
