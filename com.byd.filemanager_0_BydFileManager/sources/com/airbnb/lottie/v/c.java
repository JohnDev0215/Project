package com.airbnb.lottie.v;

/* JADX INFO: compiled from: LottieValueCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b<T> f2827a = new b<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.airbnb.lottie.r.c.a<?, ?> f2828b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected T f2829c;

    public c(T t) {
        this.f2829c = null;
        this.f2829c = t;
    }

    public T a(b<T> bVar) {
        return this.f2829c;
    }

    public final T b(float f, float f2, T t, T t2, float f3, float f4, float f5) {
        return a(this.f2827a.a(f, f2, t, t2, f3, f4, f5));
    }

    public final void c(com.airbnb.lottie.r.c.a<?, ?> aVar) {
        this.f2828b = aVar;
    }
}
