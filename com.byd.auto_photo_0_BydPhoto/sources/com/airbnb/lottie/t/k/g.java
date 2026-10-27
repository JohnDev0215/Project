package com.airbnb.lottie.t.k;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f827a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.airbnb.lottie.t.j.h f828b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.t.j.d f829c;
    private final boolean d;

    public enum a {
        MASK_MODE_ADD,
        MASK_MODE_SUBTRACT,
        MASK_MODE_INTERSECT
    }

    public g(a aVar, com.airbnb.lottie.t.j.h hVar, com.airbnb.lottie.t.j.d dVar, boolean z) {
        this.f827a = aVar;
        this.f828b = hVar;
        this.f829c = dVar;
        this.d = z;
    }

    public a a() {
        return this.f827a;
    }

    public com.airbnb.lottie.t.j.h b() {
        return this.f828b;
    }

    public com.airbnb.lottie.t.j.d c() {
        return this.f829c;
    }

    public boolean d() {
        return this.d;
    }
}
