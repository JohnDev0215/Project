package com.airbnb.lottie.t.k;

import android.graphics.PointF;

/* JADX INFO: loaded from: classes.dex */
public class j implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f845a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.airbnb.lottie.t.j.m<PointF, PointF> f846b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.t.j.f f847c;
    private final com.airbnb.lottie.t.j.b d;
    private final boolean e;

    public j(String str, com.airbnb.lottie.t.j.m<PointF, PointF> mVar, com.airbnb.lottie.t.j.f fVar, com.airbnb.lottie.t.j.b bVar, boolean z) {
        this.f845a = str;
        this.f846b = mVar;
        this.f847c = fVar;
        this.d = bVar;
        this.e = z;
    }

    @Override // com.airbnb.lottie.t.k.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar) {
        return new com.airbnb.lottie.r.b.o(fVar, aVar, this);
    }

    public com.airbnb.lottie.t.j.b b() {
        return this.d;
    }

    public String c() {
        return this.f845a;
    }

    public com.airbnb.lottie.t.j.m<PointF, PointF> d() {
        return this.f846b;
    }

    public com.airbnb.lottie.t.j.f e() {
        return this.f847c;
    }

    public boolean f() {
        return this.e;
    }

    public String toString() {
        return "RectangleShape{position=" + this.f846b + ", size=" + this.f847c + '}';
    }
}
