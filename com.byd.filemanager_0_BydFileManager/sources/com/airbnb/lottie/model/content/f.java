package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import com.airbnb.lottie.model.i.m;
import com.airbnb.lottie.r.b.o;

/* JADX INFO: compiled from: RectangleShape.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2576a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final m<PointF, PointF> f2577b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.f f2578c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2579d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f2580e;

    public f(String str, m<PointF, PointF> mVar, com.airbnb.lottie.model.i.f fVar, com.airbnb.lottie.model.i.b bVar, boolean z) {
        this.f2576a = str;
        this.f2577b = mVar;
        this.f2578c = fVar;
        this.f2579d = bVar;
        this.f2580e = z;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new o(fVar, aVar, this);
    }

    public com.airbnb.lottie.model.i.b b() {
        return this.f2579d;
    }

    public String c() {
        return this.f2576a;
    }

    public m<PointF, PointF> d() {
        return this.f2577b;
    }

    public com.airbnb.lottie.model.i.f e() {
        return this.f2578c;
    }

    public boolean f() {
        return this.f2580e;
    }

    public String toString() {
        return "RectangleShape{position=" + this.f2577b + ", size=" + this.f2578c + '}';
    }
}
