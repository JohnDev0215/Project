package com.airbnb.lottie.t.k;

import android.graphics.PointF;

/* JADX INFO: loaded from: classes.dex */
public class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f813a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.airbnb.lottie.t.j.m<PointF, PointF> f814b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.t.j.f f815c;
    private final boolean d;
    private final boolean e;

    public a(String str, com.airbnb.lottie.t.j.m<PointF, PointF> mVar, com.airbnb.lottie.t.j.f fVar, boolean z, boolean z2) {
        this.f813a = str;
        this.f814b = mVar;
        this.f815c = fVar;
        this.d = z;
        this.e = z2;
    }

    @Override // com.airbnb.lottie.t.k.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar) {
        return new com.airbnb.lottie.r.b.f(fVar, aVar, this);
    }

    public String b() {
        return this.f813a;
    }

    public com.airbnb.lottie.t.j.m<PointF, PointF> c() {
        return this.f814b;
    }

    public com.airbnb.lottie.t.j.f d() {
        return this.f815c;
    }

    public boolean e() {
        return this.e;
    }

    public boolean f() {
        return this.d;
    }
}
